package org.sana.mglj.core.VtxObject;

import org.lwjgl.opengl.GL33;

public class ElemBufObject extends GlBufferObject {
    public ElemBufObject() {
        super(GL33.GL_ELEMENT_ARRAY_BUFFER);
    }
}
