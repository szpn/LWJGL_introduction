#version 460

in vec2 pass_uvs;

out vec4 fragColor;

uniform sampler2D textureSampler;
uniform bool usesTexture;
uniform vec4 meshColor;

void main(){
    if(usesTexture){
        fragColor = texture(textureSampler,pass_uvs);
    } else{
        fragColor = meshColor;
    }

}