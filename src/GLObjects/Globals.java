package GLObjects;

import com.sun.opengl.util.j2d.TextRenderer;
import java.util.Timer;

//******************************************************************************
public class Globals {

    public static TextRenderer renderer;
    public static float textScaleFactor;
    public static float movementSensitivity = 0.4f;
    public static int delay = 0;   // delay for 5 sec.
    public static int period = 10;  // repeat every sec.
    public static float increaseSpeedInterval = .01f;
    public static Timer timerObjectInterval1 = new Timer();
    public static Timer timerObjectInterval2 = new Timer();
    public static int genListIndex = 1;
}
//******************************************************************************

