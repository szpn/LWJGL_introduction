#version 460

layout (location=0) in vec3 position;
layout (location=1) in vec2 uvs;
layout (location=2) in vec3 vertexNormal;

out vec2 pass_uvs;
out vec3 mvVertexNormal;
out vec3 mvVertexPos;

uniform mat4 projectionMatrix;
uniform mat4 worldMatrix;

void main(void){
    vec4 mvPos = worldMatrix * vec4(position, 1.0);
	gl_Position = projectionMatrix * mvPos;
	pass_uvs = uvs;
	mvVertexNormal = normalize(worldMatrix * vec4(vertexNormal, 0.0)).xyz;
	mvVertexPos = mvPos.xyz;
}