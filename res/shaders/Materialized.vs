#version 460

layout (location=0) in vec3 position;
layout (location=1) in vec2 uvs;

out vec2 pass_uvs;

uniform mat4 projectionMatrix;
uniform mat4 worldMatrix;

void main(void){
	gl_Position = projectionMatrix * worldMatrix * vec4(position, 1.0);
	pass_uvs = uvs;
}