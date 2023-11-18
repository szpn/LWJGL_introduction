package org.boot;


import org.lwjgl.opengl.*;
import org.manager.ShaderManager;
import org.manager.WorldObjectManager;
import org.render.*;
import org.render.shader.Shader;
import org.render.shader.ShaderColored;
import org.render.shader.ShaderTextured;
import org.worldobject.WorldObject;

import static java.lang.System.exit;

public class Boot {
    public static Window window;

    public static void main(String[] args){
        new Boot().run();
    }
    public void run(){
        window = new Window(640,480);
        initManagers();
        registerUsedShaders();
        loop();
        window.terminate();
    }

    private WorldObjectManager WOManager;
    private void initManagers(){
        WOManager = new WorldObjectManager();
        WorldObject.bindWorldObjectManager(WOManager);
    }

    private void registerUsedShaders(){
        WOManager.registerShader(ShaderManager.getShaderColored());
        WOManager.registerShader(ShaderManager.getShaderTextured());
    }

    public void loop(){
        GL.createCapabilities();

        WorldObject texturedDemo = DemoWorldObjects.generateDemoTexturedCubeGameObject();
        WorldObject coloredDemo = DemoWorldObjects.generateDemoColoredCubeGameObject();

        Render render = new Render();
        while(!window.shouldClose()) {
            render.cleanup();

            for(Shader shader : WOManager.getRegisteredShaders()){
                render.enableShader(shader);
                render.renderWorldObjectsWithSameShader(WOManager.getWorldObjectsUsingShader(shader));
                render.disableCurrentShader();
            }

            window.update();
        }
    }
}
