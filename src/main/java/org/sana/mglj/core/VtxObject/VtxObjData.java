package org.sana.mglj.core.VtxObject;


public class VtxObjData {
    protected float[] vtxData;
    protected int[] idxData;
    protected int[] layoutData;
    protected int sliceSize;

    public VtxObjData(VtxDataFactory vtxDataFactory, IdxDataFactory idxDataFactory, int[] layoutData) {
        this(layoutData);
        this.updateData(vtxDataFactory, idxDataFactory);
    }

    public VtxObjData(int[] layoutData) {
        this.layoutData = layoutData;

        sliceSize = 0;
        for (int num : layoutData) sliceSize += num;
    }

    public void updateData(VtxDataFactory vtxDataFactory, IdxDataFactory idxDataFactory) {
        this.vtxData = vtxDataFactory.genVtx();
        this.idxData = idxDataFactory.genIdx(this.vtxData.length / this.sliceSize);
    }

    public void updateData(float[] vtxData, int[] idxData) {
        this.vtxData = vtxData;
        this.idxData = idxData;
    }
}
