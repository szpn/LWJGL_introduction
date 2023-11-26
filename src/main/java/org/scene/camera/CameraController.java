package org.scene.camera;

import org.boot.Boot;
import org.input.InputHandler;
import org.input.InputListener;
import org.input.inputevent.KeyboardEvent;
import org.input.inputevent.MouseEvent;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.settings.CameraSettings;

import static org.lwjgl.glfw.GLFW.*;

public class CameraController extends InputListener {
    private Camera camera;
    private InputHandler inputHandler;

    private final Vector3f cameraPosOffset = new Vector3f();
    protected CameraController(Camera camera){
        this.camera = camera;
        this.inputHandler = Boot.window.getInputHandler();
    }

    protected void init(){
        inputHandler.addEventListener(MouseEvent.MOUSE_MOVED, this);
        inputHandler.addEventListener(KeyboardEvent.KEYBOARD_W_HOLD, this);
        inputHandler.addEventListener(KeyboardEvent.KEYBOARD_S_HOLD, this);
        inputHandler.addEventListener(KeyboardEvent.KEYBOARD_A_HOLD, this);
        inputHandler.addEventListener(KeyboardEvent.KEYBOARD_D_HOLD, this);
    }

    @Override
    protected void mouseMoved(Vector2f displacementPosition){
        float sensitivity = CameraSettings.getMouseSensitivity();
        camera.moveRotation(displacementPosition.x * sensitivity , displacementPosition.y * sensitivity, 0);
    }

    @Override
    protected void keyIsPressed(int keyCode){
        float moveSpeed = CameraSettings.getMoveSpeed();
        cameraPosOffset.set(0,0,0);
        switch (keyCode){
            case GLFW_KEY_W -> cameraPosOffset.z = -1;
            case GLFW_KEY_S -> cameraPosOffset.z =  1;
            case GLFW_KEY_A -> cameraPosOffset.x = -1;
            case GLFW_KEY_D -> cameraPosOffset.x =  1;
        }
        camera.movePosition(cameraPosOffset.x * moveSpeed, cameraPosOffset.y * moveSpeed, cameraPosOffset.z * moveSpeed);
    }
}
