package org.scene.worldobject.objloader;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.scene.worldobject.Mesh;
import org.scene.worldobject.MeshLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class OBJLoader {

    public static Mesh OBJtoMesh(String filename) {
        List<String> lines = readLinesFromFile(filename);

        List<Vector3f> vertices = new ArrayList<>();
        List<Vector2f> textures = new ArrayList<>();
        List<Vector3f> normals = new ArrayList<>();
        List<Face> faces = new ArrayList<>();

        for (String line : lines) {
            String[] tokens = line.split("\\s+");
            switch (tokens[0]) {
                case "v":
                    vertices.add(parseVector3f(tokens));
                    break;
                case "vt":
                    textures.add(parseVector2f(tokens));
                    break;
                case "vn":
                    normals.add(parseVector3f(tokens));
                    break;
                case "f":
                    faces.add(parseFace(tokens));
                    break;
                default:
                    // Ignore other lines
                    break;
            }
        }
        return reorderLists(vertices, textures, normals, faces);
    }

    private static Vector3f parseVector3f(String[] tokens) {
        return new Vector3f(
                Float.parseFloat(tokens[1]),
                Float.parseFloat(tokens[2]),
                Float.parseFloat(tokens[3])
        );
    }

    private static Vector2f parseVector2f(String[] tokens) {
        return new Vector2f(
                Float.parseFloat(tokens[1]),
                Float.parseFloat(tokens[2])
        );
    }

    private static Face parseFace(String[] tokens) {
        return new Face(tokens[1], tokens[2], tokens[3]);
    }

    private static List<String> readLinesFromFile(String filename) {
        try {
            Path filePath = Path.of("res", filename);
            return Files.readAllLines(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + filename, e);
        }
    }

    private static Mesh reorderLists(List<Vector3f> posList, List<Vector2f> textCoordList,
                                     List<Vector3f> normList, List<Face> facesList) {

        List<Integer> indices = new ArrayList<>();
        float[] posArr = new float[posList.size() * 3];
        float[] textCoordArr = new float[posList.size() * 2];
        float[] normArr = new float[posList.size() * 3];

        for (int i = 0; i < posList.size(); i++) {
            Vector3f pos = posList.get(i);
            posArr[i * 3] = pos.x;
            posArr[i * 3 + 1] = pos.y;
            posArr[i * 3 + 2] = pos.z;
        }

        for (Face face : facesList) {
            IdxGroup[] faceVertexIndices = face.getFaceVertexIndices();
            for (IdxGroup indValue : faceVertexIndices) {
                processFaceVertex(indValue, textCoordList, normList,
                        indices, textCoordArr, normArr);
            }
        }

        int[] indicesArr = indices.stream().mapToInt(Integer::intValue).toArray();
        return MeshLoader.createTexturedMeshWithNormals(posArr, indicesArr, textCoordArr, normArr);
    }

    private static void processFaceVertex(IdxGroup indices, List<Vector2f> textCoordList,
                                          List<Vector3f> normList, List<Integer> indicesList,
                                          float[] texCoordArr, float[] normArr) {

        int posIndex = indices.idxPos;
        indicesList.add(posIndex);

        if (indices.idxTextCoord >= 0) {
            Vector2f textCoord = textCoordList.get(indices.idxTextCoord);
            texCoordArr[posIndex * 2] = textCoord.x;
            texCoordArr[posIndex * 2 + 1] = 1 - textCoord.y;
        }
        if (indices.idxVecNormal >= 0) {
            Vector3f vecNorm = normList.get(indices.idxVecNormal);
            normArr[posIndex * 3] = vecNorm.x;
            normArr[posIndex * 3 + 1] = vecNorm.y;
            normArr[posIndex * 3 + 2] = vecNorm.z;
        }
    }
}