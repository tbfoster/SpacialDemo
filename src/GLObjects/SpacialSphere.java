package GLObjects;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

public class SpacialSphere extends SpacialObject {

    public GLUquadric quadric;

 public SpacialSphere(float vX, float vY, float vZ)
    {
        super(vX, vY, vZ);
        quadric = Globals.glu.gluNewQuadric();
        Globals.glu.gluQuadricNormals(quadric, GLU.GLU_SMOOTH);  // Create Smooth Normals (NEW)
        Globals.glu.gluQuadricTexture(quadric, true);
    }

    //**************************************************************************

    @Override
    public void draw()
    {
        Globals.gl.glPushMatrix();
        Globals.gl.glTranslatef(X, Y, Z);
        Globals.gl.glColor3f(R, G, B);
        rotateX();
        rotateY();
        rotateZ();
        Globals.glu.gluQuadricNormals(quadric, GLU.GLU_SMOOTH);  // Create Smooth Normals (NEW)
        Globals.glu.gluQuadricTexture(quadric, true);            // Create Texture Coords (NEW)
        Globals.glu.gluSphere(quadric, .1f, 32, 32);
        Globals.gl.glPopMatrix();
        /*
        Globals.renderer.begin3DRendering();
        Globals.gl.glDisable(GL.GL_DEPTH_TEST);
        //gl.glEnable(GL.GL_CULL_FACE);
        Globals.renderer.draw3D("[" + X + ":" + Y + ":" + Z +"]", X, Y+.5f, Z, .003f);
        Globals.renderer.end3DRendering();
         * 
         */
    }

    //**************************************************************************
}
