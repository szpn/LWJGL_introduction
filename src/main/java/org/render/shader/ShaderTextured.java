package org.render.shader;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL20;

public class ShaderTextured extends Shader{
    private int projectionMatrix;
    private int worldMatrix;
    private int textureSampler;
    public ShaderTextured() {
        super("Textured.vs", "Textured.fs");
    }


    public void setProjection(Matrix4f mat) {
        this.loadMatrix(projectionMatrix, mat);
    }

    public void setWorldMatrix(Matrix4f mat){
        this.loadMatrix(worldMatrix, mat);
    }

    private void setDefaultTextureSampler(){
        GL20.glUniform1i(this.textureSampler, 0);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoords");
    }


    @Override
    protected void getAllUniformLocations() {
        this.projectionMatrix = this.getUniformLocation("projectionMatrix");
        this.worldMatrix = this.getUniformLocation("worldMatrix");
        this.textureSampler = this.getUniformLocation("texture_sampler");
        setDefaultTextureSampler();

    }
}