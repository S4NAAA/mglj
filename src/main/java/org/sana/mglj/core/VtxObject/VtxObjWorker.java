package org.sana.mglj.core.VtxObject;

import org.lwjgl.opengl.GL33;

import java.util.concurrent.Future;

public class VtxObjWorker extends VtxObject implements Runnable {

    private final int[] layout;
    private float[] vtxData;
    private int[] idxData;
    private int sliceSize;
    private VtxDataFactory vtxDataFactory;
    private IdxDataFactory idxDataFactory;
    private Future<?> future;

    public VtxObjWorker(VtxDataFactory vtxDataFactory, IdxDataFactory idxDataFactory, int[] layout, int mode) {
        super(mode);
        this.updateFactories(vtxDataFactory, idxDataFactory);
        this.layout = layout;

        this.sliceSize = 0;
        for (int num : this.layout) this.sliceSize += num;
    }

    public void updateFactories(VtxDataFactory vtxDataFactory, IdxDataFactory idxDataFactory) {
        this.vtxDataFactory = vtxDataFactory;
        this.idxDataFactory = idxDataFactory;
    }

    public void run() {
        this.vtxData = vtxDataFactory.genVtx();
        this.idxData = idxDataFactory.genIdx(this.vtxData.length / this.sliceSize);
    }

    public void bufferData() {
        this.bindVao()
                .bindVbo().bufferVtxData(this.vtxData, GL33.GL_DYNAMIC_DRAW)
                .setVtxAttribLayout(this.layout, this.sliceSize)
                .unbindVao()
                .unbindVbo()
                .bindEbo().bufferIdxData(this.idxData, GL33.GL_DYNAMIC_DRAW)
                .unbindEbo();
    }

    public void draw() {
        this.bindVao().bindEbo().drawElements(this.idxData.length).unbindEbo().bindVao();
    }

    public Future<?> getFuture() {
        return future;
    }

    public void setFuture(Future<?> future) {
        this.future = future;
    }
}
