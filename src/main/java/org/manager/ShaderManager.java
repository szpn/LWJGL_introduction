package org.manager;

import org.joml.Vector3f;
import org.render.light.PointLight;
import org.render.shader.*;

import static java.lang.System.exit;

public class ShaderManager {
    private static ShaderColored shaderColored  = new ShaderColored();
    private static ShaderTextured shaderTextured = new ShaderTextured();
    private static ShaderTexturedNormals shaderTexturedNormals = new ShaderTexturedNormals();
    private static ShaderMaterialized shaderMaterialized = new ShaderMaterialized();
    private static ShaderMaterializedLightning shaderMaterializedLightning = new ShaderMaterializedLightning();

    public static void attachPointLightToShaders(PointLight pointLight){
        shaderMaterializedLightning.setPointLight(pointLight);
    }

    public static void attachCameraPosToShaders(Vector3f cameraPos){
        shaderMaterializedLightning.setCameraPos(cameraPos);
    }

    public static void setAmbientLight(Vector3f color) {
        shaderMaterializedLightning.setAmbientLight(color);
    }

    public static ShaderColored getShaderColored() {
        return shaderColored;
    }

    public static ShaderTextured getShaderTextured() {
        return shaderTextured;
    }

    public static ShaderTexturedNormals getShaderTexturedNormals() {
        return shaderTexturedNormals;
    }

    public static ShaderMaterialized getShaderMaterialized() {
        return  shaderMaterialized;
    }

    public static ShaderMaterializedLightning getShaderMaterializedLightning() {
        return shaderMaterializedLightning;
    }

}
