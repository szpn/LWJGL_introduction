package org.worldobject;

import org.joml.Vector3f;
import org.render.Mesh;

public class WorldObject {
    private final Mesh mesh;
    private final Vector3f position;
    private float scale;
    private Vector3f rotation;
    public WorldObject(Mesh mesh){
        this.mesh = mesh;
        this.position = new Vector3f(0,0,0);
        this.scale = 1f;
        this.rotation = new Vector3f(0,0,0);
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
}
