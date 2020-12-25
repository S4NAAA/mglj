package org.sana.mglj.core.Program;

import org.lwjgl.opengl.GL33;
import org.sana.mglj.core.Handle.HandleContainer;
import org.sana.mglj.core.Shader.Shader;

import java.nio.FloatBuffer;

public class Program extends HandleContainer {

    public Program() {
        super(new ProgramHandleFactory());
    }

    public Program attach(Shader shader) {
        GL33.glAttachShader(this.handle, shader.getHandle());
        shader.deleteHandle();
        return this;
    }

    public void link() {
        GL33.glLinkProgram(this.handle);
    }

    public void use() {
        GL33.glUseProgram(this.handle);
    }

    public static void resetUse() { GL33.glUseProgram(0);}

    public void setUniform2f(String name, float v1, float v2) {
        this.use();
        GL33.glUniform2f(GL33.glGetUniformLocation(this.handle, name), v1, v2);
        Program.resetUse();
    }

    public void setUniform4fv(String name, FloatBuffer fb) {
        this.use();
        GL33.glUniformMatrix4fv(GL33.glGetUniformLocation(this.handle, name), false, fb);
        Program.resetUse();
    }

    public int getStatus() {
        return GL33.glGetProgrami(this.handle, GL33.GL_LINK_STATUS);
    }

    public String getLog() {
        int lenStatus = GL33.glGetProgrami(this.handle, GL33.GL_INFO_LOG_LENGTH);

        return GL33.glGetShaderInfoLog(this.handle, lenStatus);

    }

    @Override
    public void deleteHandle() {
        GL33.glDeleteProgram(this.handle);
    }
}
