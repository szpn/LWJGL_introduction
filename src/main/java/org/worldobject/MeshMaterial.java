package org.worldobject;

import org.joml.Vector3f;
import org.joml.Vector4f;
import org.render.Texture;

public class MeshMaterial {
    private static final Vector3f DEFAULT_COLOR = new Vector3f(1.0f, 1.0f, 1.0f);

    private Vector3f diffuseColor;
    private int textureID;
    private boolean isTextured;

    public MeshMaterial(){
        this.diffuseColor = DEFAULT_COLOR;
        this.isTextured = false;
    }
    public MeshMaterial(String texturePath){
        this.textureID = Texture.loadTexture(texturePath);
        this.isTextured = true;
    }

    public Vector3f getDiffuseColor() {
        return diffuseColor;
    }

    public int getTextureID() {
        return textureID;
    }

    public boolean usesTexture(){
        return this.isTextured;
    }

    public void setDiffuseColor(Vector3f diffuseColor) {
        this.diffuseColor = diffuseColor;
    }

    public void setTextureFromPath(String texturePath) {
        this.textureID = Texture.loadTexture(texturePath);
        this.isTextured = true;
    }
}
