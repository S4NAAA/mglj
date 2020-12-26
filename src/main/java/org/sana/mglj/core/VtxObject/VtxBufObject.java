package org.sana.mglj.core.VtxObject;

import org.lwjgl.opengl.GL33;

public class VtxBufObject extends GlBufferObject {
   public VtxBufObject() {
       super(GL33.GL_ARRAY_BUFFER);
   }
}
