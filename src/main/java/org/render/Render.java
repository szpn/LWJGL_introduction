package org.render;
import org.boot.Boot;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.render.shader.Shader;
import org.render.shader.ShaderColored;
import org.render.shader.ShaderTextured;
import org.worldobject.WorldObject;

public class Render {
    private Transformation transformation;

    public Render(){
        init();
    }

    private void init(){
        transformation = new Transformation();
    }


    public void cleanup(){
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
    }

    public void renderMesh(Mesh mesh){
        GL30.glBindVertexArray(mesh.getVaoID());
        GL20.glEnableVertexAttribArray(0); //TODO: should be based on shader attrib count and type - ShaderTextured should set GL.glActivateTexture etc.
        GL20.glEnableVertexAttribArray(1);
        GL11.glDrawElements(GL11.GL_TRIANGLES, mesh.getVertexCount(), GL11.GL_UNSIGNED_INT,0);
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL30.glBindVertexArray(0);
    }

    private Shader shader;
    public void renderWorldObject(WorldObject wo){
        shader.setWorldMatrix(transformation.getWorldMatrix(wo.getPosition(), wo.getScale(), wo.getRotation()));
        Mesh mesh = wo.getMesh();

        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, Texture.loadTexture(wo.getTextureURI()));

        renderMesh(mesh);
    }

    public void enableShader(Shader shader){
        this.shader = shader;
        shader.start();
        shader.setProjection(transformation.getProjectionMatrix());
    }

    public void disableCurrentShader(){
        shader.stop();
    }



}
