package org.worldobject;

import org.render.Texture;

public class Mesh {

    private int vao;
    private int vertices;
    private int texture = 0;



    public Mesh(int vao, int vertex) {
        this.vao = vao;
        this.vertices = vertex;
    }

    public Mesh addTexture(String texture) {
        this.texture = Texture.loadTexture(texture);
        return this;
    }

    public int getTexture(){
        return this.texture;
    }

    public int getVaoID() {
        return vao;
    }

    public int getVertexCount() {
        return vertices;
    }

}