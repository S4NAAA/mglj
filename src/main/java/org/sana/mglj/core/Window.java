package org.sana.mglj.core;

import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL33;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.system.MemoryUtil.NULL;

// TODO: implement ui functionality
public class Window {
    private final long window;

    public Window(int width, int height, String title) throws RuntimeException {
        this.window = GLFW.glfwCreateWindow(width, height, title, NULL, NULL);

        if (this.window == NULL)
            throw new RuntimeException("Failed to create the GLFW window");

        GLFW.glfwSetKeyCallback(this.window, (window, key, scancode, action, mods) -> {
            if (key == GLFW.GLFW_KEY_ESCAPE && action == GLFW.GLFW_RELEASE)
                GLFW.glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
        });

        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);

            GLFW.glfwGetWindowSize(this.window, pWidth, pHeight);

            GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());

            assert videoMode != null;

            GLFW.glfwSetWindowPos(
                    this.window,
                    (videoMode.width() - pWidth.get(0)) / 2,
                    (videoMode.height() - pHeight.get(0)) / 2
            );

            GLFW.glfwMakeContextCurrent(window);
            GLFW.glfwSwapInterval(1);
            GLFW.glfwShowWindow(window);
        }
    }

    public boolean shouldClose() {
        return GLFW.glfwWindowShouldClose(this.window);
    }

    public void swapBuffers() {
        GLFW.glfwSwapBuffers(this.window);
    }

    public void pollEvents() {
        GLFW.glfwPollEvents();
    }

    public void clear() {
        GL33.glClear(GL33.GL_COLOR_BUFFER_BIT | GL33.GL_DEPTH_BUFFER_BIT);
    }

    public void delete() {
        Callbacks.glfwFreeCallbacks(this.window);
        GLFW.glfwDestroyWindow(this.window);
    }

    public float getAspect() {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer hb = stack.mallocInt(1);
            IntBuffer wb = stack.mallocInt(1);

            GLFW.glfwGetFramebufferSize(this.window, hb, wb);
            return (float) hb.get() / (float) wb.get();
        }
    }
}
