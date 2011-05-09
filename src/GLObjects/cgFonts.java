package GLObjects;

import com.sun.opengl.util.GLUT;
import javax.media.opengl.GL;

public class cgFonts {

    public static GLUT glut;

    //**************************************************************************
    public cgFonts(GLUT vglut)
    {
        glut = vglut;
    }
    //**************************************************************************

    public void renderStrokeString(GL gl, int font, String string)
    {
        // Center Our Text On The Screen
        float width = glut.glutStrokeLength(font, string);
        //gl.glTranslatef(-width / 2f, 0, 0);
        //gl.glTranslatef(6, 0, 0);
        // Render The Text
        for (int i = 0; i < string.length(); i++)
        {
            char c = string.charAt(i);
            glut.glutStrokeCharacter(font, c);
            
        }
        //gl.glTranslatef(0, 0, 1);
        //glut.glutStrokeString(font, "TESTTESTTESTSETSETSETSETSETSETSETSETSETSETSET");
    }
    //**************************************************************************
}
