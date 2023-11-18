package org.manager;

import org.render.shader.ShaderColored;
import org.render.shader.ShaderTextured;

import static java.lang.System.exit;

public class ShaderManager {
    private static ShaderColored shaderColored  = new ShaderColored();
    private static ShaderTextured shaderTextured = new ShaderTextured();

    public static ShaderColored getShaderColored() {
        return shaderColored;
    }

    public static ShaderTextured getShaderTextured() {
        return shaderTextured;
    }
}
