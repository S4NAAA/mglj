package org.sana.mglj.core.VtxObject;

import org.lwjgl.opengl.GL33;
import org.sana.mglj.core.Handle.HandleFactory;

public class BufHandleFactory extends HandleFactory<Void> {
    public BufHandleFactory() {
        super();
    }

    @Override
    public int genHandle() {
        return GL33.glGenBuffers();
    }
}
