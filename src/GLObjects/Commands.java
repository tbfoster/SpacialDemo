package GLObjects;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

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
                Globals.camera.dirVector.setPosition(0f, 0f, -20f);
                break;
            case '+':
                Globals.camera.dirVector.increaseSpeed();
                break;
            case '-':
                Globals.camera.dirVector.decreaseSpeed();
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
                    Globals.camera.dirVector.increaseSpeed();
                    commandForwardCount = 0;
                }
                break;
            }
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
            Globals.camera.dirVector.moveForward();
        }
        if (commandBackward)
        {
            Globals.camera.dirVector.moveBackward();
        }
        if (commandLeft)
        {
            Globals.camera.dirVector.strafe(-Globals.movementSensitivity);
        }
        if (commandRight)
        {
            Globals.camera.dirVector.strafe(Globals.movementSensitivity);
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
        //float thetaY = 360.0f * ((float) (x - prevMouseX) / (float) size.width);
        //float thetaX = 360.0f * ((float) (prevMouseY - y) / (float) size.height);

        if (x > prevMouseX)
        {
            Globals.camera.dirVector.yaw(-Globals.movementSensitivity);
        }
        if (x < prevMouseX)
        {
            Globals.camera.dirVector.yaw(Globals.movementSensitivity);
        }
        if (y > prevMouseY)
        {
            Globals.camera.dirVector.pitch(-Globals.movementSensitivity);
        }
        if (y < prevMouseY)
        {
            Globals.camera.dirVector.pitch(Globals.movementSensitivity);
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