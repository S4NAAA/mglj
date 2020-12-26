package org.sana.mglj.core.VtxObject;

import org.lwjgl.opengl.GL33;

// TODO: -maybe refactor for multi vbo functionality
//       -add texture functionality
//       -add pvm functionality
//       -add implementation to apply a functor on a target of the vbo

public class VtxObject {

    protected final GlVtxArrayObject vao = new GlVtxArrayObject();
    protected final VtxBufObject vbo = new VtxBufObject();
    protected final ElemBufObject ebo = new ElemBufObject();
    protected final int mode;

    // TODO: the layout of the vertex data is shader dependent, prob should do something about that

    public VtxObject(int mode) {
        this.mode = mode;
    }

    public VtxObject drawElements(int length) {
        // GL33.GL_INT doesn't work
        GL33.glDrawElements(this.mode, length, GL33.GL_UNSIGNED_INT, 0);
        return this;
    }

    public VtxObject bindVao() {
        this.vao.bind();
        return this;
    }

    public VtxObject unbindVao() {
        this.vao.unbind();
        return this;
    }

    public VtxObject bindVbo() {
        this.vbo.bind();
        return this;
    }

    public VtxObject unbindVbo() {
        this.vbo.unbind();
        return this;
    }

    public VtxObject bindEbo() {
        this.ebo.bind();
        return this;
    }

    public VtxObject unbindEbo() {
        this.ebo.unbind();
        return this;
    }

    public VtxObject bufferVtxData(float[] vtxData, int usage) {
        this.vbo.bufferData(vtxData, usage);
        return this;
    }

    public VtxObject bufferIdxData(int[] idxData, int usage) {
        this.ebo.bind().bufferData(idxData, usage).unbind();
        return this;
    }

    public VtxObject setVtxAttribLayout(int[] layout, int sliceSize) {

        int offset = 0;
        for (int i = 0; i < layout.length; i++) {
            GL33.glEnableVertexAttribArray(i);
            GL33.glVertexAttribPointer(i, layout[i], GL33.GL_FLOAT,
                    false, sliceSize * 4, (long)offset * 4);

            offset += layout[i];
        }

        return this;
    }

    public void delete() {
       vao.deleteHandle();
       vbo.deleteHandle();
       ebo.deleteHandle();
    }
}
