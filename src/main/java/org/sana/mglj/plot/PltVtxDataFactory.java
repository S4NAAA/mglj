package org.sana.mglj.plot;

import org.sana.mglj.core.VtxObject.VtxDataFactory;

public class PltVtxDataFactory implements VtxDataFactory {

    float x0, x1, z0, z1;
    int count;
    ZFunctor func;

    public PltVtxDataFactory(float x0, float x1, float z0, float z1, int count,
                             ZFunctor func) {
        this.x0 = x0;
        this.x1 = x1;
        this.z0 = z0;
        this.z1 = z1;
        this.count = count;
        this.func = func;
    }

    public float[] genVtx() {
        // FIXME: 6 is hardcoded, change when changing slice size;
        final int sliceSize = 6;


        float[] vtxData = new float[this.count * this.count * sliceSize];


        final float difX = this.x1 - this.x0;
        final float difZ = this.z1 - this.z0;
        final float dx = difX / (float) (this.count - 1);
        final float dz = difZ / (float) (this.count - 1);


        float y0 = func.getVal(dx, dz);
        float y1 = y0;

        for (int i = 0; i < vtxData.length; i += sliceSize) {
            final int j = i / sliceSize;

            final int x = (j % count);
            final int z = (j / count);

            vtxData[i] = this.x0 + (x * dx);
            vtxData[i + 2] = this.z0 + (z * dz);
            vtxData[i + 1] = func.getVal(vtxData[i], vtxData[i + 2]);

            if (vtxData[i + 1] < y0)
                y0 = vtxData[i + 1];

            if (vtxData[i + 1] > y1)
                y1 = vtxData[i + 1];
        }

        final float difY = y1 - y0;

        final float r0 = 0.4f;
        final float dr = 0.6f;
        final float g0 = 0.5f;
        final float dg = -0.4f;
        final float b0 = 1.9f;
        final float db = -1.6f;

        //TODO: Modify so triangles that conform a square have the same color
        for (int i = 0; i < vtxData.length; i += sliceSize) {
            vtxData[i] = (vtxData[i] - this.x0) / (difX);
            vtxData[i + 1] = (vtxData[i + 1] - y0) / (difY);
            vtxData[i + 2] = (vtxData[i + 2] - this.z0) / (difZ);

            final float y = vtxData[i + 1];
            vtxData[i + 3] = Math.min(1.0f, Math.max(r0 + y * dr, 0.0f));
            vtxData[i + 4] = Math.min(1.0f, Math.max(g0 + y * dg, 0.0f));
            vtxData[i + 5] = Math.min(1.0f, Math.max(b0 + y * db, 0.0f));
        }

        return vtxData;
    }
}
