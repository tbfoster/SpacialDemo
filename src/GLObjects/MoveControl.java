package GLObjects;

import java.util.TimerTask;

public class MoveControl {
    //**************************************************************************

    //public boolean Globals.movementOn = false;
    public float maxSpeed = 5;
    public float speedIncrement = 0.5f;
    public int threshold = 0;
    public int nextThreshold = 10;
    public int commandMovementMax = 100;
    public int commandMovementCount = 0;

    //**************************************************************************
    public void init()
    {
        Globals.timerSpeedControl.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run()
            {
                nextInterval();
            }
        }, 0, 100);
    }

    //**************************************************************************
    public void nextInterval()
    {
        if(!Globals.movementOn) {
            threshold = 0;
            resetSpeed();
        }
        if(Globals.movementOn) threshold = threshold + 1;
        if (threshold > nextThreshold)
        {
            threshold = 0;
            increaseSpeed();
        }
    }
    //**************************************************************************

    public void movementOn()
    {
        Globals.movementOn = true;
    }
    //**************************************************************************

    public void movementOff()
    {
        Globals.movementOn = false;
    }
    //**************************************************************************

    public boolean isMovementOn()
    {
        if (Globals.movementOn)
        {
            return true;
        } else
        {
            return false;
        }
    }
    //**************************************************************************

    public void increaseThreshold()
    {
        threshold = threshold = 1;
    }
    //**************************************************************************

    public void resetThreshold()
    {
        threshold = 0;
        Globals.movementOn = false;
    }
    //**************************************************************************

    public void increaseSpeed()
    {
        Globals.speed = Globals.speed + speedIncrement;
        if (Globals.speed > maxSpeed)
        {
            Globals.speed = maxSpeed;
        }
    }
    //**************************************************************************
    public void resetSpeed()
    {
        Globals.speed = 0.03f;
    }
    //**************************************************************************
}
