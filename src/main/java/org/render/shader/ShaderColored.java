package org.render.shader;

import org.joml.Matrix4f;

public class ShaderColored extends Shader{

    private int projectionMatrix;
    private int worldMatrix;

    public ShaderColored() {
        super("Colored.vs", "Colored.fs");
    }

    public void setProjection(Matrix4f mat) {
        this.loadMatrix(projectionMatrix, mat);
    }

    public void setWorldMatrix(Matrix4f mat){
        this.loadMatrix(worldMatrix, mat);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "fragmentColor");
    }

    @Override
    protected void getAllUniformLocations() {
        this.projectionMatrix = this.getUniformLocation("projectionMatrix");
        this.worldMatrix = this.getUniformLocation("worldMatrix");
    }
}
