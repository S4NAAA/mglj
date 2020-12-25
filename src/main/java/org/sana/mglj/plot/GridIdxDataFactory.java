package org.sana.mglj.plot;

import org.sana.mglj.core.VtxObject.IdxDataFactory;

public class GridIdxDataFactory implements IdxDataFactory {
    @Override
    public int[] genIdx(int vtxCount) {
        int[] tmp = new int[4 * vtxCount / 3];

        int i = 0;
        int j = 0;

        for (; i < tmp.length; i += 4, j += 3) {
            tmp[i] = j;
            tmp[i + 1] = j + 1;
            tmp[i + 2] = j + 1;
            tmp[i + 3] = j + 2;
        }

        return tmp;
    }
}
