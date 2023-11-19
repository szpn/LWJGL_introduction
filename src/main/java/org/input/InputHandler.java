package org.input;

import org.input.inputevent.KeyboardEvent;
import org.input.inputevent.MouseEvent;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class InputHandler {
    private final Map<MouseEvent, ArrayList<InputListener>> registeredMouseEventListeners = new EnumMap<>(MouseEvent.class);
    private final Map<KeyboardEvent, ArrayList<InputListener>> registeredKeyboardEventListeners = new EnumMap<>(KeyboardEvent.class);


    private final MouseInputHandler mouseInputHandler;
    private final KeyboardInputHandler keyboardInputHandler;
    public InputHandler(long windowID){
        initListenersLists();
        this.mouseInputHandler = new MouseInputHandler(windowID, this);
        this.keyboardInputHandler = new KeyboardInputHandler(windowID, this);
        mouseInputHandler.init();
        keyboardInputHandler.init();
    }

    private void initListenersLists(){
        for (MouseEvent mouseEvent : MouseEvent.values()){
            registeredMouseEventListeners.put(mouseEvent, new ArrayList<>());
        }
        for (KeyboardEvent keyboardEvent : KeyboardEvent.values()){
            registeredKeyboardEventListeners.put(keyboardEvent, new ArrayList<>());
        }
    }

    public void processInputs(){
        mouseInputHandler.processInputs();
        keyboardInputHandler.processInputs();
    }

    protected void mouseEvent(MouseEvent event){
        Object details = createDetailsForMouseEvent(event);
        List<InputListener> listenersOfEvent = registeredMouseEventListeners.get(event);
        for(InputListener listener : listenersOfEvent){
            listener.mouseEventOccurred(event, details);
        }
    }

    protected void keyboardEvent(KeyboardEvent event){
        Object details = createDetailsForKeyboardEvent(event);
        List<InputListener> listenersOfEvent = registeredKeyboardEventListeners.get(event);
        for(InputListener listener : listenersOfEvent){
            listener.keyboardEventOccurred(event, details);
        }
    }

    private Object createDetailsForMouseEvent(MouseEvent event){
        return switch(event){
            case MOUSE_MOVED -> mouseInputHandler.getDisplacementVec();
            case MOUSE_LEFT_CLICK -> true;
            case MOUSE_RIGHT_CLICK -> true;
            default -> null;
        };
    }

    private Object createDetailsForKeyboardEvent(KeyboardEvent event){
        return switch (event){
            default -> null;
        };
    }

    public void addEventListener(MouseEvent event, InputListener listener){
        List<InputListener> listenersOfEvent = registeredMouseEventListeners.get(event);
        listenersOfEvent.add(listener);
    }

    public void addEventListener(KeyboardEvent event, InputListener listener) {
        List<InputListener> listenersOfEvent = registeredKeyboardEventListeners.get(event);
        listenersOfEvent.add(listener);
    }

    public void removeEventListener(MouseEvent event, InputListener listener){
        List<InputListener> listenersOfEvent = registeredMouseEventListeners.get(event);
        listenersOfEvent.remove(listener);
    }

    public void removeEventListener(KeyboardEvent event, InputListener listener) {
        List<InputListener> listenersOfEvent = registeredKeyboardEventListeners.get(event);
        listenersOfEvent.remove(listener);
    }


}
