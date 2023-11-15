package org.boot;


import org.lwjgl.opengl.*;
import org.manager.ShaderManager;
import org.manager.WorldObjectManager;
import org.render.*;
import org.render.shader.Shader;
import org.render.shader.ShaderColored;
import org.worldobject.WorldObject;

public class Boot {
    public static Window window;

    public static void main(String[] args){
        new Boot().run();
    }
    public void run(){
        window = new Window(640,480);
        initManagers();
        loop();
        window.terminate();
    }

    private WorldObjectManager WOManager;
    private ShaderManager shaderManager;
    private void initManagers(){
        WOManager = new WorldObjectManager();
        WorldObject.bindWorldObjectManager(WOManager);
        shaderManager = new ShaderManager();
    }

    public void loop(){
        GL.createCapabilities();
        ShaderColored shaderColored = shaderManager.getShaderColored();
        WOManager.registerShader(shaderColored);

        float[] vertices = {
                -0.5f,  0.5f,   -0.5f,
                -0.5f,  -0.5f,  -0.5f,
                0.5f,   0.5f,   -0.5f,
                0.5f,   -0.5f,  -0.5f,
                -0.5f,  0.5f,  0.5f,
                -0.5f,  -0.5f,   0.5f,
                0.5f,   0.5f,   0.5f,
                0.5f,   -0.5f,  0.5f,
        };
        int[] indices = {
                4,5,6,6,5,7, //front
                0,1,2,2,1,3, //back
                0,1,4,4,1,5, //left
                6,7,2,2,7,3, // right
                3,1,7,7,1,5, //bottom
                2,0,6,6,0,4 //top


        };
        float[] colors = {
                0.5f, 0.0f, 0.0f,
                0.0f, 0.5f, 0.0f,
                0.0f, 0.0f, 0.5f,
                0.0f, 0.5f, 0.5f,
                0.5f, 0.0f, 0.0f,
                0.0f, 0.5f, 0.0f,
                0.0f, 0.0f, 0.5f,
                0.0f, 0.5f, 0.5f,
        };
        Mesh mesh1 = MeshLoader.createColoredMesh(vertices, indices, colors);
        WorldObject wo1 = new WorldObject(mesh1);
        wo1.addShader(shaderColored);
        wo1.setPosition(0,0,-2f);


        Render render = new Render();
        while(!window.shouldClose()) {
            render.cleanup();

            for(Shader shader : WOManager.getRegisteredShaders()){
                render.enableShader(shader);
                for(WorldObject wo : WOManager.getWorldObjectsUsingShader(shader)){
                    float rotation = wo.getRotation().y + 0.5f;
                    wo.setRotation(0, rotation, rotation);
                    render.renderWorldObject(wo);
                }
                render.disableCurrentShader();

            }

            window.update();
        }
    }
}
