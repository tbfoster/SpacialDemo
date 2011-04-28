package GLObjects;

import mri.v3ds.V3dsScene;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.*;
import java.awt.geom.*;
import javax.media.opengl.*;
import javax.media.opengl.glu.*;
import com.sun.opengl.util.*;
import com.sun.opengl.util.j2d.*;
import java.awt.Dimension;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import processing.core.PVector;

//**************************************************************************
public class Demo implements GLEventListener, MouseListener, MouseMotionListener, KeyListener {

    public static GL gl;
    public static GLU glu;
    public static boolean debuggingOn = false;
    private V3dsScene VScene;
    CameraView camera;
    Universe universe;
    HeadsUpDisplay hud;
    Commands commands;

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

        //**********************************************************************
        Globals.timerObjectInterval1.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run()
            {
                Universe.nextInterval();
            }
        }, Globals.delay, Globals.period);
        //**********************************************************************
        Globals.timerObjectInterval2.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run()
            {
            }
        }, Globals.delay, Globals.period);
        //**********************************************************************


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
        glu = new GLU();
        gl = drawable.getGL();
        gl.glEnable(GL.GL_DEPTH_TEST);
        camera = new CameraView();

        Rectangle2D bounds = Globals.renderer.getBounds("Bottom");
        float w = (float) bounds.getWidth();
        float h = (float) bounds.getHeight();
        Globals.textScaleFactor = 1.0f / (w * 1.1f);
        universe = new Universe();
        hud = new HeadsUpDisplay(drawable);
        commands = new Commands();
        universe.createObjects(gl, glu);
        gl.setSwapInterval(0);
        drawable.addMouseListener(this);
        drawable.addMouseMotionListener(this);
        drawable.addKeyListener(this);
        gl = drawable.getGL();

        //VScene = new V3dsScene(gl, "sponza.3ds");
        VScene = new V3dsScene(gl, "test.3ds");
        //VScene = new V3dsScene(gl, "Beast.3ds");
    }

    //**************************************************************************
    @Override
    public void display(GLAutoDrawable drawable)
    {
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
        camera.draw(glu);
        commands.movementTimer();
        universe.draw();
        hud.draw();
        //VScene.draw(gl);
    }

    //**************************************************************************
    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height)
    {
        gl = drawable.getGL();
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45, (float) width / (float) height, 5, 555);
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
        commands.mousePressed(e);
    }

    //**************************************************************************
    @Override
    public void mouseReleased(MouseEvent e)
    {
        commands.mousePressed(e);
    }

    //**************************************************************************
    @Override
    public void mouseClicked(MouseEvent e)
    {
    }

    //**************************************************************************
    @Override
    public void mouseDragged(MouseEvent e)
    {
        commands.mouseDragged(e);
    }
    //**************************************************************************

    @Override
    public void mouseMoved(MouseEvent e)
    {
        commands.mouseMoved(e);
    }

    //**************************************************************************
    @Override
    public void keyPressed(KeyEvent e)
    {
        commands.keyPressed(e);
        //processKeyEvent(e, false);
    }

    //**************************************************************************
    @Override
    public void keyReleased(KeyEvent e)
    {
        commands.keyReleased(e);
    }

    //**************************************************************************
    @Override
    public void keyTyped(KeyEvent e)
    {
    }

    //**************************************************************************
    public static void debugSystem()
    {
        JFrame frame2 = new JFrame("FrameDemo");
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame2.add(panel);
        frame2.pack();
        frame2.setSize(new Dimension(400, 400));
        frame2.setVisible(true);
    }
    //**************************************************************************

    public static void showDebug()
    {
        //formatDebug(DirectionVector.position, dirPosition, "    Dir:    ");
        //formatDebug(DirectionVector.nTarget, dirTarget, "    Target: ");
        //formatDebug(DirectionVector.nRight, dirRight, "    Right:  ");
        //formatDebug(DirectionVector.nUp, dirUp, "    Up:     ");
        //formatDebug(DirectionVector.plotPosition, dirPlot, "    Plot:   ");
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
