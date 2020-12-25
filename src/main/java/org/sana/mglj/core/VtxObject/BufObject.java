package org.sana.mglj.core.VtxObject;

import org.lwjgl.opengl.GL33;
import org.sana.mglj.core.Handle.HandleContainer;

public class BufObject extends HandleContainer {
    public BufObject() {
        super(new BufHandleFactory());
    }

    @Override
    public void deleteHandle() {
        GL33.glDeleteBuffers(this.handle);
    }
}
