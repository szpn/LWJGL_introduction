package org.render;

import java.nio.IntBuffer;

import org.boot.Boot;
import org.joml.Matrix4f;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.MemoryStack;


import static org.lwjgl.opengl.GL11C.GL_DEPTH_TEST;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Window {
    public long window;

    public int width;
    public int height;
    public float aspectRatio;

    private GLFWWindowSizeCallback windowSize;

    public Window(int width, int height) {
        init(width, height);
    }

    private void init(int width, int height){
        GLFWErrorCallback.createPrint(System.err).set();
        this.width = width;
        this.height = height;
        this.aspectRatio = (float) width /height;

        if(!GLFW.glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        GLFW.glfwDefaultWindowHints();
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE);

        window = GLFW.glfwCreateWindow(width, height, "LWJGL", NULL, NULL);
        if(window == NULL) {
            throw new IllegalStateException("Unable to create GLFW Window");
        }

        GLFW.glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {});

        try(MemoryStack stack = stackPush()){
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);

            GLFW.glfwGetWindowSize(window, pWidth, pHeight);

            GLFWVidMode vidmode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());

            GLFW.glfwSetWindowPos(
                    window,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );

            GLFW.glfwMakeContextCurrent(window);
            GLFW.glfwSwapInterval(1);
            GLFW.glfwShowWindow(window);
        }

        GLFW.glfwSetWindowSizeCallback(window, windowSize = new GLFWWindowSizeCallback(){
                    @Override
                    public void invoke(long window, int width, int height){
                        Boot.window.height = height;
                        Boot.window.width = width;
                        Boot.window.aspectRatio = (float) width /height;
                        GL11.glViewport(0,0,Boot.window.width, Boot.window.height);
                    }
                });


        GL.createCapabilities();
        GL11.glEnable(GL_DEPTH_TEST);
    }

    public void update() {
        GLFW.glfwSwapBuffers(window);
        GLFW.glfwPollEvents();
    }

    public boolean shouldClose() {
        return GLFW.glfwWindowShouldClose(window);
    }

    public void terminate() {
        Callbacks.glfwFreeCallbacks(window);
        GLFW.glfwDestroyWindow(window);

        GLFW.glfwTerminate();
        GLFW.glfwSetErrorCallback(null).free();
    }

    public float getAspectRatio(){
        return this.aspectRatio;
    }
}
