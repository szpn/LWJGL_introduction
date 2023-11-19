package org.worldobject;

import org.render.Texture;

public class Mesh {

    private int vao;
    private int vertices;
    private int texture;



    public Mesh(int vao, int vertex) {
        this.vao = vao;
        this.vertices = vertex;
        this.texture = Texture.loadTexture("missing.png");
    }

    public void addTextureFromFile(String texture) {
        this.texture = Texture.loadTexture(texture);
    }

    public int getTextureID(){
        return this.texture;
    }

    public int getVaoID() {
        return vao;
    }

    public int getVertexCount() {
        return vertices;
    }

}