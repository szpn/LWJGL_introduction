package org.input;

import org.input.inputevent.KeyboardEvent;
import org.input.inputevent.MouseEvent;
import org.joml.Vector2f;

public abstract class InputListener {
    protected void mouseEventOccurred(MouseEvent event, Object details){
        switch (event){
            case MOUSE_MOVED -> mouseMoved((Vector2f) details);
            case MOUSE_LEFT_CLICK -> mouseLeftClick((boolean) details);
            case MOUSE_RIGHT_CLICK -> mouseRightClick((boolean) details);
        }
    }

    protected void keyboardEventOccurred(KeyboardEvent event, Object details){

    }

    protected void mouseMoved(Vector2f displacementPosition){}
    protected void mouseLeftClick(boolean clicked){}
    protected void mouseRightClick(boolean clicked){}


}
