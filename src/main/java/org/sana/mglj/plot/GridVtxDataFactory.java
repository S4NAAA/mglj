package org.sana.mglj.plot;

import org.sana.mglj.core.VtxObject.VtxDataFactory;

public class GridVtxDataFactory implements VtxDataFactory {

    int count;

    public GridVtxDataFactory(int count) {
        this.count = count;
    }

    public float[] genVtx() {
        //FIXME: hardcoded

        final int sliceSize = 6;

        final float r = 1.0f;
        final float g = 1.0f;
        final float b = 1.0f;

        // FIXME: -a lot of wasted space with rgb being the same for all the grid
        //        -I don't know the overhead of switching shaders so I'll leave it
        //          like that for the foreseeable future

        float[] tmp = new float[count * sliceSize * 3 * 3];

        final float dl = 1.0f / (float) (count - 1);

        for (int i = 0; i < tmp.length / 3; i += (sliceSize * 3)) {

            final int j = i / (sliceSize * 3);
            // pos
            tmp[i] = j * dl;             // x
            tmp[i + 1] = 0;              // y
            tmp[i + 2] = 0;              // z
            // tex
            tmp[i + 3] = r;
            tmp[i + 4] = g;
            tmp[i + 5] = b;

            // pos
            tmp[i + 6] = j * dl;
            tmp[i + 7] = 0;
            tmp[i + 8] = 1;
            // tex
            tmp[i + 9] = r;
            tmp[i + 10] = g;
            tmp[i + 11] = b;

            // pos
            tmp[i + 12] = j * dl;
            tmp[i + 13] = 1;
            tmp[i + 14] = 1;
            // tex
            tmp[i + 15] = r;
            tmp[i + 16] = g;
            tmp[i + 17] = b;
        }

        for (int i = tmp.length / 3; i < 2 * tmp.length / 3; i += (sliceSize * 3)) {

            final int j = (i - tmp.length / 3) / (sliceSize * 3);

            // pos
            tmp[i] = 0;             // x
            tmp[i + 1] = 0;         // y
            tmp[i + 2] = j * dl;    // z
            // te
            tmp[i + 3] = r;
            tmp[i + 4] = g;
            tmp[i + 5] = b;

            // pos
            tmp[i + 6] = 1;
            tmp[i + 7] = 0;
            tmp[i + 8] = j * dl;
            // tex
            tmp[i + 9] = r;
            tmp[i + 10] = g;
            tmp[i + 11] = b;

            // pos
            tmp[i + 12] = 1;
            tmp[i + 13] = 1;
            tmp[i + 14] = j * dl;
            // tex
            tmp[i + 15] = r;
            tmp[i + 16] = g;
            tmp[i + 17] = b;
        }

        for (int i = 2 * tmp.length / 3; i < tmp.length; i += (sliceSize * 3)) {

            final int j = (i - 2 * tmp.length / 3) / (sliceSize * 3);

            // pos
            tmp[i] = 0;             // x
            tmp[i + 1] = j * dl;    // y
            tmp[i + 2] = 1;         // z
            // tex
            tmp[i + 3] = r;
            tmp[i + 4] = g;
            tmp[i + 5] = b;

            // pos
            tmp[i + 6] = 1;
            tmp[i + 7] = j * dl;
            tmp[i + 8] = 1;
            // tex
            tmp[i + 9] = r;
            tmp[i + 10] = g;
            tmp[i + 11] = b;

            // pos
            tmp[i + 12] = 1;
            tmp[i + 13] = j * dl;
            tmp[i + 14] = 0;
            // tex
            tmp[i + 15] = r;
            tmp[i + 16] = g;
            tmp[i + 17] = b;
        }

        return tmp;
    }
}
