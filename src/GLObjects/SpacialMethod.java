package GLObjects;

import com.sun.opengl.util.GLUT;
import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import java.util.Scanner;

public final class SpacialMethod extends SpacialObject {

    public String displayMethod = "test";
    public float scaleX = 1.0f;
    public float scaleY = 1.0f;
    public float scaleZ = 1.0f;
    cgFonts fonts;
    SpacialPlane backPlane;

    //**************************************************************************
    public SpacialMethod(GL vgl, GLU vglu, float vX, float vY, float vZ)
    {
        super(vgl, vglu, vX, vY, vZ);
    }
    //**************************************************************************

    public SpacialMethod(GL vgl, GLU vglu, GLUT vglut, float vX, float vY, float vZ, String vDisplayMethod)
    {
        super(vgl, vglu, vX, vY, vZ);
        glut = vglut;
        fonts = new cgFonts(glut);
        displayMethod = vDisplayMethod;
        backPlane = new SpacialPlane(gl, glu, vX, vY, vZ);
        backPlane.LoadGLTextures("/home/tbfoster/NetBeansProjects/SpacialDemo/data/NeHe.png");
        backPlane.setColor(1f, 1f, 0f);
        backPlane.setSize(5, 5);

        compile();
    }

    //**************************************************************************
    @Override
    public void compile()
    {
        float y = Y;
        gl.glPushMatrix();

        genListID = gl.glGenLists(Globals.genListIndex);
        gl.glNewList(genListID, gl.GL_COMPILE);

        fonts.setColor(0, 1.0f, 0);
        fonts.renderStrokeString(gl, GLUT.STROKE_MONO_ROMAN, X, Y, Z, displayMethod);

        backPlane.setPosition(X, Y-1, Z - .5f);
        backPlane.setSize(8, 1);
        backPlane.draw();

        gl.glEndList();
        gl.glPopMatrix();
    }
    //**************************************************************************

    @Override
    public void draw()
    {
        if (active)
        {
            gl.glCallList(genListID);
        }
    }
    //**************************************************************************
}
