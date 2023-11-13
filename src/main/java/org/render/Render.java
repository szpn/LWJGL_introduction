package org.render;
import org.boot.Boot;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.render.shader.Shader;
import org.render.shader.ShaderColored;
import org.render.shader.ShaderTextured;

public class Render {

    ShaderColored shader;
    Transformation transformation;

    public Render(){
        init();
    }

    private void init(){
        transformation = new Transformation();
        shader = new ShaderColored();
    }


    public void cleanup(){
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
    }

    public void renderMesh(Mesh mesh){
        shader.start();
        shader.setProjection(transformation.getProjectionMatrix());
        GL30.glBindVertexArray(mesh.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL11.glDrawElements(GL11.GL_TRIANGLES, mesh.getVertexCount(), GL11.GL_UNSIGNED_INT,0);
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL30.glBindVertexArray(0);
        shader.stop();
    }
}
