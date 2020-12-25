package org.sana.mglj.plot;

import org.lwjgl.opengl.GL33;
import org.sana.mglj.core.Shader.FragShader;
import org.sana.mglj.core.Program.Program;
import org.sana.mglj.core.Shader.Shader;
import org.sana.mglj.core.Shader.VtxShader;

public class PltProgram extends Program {
    public PltProgram() {
        Shader vtxShader = new VtxShader();
        Shader fragShader = new FragShader();

        vtxShader.source("src/main/resources/PltVtxShader.vert").compile();
        if (vtxShader.getStatus() == GL33.GL_FALSE) {
            throw new IllegalStateException(vtxShader.getLog());
        }

        fragShader.source("src/main/resources/PltFragShader.frag").compile();
        if (fragShader.getStatus() == GL33.GL_FALSE) {
            throw new IllegalStateException(fragShader.getLog());
        }

        this.attach(vtxShader).attach(fragShader).link();

        if (getStatus() == GL33.GL_FALSE) {
            throw new IllegalStateException(getLog());
        }
    }
}
