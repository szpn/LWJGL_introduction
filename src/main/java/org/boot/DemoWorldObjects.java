package org.boot;

import org.manager.ShaderManager;
import org.render.shader.ShaderTexturedNormals;
import org.worldobject.Mesh;
import org.worldobject.MeshLoader;
import org.render.shader.ShaderColored;
import org.render.shader.ShaderTextured;
import org.worldobject.WorldObject;
import org.worldobject.objloader.OBJLoader;

public class DemoWorldObjects {
    private static final ShaderColored shaderColored = ShaderManager.getShaderColored();
    private static final ShaderTextured shaderTextured = ShaderManager.getShaderTextured();
    private static final ShaderTexturedNormals shaderTexturedNormals = ShaderManager.getShaderTexturedNormals();
    static public WorldObject generateDemoTexturedCubeGameObject(){
        float[] vertices = {
                -0.5f,  0.5f,   -0.5f,
                -0.5f,  -0.5f,  -0.5f,
                0.5f,   0.5f,   -0.5f,
                0.5f,   -0.5f,  -0.5f,
                -0.5f,  0.5f,  0.5f,
                -0.5f,  -0.5f,   0.5f,
                0.5f,   0.5f,   0.5f,
                0.5f,   -0.5f,  0.5f,
                //repeated vertices for top of the cube to fix the problem with texture mapping
                -0.5f,  0.5f,   -0.5f,
                0.5f,   0.5f,   -0.5f,
                -0.5f,  0.5f,  0.5f,
                0.5f,   0.5f,   0.5f,
                //same for bottom of the cube
                -0.5f,  -0.5f,  -0.5f,
                0.5f,   -0.5f,  -0.5f,
                -0.5f,  -0.5f,   0.5f,
                0.5f,   -0.5f,  0.5f,
        };
        int[] indices = {
                4,5,6,6,5,7, //front
                0,1,2,2,1,3, //back
                0,1,4,4,1,5, //left
                6,7,2,2,7,3, // right
                9,8,10,9,10,11, //top
                13,12,14,13,14,15//bottom
        };
        float[] UVs = {
                0f, 0f,
                0f, 1f,
                1f, 0f,
                1f, 1f,
                1f, 0f,
                1f, 1f,
                0f, 0f,
                0f, 1f,
                0f, 0f,
                1f, 0f,
                0f, 1f,
                1f, 1f,
                0f, 0f,
                1f, 0f,
                0f, 1f,
                1f, 1f
        };
        Mesh mesh = MeshLoader.createTexturedMesh(vertices, indices, UVs);
        mesh.addTextureFromFile("bricks.jpg");
        WorldObject demoTexturedObject = new WorldObject(mesh);
        demoTexturedObject.addShader(shaderTextured);
        demoTexturedObject.setPosition(1,0,-2f);

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
        Mesh mesh = OBJLoader.OBJtoMesh("bunny.obj");
        WorldObject demoOBJ = new WorldObject(mesh);
        demoOBJ.addShader(shaderTexturedNormals);
        demoOBJ.setPosition(-1,-3f,-2f);

        return demoOBJ;
    }
}
