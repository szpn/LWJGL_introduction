package org.render.shader;

import org.lwjgl.opengl.GL20;
import org.worldobject.Mesh;

public class ShaderTexturedNormals extends ShaderTextured{
    public ShaderTexturedNormals(){
        super("TexturedNormals.vs", "TexturedNormals.fs");
    }

    @Override
    public void prepareForDrawingMesh(Mesh mesh) {
        super.prepareForDrawingMesh(mesh);
        GL20.glEnableVertexAttribArray(2); //vertex normals vector (x,y,z)
    }

    @Override
    public void doneDrawingMesh() {
        super.doneDrawingMesh();
        GL20.glDisableVertexAttribArray(2);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttributes();
        super.bindAttribute(2, "normal");
    }
}
