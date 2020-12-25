package org.sana.mglj.core.Shader;

import org.lwjgl.opengl.GL33;
import org.sana.mglj.core.Handle.HandleContainer;
import org.sana.mglj.core.Handle.HandleFactory;

import java.io.*;

public class Shader extends HandleContainer {

    public Shader(HandleFactory generator) {
        super(generator);
    }

    @Override
    public void deleteHandle() {
        GL33.glDeleteShader(handle);
    }

    public Shader source(String path) {
        GL33.glShaderSource(this.handle, this.readFile(path));
        return this;
    }

    public void compile() {
        GL33.glCompileShader(this.handle);
    }

    public int getStatus() {
        return GL33.glGetShaderi(this.handle, GL33.GL_COMPILE_STATUS);
    }

    public String getLog() {
        int lenStatus = GL33.glGetShaderi(this.handle, GL33.GL_INFO_LOG_LENGTH);
         return GL33.glGetShaderInfoLog(this.handle, lenStatus);
    }

    protected String readFile(String path) {
        StringBuilder builder = new StringBuilder();

        try (InputStream in = new FileInputStream(path);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }

            return builder.toString();
        } catch (IOException ex) {
            throw new RuntimeException("Couldn't load file"
                    + System.lineSeparator() + ex.getMessage());
        }
    }
}
