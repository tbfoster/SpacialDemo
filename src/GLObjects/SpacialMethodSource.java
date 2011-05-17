package GLObjects;

import com.sun.opengl.util.GLUT;
import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import java.util.Scanner;

public final class SpacialMethodSource extends SpacialObject {

    float TEXT_BACKGROUND_OFFSET = 0.5f;
    float TEXT_SELECT_OFFSET = .3f;
    public String methodCodeBlock = "test";
    public String methodName = "test";
    public float scaleX = 1.0f;
    public float scaleY = 1.0f;
    public float scaleZ = 1.0f;
    float windowX, windowY, windowZ;
    float selectX, selectY, selectZ;
    float backgroundWidth = 45;  // 50 handles about 80 characters
    float backgroundHeight;
    float endY = Y;
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
        endY = Y;
        x = 0f;
        SpacialSphere tSphere = new SpacialSphere(gl, glu, glut, X, Y, Z - .5f);
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
            fonts.renderStrokeString(gl, GLUT.STROKE_MONO_ROMAN, X, endY, Z, temp);  // for now, trim to 80 - TODO
            endY = endY - 1;
            if (x < temp.length())
            {
                x = temp.length();
            }
        }
        windowX = X - .5f;
        windowY = endY + 1f;
        windowZ = Z - TEXT_BACKGROUND_OFFSET;
        backgroundHeight = Y - endY;
        selectX = windowX + .2f;
        selectY = Y - .5f;
        selectZ = Z - TEXT_SELECT_OFFSET;

        backPlane.setPosition(windowX, windowY, windowZ);
        backPlane.setSize(backgroundWidth - .2f, backgroundHeight);  // 50 handles about 
        backPlane.draw();

        highlightPlane.setPosition(selectX, selectY, selectZ);
        highlightPlane.setSize(backgroundWidth - TEXT_BACKGROUND_OFFSET, 1.5f);  

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
            if (selected)
            {
                highlightPlane.draw();
            }
        }
    }
    //**************************************************************************
}
