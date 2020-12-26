package org.sana.mglj.core;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL33;

import java.util.Objects;

//TODO: -implement VtxObject control system
//      -implement shader controls system

public class Context {
    public final Window window;

    public Context() {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!GLFW.glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");

        GLFW.glfwDefaultWindowHints();
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE);
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3);
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 3);
        GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_FORWARD_COMPAT, GL33.GL_TRUE);
        GLFW.glfwWindowHint(GLFW.GLFW_SAMPLES, 4);

        window = new Window(600, 600, "STAN LOONA");
        GL.createCapabilities();
        GL33.glEnable(GL33.GL_DEPTH_TEST);
        GL33.glClearColor(0.0f, 43.0f / 256.0f, 54.0f / 256.0f, 1.0f);
        //TODO: Fix colormap system so I don't need wireframe to visualize functions
        //GL33.glPolygonMode(GL33.GL_FRONT_AND_BACK, GL33.GL_LINE);
    }

    public Window getWindow() {
        return this.window;
    }

    public void delete() {
        this.window.delete();
        GLFW.glfwTerminate();
        Objects.requireNonNull(GLFW.glfwSetErrorCallback(null)).free();
    }
}
