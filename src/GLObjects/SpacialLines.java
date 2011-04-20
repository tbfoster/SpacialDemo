/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GLObjects;

public class SpacialLines extends SpacialObject {
    float EndX, EndY, EndZ;

    public SpacialLines(float vX, float vY, float vZ)
    {
        super(vX, vY, vZ);
    }

    //**************************************************************************
    @Override
    public void draw()
    {
        Globals.gl.glBegin(Globals.gl.GL_LINES);
        Globals.gl.glVertex3f(X, Y, Z); // origin of the line
        Globals.gl.glVertex3f(200.0f, 140.0f, 5.0f); // ending point of the line
        Globals.gl.glEnd();

    }
    //**************************************************************************
}
