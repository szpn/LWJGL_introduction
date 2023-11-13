package org.render.shader;

import org.joml.Matrix4f;

public class ShaderColored extends Shader{

    private int projectionMatrix;

    public ShaderColored() {
        super("Colored.vs", "Colored.fs");
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "fragmentColor");
    }

    public void setProjection(Matrix4f mat) {
        this.loadMatrix(projectionMatrix, mat);
    }

    @Override
    protected void getAllUniformLocations() {
        this.projectionMatrix = this.getUniformLocation("projectionMatrix");
    }
}
