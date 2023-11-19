package org.input;


import org.input.inputevent.KeyboardEvent;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;

public class KeyboardInputHandler {
    private static final int FIRST_KEY_VALUE = 48; // "0"
    private static final int LAST_KEY_VALUE = 90; // "Z"
    private static final int USED_KEY_RANGE = LAST_KEY_VALUE - FIRST_KEY_VALUE;
    private final long windowID;
    private final InputHandler inputHandler;

    private final List<Integer> currentlyPressedKeys = new ArrayList<>();

    protected KeyboardInputHandler(long windowID, InputHandler inputHandler){
        this.windowID = windowID;
        this.inputHandler = inputHandler;
    }

    protected void init() {

        GLFW.glfwSetKeyCallback(windowID, (windowHandle, key, scancode, action, mods) -> {
            if((key < FIRST_KEY_VALUE || key > LAST_KEY_VALUE) && key != KeyboardEvent.KEYBOARD_ESC_HOLD.toKeyCode()){
                return;
            }
            switch(action){
                case GLFW_PRESS -> keyPressed(key);
                case GLFW_RELEASE -> keyReleased(key);
                default -> {} // ignore other actions
            }
        });
    }

    private void keyPressed(int keyCode){
        currentlyPressedKeys.add((Integer)keyCode);
    }

    private void keyReleased(int keyCode){
        currentlyPressedKeys.remove((Integer)keyCode);
    }

    protected void processInputs() {
        for (Integer keyPressed : currentlyPressedKeys){
            KeyboardEvent event = KeyboardEvent.fromKeyCode(keyPressed);
            inputHandler.keyboardEvent(event);
        }
    }

}
