package GLObjects;

import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureData;
import com.sun.opengl.util.texture.TextureIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import javax.imageio.ImageIO;
import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

public class SpacialSphere extends SpacialObject {

    public GLUquadric quadric;
    private static ByteBuffer imageBuf1;
    public static Texture text;

    //**************************************************************************
    public SpacialSphere(GL vgl, GLU vglu, float vX, float vY, float vZ)
    {
        super(vgl, vglu, vX, vY, vZ);
        quadric = glu.gluNewQuadric();
        glu.gluQuadricNormals(quadric, GLU.GLU_SMOOTH);  // Create Smooth Normals (NEW)
        glu.gluQuadricTexture(quadric, true);
        //LoadGLTextures();
    }
    //**************************************************************************

    public void compile()
    {
        genListID = gl.glGenLists(Globals.genListIndex);
        gl.glNewList(genListID, gl.GL_COMPILE);

        gl.glColor3f(R, G, B);
        text.setTexParameteri(gl.GL_TEXTURE_MIN_FILTER, gl.GL_LINEAR);
        text.setTexParameteri(gl.GL_TEXTURE_MAG_FILTER, gl.GL_LINEAR);
        text.setTexParameterf(gl.GL_TEXTURE_WRAP_S, gl.GL_REPEAT);
        text.setTexParameterf(gl.GL_TEXTURE_WRAP_T, gl.GL_REPEAT);
        text.disable();
        gl.glTexEnvf(gl.GL_TEXTURE_ENV, gl.GL_TEXTURE_ENV_MODE, gl.GL_DECAL);
        glu.gluQuadricNormals(quadric, GLU.GLU_SMOOTH);  // Create Smooth Normals (NEW)
        glu.gluQuadricTexture(quadric, true);            // Create Texture Coords (NEW)
        glu.gluSphere(quadric, .02f, 32, 32);

        gl.glEndList();
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
        gl.glCallList(genListID);

        gl.glPopMatrix();
    }
    //**************************************************************************

    public static void LoadGLTextures(String vFilename)
    {
        BufferedImage image = new BufferedImage(256, 256, BufferedImage.TYPE_INT_ARGB);
        try
        {
            image = ImageIO.read(new File(vFilename));

        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
        text = TextureIO.newTexture(image, false);
    }
    //**************************************************************************
}
