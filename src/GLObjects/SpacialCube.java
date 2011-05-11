package GLObjects;

import java.awt.geom.Rectangle2D;
import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

public class SpacialCube extends SpacialObject {

    public SpacialCube(GL vgl, GLU vglu, float vX, float vY, float vZ)
    {
        super(vgl, vglu, vX, vY, vZ);
    }

    //**************************************************************************
    @Override
    public void draw()
    {
        gl.glPushMatrix();
        gl.glTranslatef(X, Y, Z);
        rotateX();
        rotateY();
        rotateZ();

        gl.glBegin(gl.GL_QUADS);

        gl.glColor3f(.5f, .5f, .5f);
        gl.glNormal3f(0.0f, 0.0f, 1.0f);               // Front Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, 1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, 1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, 1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, 1.0f);


        gl.glColor3f(.1f, .5f, .9f);
        gl.glNormal3f(0.0f, 0.0f, -1.0f);                // Back Face
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);

        gl.glNormal3f(0.0f, 1.0f, 0.0f);                 // Top Face
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, 1.0f, 1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, 1.0f, 1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);

        gl.glNormal3f(0.0f, -1.0f, 0.0f);              // Bottom Face
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, 1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, 1.0f);

        gl.glNormal3f(1.0f, 0.0f, 0.0f);               // Right Face
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, -1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(1.0f, 1.0f, 1.0f);
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(1.0f, -1.0f, 1.0f);

        gl.glNormal3f(-1.0f, 0.0f, 0.0f);               // Left Face
        gl.glTexCoord2f(0.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        gl.glTexCoord2f(1.0f, 0.0f);
        gl.glVertex3f(-1.0f, -1.0f, 1.0f);
        gl.glTexCoord2f(1.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, 1.0f);
        gl.glTexCoord2f(0.0f, 1.0f);
        gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        gl.glEnd();

        drawFace(gl, 1.0f, 0.2f, 0.2f, 0.8f, "Top");
        gl.glPopMatrix();
    }

    //**************************************************************************
    private void drawFace(GL gl, float faceSize, float r, float g, float b, String text)
    {
        //float halfFaceSize = faceSize / 2;
        //gl.glColor3f(r, g, b);
        //Globals.renderer.begin3DRendering();
        //gl.glDisable(GL.GL_DEPTH_TEST);
        //gl.glEnable(GL.GL_CULL_FACE);
        //Rectangle2D bounds = Globals.renderer.getBounds(text);
        //float w = (float) bounds.getWidth();
        //float h = (float) bounds.getHeight();
        //Globals.renderer.draw3D(text, w / -2.0f * Globals.textScaleFactor, h / -2.0f * Globals.textScaleFactor, halfFaceSize, Globals.textScaleFactor);
        //Globals.renderer.end3DRendering();
    }
    //**************************************************************************
}
