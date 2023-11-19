package org.manager;

import org.render.shader.Shader;
import org.worldobject.WorldObject;

import java.util.*;

public class WorldObjectManager {
    private Map<Shader, List<WorldObject>> registeredShaders = new HashMap<>();
    public WorldObjectManager(){

    }

    public void registerShader(Shader shader){
        if(registeredShaders.containsKey(shader)){
            throw new RuntimeException("Shader was already registered!");
        }
        registeredShaders.put(shader, new ArrayList<>());
    }

    public List<Shader> getRegisteredShaders(){
        return new ArrayList<>(registeredShaders.keySet());
    }

    public void registerWorldObjectUsesShader(WorldObject worldObject, Shader shader){
        if(!registeredShaders.containsKey(shader)){
            throw new IllegalStateException("Trying to add not registered Shader!");
        }
        registeredShaders.get(shader).add(worldObject);
    }

    public List<WorldObject> getWorldObjectsUsingShader(Shader shader){
        return registeredShaders.get(shader);
    }

}
