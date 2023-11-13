package org.render.shader;

public class ShaderColored extends Shader{

    public ShaderColored() {
        super("Colored.vs", "Colored.fs");
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "fragmentColor");
    }

    @Override
    protected void getAllUniformLocations() {

    }
}
