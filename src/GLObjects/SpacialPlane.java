package GLObjects;

import javax.media.opengl.GL;

public class SpacialPlane extends SpacialObject {

    public SpacialPlane(float vX, float vY, float vZ)
    {
        super(vX, vY, vZ);
    }

    //**************************************************************************

    @Override
    public void draw()
    {
        float size = 1;
        Globals.gl.glPushMatrix();

        Globals.gl.glTranslatef(X, Y, Z);
        Globals.gl.glColor3f(R, Z, B);
        rotateX();
        rotateY();
        rotateZ();
        Globals.gl.glBegin(GL.GL_QUADS);
        Globals.gl.glVertex3f(-size, -size, size);
        Globals.gl.glVertex3f(size, -size, size);
        Globals.gl.glVertex3f(size, size, size);
        Globals.gl.glVertex3f(-size, size, size);
        Globals.gl.glEnd();

        Globals.gl.glPopMatrix();
    }

    //**************************************************************************
}
