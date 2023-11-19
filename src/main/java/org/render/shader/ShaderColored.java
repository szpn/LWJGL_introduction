package org.render.shader;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL20;
import org.worldobject.Mesh;

public class ShaderColored extends Shader{

    private int projectionMatrix;
    private int worldMatrix;

    public ShaderColored() {
        super("Colored.vs", "Colored.fs");
    }

    @Override
    public void prepareForDrawingMesh(Mesh mesh){
        GL20.glEnableVertexAttribArray(0); //vertex positions
        GL20.glEnableVertexAttribArray(1); //color values (r,g,b)
    }

    @Override
    public void doneDrawingMesh() {
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
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
