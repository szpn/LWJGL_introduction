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
        shaderManager = new ShaderManager();
    }

    public void loop(){
        GL.createCapabilities();
        ShaderColored shaderColored = shaderManager.getShaderColored();
        WOManager.registerShader(shaderColored);

        float[] vertices = {-0.5f,-0.5f,-2f,
                -0.5f, 0.5f, -2f,
                0.5f,0.5f,-2f,
                0.5f, -0.5f,-2f};
        int[] indices = {0,1,2,
                0,2,3};
        float[] colors = {1f, 1f, 1f,
                            1f, 0f, 0f,
                            1f, 1f, 0f,
                            1f, 0f, 1f};
        Mesh mesh1 = MeshLoader.createColoredMesh(vertices, indices, colors);
        WorldObject wo1 = new WorldObject(mesh1);
        WOManager.registerWorldObject(wo1, shaderColored);


        Render render = new Render();
        while(!window.shouldClose()) {
            render.cleanup();

            for(Shader shader : WOManager.getRegisteredShaders()){
                render.enableShader(shader);
                for(WorldObject wo : WOManager.getWorldObjectsUsingShader(shader)){
                    render.renderWorldObject(wo);
                }
                render.disableCurrentShader();

            }

            window.update();
        }
    }
}
