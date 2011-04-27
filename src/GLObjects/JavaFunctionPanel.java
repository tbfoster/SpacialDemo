package GLObjects;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

public class JavaFunctionPanel extends SpacialObject {

    public static DirectionVector dirVector = new DirectionVector(0, 0, 0);

    //**************************************************************************
    public JavaFunctionPanel(GL vgl, GLU vglu, float vX, float vY, float vZ)
    {
        super(vgl, vglu, vX, vY, vZ);
        //LoadGLTextures();
    }
    //**************************************************************************

    public void compile()
    {
    }
    //**************************************************************************

    @Override
    public void draw()
    {
    }
    //**************************************************************************
}
