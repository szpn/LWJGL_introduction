package org.input;

import org.input.inputevent.MouseEvent;
import org.joml.Vector2d;
import org.joml.Vector2f;

import static org.lwjgl.glfw.GLFW.*;

public class MouseInputHandler {
    private final long windowID;
    private InputHandler inputHandler;

    private final Vector2d previousPos;
    private final Vector2d currentPos;

    private final Vector2f displacementVec;

    private boolean inWindow = false;
    private boolean leftButtonPressed = false;
    private boolean previousLeftButtonPressed = false;
    private boolean rightButtonPressed = false;
    private boolean previousRightButtonPressed = false;

    protected MouseInputHandler(long windowID, InputHandler inputHandler){
        this.windowID = windowID;
        this.inputHandler = inputHandler;
        previousPos = new Vector2d(-1, -1);
        currentPos = new Vector2d(0,0);
        displacementVec = new Vector2f();
    }

    protected void init() {
        glfwSetCursorPosCallback(windowID, (windowHandle, xpos, ypos) -> {
            currentPos.x = xpos;
            currentPos.y = ypos;
        });
        glfwSetCursorEnterCallback(windowID, (windowHandle, entered) -> {
            inWindow = entered;
        });
        glfwSetMouseButtonCallback(windowID, (windowHandle, button, action, mode) -> {
            leftButtonPressed = button == GLFW_MOUSE_BUTTON_1 && action == GLFW_PRESS;
            rightButtonPressed = button == GLFW_MOUSE_BUTTON_2 && action == GLFW_PRESS;
        });
    }


    protected void processInputs() {
        processMouseDisplacement();
        processLeftMouseClick();
        processRightMouseClick();
    }

    private void processMouseDisplacement(){
        displacementVec.x = 0;
        displacementVec.y = 0;
        if (previousPos.x > 0 && previousPos.y > 0 && inWindow) {
            double deltax = currentPos.x - previousPos.x;
            double deltay = currentPos.y - previousPos.y;
            if(deltax != 0 || deltay != 0){
                displacementVec.y = (float) deltax;
                displacementVec.x = (float) deltay;
                inputHandler.mouseEvent(MouseEvent.MOUSE_MOVED);
            }
        }
        previousPos.x = currentPos.x;
        previousPos.y = currentPos.y;
    }

    private void processLeftMouseClick(){
        if(leftButtonPressed != previousLeftButtonPressed && leftButtonPressed){
            inputHandler.mouseEvent(MouseEvent.MOUSE_LEFT_CLICK);
        }
        previousLeftButtonPressed = leftButtonPressed;
    }

    private void processRightMouseClick(){
        if(rightButtonPressed != previousRightButtonPressed && rightButtonPressed){
            inputHandler.mouseEvent(MouseEvent.MOUSE_RIGHT_CLICK);
        }
        previousRightButtonPressed = rightButtonPressed;
    }

    protected Vector2f getDisplacementVec() {
        return displacementVec;
    }

    protected boolean isLeftButtonPressed() {
        return leftButtonPressed;
    }

    protected boolean isRightButtonPressed() {
        return rightButtonPressed;
    }


}
