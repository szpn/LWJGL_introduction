package org.render.shader;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.worldobject.Mesh;
import org.worldobject.MeshMaterial;

public class ShaderMaterialized extends Shader{
    private int projectionMatrix;
    private int worldMatrix;
    private int textureSampler;
    private int usesTexture;
    private int fragmentColor;

    public ShaderMaterialized(){
        super("Materialized.vs", "Materialized.fs");
    }
    protected ShaderMaterialized(String vert, String frag){
        super(vert, frag);
    }

    @Override
    public void prepareForDrawingMesh(Mesh mesh) {
        GL20.glEnableVertexAttribArray(0); //position
        GL20.glEnableVertexAttribArray(1); //texture coordinates

        prepareRenderingForMaterial( mesh.getMaterial());
    }

    private void prepareRenderingForMaterial(MeshMaterial material){
        if(material.usesTexture()){
            GL13.glActiveTexture(GL13.GL_TEXTURE0);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, material.getTextureID());
            setUsesTexture(true);
        }else{
            setMeshColor(material.getDiffuseColor());
            setUsesTexture(false);
        }
    }

    @Override
    public void doneDrawingMesh() {
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
    }

    @Override
    public void setProjection(Matrix4f mat) {
        this.loadMatrix(projectionMatrix, mat);
    }

    @Override
    public void setWorldMatrix(Matrix4f mat) {
        this.loadMatrix(worldMatrix, mat);
    }

    private void setUsesTexture(boolean usesTexture){
        this.loadBoolean(this.usesTexture, usesTexture);
    }

    private void setDefaultTextureSampler(){
        GL20.glUniform1i(textureSampler, 0);
    }

    private void setMeshColor(Vector4f color){
        this.loadVector(fragmentColor, color);
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
        this.usesTexture = this.getUniformLocation("usesTexture");
        this.textureSampler = this.getUniformLocation("texture_sampler");
        this.fragmentColor = this.getUniformLocation("meshColor");
        setDefaultTextureSampler();
    }
}
