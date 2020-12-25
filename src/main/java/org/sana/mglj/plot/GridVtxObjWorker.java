package org.sana.mglj.plot;

import org.lwjgl.opengl.GL33;
import org.sana.mglj.core.VtxObject.VtxObjWorker;

// TODO: -add number labels

public class GridVtxObjWorker extends VtxObjWorker {
    public GridVtxObjWorker(int count) {
        super(new GridVtxDataFactory(count), new GridIdxDataFactory(),
                new int[]{3, 3}, GL33.GL_LINES);
    }
}
