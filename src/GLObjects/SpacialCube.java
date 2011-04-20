package GLObjects;

import java.awt.geom.Rectangle2D;
import javax.media.opengl.GL;

public class SpacialCube extends SpacialObject {

    public SpacialCube(float vX, float vY, float vZ)
    {
        super(vX, vY, vZ);
    }

    //**************************************************************************
    @Override
    public void draw()
    {
        Globals.gl.glPushMatrix();
        Globals.gl.glTranslatef(X, Y, Z);
        rotateX();
        rotateY();
        rotateZ();

        Globals.gl.glBegin(Globals.gl.GL_QUADS);

        Globals.gl.glColor3f(.5f, .5f, .5f);
        Globals.gl.glNormal3f(0.0f, 0.0f, 1.0f);               // Front Face
        Globals.gl.glTexCoord2f(0.0f, 0.0f);
        Globals.gl.glVertex3f(-1.0f, -1.0f, 1.0f);
        Globals.gl.glTexCoord2f(1.0f, 0.0f);
        Globals.gl.glVertex3f(1.0f, -1.0f, 1.0f);
        Globals.gl.glTexCoord2f(1.0f, 1.0f);
        Globals.gl.glVertex3f(1.0f, 1.0f, 1.0f);
        Globals.gl.glTexCoord2f(0.0f, 1.0f);
        Globals.gl.glVertex3f(-1.0f, 1.0f, 1.0f);


        Globals.gl.glColor3f(.1f, .5f, .9f);
        Globals.gl.glNormal3f(0.0f, 0.0f, -1.0f);                // Back Face
        Globals.gl.glTexCoord2f(1.0f, 0.0f);
        Globals.gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        Globals.gl.glTexCoord2f(1.0f, 1.0f);
        Globals.gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        Globals.gl.glTexCoord2f(0.0f, 1.0f);
        Globals.gl.glVertex3f(1.0f, 1.0f, -1.0f);
        Globals.gl.glTexCoord2f(0.0f, 0.0f);
        Globals.gl.glVertex3f(1.0f, -1.0f, -1.0f);

        Globals.gl.glNormal3f(0.0f, 1.0f, 0.0f);                 // Top Face
        Globals.gl.glTexCoord2f(0.0f, 1.0f);
        Globals.gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        Globals.gl.glTexCoord2f(0.0f, 0.0f);
        Globals.gl.glVertex3f(-1.0f, 1.0f, 1.0f);
        Globals.gl.glTexCoord2f(1.0f, 0.0f);
        Globals.gl.glVertex3f(1.0f, 1.0f, 1.0f);
        Globals.gl.glTexCoord2f(1.0f, 1.0f);
        Globals.gl.glVertex3f(1.0f, 1.0f, -1.0f);

        Globals.gl.glNormal3f(0.0f, -1.0f, 0.0f);              // Bottom Face
        Globals.gl.glTexCoord2f(1.0f, 1.0f);
        Globals.gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        Globals.gl.glTexCoord2f(0.0f, 1.0f);
        Globals.gl.glVertex3f(1.0f, -1.0f, -1.0f);
        Globals.gl.glTexCoord2f(0.0f, 0.0f);
        Globals.gl.glVertex3f(1.0f, -1.0f, 1.0f);
        Globals.gl.glTexCoord2f(1.0f, 0.0f);
        Globals.gl.glVertex3f(-1.0f, -1.0f, 1.0f);

        Globals.gl.glNormal3f(1.0f, 0.0f, 0.0f);               // Right Face
        Globals.gl.glTexCoord2f(1.0f, 0.0f);
        Globals.gl.glVertex3f(1.0f, -1.0f, -1.0f);
        Globals.gl.glTexCoord2f(1.0f, 1.0f);
        Globals.gl.glVertex3f(1.0f, 1.0f, -1.0f);
        Globals.gl.glTexCoord2f(0.0f, 1.0f);
        Globals.gl.glVertex3f(1.0f, 1.0f, 1.0f);
        Globals.gl.glTexCoord2f(0.0f, 0.0f);
        Globals.gl.glVertex3f(1.0f, -1.0f, 1.0f);

        Globals.gl.glNormal3f(-1.0f, 0.0f, 0.0f);               // Left Face
        Globals.gl.glTexCoord2f(0.0f, 0.0f);
        Globals.gl.glVertex3f(-1.0f, -1.0f, -1.0f);
        Globals.gl.glTexCoord2f(1.0f, 0.0f);
        Globals.gl.glVertex3f(-1.0f, -1.0f, 1.0f);
        Globals.gl.glTexCoord2f(1.0f, 1.0f);
        Globals.gl.glVertex3f(-1.0f, 1.0f, 1.0f);
        Globals.gl.glTexCoord2f(0.0f, 1.0f);
        Globals.gl.glVertex3f(-1.0f, 1.0f, -1.0f);
        Globals.gl.glEnd();

        drawFace(Globals.gl, 1.0f, 0.2f, 0.2f, 0.8f, "Top");
        Globals.gl.glPopMatrix();
    }

    //**************************************************************************
    private void drawFace(GL gl, float faceSize, float r, float g, float b, String text)
    {
        float halfFaceSize = faceSize / 2;
        gl.glColor3f(r, g, b);
        Globals.renderer.begin3DRendering();
        gl.glDisable(GL.GL_DEPTH_TEST);
        //gl.glEnable(GL.GL_CULL_FACE);
        Rectangle2D bounds = Globals.renderer.getBounds(text);
        float w = (float) bounds.getWidth();
        float h = (float) bounds.getHeight();
        Globals.renderer.draw3D(text, w / -2.0f * Globals.textScaleFactor, h / -2.0f * Globals.textScaleFactor, halfFaceSize, Globals.textScaleFactor);
        Globals.renderer.end3DRendering();
    }
    //**************************************************************************
}
