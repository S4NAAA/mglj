package org.sana.mglj.core.VtxObject;

import org.lwjgl.opengl.GL33;
import org.sana.mglj.core.Handle.HandleFactory;

public class ArrHandleFactory extends HandleFactory<Void> {
    ArrHandleFactory() { super(); }

    @Override
    public int genHandle() {
        return GL33.glGenVertexArrays();
    }
}
