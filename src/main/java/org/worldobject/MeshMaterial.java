package org.worldobject;

import org.joml.Vector3f;
import org.joml.Vector4f;
import org.render.Texture;

public class MeshMaterial {
    private static final Vector4f DEFAULT_COLOR = new Vector4f(1.0f, 0f, 1.0f, 1.0f);

    private int textureID;
    private boolean isTextured;

    private Vector4f diffuseColor;
    private Vector4f ambientColor;
    private Vector4f specularColor;
    private float reflectance = 1f;


    public MeshMaterial(){
        this.diffuseColor = DEFAULT_COLOR;
        this.ambientColor = DEFAULT_COLOR;
        this.specularColor = DEFAULT_COLOR;
        this.isTextured = false;
    }
    public MeshMaterial(String texturePath){
        this.textureID = Texture.loadTexture(texturePath);
        this.isTextured = true;
    }

    public Vector4f getDiffuseColor() {
        return diffuseColor;
    }

    public Vector4f getAmbientColor() {
        return ambientColor;
    }

    public Vector4f getSpecularColor() {
        return specularColor;
    }

    public float getReflectance() {
        return reflectance;
    }

    public int getTextureID() {
        return textureID;
    }

    public boolean usesTexture(){
        return this.isTextured;
    }

    public void setDiffuseColor(Vector4f diffuseColor) {
        this.diffuseColor = diffuseColor;
    }

    public void setAmbientColor(Vector4f ambientColor) {
        this.ambientColor = ambientColor;
    }

    public void setSpecularColor(Vector4f specularColor) {
        this.specularColor = specularColor;
    }

    public void setReflectance(float reflectance) {
        this.reflectance = reflectance;
    }

    public void setTextureFromPath(String texturePath) {
        this.textureID = Texture.loadTexture(texturePath);
        this.isTextured = true;
    }
}
