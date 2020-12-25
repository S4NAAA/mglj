package org.sana.mglj.plot;

import org.lwjgl.opengl.GL33;
import org.sana.mglj.core.VtxObject.VtxObjWorker;

public class PltVtxObjWorker extends VtxObjWorker {

    public PltVtxObjWorker(float x0, float x1, float z0, float z1, int count,
                           ZFunctor func) {
        super(new PltVtxDataFactory(x0, x1, z0, z1, count, func),
                new PltIdxDataFactory(count), new int[]{3, 3}, GL33.GL_TRIANGLES);
    }
}
