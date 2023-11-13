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
                -0.50f, 0.50f, 0,
                0.50f,0.50f,0f,
                0.50f, -0.5f,0};
        int[] indices = {0,1,2,
                0,2,3};

        Mesh mesh1 = MeshLoader.createSimpleMesh(vertices, indices);
        float[] colors = {1f, 1f, 1f,
                            1f, 0f, 0f,
                            0f, 1f, 0f,
                            0f, 0f, 1f};
        Mesh mesh2 = MeshLoader.createColoredMesh(vertices, indices, colors);

        Render render = new Render();


        while(!window.shouldClose()) {
            render.cleanup();
            render.render(mesh2);

            window.update();
        }
    }
}
