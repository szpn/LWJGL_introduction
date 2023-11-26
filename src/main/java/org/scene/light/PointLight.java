package org.scene.light;

import org.joml.Vector3f;

public class PointLight {
    private Vector3f position;
    private Vector3f color;
    private float intensity;
    private Attenuation attenuation;

    public PointLight(Vector3f position,Vector3f color,float intensity) {
        this.attenuation = new Attenuation(5,0.5f,0.1f);
        this.position = position;
        this.color = color;
        this.intensity = intensity;
    }

    public PointLight(PointLight pointLight) {
        this.attenuation = pointLight.getAttenuation();
        this.intensity = pointLight.getIntensity();
        this.color = pointLight.getColor();
        this.position = new Vector3f(pointLight.getPosition());
    }


    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(float x, float y, float z) {
        position.set(x,y,z);
    }

    public Vector3f getColor() {
        return color;
    }

    public void setColor(Vector3f color) {
        this.color = color;
    }

    public void setColor(float r, float g, float b){
        color.set(r,g,b);
    }

    public float getIntensity() {
        return intensity;
    }

    public void setIntensity(float intensity) {
        this.intensity = intensity;
    }

    public Attenuation getAttenuation() {
        return attenuation;
    }

    public void setAttenuation(Attenuation attenuation) {
        this.attenuation = attenuation;
    }
}
