package org.sana.mglj.core.Shader;

import org.lwjgl.opengl.GL33;

public class GeomShader extends Shader {
    public GeomShader() {
        super(new ShaderHandleFactory(GL33.GL_GEOMETRY_SHADER));
    }
}
