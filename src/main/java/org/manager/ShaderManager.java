package org.manager;

import org.render.shader.ShaderColored;
import org.render.shader.ShaderMaterialized;
import org.render.shader.ShaderTextured;
import org.render.shader.ShaderTexturedNormals;

import static java.lang.System.exit;

public class ShaderManager {
    private static ShaderColored shaderColored  = new ShaderColored();
    private static ShaderTextured shaderTextured = new ShaderTextured();
    private static ShaderTexturedNormals shaderTexturedNormals = new ShaderTexturedNormals();
    private static ShaderMaterialized shaderMaterialized = new ShaderMaterialized();

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
}
