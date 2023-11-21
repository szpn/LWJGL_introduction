package org.render.shader;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.worldobject.Mesh;

public class ShaderTextured extends Shader{
    private int projectionMatrix;
    private int worldMatrix;
    private int textureSampler;
    public ShaderTextured() {
        super("Textured.vs", "Textured.fs");
    }
    protected ShaderTextured(String vert, String frag){
        super(vert, frag);
    }

    @Override
    public void prepareForDrawingMesh(Mesh mesh){
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, mesh.getMaterial().getTextureID());
        GL20.glEnableVertexAttribArray(0); //vertex positions
        GL20.glEnableVertexAttribArray(1); //texture coordinates (x,y)
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