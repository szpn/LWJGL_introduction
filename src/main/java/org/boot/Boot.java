package org.boot;


import org.joml.Vector3f;
import org.render.*;
import org.scene.camera.Camera;
import org.scene.light.PointLight;
import org.render.window.Window;
import org.scene.Scene;
import org.settings.WindowSettings;
import org.scene.worldobject.WorldObject;



public class Boot {
    public static Window window;

    public static void main(String[] args){
        new Boot().run();
    }
    public void run(){
        window = new Window(WindowSettings.getWindowWidth(), WindowSettings.getWindowHeight());

        loop();

        window.terminate();
    }

    public void loop(){
        Scene scene = new Scene();

        WorldObject texturedDemo = DemoWorldObjects.generateDemoTexturedCubeGameObject();
        WorldObject objFileDemo = DemoWorldObjects.generateDemoUsingOBJFile();



        Camera camera = new Camera();
        scene.attachCamera(camera);

        PointLight pointLight = new PointLight(new Vector3f(2f,1.5f,-3f), new Vector3f(1f,0f,1f), 10);
        scene.attachPointLight(pointLight);

        Render render = new Render();
        while(!window.shouldClose()) {
            window.processInputs();

            render.cleanup();
            render.renderScene(scene);

            window.update();
        }
    }
}
