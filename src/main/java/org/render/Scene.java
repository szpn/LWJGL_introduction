package org.render;

import org.joml.Vector3f;
import org.manager.ShaderManager;
import org.manager.WorldObjectManager;
import org.render.camera.Camera;
import org.render.light.PointLight;
import org.render.shader.Shader;
import org.worldobject.WorldObject;

import java.util.List;

public class Scene {
    private static final Vector3f DEFAULT_AMBIENT_LIGHT = new Vector3f(0.15f, 0.15f, 0.15f);
    private Camera camera;
    private PointLight pointLight;
    private Vector3f ambientLightColor = DEFAULT_AMBIENT_LIGHT;
    private final WorldObjectManager WOManager;

    public Scene(){
        this.WOManager = new WorldObjectManager();
        WorldObject.bindWorldObjectManager(WOManager); //TODO: SceneWorldObjectManager class to handle invidual scenes
        WOManager.registerShader(ShaderManager.getShaderMaterialized());
        WOManager.registerShader(ShaderManager.getShaderMaterializedLightning());
    }

    private void initScene(){

    }

    public void attachCamera(Camera camera){
        this.camera = camera;
    }

    public void attachPointLight(PointLight pointLight){
        this.pointLight = pointLight;
    }

    public void addWorldObject(WorldObject wo){
        //TODO: finish this after creating SceneWorldObjectManager
    }

    public WorldObjectManager getWOManager(){
        return this.WOManager;
    }

    public List<Shader> getUsedShaders(){
        return WOManager.getRegisteredShaders();
    }

    public Vector3f getAmbientLight(){
        return ambientLightColor;
    }

    public Camera getCamera() {
        if(camera == null){
            throw new RuntimeException("Scene doesnt have attached camera!");
        }
        return camera;
    }

    public PointLight getPointLight() {
        return pointLight;
    }

    public void update(){

    }


}
