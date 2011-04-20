package GLObjects;

import java.text.DecimalFormat;
import javax.media.opengl.GLAutoDrawable;

public class HeadsUpDisplay {

    public static GLAutoDrawable drawable;
    private DecimalFormat format = new DecimalFormat("###");

    public static void HeadsUpDisplay()
    {
    }
    //**************************************************************************

    public HeadsUpDisplay(GLAutoDrawable vDrawable)
    {

        drawable = vDrawable;
    }

    //**************************************************************************
    public void draw()
    {
        //drawDebug();
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

