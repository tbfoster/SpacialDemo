//*****************
package GLObjects;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.*;
import java.awt.geom.*;
import javax.media.opengl.*;
import javax.media.opengl.glu.*;
import com.sun.opengl.util.*;
import com.sun.opengl.util.j2d.*;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import processing.core.PVector;

//**************************************************************************
public class Demo implements GLEventListener, MouseListener, MouseMotionListener, KeyListener {

    private static Component emptyLabel;
    public static boolean debuggingOn = false;
    private static JLabel dirPosition = new JLabel();
    private static JLabel dirDirection = new JLabel();
    private static JLabel dirRight = new JLabel();
    private static JLabel dirUp = new JLabel();
    private static JLabel dirTarget = new JLabel();
    private static JLabel dirPlot = new JLabel();
    private static JLabel dirMatrix = new JLabel();
    private V3dsScene VScene;
    public static ArrayList objectList = new ArrayList();
    private float xAng;
    private float yAng;
    //private Time time;
    //private FPSCounter fps;
    private int prevMouseX, prevMouseY;
    private boolean mouseRButtonDown = false;
    private float view_rotx = 20.0f, view_roty = 30.0f, view_rotz = 0.0f;
    SpacialCube sCube, sCube2;
    SpacialPlane plane1, plane2;
    HeadsUpDisplay hud;

    //**************************************************************************
    public static void main(String[] args)
    {
        Frame frame = new Frame("Text Cube");
        frame.setLayout(new BorderLayout());

        GLCanvas canvas = new GLCanvas();
        final Demo demo = new Demo();

        canvas.addGLEventListener(demo);
        frame.add(canvas, BorderLayout.CENTER);

        frame.setSize(1024, 768);
        final Animator animator = new Animator(canvas);

        Globals.timerObjectInterval1.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run()
            {
                SpacialObject obj;
                int index = 0;
                Iterator itr = objectList.iterator();
                while (itr.hasNext())
                {
                    obj = (SpacialObject) objectList.get(index);
                    obj.nextInterval();
                    index = index + 1;
                    itr.next();
                }
            }
        }, Globals.delay, Globals.period);


        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e)
            {
                new Thread(new Runnable() {

                    @Override
                    public void run()
                    {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
        frame.show();
        animator.start();

        if (debuggingOn)
        {
            debugSystem();
        }
    }

    //**************************************************************************
    @Override
    public void init(GLAutoDrawable drawable)
    {
        Globals.renderer = new TextRenderer(new Font("SansSerif", Font.PLAIN, 18));
        Globals.glu = new GLU();
        Globals.gl = drawable.getGL();
        Globals.gl.glEnable(GL.GL_DEPTH_TEST);
        Globals.camera = new CameraView(0, 0, -20f);
        Rectangle2D bounds = Globals.renderer.getBounds("Bottom");
        float w = (float) bounds.getWidth();
        float h = (float) bounds.getHeight();
        Globals.textScaleFactor = 1.0f / (w * 1.1f);
        //fps = new FPSCounter(drawable, 36);
        hud = new HeadsUpDisplay(drawable);

        createObjects();

        Globals.camera.xIncrease = .3f;
        Globals.camera.yIncrease = .3f;
        Globals.camera.zIncrease = .3f;

        //time = new SystemTime();
        //((SystemTime) time).rebase();
        Globals.gl.setSwapInterval(0);
        drawable.addMouseListener(this);
        drawable.addMouseMotionListener(this);
        drawable.addKeyListener(this);
        Globals.gl = drawable.getGL();
        //VScene = new V3dsScene();
        //VScene = new V3dsScene(Globals.gl, "sponza.3ds");
        VScene = new V3dsScene(Globals.gl, "test.3ds");
    }

    //**************************************************************************
    @Override
    public void display(GLAutoDrawable drawable)
    {
        //VScene.draw();

        SpacialObject obj;
        Globals.gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        Globals.gl.glMatrixMode(GL.GL_MODELVIEW);
        Globals.gl.glLoadIdentity();
        Globals.camera.draw();

        int index = 0;
        Iterator itr = objectList.iterator();
        while (itr.hasNext())
        {
            obj = (SpacialObject) objectList.get(index);
            obj.draw();
            index = index + 1;
            itr.next();
        }
        hud.draw();

        //fps.draw();
        //time.update();
        VScene.draw(Globals.gl);

        //xAng += 200 * (float) time.deltaT();
        //yAng += 150 * (float) time.deltaT();
    }

    //**************************************************************************
    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height)
    {
        Globals.gl = drawable.getGL();
        Globals.gl.glMatrixMode(GL.GL_PROJECTION);
        Globals.gl.glLoadIdentity();
        Globals.glu.gluPerspective(55, (float) width / (float) height, 5, 155);
    }

    //**************************************************************************
    @Override
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged)
    {
    }

    //**************************************************************************
    @Override
    public void mouseEntered(MouseEvent e)
    {
    }

    //**************************************************************************
    @Override
    public void mouseExited(MouseEvent e)
    {
    }

    //**************************************************************************
    @Override
    public void mousePressed(MouseEvent e)
    {
        prevMouseX = e.getX();
        prevMouseY = e.getY();

        if ((e.getModifiers() & MouseEvent.BUTTON3_MASK) != 0)
        {
            mouseRButtonDown = true;
        }
    }

    //**************************************************************************
    @Override
    public void mouseReleased(MouseEvent e)
    {
        if ((e.getModifiers() & MouseEvent.BUTTON3_MASK) != 0)
        {
            mouseRButtonDown = false;
        }
    }

    //**************************************************************************
    @Override
    public void mouseClicked(MouseEvent e)
    {
        showDebug();
        //Globals.camera.
    }

    //**************************************************************************
    @Override
    public void mouseDragged(MouseEvent e)
    {
        int x = e.getX();
        int y = e.getY();

        Dimension size = e.getComponent().getSize();

        float thetaY = 360.0f * ((float) (x - prevMouseX) / (float) size.width);
        float thetaX = 360.0f * ((float) (prevMouseY - y) / (float) size.height);

        //Globals.camera.xAngle = view_rotx;
        //Globals.camera.yAngle = view_roty;
        if(x > prevMouseX)
        {
            Globals.camera.dirVector.yaw(-Globals.movementSensitivity);
        }
        if(x < prevMouseX)
        {
            Globals.camera.dirVector.yaw(Globals.movementSensitivity);
        }
        if(y > prevMouseY)
        {
            Globals.camera.dirVector.pitch(-Globals.movementSensitivity);
        }
        if(y < prevMouseY)
        {
            Globals.camera.dirVector.pitch(Globals.movementSensitivity);
        }


        prevMouseX = x;
        prevMouseY = y;

        view_rotx += thetaX;
        view_roty += thetaY;

    }
    //**************************************************************************

    @Override
    public void mouseMoved(MouseEvent e)
    {
        int x = e.getX();
        int y = e.getY();
        //if(x > prevMouseX) camera.X = camera.X + .05f;
        //if(x > prevMouseX) camera.X = camera.X - .05f;

        prevMouseX = x;
        prevMouseY = y;
    }

    //**************************************************************************
    @Override
    public void keyPressed(KeyEvent e)
    {
        processKeyEvent(e, false);
    }

    //**************************************************************************
    @Override
    public void keyReleased(KeyEvent e)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_F:
                //renderer.switchFilter();
                break;
            case KeyEvent.VK_SPACE:
                //renderer.switchObject();
                break;
            case KeyEvent.VK_L:
                //renderer.toggleLighting();
                break;
            default:
                processKeyEvent(e, false);
        }
    }

    //**************************************************************************
    @Override
    public void keyTyped(KeyEvent e)
    {
        char key = e.getKeyChar();
        switch (key)
        {
            case 't':
            case 'T':
            {
                Globals.camera.dirVector.setTarget(new PVector(0, 0, 0));
                showDebug();
                break;
            }
            case 'a':
            case 'A':
            {
                Globals.camera.dirVector.strafe(-Globals.movementSensitivity);
                break;
            }
            case 'd':
            case 'D':
            {
                Globals.camera.dirVector.strafe(Globals.movementSensitivity);
                break;
            }
            case 's':
            case 'S':
            {
                Globals.camera.moveBackward();
                break;
            }
            case 'w':
            case 'W':
            {
                Globals.camera.moveForward();
                break;
            }
            case 'f':
            case 'F':
            {
                Globals.camera.dirVector.yaw(1);
                break;
            }
        }
        showDebug();
    }

    //**************************************************************************
    private void processKeyEvent(KeyEvent e, boolean pressed)
    {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_PAGE_UP:
                //renderer.zoomIn(pressed);
                break;
            case KeyEvent.VK_PAGE_DOWN:
                //renderer.zoomOut(pressed);
                break;
            case KeyEvent.VK_UP:
                //renderer.increaseXspeed(pressed);
                break;
            case KeyEvent.VK_DOWN:
                //renderer.decreaseXspeed(pressed);
                break;
            case KeyEvent.VK_RIGHT:
                //renderer.increaseYspeed(pressed);
                break;
            case KeyEvent.VK_LEFT:
                //renderer.decreaseYspeed(pressed);
                break;
        }
    }
    //**************************************************************************

    public void createObjects()
    {
        SpacialSphere sphere1 = new SpacialSphere(3, 2, 3);
        sphere1.xIncrease = .03f;

        sCube = new SpacialCube(-3, -1, 0);
        objectList.add(sCube);
        sCube2 = new SpacialCube(3, 1, 0);
        sCube2.xIncrease = .25f;
        objectList.add(sCube2);
        plane1 = new SpacialPlane(-2, -1, 0);
        plane1.setColor(.7f, .8f, .2f);
        objectList.add(plane1);
        plane2 = new SpacialPlane(-2, 2, 0);
        plane2.setColor(.3f, .4f, .6f);
        plane2.xAngle = 90;
        plane2.zIncrease = .13f;
        objectList.add(plane2);
        objectList.add(sphere1);

        for (int i = 1; i < 10; i++)
        {
            SpacialSphere tSphere = new SpacialSphere(i, 0, 0);
            objectList.add(tSphere);
        }
        for (int i = 1; i < 10; i++)
        {
            SpacialSphere tSphere = new SpacialSphere(0, i, 0);
            objectList.add(tSphere);
        }
        for (int i = 1; i < 10; i++)
        {
            SpacialSphere tSphere = new SpacialSphere(0, 0, i);
            objectList.add(tSphere);
        }

    }
    //**************************************************************************

    public static void debugSystem()
    {
        JFrame frame2 = new JFrame("FrameDemo");
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //emptyLabel = new Component() {};
        //frame2.getContentPane().add(emptyLabel, BorderLayout.CENTER);
        JPanel panel = new JPanel();

        panel.add(dirPosition);
        panel.add(dirTarget);
        panel.add(dirRight);
        panel.add(dirUp);
        panel.add(dirPlot);
        frame2.add(panel);
        frame2.pack();
        frame2.setSize(new Dimension(400, 400));
        frame2.setVisible(true);
    }
    //**************************************************************************

    public static void showDebug()
    {
        formatDebug(DirectionVector.position, dirPosition, "    Dir:    ");
        formatDebug(DirectionVector.nTarget, dirTarget, "    Target: ");
        formatDebug(DirectionVector.nRight, dirRight, "    Right:  ");
        formatDebug(DirectionVector.nUp, dirUp, "    Up:     ");
        formatDebug(DirectionVector.plotPosition, dirPlot, "    Plot:   ");
        //private static JLabel dirMatrix = new JLabel();
    }
    //**************************************************************************

    public static void formatDebug(PVector vVector, JLabel vLabel, String vName)
    {
        String returnValue = vName + ":" + vVector.x + "." + vVector.y + "." + vVector.z;
        vLabel.setText(returnValue);
    }
    //**************************************************************************
}
