package org.render.shader;

import org.joml.Matrix4f;

public class ShaderTextured extends Shader{
    private int projectionMatrix;
    private int worldMatrix;
    public ShaderTextured() {
        super("Textured.vs", "Textured.fs");
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoords");
    }

    public void setProjection(Matrix4f mat) {
        this.loadMatrix(projectionMatrix, mat);
    }

    public void setWorldMatrix(Matrix4f mat){
        this.loadMatrix(worldMatrix, mat);
    }

    @Override
    protected void getAllUniformLocations() {
        this.projectionMatrix = this.getUniformLocation("projectionMatrix");
        this.worldMatrix = this.getUniformLocation("worldMatrix");
    }
}