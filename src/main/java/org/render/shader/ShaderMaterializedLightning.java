package org.render.shader;

import org.joml.Vector3f;
import org.lwjgl.opengl.GL20;
import org.scene.light.Attenuation;
import org.scene.light.PointLight;
import org.scene.worldobject.Mesh;
import org.scene.worldobject.MeshMaterial;

public class ShaderMaterializedLightning extends  ShaderMaterialized{

    private int cameraPos;
    private int ambientLight;
    private int pointLightColor;
    private int pointLightPosition;
    private int pointLightIntensity;
    private int pointLightAttC;
    private int pointLightAttLinear;
    private int pointLightAttExponent;
    private int materialAmbient;
    private int materialDiffuse;
    private int materialSpecular;
    private int materialReflectance;


    public ShaderMaterializedLightning(){
        super("MaterializedLightning.vs", "MaterializedLightning.fs");
    }



    @Override
    public void prepareForDrawingMesh(Mesh mesh) {
        super.prepareForDrawingMesh(mesh);
        GL20.glEnableVertexAttribArray(2); // vertex normals

        setMaterialLightningSettings(mesh.getMaterial());
    }

    private void setMaterialLightningSettings(MeshMaterial material){
        this.loadVector(materialAmbient, material.getAmbientColor());
        this.loadVector(materialDiffuse, material.getDiffuseColor());
        this.loadVector(materialSpecular, material.getSpecularColor());
        this.loadFloat(materialReflectance, material.getReflectance());
    }

    public void setPointLight(PointLight pointLight){
        this.loadVector(pointLightPosition, pointLight.getPosition());
        this.loadVector(pointLightColor, pointLight.getColor());
        this.loadFloat(pointLightIntensity, pointLight.getIntensity());
        Attenuation att = pointLight.getAttenuation();
        this.loadFloat(pointLightAttC, att.getConstant());
        this.loadFloat(pointLightAttLinear, att.getLinear());
        this.loadFloat(pointLightAttExponent, att.getExponent());
    }

    public void setAmbientLight(Vector3f color){
        this.loadVector(ambientLight, color);
    }

    @Override
    public void doneDrawingMesh() {
        super.doneDrawingMesh();
        GL20.glDisableVertexAttribArray(2);
    }


    public void setCameraPos(Vector3f cameraPos) {
        this.loadVector(this.cameraPos, cameraPos);
    }


    @Override
    protected void bindAttributes() {
        super.bindAttributes();
        super.bindAttribute(2, "normal");
    }

    @Override
    protected void getAllUniformLocations() {
        super.getAllUniformLocations();
        this.cameraPos = this.getUniformLocation("camera_pos");
        this.ambientLight = this.getUniformLocation("ambientLight");

        this.pointLightColor = this.getUniformLocation("pointLight.colour");
        this.pointLightPosition = this.getUniformLocation("pointLight.position");
        this.pointLightIntensity = this.getUniformLocation("pointLight.intensity");
        this.pointLightAttC = this.getUniformLocation("pointLight.att.constant");
        this.pointLightAttLinear = this.getUniformLocation("pointLight.att.linear");
        this.pointLightAttExponent = this.getUniformLocation("pointLight.att.exponent");

        this.materialAmbient = this.getUniformLocation("material.ambient");
        this.materialDiffuse = this.getUniformLocation("material.diffuse");
        this.materialSpecular = this.getUniformLocation("material.specular");
        this.materialReflectance = this.getUniformLocation("material.reflectance");
    }


}
