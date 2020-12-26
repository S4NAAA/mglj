package org.sana.mglj.plot;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL33;
import org.lwjgl.system.MemoryStack;
import org.sana.mglj.core.Context;
import org.sana.mglj.core.Program.Program;
import org.sana.mglj.core.VtxObject.VtxObjWorker;
import org.sana.mglj.core.VtxObject.VtxObjWrkManager;

import java.nio.FloatBuffer;
import java.util.concurrent.ExecutionException;

public class PlotDemo {
    private final Context ctx;
    private final VtxObjWrkManager vtxObjWrkManager;
    private final Program pltProgram;
    private final Program txtProgram;

    public PlotDemo() {
        ctx = new Context();

        vtxObjWrkManager = new VtxObjWrkManager();

        vtxObjWrkManager.addWorker(new PltVtxObjWorker(-1.0f, 1.0f, -1.0f, 1.0f, 64,
                (x, z) -> (float) (Math.sin(3.1 * (x * x + z * z)))));

        vtxObjWrkManager.addWorker(new GridVtxObjWorker(16));

        vtxObjWrkManager.addWorker(new VtxObjWorker(() -> new float[]{-0.1f, 0.0f, 0.0f, -0.1f, 0.0f, 0.1f},
                (c) -> new int[]{0, 1}, new int[]{3}, GL33.GL_POINTS));

        vtxObjWrkManager.submit();

        pltProgram = new PltProgram();
        txtProgram = new TxtProgram();

        txtProgram.use()
                .setUniform2f("size", 0.2f, 0.05f)
                .setUniform2f("topLeft", 0.0f, 0.0f)
                .setUniform2f("botRight", 1.0f, 1.0f)
                .resetUse();

        try {
            vtxObjWrkManager.await();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        int i = 0;

        while (!ctx.getWindow().shouldClose()) {

            ctx.getWindow().clear();

            //TODO: make pvm matrix creation a class
            try (MemoryStack stack = MemoryStack.stackPush()) {
                FloatBuffer fb = new Matrix4f()
                        //.perspective((float) Math.toRadians(70.0f), rdr.getWindow().getAspect(), 0.01f, 50.0f)
                        .ortho(-ctx.getWindow().getAspect(), ctx.getWindow().getAspect(), -1, 1, 0.1f, 5.0f)
                        .lookAt(0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f)
                        .translate(0.0f, -0.2f, -1.0f)
                        .rotate((float) Math.PI / 6.3f, 1.0f, 0.0f, 0.0f)
                        .rotate(i / 60.0f, 0.0f, 1.0f, 0.0f)
                        .scale(0.7f)
                        .translate(-0.5f, -0.5f, -0.5f) //NOTE: vertices are normalized from 0 to 1
                        .get(stack.mallocFloat(16));
                pltProgram.use().setUniform4fv("pvm", fb).resetUse();
                txtProgram.use().setUniform4fv("pvm", fb).resetUse();
            }

            pltProgram.use();
            vtxObjWrkManager.drawRange(0, 2);
            pltProgram.resetUse();

            txtProgram.use();
            vtxObjWrkManager.drawIndex(2);
            pltProgram.resetUse();


            ctx.getWindow().swapBuffers();
            ctx.getWindow().pollEvents();
            i++;
        }
    }

    public void delete() {
        vtxObjWrkManager.delete();
        pltProgram.deleteHandle();
        txtProgram.deleteHandle();
        ctx.delete();
    }
}
