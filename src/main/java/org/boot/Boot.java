package org.boot;


import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL11;
import org.manager.ShaderManager;
import org.manager.WorldObjectManager;
import org.render.*;
import org.render.camera.Camera;
import org.render.light.PointLight;
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
//        WOManager.registerShader(ShaderManager.getShaderTextured());
//        WOManager.registerShader(ShaderManager.getShaderTexturedNormals());
        WOManager.registerShader(ShaderManager.getShaderMaterialized());
        WOManager.registerShader(ShaderManager.getShaderMaterializedLightning());
    }

    public void loop(){
        Camera camera = new Camera();

        WorldObject texturedDemo = DemoWorldObjects.generateDemoTexturedCubeGameObject();
        //WorldObject coloredDemo = DemoWorldObjects.generateDemoColoredCubeGameObject();
        WorldObject objFileDemo = DemoWorldObjects.generateDemoUsingOBJFile();

        PointLight pointLight = new PointLight(new Vector3f(0f,0f,0f), new Vector3f(1f,0f,1f), 20);

        Render render = new Render();
        while(!window.shouldClose()) {
            window.processInputs();

            render.cleanup();
            render.useCameraView(camera);


            for(Shader shader : WOManager.getRegisteredShaders()){
                render.enableShader(shader);

                ShaderManager.setAmbientLight(new Vector3f(0.15f, 0.15f, 0.15f));
                //pointLight.setPosition(camera.getPosition().x,camera.getPosition().y,camera.getPosition().z);

                PointLight currPointLight = new PointLight(pointLight);
                Vector3f lightPos = currPointLight.getPosition();
                Vector4f aux = new Vector4f(lightPos, 1);
                aux.mul(render.cameraViewMatrix);
                lightPos.set(aux.x ,aux.y, aux.z);



                ShaderManager.attachPointLightToShaders(currPointLight);
                ShaderManager.attachCameraPosToShaders(camera.getPosition());

                render.renderWorldObjectsWithSameShader(WOManager.getWorldObjectsUsingShader(shader));

                render.disableCurrentShader();
            }

            window.update();
        }
    }
}
