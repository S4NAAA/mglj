package org.sana.mglj.core.VtxObject;

import java.util.concurrent.Future;

public class VtxObjWorker extends VtxObject implements Runnable {

    private VtxDataFactory vtxDataFactory;
    private IdxDataFactory idxDataFactory;
    private Future<?> future;

    public VtxObjWorker(VtxDataFactory vtxDataFactory, IdxDataFactory idxDataFactory, int[] sliceData, int mode) {
        super(sliceData, mode);
        this.updateFactories(vtxDataFactory, idxDataFactory);
    }

    public void updateFactories(VtxDataFactory vtxDataFactory, IdxDataFactory idxDataFactory) {
        this.vtxDataFactory = vtxDataFactory;
        this.idxDataFactory = idxDataFactory;
    }

    public void run() {
        this.updateData(vtxDataFactory, idxDataFactory);
    }

    public Future<?> getFuture() {
        return future;
    }

    public void setFuture(Future<?> future) {
        this.future = future;
    }
}
