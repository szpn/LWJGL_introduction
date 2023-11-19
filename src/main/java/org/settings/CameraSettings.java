package org.settings;

public class CameraSettings {
    private static final float MOUSE_SENSITIVITY = 0.2f;
    private static final float MOVE_SPEED = 0.1f;

    public static float getMouseSensitivity() {
        return MOUSE_SENSITIVITY;
    }

    public static float getMoveSpeed() {
        return MOVE_SPEED;
    }
}
