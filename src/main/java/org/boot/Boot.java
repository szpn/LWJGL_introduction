package org.boot;


import org.lwjgl.opengl.*;
import org.render.*;

public class Boot {
    public static Window window;

    public static void main(String[] args){
        new Boot().run();
    }
    public void run(){
        window = new Window(640,480);
        loop();
        window.terminate();
    }

    public void loop(){
        GL.createCapabilities();

        float[] vertices = {-0.50f,-0.50f,0f,
                0.50f, -0.50f, 0,
                -0.50f,0.50f,0f,
                0.50f, 0.5f,0};
        int[] indices = {0,1,2,
                1,2,3};

        float[] UVs = {0f,0f,
                1f, 1f,
                0f,0f,
                1f,0f};
        Mesh meshmeyek = MeshLoader.createMesh(vertices, UVs,indices).addTexture("coffee.png");

        Render render = new Render();


        while(!window.shouldClose()) {
            render.cleanup();
            render.render(meshmeyek);

            window.update();
        }
    }
}
