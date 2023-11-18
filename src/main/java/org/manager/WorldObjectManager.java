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

    public void registerWorldObject(WorldObject worldObject, Shader shader){
        registeredShaders.get(shader).add(worldObject);
    }

    public List<Shader> getRegisteredShaders(){
        return new ArrayList<>(registeredShaders.keySet());
    }

    public List<WorldObject> getWorldObjectsUsingShader(Shader shader){
        return registeredShaders.get(shader);
    }

}
