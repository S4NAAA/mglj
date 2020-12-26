#version 330 core

layout (location = 0) in vec3 textPosition;

uniform mat4 pvm;

void main() {
  gl_Position = pvm * vec4(textPosition, 1.0f);
}
