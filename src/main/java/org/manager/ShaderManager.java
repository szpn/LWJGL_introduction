package org.manager;

import org.joml.Vector3f;
import org.scene.light.PointLight;
import org.render.shader.*;

public class ShaderManager {
    private static ShaderColored shaderColored  = new ShaderColored();
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



    public static ShaderMaterialized getShaderMaterialized() {
        return  shaderMaterialized;
    }

    public static ShaderMaterializedLightning getShaderMaterializedLightning() {
        return shaderMaterializedLightning;
    }

}
