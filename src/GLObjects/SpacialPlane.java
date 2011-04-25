package GLObjects;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

public class SpacialPlane extends SpacialObject {

    public SpacialPlane(GL vgl, GLU vglu, float vX, float vY, float vZ)
    {
        super(vgl, vglu, vX, vY, vZ);
    }

    //**************************************************************************

    @Override
    public void draw()
    {
        float size = 1;
        gl.glPushMatrix();

        gl.glTranslatef(X, Y, Z);
        gl.glColor3f(R, Z, B);
        rotateX();
        rotateY();
        rotateZ();
        gl.glBegin(GL.GL_QUADS);
        gl.glVertex3f(-size, -size, size);
        gl.glVertex3f(size, -size, size);
        gl.glVertex3f(size, size, size);
        gl.glVertex3f(-size, size, size);
        gl.glEnd();

        gl.glPopMatrix();
    }

    //**************************************************************************
}
