package org.sana.mglj.core;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL33;
import org.lwjgl.system.MemoryStack;
import org.sana.mglj.core.VtxObject.VtxObjWorker;
import org.sana.mglj.core.VtxObject.VtxObjWrkManager;
import org.sana.mglj.plot.GridVtxObjWorker;
import org.sana.mglj.plot.PltProgram;
import org.sana.mglj.plot.PltVtxObjWorker;
import org.sana.mglj.plot.TxtProgram;

import java.nio.FloatBuffer;
import java.util.concurrent.ExecutionException;


public class App {

    public static void main(String[] args) {
        Initializer rdr = new Initializer();
        VtxObjWrkManager vtxObjWrkManager = new VtxObjWrkManager();

        vtxObjWrkManager.addWorker(new GridVtxObjWorker(16));


        vtxObjWrkManager.addWorker(new PltVtxObjWorker(-1.0f, 1.0f, -1.0f, 1.0f, 64,
                (x, z) -> (float) (Math.sin(3.1 * (x * x + z * z)))));

        vtxObjWrkManager.addWorker(new VtxObjWorker(() -> new float[]{-0.1f, 0.0f, 0.0f, -0.1f, 0.0f, 0.1f},
               (c) -> new int[]{0, 1}, new int[]{3}, GL33.GL_POINTS));

        vtxObjWrkManager.submit();
        PltProgram shader1 = new PltProgram();
        TxtProgram shader2 = new TxtProgram();

        shader2.setUniform2f("size", 0.2f, 0.05f);
        shader2.setUniform2f("topLeft", 0.0f, 0.0f);
        shader2.setUniform2f("botRight", 1.0f, 1.0f);

        int i = 0;

        try {
            vtxObjWrkManager.await();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        while (!rdr.getWindow().shouldClose()) {

            rdr.getWindow().clear();

            //TODO: make pvm matrix creation a class
            try (MemoryStack stack = MemoryStack.stackPush()) {
                FloatBuffer fb = new Matrix4f()
                        //.perspective((float) Math.toRadians(70.0f), rdr.getWindow().getAspect(), 0.01f, 50.0f)
                        .ortho(-rdr.getWindow().getAspect(), rdr.getWindow().getAspect(), -1, 1, 0.1f, 5.0f)
                        .lookAt(0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f)
                        .translate(0.0f, -0.2f, -1.0f)
                        .rotate((float) Math.PI / 6.3f, 1.0f, 0.0f, 0.0f)
                        .rotate(i / 60.0f, 0.0f, 1.0f, 0.0f)
                        .scale(0.7f)
                        .translate(-0.5f, -0.5f, -0.5f) //NOTE: vertices are normalized from 0 to 1
                        .get(stack.mallocFloat(16));
                shader1.setUniform4fv("pvm", fb);
                shader2.setUniform4fv("pvm", fb);
            }

            shader1.use();
            vtxObjWrkManager.drawRange(0, 2);

            shader2.use();
            vtxObjWrkManager.drawIndex(vtxObjWrkManager.workersSize() - 1);

            rdr.getWindow().swapBuffers();
            rdr.getWindow().pollEvents();
            i++;
        }

        vtxObjWrkManager.delete();
        shader1.deleteHandle();
        shader2.deleteHandle();
        rdr.delete();
    }
}
