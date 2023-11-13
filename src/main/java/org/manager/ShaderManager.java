package org.manager;

import org.render.shader.ShaderColored;
import org.render.shader.ShaderTextured;

public class ShaderManager {
    private ShaderColored shaderColored;
    private ShaderTextured shaderTextured;
    public ShaderManager(){

    }
    private void Init(){
        this.shaderColored = new ShaderColored();
        this.shaderTextured = new ShaderTextured();
    }

    public ShaderColored getShaderColored() {
        return shaderColored;
    }

    public ShaderTextured getShaderTextured() {
        return shaderTextured;
    }
}
