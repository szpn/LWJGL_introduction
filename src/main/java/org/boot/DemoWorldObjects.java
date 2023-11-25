package org.boot;

import org.joml.Vector4f;
import org.manager.ShaderManager;
import org.render.shader.*;
import org.worldobject.Mesh;
import org.worldobject.MeshLoader;
import org.worldobject.MeshMaterial;
import org.worldobject.WorldObject;
import org.worldobject.objloader.OBJLoader;

public class DemoWorldObjects {
    private static final ShaderColored shaderColored = ShaderManager.getShaderColored();
    private static final ShaderTextured shaderTextured = ShaderManager.getShaderTextured();
    private static final ShaderTexturedNormals shaderTexturedNormals = ShaderManager.getShaderTexturedNormals();
    private static final ShaderMaterialized shaderMaterialized = ShaderManager.getShaderMaterialized();
    private static final ShaderMaterializedLightning shaderMaterializedLightning = ShaderManager.getShaderMaterializedLightning();
    static public WorldObject generateDemoTexturedCubeGameObject(){

        Mesh mesh = OBJLoader.OBJtoMesh("cube.obj");

        MeshMaterial material = new MeshMaterial();
        material.setAmbientColor(new Vector4f(1f,1f,1f,1f));
        material.setDiffuseColor(new Vector4f(1f,1f,1f,1f));
        //material.setTextureFromPath("bricks.jpg");

        mesh.setMaterial(material);

        WorldObject demoTexturedObject = new WorldObject(mesh);
        demoTexturedObject.addShader(shaderMaterializedLightning);
        demoTexturedObject.setPosition(0,1,-5f);

        return demoTexturedObject;
    }

    static public WorldObject generateDemoTexturedCubeGameObjectNoLightning(){

        Mesh mesh = OBJLoader.OBJtoMesh("cube.obj");

        MeshMaterial material = new MeshMaterial();
        material.setAmbientColor(new Vector4f(1f,1f,1f,1f));
        material.setDiffuseColor(new Vector4f(1f,1f,1f,1f));

        mesh.setMaterial(material);

        WorldObject demoTexturedObject = new WorldObject(mesh);
        demoTexturedObject.addShader(shaderMaterialized);
        demoTexturedObject.setPosition(0,1,-5f);
        demoTexturedObject.setScale(0.1f);

        return demoTexturedObject;
    }
    static public WorldObject generateDemoColoredCubeGameObject(){
        float[] vertices = {
                -0.5f,  0.5f,   -0.5f,
                -0.5f,  -0.5f,  -0.5f,
                0.5f,   0.5f,   -0.5f,
                0.5f,   -0.5f,  -0.5f,
                -0.5f,  0.5f,  0.5f,
                -0.5f,  -0.5f,   0.5f,
                0.5f,   0.5f,   0.5f,
                0.5f,   -0.5f,  0.5f,
        };
        int[] indices = {
                4,5,6,6,5,7, //front
                0,1,2,2,1,3, //back
                0,1,4,4,1,5, //left
                6,7,2,2,7,3, // right
                3,1,7,7,1,5, //bottom
                2,0,6,6,0,4 //top
        };
        float[] colors = {
                0.5f, 0.0f, 0.0f,
                0.0f, 0.5f, 0.0f,
                0.0f, 0.0f, 0.5f,
                0.0f, 0.5f, 0.5f,
                0.5f, 0.0f, 0.0f,
                0.0f, 0.5f, 0.0f,
                0.0f, 0.0f, 0.5f,
                0.0f, 0.5f, 0.5f,
        };
        Mesh mesh = MeshLoader.createColoredMesh(vertices, indices, colors);
        WorldObject demoColoredObject = new WorldObject(mesh);
        demoColoredObject.addShader(shaderColored);
        demoColoredObject.setPosition(-1,0,-2f);

        return demoColoredObject;
    }

    static public WorldObject generateDemoUsingOBJFile(){
        Mesh mesh = OBJLoader.OBJtoMesh("owlbear.obj");

        MeshMaterial material = new MeshMaterial();
        material.setTextureFromPath("owlbear.png");

        mesh.setMaterial(material);

        WorldObject demoOBJ = new WorldObject(mesh);
        //demoOBJ.addShader(shaderMaterialized);
        demoOBJ.addShader(shaderMaterializedLightning);
        demoOBJ.setPosition(5f,0f,-3f);

        return demoOBJ;
    }
}
