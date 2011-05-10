package GLObjects;

import com.sun.opengl.util.GLUT;
import javax.media.opengl.GL;

public class cgFonts {

    public static GLUT glut;
    public float scaleX = 0.005f;
    public float scaleY = 0.005f;
    public float scaleZ = 0.0f;

    //**************************************************************************
    public cgFonts(GLUT vglut)
    {
        glut = vglut;
    }
    //**************************************************************************

    public void setScale(float vX, float vY, float vZ)
    {
        scaleX = vX;
        scaleY = vY;
        scaleZ = vZ;
    }
    //**************************************************************************

    public void renderStrokeString(GL gl, int font, float vX, float vY, float vZ, String string)
    {
        gl.glPushMatrix();
        gl.glTranslatef(vX, vY, vZ);
        gl.glScalef(scaleX, scaleY, scaleZ);
        gl.glColor3f(1f, 0, 1f);

        float width = glut.glutStrokeLength(font, string);
        for (int i = 0; i < string.length(); i++)
        {
            char c = string.charAt(i);
            glut.glutStrokeCharacter(font, c);

        }
        gl.glPopMatrix();
    }
    //**************************************************************************
}
