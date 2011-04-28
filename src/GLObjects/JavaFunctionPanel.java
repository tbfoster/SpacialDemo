package GLObjects;

import java.awt.geom.Rectangle2D;
import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

public class JavaFunctionPanel extends SpacialObject {

    public static DirectionVector dirVector = new DirectionVector(0, 0, 0);
    String functionName = "test";

    //**************************************************************************
    public JavaFunctionPanel(GL vgl, GLU vglu, float vX, float vY, float vZ)
    {
        super(vgl, vglu, vX, vY, vZ);
        //LoadGLTextures();
    }
    //**************************************************************************

    public JavaFunctionPanel(GL vgl, GLU vglu, float vX, float vY, float vZ, String vName)
    {
        super(vgl, vglu, vX, vY, vZ);
        functionName = vName;
        //LoadGLTextures();
    }

    //**************************************************************************
    @Override
    public void compile()
    {
    }
    //**************************************************************************

    @Override
    public void draw()
    {
        float faceSize = 04f;
        float halfFaceSize = faceSize / 2;

        gl.glPushMatrix();
        gl.glTranslatef(X, Y, Z);
        rotateX();
        rotateY();
        rotateZ();

        gl.glColor3f(R, G, B);
        Globals.renderer.begin3DRendering();
        gl.glDisable(GL.GL_DEPTH_TEST);
        //gl.glEnable(GL.GL_CULL_FACE);
        Rectangle2D bounds = Globals.renderer.getBounds(functionName);
        float w = (float) bounds.getWidth();
        float h = (float) bounds.getHeight();
        Globals.renderer.draw3D(functionName, w / -2.0f * Globals.textScaleFactor, h / -2.0f * Globals.textScaleFactor, halfFaceSize, Globals.textScaleFactor);
        Globals.renderer.end3DRendering();

        gl.glPopMatrix();
    }
//**************************************************************************
}
