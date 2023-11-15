#version 330

layout (location=0) in vec3 position;
layout (location=1) in vec3 in_color;

out vec3 exColour;

uniform mat4 projectionMatrix;
uniform mat4 worldMatrix;

void main()
{
    exColour = in_color;
    gl_Position = projectionMatrix * worldMatrix * vec4(position, 1.0);
}