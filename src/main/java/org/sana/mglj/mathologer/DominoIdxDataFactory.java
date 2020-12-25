package org.sana.mglj.mathologer;

import org.sana.mglj.core.VtxObject.IdxDataFactory;

public class DominoIdxDataFactory implements IdxDataFactory {

    @Override
    public int[] genIdx(int vtxCount) {
        return new int[] {0, 1, 3, 3, 4, 1, 1, 2, 4, 4, 5, 2};
    }
}
