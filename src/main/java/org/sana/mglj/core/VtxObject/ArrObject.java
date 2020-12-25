package org.sana.mglj.core.VtxObject;

import org.lwjgl.opengl.GL33;
import org.sana.mglj.core.Handle.HandleContainer;

public class ArrObject extends HandleContainer {
    public ArrObject() { super(new ArrHandleFactory()); }

    @Override
    public void deleteHandle() {
        GL33.glDeleteVertexArrays(this.handle);
    }
}
