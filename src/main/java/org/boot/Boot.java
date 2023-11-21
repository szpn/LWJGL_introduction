package org.boot;


import org.manager.ShaderManager;
import org.manager.WorldObjectManager;
import org.render.*;
import org.render.camera.Camera;
import org.render.shader.Shader;
import org.render.window.Window;
import org.settings.WindowSettings;
import org.worldobject.WorldObject;

public class Boot {
    public static Window window;
    private WorldObjectManager WOManager;

    public static void main(String[] args){
        new Boot().run();
    }
    public void run(){
        window = new Window(WindowSettings.getWindowWidth(), WindowSettings.getWindowHeight());

        initManagers();
        registerUsedShaders();

        loop();

        window.terminate();
    }

    private void initManagers(){
        WOManager = new WorldObjectManager();
        WorldObject.bindWorldObjectManager(WOManager);
    }

    private void registerUsedShaders(){
        WOManager.registerShader(ShaderManager.getShaderColored());
        WOManager.registerShader(ShaderManager.getShaderTextured());
        WOManager.registerShader(ShaderManager.getShaderTexturedNormals());
        WOManager.registerShader(ShaderManager.getShaderMaterialized());
    }

    public void loop(){
        Camera camera = new Camera();

        //WorldObject texturedDemo = DemoWorldObjects.generateDemoTexturedCubeGameObject();
        //WorldObject coloredDemo = DemoWorldObjects.generateDemoColoredCubeGameObject();
        WorldObject objFileDemo = DemoWorldObjects.generateDemoUsingOBJFile();

        Render render = new Render();
        while(!window.shouldClose()) {
            window.processInputs();

            render.cleanup();
            render.useCameraView(camera);

            for(Shader shader : WOManager.getRegisteredShaders()){
                render.enableShader(shader);

                render.renderWorldObjectsWithSameShader(WOManager.getWorldObjectsUsingShader(shader));

                render.disableCurrentShader();
            }

            window.update();
        }
    }
}
