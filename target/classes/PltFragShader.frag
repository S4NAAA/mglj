#version 330 core

// A lot of things are unused, just there for later

//flat in vec2 outTextureCoord;
flat in vec4 outColor;
out vec4 fragmentColor;

//uniform vec2 texOffset;

void main() {
  fragmentColor = outColor;
}
