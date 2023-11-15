package org.render;

import org.boot.Boot;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Transformation {
    private final Matrix4f projectionMatrix;
    private final Matrix4f worldMatrix;
    public Transformation(){
        this.projectionMatrix = new Matrix4f();
        this.worldMatrix = new Matrix4f();
    }

    public final Matrix4f getProjectionMatrix(){
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
}
