package org.render;

import org.boot.Boot;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.scene.camera.Camera;
import org.scene.light.PointLight;
import org.settings.RenderSettings;
import org.scene.worldobject.WorldObject;

public class Transformation {
    private final Matrix4f projectionMatrix;
    private final Matrix4f worldMatrix;
    private final Matrix4f viewMatrix;
    private final Matrix4f objectViewMatrix;

    public Transformation(){
        this.projectionMatrix = new Matrix4f();
        this.worldMatrix = new Matrix4f();
        this.viewMatrix = new Matrix4f();
        this.objectViewMatrix = new Matrix4f();
    }

    public Matrix4f getProjectionMatrix(){
        float aspectRatio = Boot.window.getAspectRatio();
        projectionMatrix.identity();
        projectionMatrix.perspective(RenderSettings.getFOV(), aspectRatio, RenderSettings.getzNear(), RenderSettings.getzFar());
        return projectionMatrix;
    }

    public Matrix4f getWorldMatrix(Vector3f offset, float scale, Vector3f rotation){
        worldMatrix.identity().translate(offset).
                rotateX((float)Math.toRadians(rotation.x)).
                rotateY((float)Math.toRadians(rotation.y)).
                rotateZ((float)Math.toRadians(rotation.z)).
                scale(scale);
        return worldMatrix;
    }

    public Matrix4f getWorldMatrix(WorldObject wo){
        return getWorldMatrix(wo.getPosition(), wo.getScale(), wo.getRotation());
    }

    public Matrix4f getViewMatrix(Camera camera) {
        Vector3f cameraPos = camera.getPosition();
        Vector3f rotation = camera.getRotation();

        viewMatrix.identity();
        viewMatrix.rotate((float)Math.toRadians(rotation.x), new Vector3f(1, 0, 0))
                .rotate((float)Math.toRadians(rotation.y), new Vector3f(0, 1, 0));
        viewMatrix.translate(-cameraPos.x, -cameraPos.y, -cameraPos.z);
        return viewMatrix;
    }

    public Matrix4f calculateObjectViewMatrix(Matrix4f worldMatrix, Matrix4f viewMatrix){
        Matrix4f objectViewMatrix = new Matrix4f(viewMatrix);
        return objectViewMatrix.mul(worldMatrix);
    }

    public PointLight convertLightPosToWorldPos(PointLight pointLight){
        PointLight worldPointLight = new PointLight(pointLight);
        Vector3f lightPos = worldPointLight.getPosition();
        Vector4f aux = new Vector4f(lightPos, 1);
        aux.mul(viewMatrix);
        lightPos.set(aux.x ,aux.y, aux.z);
        return worldPointLight;
    }


}
