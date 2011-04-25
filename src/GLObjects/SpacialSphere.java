package GLObjects;

import com.sun.opengl.util.texture.Texture;
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

    private static Object getLogger(String name)
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    int genList;
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
        LoadGLTextures();
    }
    //**************************************************************************

    public void compile()
    {
        genList = gl.glGenLists(Globals.genListIndex);
        gl.glNewList(genList, gl.GL_COMPILE);
        //gl.glBindTexture(gl.GL_TEXTURE_2D, texture);
        gl.glEnable(gl.GL_TEXTURE_2D);
        gl.glEndList();
    }

    //**************************************************************************
    @Override
    public void draw()
    {
        gl.glPushMatrix();
        gl.glTranslatef(X, Y, Z);
        gl.glColor3f(R, G, B);
        rotateX();
        rotateY();
        rotateZ();

        gl.glEnable(gl.GL_TEXTURE_2D);
        text.setTexParameteri(gl.GL_TEXTURE_MIN_FILTER, gl.GL_LINEAR);
        text.setTexParameteri(gl.GL_TEXTURE_MAG_FILTER, gl.GL_LINEAR);
        text.bind();
        text.enable();

        
        gl.glTexParameterf(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_WRAP_S, gl.GL_REPEAT);
        gl.glTexParameterf(gl.GL_TEXTURE_2D, gl.GL_TEXTURE_WRAP_T, gl.GL_REPEAT);
        
        gl.glTexEnvf(gl.GL_TEXTURE_ENV, gl.GL_TEXTURE_ENV_MODE, gl.GL_DECAL);
        glu.gluQuadricNormals(quadric, GLU.GLU_SMOOTH);  // Create Smooth Normals (NEW)
        glu.gluQuadricTexture(quadric, true);            // Create Texture Coords (NEW)
        glu.gluSphere(quadric, .1f, 32, 32);
        gl.glPopMatrix();
    }
    //**************************************************************************

    public static void LoadGLTextures()
    {

        BufferedImage image = new BufferedImage(256, 256, BufferedImage.TYPE_INT_ARGB);
        try
        {
            image = ImageIO.read(new File("/home/tbfoster/NetBeansProjects/SpacialDemo/data/NeHe.png"));
        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
            text = TextureIO.newTexture(image, false);
            
            //text.setTexParameteri(gl.GL_TEXTURE_MIN_FILTER, gl.GL_LINEAR);
            //text.setTexParameteri(gl.GL_TEXTURE_MAG_FILTER, gl.GL_LINEAR);
            //text.bind();

    }
}
