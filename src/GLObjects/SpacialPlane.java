package GLObjects;

import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

public class SpacialPlane extends SpacialObject {

    public Texture text;
    BufferedImage image = new BufferedImage(256, 256, BufferedImage.TYPE_INT_ARGB);
    //BufferedImage image = new BufferedImage(

    public SpacialPlane(GL vgl, GLU vglu, float vX, float vY, float vZ)
    {
        super(vgl, vglu, vX, vY, vZ);
        //text = TextureIO.newTexture(image, false);
    }

    //**************************************************************************
    @Override
    public void draw()
    {
        float size = 1;
        float vX = 0;  float vY = 0;
        float width = 1;
        float height = 1;
        gl.glPushMatrix();

        gl.glTranslatef(X, Y, Z);
        gl.glColor3f(R, G, B);
        rotateX();
        rotateY();
        rotateZ();

        //text.bind();
        text.setTexParameteri(gl.GL_TEXTURE_MIN_FILTER, gl.GL_LINEAR);
        text.setTexParameteri(gl.GL_TEXTURE_MAG_FILTER, gl.GL_LINEAR);
        text.setTexParameterf(gl.GL_TEXTURE_WRAP_S, gl.GL_REPEAT);
        text.setTexParameterf(gl.GL_TEXTURE_WRAP_T, gl.GL_REPEAT);
        gl.glTexEnvf(gl.GL_TEXTURE_ENV, gl.GL_TEXTURE_ENV_MODE, gl.GL_DECAL);
        text.enable();

        gl.glBegin(GL.GL_QUADS);
        gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(vX,  vY,  0);
        gl.glTexCoord2f(size, 0.0f); gl.glVertex3f(vX+width, vY,  0);
        gl.glTexCoord2f(size, size); gl.glVertex3f(vX+width, vY+height,  0);
        gl.glTexCoord2f(0.0f, size); gl.glVertex3f(vX,  vY+height,  0);
        
        //gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-size, -size,  0);
        //gl.glTexCoord2f(size, 0.0f); gl.glVertex3f( size, -size,  0);
        //gl.glTexCoord2f(size, size); gl.glVertex3f( size,  size,  0);
        //gl.glTexCoord2f(0.0f, size); gl.glVertex3f(-size,  size,  0);
        text.disable();
        
        gl.glEnd();
        gl.glPopMatrix();

    }

    //**************************************************************************
    public void LoadGLTextures(String vFilename)
    {
        //BufferedImage image = new BufferedImage(256, 256, BufferedImage.TYPE_INT_ARGB);
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
    public void setTextureImage(BufferedImage vImage)
    {
        image = vImage;
        text = TextureIO.newTexture(image, false);
    }
    //**************************************************************************
}
