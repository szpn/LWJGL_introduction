package org.worldobject;

import org.joml.Vector3f;
import org.manager.WorldObjectManager;
import org.render.Mesh;
import org.render.Texture;
import org.render.shader.Shader;

public class WorldObject {
    private static WorldObjectManager WOManager;
    private final Mesh mesh;
    private String texture =  "missing.png";
    private final Vector3f position;
    private float scale;
    private Vector3f rotation;
    public WorldObject(Mesh mesh){
        if(!isManagerBound()){
            throw new RuntimeException("WorldObjectManager is not bound to WorldObject class!");
        }
        this.mesh = mesh;
        this.position = new Vector3f(0,0,0);
        this.scale = 1f;
        this.rotation = new Vector3f(0,0,0);
    }

    public static void bindWorldObjectManager(WorldObjectManager manager){
        WOManager = manager;
    }

    private boolean isManagerBound(){
        return WOManager != null;
    }

    public Vector3f getPosition(){
        return this.position;
    }

    public void setPosition(float x, float y, float z){
        this.position.x = x;
        this.position.y = y;
        this.position.z = z;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale){
        this.scale = scale;
    }

    public Vector3f getRotation(){
        return this.rotation;
    }

    public void setRotation(float x, float y, float z){
        this.rotation.x = x;
        this.rotation.y = y;
        this.rotation.z = z;
    }

    public Mesh getMesh() {
        return mesh;
    }

    public void addShader(Shader shader){
        WOManager.registerWorldObject(this, shader);
    }

    public void addTextureURI(String texture){
        this.texture = texture;
    }

    public String getTextureURI(){
        return this.texture;
    }
}
