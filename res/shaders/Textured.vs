#version 460

in vec3 position;
in vec2 uvs;

out vec2 pass_uvs;

uniform mat4 projection;

void main(void){
	gl_Position = projection * vec4(position, 1.0);
	pass_uvs = uvs;
}