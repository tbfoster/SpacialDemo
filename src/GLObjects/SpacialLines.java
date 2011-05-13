package GLObjects;

import com.sun.opengl.util.GLUT;
import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

public class SpacialLines extends SpacialObject {
    float EndX, EndY, EndZ;

    public SpacialLines(GL vgl, GLU vglu, GLUT vglut, float vX, float vY, float vZ)
    {
        super(vgl, vglu, vglut, vX, vY, vZ);
    }

    //**************************************************************************
    @Override
    public void draw()
    {
        gl.glBegin(gl.GL_LINES);
        gl.glVertex3f(X, Y, Z); // origin of the line
        gl.glVertex3f(200.0f, 140.0f, 5.0f); // ending point of the line
        gl.glEnd();

    }
    //**************************************************************************
}
