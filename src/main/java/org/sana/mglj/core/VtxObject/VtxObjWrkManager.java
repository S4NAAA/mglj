package org.sana.mglj.core.VtxObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//TODO: -implement framebuffers

//NOTE: -I'm pretty sure the overhead of multithreading is not worth it in this case
//      -Just did it as a proof of concept

public class VtxObjWrkManager {
    private final ExecutorService executorService = Executors.newFixedThreadPool(4);
    private final List<VtxObjWorker> vtxObjWorkers = new ArrayList<>();

    public void submit() {
        for (var vtxObjWorker : vtxObjWorkers)
            vtxObjWorker.setFuture(executorService.submit(vtxObjWorker));
    }

    public void await() throws ExecutionException, InterruptedException {
        for (var vtxObjWorker : vtxObjWorkers) {
            vtxObjWorker.getFuture().get();
            vtxObjWorker.bufferData();
        }
    }

    // TODO: -implement an id system for the vtxObjWorkers

    public void addWorker(VtxObjWorker vtxObjWorker) {
        this.vtxObjWorkers.add(vtxObjWorker);
    }

    public VtxObjWorker getWorker(int i) {
        return this.vtxObjWorkers.get(i);
    }

    public void drawAll() {
        for (var vtxObjWorker : vtxObjWorkers)
            vtxObjWorker.draw();
    }

    public void drawRange(int start, int end) {
        for (int i = start; i < end; i++)
            vtxObjWorkers.get(i).draw();
    }

    public void drawIndex(int i) {
        vtxObjWorkers.get(i).draw();
    }

    public int workersSize() {
        return this.vtxObjWorkers.size();
    }

    public void delete() {
        for (var vtxObjWorker : vtxObjWorkers)
            vtxObjWorker.delete();

        executorService.shutdown();
    }
}
