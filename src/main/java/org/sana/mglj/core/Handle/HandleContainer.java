package org.sana.mglj.core.Handle;

public abstract class HandleContainer {
    protected final int handle;
    public HandleContainer(HandleFactory generator) {
        this.handle = generator.genHandle();
    }

    public abstract void deleteHandle();

    public int getHandle() { return handle; }
}
