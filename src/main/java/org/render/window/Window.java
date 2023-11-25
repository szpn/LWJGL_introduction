package org.render.window;

import java.nio.IntBuffer;

import org.boot.Boot;
import org.input.InputHandler;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.MemoryStack;
import org.settings.WindowSettings;


import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11C.GL_DEPTH_TEST;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Window {
    public long window;

    public int width;
    public int height;
    public float aspectRatio;

    private InputHandler inputHandler;
    private WindowController windowController;

    private GLFWWindowSizeCallback windowSize;

    public Window(int width, int height) {
        init(width, height);
        createInputHandler();
        this.windowController = new WindowController(this);
        this.windowController.init();
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

        GLFW.glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_DISABLED);

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
        GL11.glEnable(GL_BLEND);
        GL11.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        //GL11.glPolygonMode( GL11.GL_FRONT_AND_BACK, GL11.GL_LINE );
    }

    private void createInputHandler(){
        inputHandler = new InputHandler(window);
    }
    public InputHandler getInputHandler(){
        if(inputHandler == null){
            throw new RuntimeException("InputHandler was not initialized in window!");
        }
        return inputHandler;
    }

    public void processInputs(){
        GLFW.glfwPollEvents();
        inputHandler.processInputs();
    }


    public void update() {
        GLFW.glfwSwapBuffers(window);
    }

    protected void closeWindow(){
        GLFW.glfwSetWindowShouldClose(window, true);
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
