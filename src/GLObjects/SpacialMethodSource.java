package GLObjects;

import com.sun.opengl.util.GLUT;
import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import java.util.Scanner;

public final class SpacialMethodSource extends SpacialObject {

    public String methodCodeBlock = "test";
    public String methodName = "test";
    public float scaleX = 1.0f;
    public float scaleY = 1.0f;
    public float scaleZ = 1.0f;
    float y = Y;
    float x = 0f;
    cgFonts fonts;
    SpacialPlane backPlane;
    SpacialPlane highlightPlane;

    //**************************************************************************
    public SpacialMethodSource(GL vgl, GLU vglu, GLUT vglut, float vX, float vY, float vZ)
    {
        super(vgl, vglu, vglut, vX, vY, vZ);
    }
    //**************************************************************************

    public SpacialMethodSource(GL vgl, GLU vglu, GLUT vglut, float vX, float vY, float vZ, String vMetholdName, String vMethodCodeBlock)
    {
        super(vgl, vglu, vglut, vX, vY, vZ);
        fonts = new cgFonts(glut);
        methodCodeBlock = vMethodCodeBlock;
        methodName = vMetholdName;
        backPlane = new SpacialPlane(gl, glu, glut, vX, vY, vZ);
        backPlane.LoadGLTextures("/home/tbfoster/NetBeansProjects/SpacialDemo/data/NeHe2.png");
        backPlane.setColor(1f, 1f, 0f);
        backPlane.setSize(5, 5);

        highlightPlane = new SpacialPlane(gl, glu, glut, vX, vY, vZ);
        highlightPlane.LoadGLTextures("/home/tbfoster/NetBeansProjects/SpacialDemo/data/01_St_kp.jpg");
        highlightPlane.setColor(1f, 1f, 0f);
        highlightPlane.setSize(5, 5);
    }

    //**************************************************************************
    @Override
    public void compile()
    {
        y = Y;
        x = 0f;
          SpacialSphere tSphere = new SpacialSphere(gl, glu, glut, X, Y, Z-.3f);
            tSphere.setColor(1f, 1f, 1f);
            tSphere.compile();
            Universe.objectList.add(tSphere);
            
        gl.glPushMatrix();

        genListID = gl.glGenLists(Globals.genListIndex);
        gl.glNewList(genListID, gl.GL_COMPILE);

        fonts.setColor(0, 1.0f, 0);
        Scanner in = new Scanner(methodCodeBlock);
        while (in.hasNext())
        {
            String temp = in.nextLine();
            if (temp.length() > 80)
            {
                temp = temp.substring(1, 80);
            }
            fonts.renderStrokeString(gl, GLUT.STROKE_MONO_ROMAN, X, y, Z, temp);  // for now, trim to 80 - TODO
            y = y - 1;
            if (x < temp.length())
            {
                x = temp.length();
            }
        }

        backPlane.setPosition(X - .5f, y + 1f, Z - .5f);
        backPlane.setSize((Y - y), 45);  // 50 handles about 
        backPlane.draw();

        //if (selected)
        {
          
        }

        gl.glEndList();
        gl.glPopMatrix();
    }
    //**************************************************************************

    @Override
    public void draw()
    {
        if (active)
        {
            gl.glCallList(genListID);
        }
        if (selected)
        {
            highlightPlane.setPosition(X - .5f, y + 1f, Z - .3f);
            highlightPlane.setSize(1, 45);  // 50 handles about 
            highlightPlane.draw();
        }
    }
    //**************************************************************************
}
