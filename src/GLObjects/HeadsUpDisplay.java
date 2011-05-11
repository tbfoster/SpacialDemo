package GLObjects;

import com.sun.opengl.util.GLUT;
import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

public class HeadsUpDisplay {

    public static GL gl;
    public static GLU glu;
    public static GLUT glut;
    cgFonts fonts = new cgFonts(glut);

    //**************************************************************************
    HeadsUpDisplay(GL vgl, GLU vglu, GLUT vglut)
    {
        gl = vgl;
        glu = vglu;
        glut = vglut;
    }

    //**************************************************************************
    public void draw()
    {
        gl.glPushMatrix();
        fonts.setScale(0.015f, 0.015f, 0.0f);
        gl.glColor3f(1f, 1f, 1f);
        String temp = new Integer(Globals.FPS).toString();
        fonts.renderStrokeString(gl, GLUT.STROKE_MONO_ROMAN, -18, 0, 0, temp);
        gl.glPopMatrix();
    }
    //**************************************************************************
}
//**************************************************************************

