package org.render;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.manager.ShaderManager;
import org.manager.WorldObjectManager;
import org.render.camera.Camera;
import org.render.light.PointLight;
import org.render.shader.Shader;
import org.render.shader.ShaderMaterializedLightning;
import org.worldobject.Mesh;
import org.worldobject.WorldObject;

import java.util.List;

public class Render {
    private Transformation transformation;
    private Shader shader;
    public Matrix4f cameraViewMatrix;

    public Render(){
        init();
    }

    private void init(){
        transformation = new Transformation();
    }


    private void useCameraView(Camera camera){
        this.cameraViewMatrix = transformation.getViewMatrix(camera);
    }

    public void renderScene(Scene scene){
        useCameraView(scene.getCamera());
        for(Shader shader : scene.getUsedShaders()){
            enableShader(shader);
            prepareEnabledShader(scene);


            List<WorldObject> WOUsingShader = scene.getWOManager().getWorldObjectsUsingShader(shader);
            renderWorldObjectsWithSameShader(WOUsingShader);

            disableCurrentShader();
        }
    }

    private void enableShader(Shader shader){
        this.shader = shader;
        shader.start();
        shader.setProjection(transformation.getProjectionMatrix());
    }


    private void prepareEnabledShader(Scene scene){
        ShaderManager.setAmbientLight(scene.getAmbientLight());

        PointLight pointLightInWorldPos = transformation.convertLightPosToWorldPos(scene.getPointLight());
        ShaderManager.attachPointLightToShaders(pointLightInWorldPos);

        ShaderManager.attachCameraPosToShaders(scene.getCamera().getPosition());
    }

    private void disableCurrentShader(){
        shader.stop();
    }

    private void renderWorldObjectsWithSameShader(List<WorldObject> WOs){
        for(WorldObject wo : WOs){
            renderWorldObject(wo);
        }
    }


    private void renderWorldObject(WorldObject wo){
        Matrix4f objectWorldMatrix = transformation.getWorldMatrix(wo);
        Matrix4f objectViewMatrix = transformation.calculateObjectViewMatrix(objectWorldMatrix, this.cameraViewMatrix);
        shader.setWorldMatrix(objectViewMatrix);

        Mesh mesh = wo.getMesh();
        renderMesh(mesh);
    }

    private void renderMesh(Mesh mesh){
        GL30.glBindVertexArray(mesh.getVaoID());

        shader.prepareForDrawingMesh(mesh);
        GL11.glDrawElements(GL11.GL_TRIANGLES, mesh.getVertexCount(), GL11.GL_UNSIGNED_INT,0);
        shader.doneDrawingMesh();

        GL30.glBindVertexArray(0);
    }



    public void cleanup(){
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
    }

}
