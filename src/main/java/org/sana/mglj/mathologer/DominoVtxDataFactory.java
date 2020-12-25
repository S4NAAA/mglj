package org.sana.mglj.mathologer;

import org.sana.mglj.core.VtxObject.VtxDataFactory;

public class DominoVtxDataFactory implements VtxDataFactory {
    @Override
    public float[] genVtx() {
        return new float[] {-1, -1, 0, 0, 0, 0,
                             0, -1, 0, 0, 0, 0,
                             1, -1, 0, 0, 0, 0,
                            -1,  0, 0, 0, 0, 0,
                             0,  0, 0, 0, 0, 0,
                             1,  0, 0, 0,0 , 0};
    }
}
