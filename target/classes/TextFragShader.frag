#version 330 core

in vec2 outTexturePosition;
out vec4 fragColor;

//uniform sampler2D tex;

void main() {
  //fragColor = vec4(outTexturePosition, 1.0f, 1.0f);
  fragColor = vec4(1.0f, 1.0f, 1.0f, 1.0f);
}
