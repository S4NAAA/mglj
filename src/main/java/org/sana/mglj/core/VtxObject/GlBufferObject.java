package org.sana.mglj.core.VtxObject;

import org.lwjgl.opengl.GL33;
import org.sana.mglj.core.Handle.HandleContainer;

public class GlBufferObject extends HandleContainer {
    public final int bufferType;
    public GlBufferObject(int bufferType) {
        super(new GlBufferHandleFactory());
        this.bufferType = bufferType;
    }

    @Override
    public void deleteHandle() {
        GL33.glDeleteBuffers(this.handle);
    }

    public GlBufferObject bind() {
        GL33.glBindBuffer(this.bufferType, handle);
        return this;
    }

    public void unbind() {
        GL33.glBindBuffer(this.bufferType, 0);
    }

    public GlBufferObject bufferData(float[] data, int usage) {
        GL33.glBufferData(this.bufferType, data, usage);
        return this;
    }

    public GlBufferObject bufferData(int[] data, int usage) {
        GL33.glBufferData(this.bufferType, data, usage);
        return this;
    }
}
