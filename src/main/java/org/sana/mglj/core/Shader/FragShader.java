package org.sana.mglj.core.Shader;

import org.lwjgl.opengl.GL33;

public class FragShader extends Shader {
    public FragShader() {
        super(new ShaderHandleFactory(GL33.GL_FRAGMENT_SHADER));
    }
}
