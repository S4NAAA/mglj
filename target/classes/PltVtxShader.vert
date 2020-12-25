#version 330 core

// A lot of things are unused, just there for later
// TODO: add texture usage and implement a texture generator

layout (location = 0) in vec3 vertexPosition;
layout (location = 1) in vec3 vertexColor;
flat out vec4 outColor;

uniform mat4 pvm;

void main() {
  gl_Position = pvm * vec4(vertexPosition, 1.0f);

  outColor = vec4(vertexColor, 1.0f);
}
