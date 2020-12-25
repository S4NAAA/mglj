package org.sana.mglj.core.Shader;

import org.lwjgl.opengl.GL33;

public class VtxShader extends Shader {
    public VtxShader() {
        super(new ShaderHandleFactory(GL33.GL_VERTEX_SHADER));
    }
}
