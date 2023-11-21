package org.worldobject;

import org.render.Texture;

public class Mesh {

    private int vao;
    private int vertices;
    private MeshMaterial material;



    public Mesh(int vao, int vertex) {
        this.vao = vao;
        this.vertices = vertex;
        this.material = new MeshMaterial();
    }

    public Mesh(int vao, int vertex, MeshMaterial material){
        this.vao = vao;
        this.vertices = vertex;
        this.material = material;
    }

    public MeshMaterial getMaterial(){
        return this.material;
    }

    public void setMaterial(MeshMaterial material) {
        this.material = material;
    }

    public int getVaoID() {
        return vao;
    }

    public int getVertexCount() {
        return vertices;
    }

}