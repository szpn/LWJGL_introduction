#version 460

in vec2 pass_uvs;

out vec4 fragColor;

uniform sampler2D textureSampler;

void main(){
	fragColor = texture(textureSampler,pass_uvs);
}