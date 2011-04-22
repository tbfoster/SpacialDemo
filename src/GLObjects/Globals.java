package GLObjects;

import com.sun.opengl.util.j2d.TextRenderer;
import java.util.Timer;
import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

//******************************************************************************
public class Globals {

    public static GL gl;
    public static TextRenderer renderer;
    public static float textScaleFactor;
    public static GLU glu;
    public static float movementSensitivity = 0.8f;
    public static int delay = 0;   // delay for 5 sec.
    public static int period = 10;  // repeat every sec.
    public static float increaseSpeedInterval = .02f;
    public static Timer timerObjectInterval1 = new Timer();
    public static Timer timerObjectInterval2 = new Timer();
    public static CameraView camera;
}
//******************************************************************************

