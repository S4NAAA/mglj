package org.sana.mglj.core.VtxObject;

import org.lwjgl.opengl.GL33;
import org.sana.mglj.core.Handle.HandleFactory;

public class GlVtxArrayHandleFactory implements HandleFactory {
    @Override
    public int genHandle() {
        return GL33.glGenVertexArrays();
    }
}
