package org.sana.mglj.core.VtxObject;

import org.lwjgl.opengl.GL33;

// TODO: -maybe refactor for multi vbo functionality
//       -add texture functionality
//       -add pvm functionality
//       -add implementation to apply a functor on a target of the vbo

public class VtxObject extends VtxObjData {

    protected final ArrObject vao = new ArrObject();
    protected final BufObject vbo = new BufObject();
    protected final BufObject ebo = new BufObject();
    protected final int mode;

    public VtxObject(int[] sliceData, int mode) {
        super(sliceData);
        this.mode = mode;
    }

    public VtxObject(VtxDataFactory vtxDataFactory, IdxDataFactory idxDataFactory, int[] sliceData, int mode) {
        super(vtxDataFactory, idxDataFactory, sliceData);
        this.mode = mode;
    }

    public void draw() {
        GL33.glBindVertexArray(this.vao.getHandle());
        GL33.glEnableVertexAttribArray(0);
        GL33.glBindBuffer(GL33.GL_ELEMENT_ARRAY_BUFFER, this.ebo.getHandle());

        // GL33.GL_INT doesn't work
        GL33.glDrawElements(this.mode, this.idxData.length, GL33.GL_UNSIGNED_INT, 0);

        GL33.glBindBuffer(GL33.GL_ELEMENT_ARRAY_BUFFER, 0);
        GL33.glBindVertexArray(0);
    }

    public void bufferData() {
        GL33.glBindVertexArray(this.vao.getHandle());

        GL33.glBindBuffer(GL33.GL_ARRAY_BUFFER, this.vbo.getHandle());
        GL33.glBufferData(GL33.GL_ARRAY_BUFFER, this.vtxData, GL33.GL_DYNAMIC_DRAW);


        int offset = 0;
        for (int i = 0; i < layoutData.length; i++) {
            GL33.glEnableVertexAttribArray(i);
            GL33.glVertexAttribPointer(i, layoutData[i], GL33.GL_FLOAT,
                    false, sliceSize * 4, (long)offset * 4);

            offset += layoutData[i];
        }

        GL33.glBindVertexArray(0);
        GL33.glBindBuffer(GL33.GL_ARRAY_BUFFER, 0);

        GL33.glBindBuffer(GL33.GL_ELEMENT_ARRAY_BUFFER, this.ebo.getHandle());
        GL33.glBufferData(GL33.GL_ELEMENT_ARRAY_BUFFER, this.idxData, GL33.GL_DYNAMIC_DRAW);

        GL33.glBindBuffer(GL33.GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    public void delete() {
       vao.deleteHandle();
       vbo.deleteHandle();
       ebo.deleteHandle();
    }
/*
  public void updateBuffer() {
    GL33.glBindBuffer(GL33.GL_ARRAY_BUFFER, this.vbo);

    Objects.requireNonNull(GL33.glMapBuffer(GL33.GL_ARRAY_BUFFER, GL33.GL_WRITE_ONLY))
        .asFloatBuffer().put(this.vtxData);

    GL33.glUnmapBuffer(GL33.GL_ARRAY_BUFFER);
    GL33.glBindBuffer(GL33.GL_ARRAY_BUFFER, 0);
  }

 */
}
