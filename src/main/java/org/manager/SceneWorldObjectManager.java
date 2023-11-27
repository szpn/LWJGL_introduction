package org.manager;

import org.render.shader.Shader;
import org.scene.worldobject.WorldObject;

import java.util.*;

public class SceneWorldObjectManager {
    private final Map<Shader, List<WorldObject>> worldObjectsUsingShader = new HashMap<>();

    public SceneWorldObjectManager(){
        for(Shader shader : ShaderManager.getAllShaders()){
            worldObjectsUsingShader.put(shader, new ArrayList<>());
        }
    }

    public void addWorldObject(WorldObject wo){
        worldObjectShader = wo.getShader();
    }

    public void removeWorldObject(WorldObject wo){

    }

    public void getUsedShaders(){

    }


}
