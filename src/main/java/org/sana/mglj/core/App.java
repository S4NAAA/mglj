package org.sana.mglj.core;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL33;
import org.lwjgl.system.MemoryStack;
import org.sana.mglj.core.VtxObject.VtxObjWorker;
import org.sana.mglj.core.VtxObject.VtxObjWrkManager;
import org.sana.mglj.mathologer.DominoVtxObjWorker;
import org.sana.mglj.plot.*;

import java.nio.FloatBuffer;
import java.util.concurrent.ExecutionException;


public class App {

    public static void main(String[] args) {
        PlotDemo demo = new PlotDemo();
        demo.run();
        demo.delete();
    }
}
