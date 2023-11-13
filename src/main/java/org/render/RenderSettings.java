package org.render;


import org.joml.Math;

public class RenderSettings {
    private static final float FOV = (float) Math.toRadians(60.0f);
    private static final float Z_NEAR = 0.01f;
    private static final float Z_FAR = 100.f;

    public static float getFOV() {
        return FOV;
    }

    public static float getzFar() {
        return Z_FAR;
    }

    public static float getzNear() {
        return Z_NEAR;
    }
}
