package GLObjects;

import gl4java.utils.textures.PngTextureLoader;
import mri.v3ds.*;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

public class SpacialSphere extends SpacialObject {

    public int[] texture = new int[1]; //Storage for one texture ( NEW )
    int genList;
    public GLUquadric quadric;
    Material3ds test = new Material3ds();

    //**************************************************************************
    public SpacialSphere(float vX, float vY, float vZ)
    {
        super(vX, vY, vZ);
        quadric = Globals.glu.gluNewQuadric();
        Globals.glu.gluQuadricNormals(quadric, GLU.GLU_SMOOTH);  // Create Smooth Normals (NEW)
        Globals.glu.gluQuadricTexture(quadric, true);
        
    }
    //**************************************************************************
    public void compile()
    {
        genList = Globals.gl.glGenLists(Globals.genListIndex);
        Globals.gl.glNewList(genList, Globals.gl.GL_COMPILE);
        //Globals.gl.glBindTexture(Globals.gl.GL_TEXTURE_2D, texture);
        Globals.gl.glEnable(Globals.gl.GL_TEXTURE_2D);
        Globals.gl.glEndList();
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
    public boolean LoadGLTextures()
      {
         PngTextureLoader texLoader = new PngTextureLoader(Globals.gl, Globals.glu);
         texLoader.readTexture("data/nehe.png");

         if(texLoader.isOk())
         {
            //Create Texture
            //Globals.gl.glGenTextures(1, texture);
            Globals.gl.glBindTexture(Globals.gl.GL_TEXTURE_2D, texture[0]);

            Globals.gl.glTexParameteri(Globals.gl.GL_TEXTURE_2D, Globals.gl.GL_TEXTURE_MAG_FILTER, GL_LINEAR);
            Globals.gl.glTexParameteri(Globals.gl.GL_TEXTURE_2D, Globals.gl.GL_TEXTURE_MIN_FILTER, Globals.gl.GL_LINEAR);

            Globals.gl.glTexImage2D(Globals.gl.GL_TEXTURE_2D,
                           0,
                           3,
                           texLoader.getImageWidth(),
                           texLoader.getImageHeight(),
                           0,
                           Globals.gl.GL_RGB,
                           Globals.gl.GL_UNSIGNED_BYTE,
                           texLoader.getTexture());
            return true;
         }
         return false;
      }
}
