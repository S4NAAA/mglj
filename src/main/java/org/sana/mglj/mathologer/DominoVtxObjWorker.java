package org.sana.mglj.mathologer;

import org.lwjgl.opengl.GL33;
import org.sana.mglj.core.VtxObject.IdxDataFactory;
import org.sana.mglj.core.VtxObject.VtxDataFactory;
import org.sana.mglj.core.VtxObject.VtxObjWorker;

public class DominoVtxObjWorker extends VtxObjWorker {
    public DominoVtxObjWorker() {
        super(new DominoVtxDataFactory(), new DominoIdxDataFactory(),
                new int[] {3, 3}, GL33.GL_TRIANGLES);
    }
}
