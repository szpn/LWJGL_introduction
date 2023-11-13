package org.render.shader;

public class ShaderColored extends Shader{

    public ShaderColored() {
        super("Vertex.vs", "Fragment.fs");
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
