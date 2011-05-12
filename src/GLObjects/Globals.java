package GLObjects;

import java.util.Timer;

//******************************************************************************
public class Globals {

    //public static TextRenderer renderer;
    public static float speed = 0.03f;
    public static boolean movementOn;
    
    
    public static float textScaleFactor;
    public static float movementSensitivity = 0.02f;
    public static float mouseSensitivity = 1f;
    public static int commandMovementCount = 0;
    public static int delay = 0;   // delay for 5 sec.
    public static int period = 10;  // repeat every sec.
    public static int fpsCount = 0;
    public static int FPS = 0;
    public static float increaseSpeedInterval = .01f;
    public static Timer timerObjectInterval1 = new Timer();
    public static Timer timerObjectInterval2 = new Timer();
    public static Timer timerSpeedControl = new Timer();
    public static int genListIndex = 1;
    public static int frameWidth = 1680;
    public static int frameHeight = 1050;
    public static int hudHeight = 200;
    public static CameraView camera;
    public static CameraView hudCamera;
}
//******************************************************************************

