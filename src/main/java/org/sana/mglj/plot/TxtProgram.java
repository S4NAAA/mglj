package org.sana.mglj.plot;

import org.lwjgl.opengl.GL33;
import org.sana.mglj.core.Program.Program;
import org.sana.mglj.core.Shader.FragShader;
import org.sana.mglj.core.Shader.GeomShader;
import org.sana.mglj.core.Shader.Shader;
import org.sana.mglj.core.Shader.VtxShader;

public class TxtProgram extends Program {
    public TxtProgram() {
        Shader vtxShader = new VtxShader();
        Shader geomShader = new GeomShader();
        Shader fragShader = new FragShader();

        vtxShader.source("src/main/resources/TextVtxShader.vert").compile();
        if (vtxShader.getStatus() == GL33.GL_FALSE) {
            throw new IllegalStateException(vtxShader.getLog());
        }

        geomShader.source("src/main/resources/TextGeomShader.geom").compile();
        if (geomShader.getStatus() == GL33.GL_FALSE) {
            throw new IllegalStateException(geomShader.getLog());
        }

        fragShader.source("src/main/resources/TextFragShader.frag").compile();
        if (fragShader.getStatus() == GL33.GL_FALSE) {
            throw new IllegalStateException(fragShader.getLog());
        }

        this.attach(vtxShader).attach(geomShader).attach(fragShader).link();

        vtxShader.deleteHandle();
        geomShader.deleteHandle();
        fragShader.deleteHandle();

        if (getStatus() == GL33.GL_FALSE) {
            throw new IllegalStateException(getLog());
        }
    }
}
