#version 330 core

layout (points) in;
layout (triangle_strip, max_vertices = 4) out;

//TODO: add relative size
out vec2 outTexturePosition;

uniform vec2 size;
uniform vec2 topLeft;
uniform vec2 botRight;

void buildBox(vec4 position) {
  gl_Position = position;
  outTexturePosition = topLeft;
  EmitVertex();
  gl_Position = position + vec4(0.0f, -size.y, 0.0f, 0.0f);
  outTexturePosition = vec2(topLeft.x, botRight.y);
  EmitVertex();
  gl_Position = position + vec4(size.x, 0.0f, 0.0f,  0.0f);
  outTexturePosition = vec2(botRight.x, topLeft.y);
  EmitVertex();
  gl_Position = position + vec4(size.x, -size.y, 0.0f, 0.0f);
  outTexturePosition = vec2(botRight.x, botRight.y);
  EmitVertex();
  EndPrimitive();
}

void main() {
  buildBox(gl_in[0].gl_Position);
}