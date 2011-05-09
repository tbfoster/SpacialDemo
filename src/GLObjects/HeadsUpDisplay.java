package GLObjects;

import java.text.DecimalFormat;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.glu.GLU;

public class HeadsUpDisplay {

    public static GL gl;
    public static GLU glu;
    public static SpacialSphere plane3;

    //**************************************************************************
    HeadsUpDisplay(GL vgl, GLU vglu)
    {
         gl = vgl;
        glu = vglu;
        plane3 = new SpacialSphere(gl, glu, 1, 1, 0);
        plane3.LoadGLTextures("/home/tbfoster/NetBeansProjects/SpacialDemo/data/NeHe.png");
        plane3.compile();
        plane3.setColor(1f, 1f, 0f);
    }

    //**************************************************************************
    public void draw()
    {
         plane3.draw();
    }
    //**************************************************************************

    /*
    public void drawDebug()
    {
        int x, y;
        x = 1;
        String eye = "eye:" + format.format(Globals.camera.eye.x) + "." + format.format(Globals.camera.eye.y) + "." + format.format(Globals.camera.eye.z);
        String tgt  = "tgt:" + format.format(Globals.camera.target.x) + "." + format.format(Globals.camera.target.y) + "." + format.format(Globals.camera.target.z);
        String dir  = "dir:" + format.format(Globals.camera.direction.x) + "." + format.format(Globals.camera.direction.y) + "." + format.format(Globals.camera.direction.z);        y = 50;
        Globals.renderer.beginRendering(drawable.getWidth(), drawable.getHeight());
        Globals.renderer.draw(eye, x, y);
        Globals.renderer.draw(tgt, x, y+20);
        Globals.renderer.draw(dir, x, y+40);
        Globals.renderer.endRendering();
    }
     * 
     */
}
//**************************************************************************

