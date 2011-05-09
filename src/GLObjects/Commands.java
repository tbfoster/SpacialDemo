package GLObjects;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import processing.core.PVector;

//******************************************************************************
public class Commands {

    boolean commandForward = false;
    boolean commandBackward = false;
    boolean commandLeft = false;
    boolean commandRight = false;
    int commandBackwardCount = 0;
    int commandForwardCount = 0;
    private int prevMouseX, prevMouseY;
    private boolean mouseRButtonDown = false;

    //**************************************************************************
    public void keyPressed(KeyEvent e)
    {
        char key = e.getKeyChar();
        switch (key)
        {
            case KeyEvent.VK_HOME:
            case 'm':
                //Demo.CameraView.dv.setPosition(0f, 0f, 20f);
                //PVector pv = new PVector(0, 0, 0);
                //CameraView.dirVector.setTarget(pv);
                break;
            case '+':
                //CameraView.dirVector.increaseSpeed();
                
                break;
            case '-':
                //CameraView.dirVector.decreaseSpeed();
                break;
            case 'a':
            case 'A':
            {
                commandLeft = true;
                break;
            }
            case 'd':
            case 'D':
            {
                commandRight = true;
                break;
            }
            case 's':
            case 'S':
            {
                commandBackward = true;
                commandBackwardCount++;
                break;
            }
            case 'w':
            case 'W':
            {
                commandForward = true;
                commandForwardCount++;
                if (commandForwardCount > 2)
                {
                    Globals.camera.dv.increaseSpeed();
                    commandForwardCount = 0;
                }
                break;
            }
            case 't':
                Globals.camera.dv.setTarget(new PVector(-3f, -2f, 3f));
                break;
        }
    }

    //**************************************************************************
    public void keyReleased(KeyEvent e)
    {
        char key = e.getKeyChar();
        switch (key)
        {
            case 'a':
            case 'A':
            {
                commandLeft = false;
                break;
            }
            case 'd':
            case 'D':
            {
                commandRight = false;
                break;
            }
            case 's':
            case 'S':
            {
                commandBackward = false;
                commandBackwardCount = 0;
                break;
            }
            case 'w':
            case 'W':
            {
                commandForward = false;
                commandForwardCount = 0;
                break;
            }
        }
    }
    //**************************************************************************

    public void movementTimer()
    {
        if (commandForward)
        {
            Globals.camera.dv.moveForward();
        }
        if (commandBackward)
        {
            Globals.camera.dv.moveBackward();
        }
        if (commandLeft)
        {
            Globals.camera.dv.strafe(-Globals.movementSensitivity);
        }
        if (commandRight)
        {
            Globals.camera.dv.strafe(Globals.movementSensitivity);
        }
    }
    //**************************************************************************

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

    public void mouseReleased(MouseEvent e)
    {
        if ((e.getModifiers() & MouseEvent.BUTTON3_MASK) != 0)
        {
            mouseRButtonDown = false;
        }
    }
    //**************************************************************************

    public void mouseDragged(MouseEvent e)
    {
        int x = e.getX();
        int y = e.getY();

        if (x > prevMouseX)
        {
            Globals.camera.dv.yaw(-Globals.mouseSensitivity);
        }
        if (x < prevMouseX)
        {
            Globals.camera.dv.yaw(Globals.mouseSensitivity);
        }
        if (y > prevMouseY)
        {
            Globals.camera.dv.pitch(-Globals.mouseSensitivity);
        }
        if (y < prevMouseY)
        {
            Globals.camera.dv.pitch(Globals.mouseSensitivity);
        }
        prevMouseX = x;
        prevMouseY = y;
    }
    //**************************************************************************

    public void mouseMoved(MouseEvent e)
    {
        int x = e.getX();
        int y = e.getY();
        prevMouseX = x;
        prevMouseY = y;
    }
    //**************************************************************************
}
