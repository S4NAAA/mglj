package org.sana.mglj.core.Handle;

public abstract class HandleFactory<T> {
    public T arg;
    public HandleFactory(T arg) {
        this.arg = arg;
    }
    public HandleFactory() {}

    public abstract int genHandle();
}
