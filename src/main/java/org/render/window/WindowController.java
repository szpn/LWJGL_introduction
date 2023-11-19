package org.render.window;

import org.boot.Boot;
import org.input.InputHandler;
import org.input.InputListener;
import org.input.inputevent.KeyboardEvent;
import org.input.inputevent.MouseEvent;
import org.lwjgl.glfw.GLFW;

public class WindowController extends InputListener {
    private Window window;
    private InputHandler inputHandler;
    protected WindowController(Window window){
        this.window = window;
    }

    protected void init(){
        this.inputHandler = window.getInputHandler();
        inputHandler.addEventListener(KeyboardEvent.KEYBOARD_ESC_HOLD, this);
    }

    @Override
    protected void keyIsPressed(int keyCode){
        if (keyCode == GLFW.GLFW_KEY_ESCAPE) window.closeWindow();
    }
}
