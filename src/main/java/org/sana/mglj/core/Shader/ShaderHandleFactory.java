package org.sana.mglj.core.Shader;

import org.lwjgl.opengl.GL33;
import org.sana.mglj.core.Handle.HandleFactory;

public class ShaderHandleFactory implements HandleFactory{
    public final int shaderType;
    public ShaderHandleFactory(int shaderType) {
        this.shaderType = shaderType;
    }

    @Override
    public int genHandle() {
        return GL33.glCreateShader(this.shaderType);
    }
}
