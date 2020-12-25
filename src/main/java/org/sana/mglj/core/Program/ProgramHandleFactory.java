package org.sana.mglj.core.Program;

import org.lwjgl.opengl.GL33;
import org.sana.mglj.core.Handle.HandleFactory;

public class ProgramHandleFactory implements HandleFactory {

    @Override
    public int genHandle() {
        return GL33.glCreateProgram();
    }
}
