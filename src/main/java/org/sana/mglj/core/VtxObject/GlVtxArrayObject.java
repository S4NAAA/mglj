package org.sana.mglj.core.VtxObject;

import org.lwjgl.opengl.GL33;
import org.sana.mglj.core.Handle.HandleContainer;

public class GlVtxArrayObject extends HandleContainer {
    public GlVtxArrayObject() { super(new GlVtxArrayHandleFactory()); }

    @Override
    public void deleteHandle() {
        GL33.glDeleteVertexArrays(this.handle);
    }

    public GlVtxArrayObject bind() {
        GL33.glBindVertexArray(this.handle);
        return this;
    }

    public void unbind() {
        GL33.glBindVertexArray(0);
    }
}
