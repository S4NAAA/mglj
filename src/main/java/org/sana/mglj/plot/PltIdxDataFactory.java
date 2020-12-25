package org.sana.mglj.plot;

import org.sana.mglj.core.VtxObject.IdxDataFactory;

public class PltIdxDataFactory implements IdxDataFactory {
    private final int count;

    public PltIdxDataFactory(int count) {
        this.count = count;
    }

    @Override
    public int[] genIdx(int vtxCount) {
        assert (vtxCount % 6 == 0);

        int[] tmp = new int[(count - 1) * (count - 1) * 6];

        for (int z = 0; z < (count - 1); z++) {
            for (int x = 0; x < (count - 1); x++) {
                final int i = (z * (count - 1) + x) * 6;
                final int j = z * count + x;

                tmp[i] = j;
                tmp[i + 1] = j + 1;
                tmp[i + 2] = j + count + 1;
                tmp[i + 3] = j + count + 1;
                tmp[i + 4] = j + count;
                tmp[i + 5] = j;
            }
        }

        return tmp;
    }
}
