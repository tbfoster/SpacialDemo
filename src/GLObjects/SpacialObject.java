// **** Latest
package GLObjects;

public class SpacialObject {

    public float X;
    public float Y;
    public float Z;
    public float xAngle, xIncrease;
    public float yAngle, yIncrease;
    public float zAngle, zIncrease;
    public float R;
    public float G;
    public float B;

    //**************************************************************************

    public SpacialObject(float vX, float vY, float vZ)
    {
        X = vX;
        Y = vY;
        Z = vZ;
        R = 1;
        G = 1;
        B = 1;
    }

    //**************************************************************************

    public void setColor(float vR, float vG, float vB)
    {
        R = vR;
        G = vG;
        B = vB;
    }

    //**************************************************************************

    public void increaseX(float vX)
    {
        xAngle = xAngle + vX;
        if(xAngle > 360) xAngle = 0;
    }

    //**************************************************************************

    public void draw()
    {
    }

    
    //**************************************************************************

    public void reSize()
    {
    }

    //**************************************************************************

    public void rotateX()
    {
        Globals.gl.glRotatef(xAngle, 1, 0, 0);
    }

    //**************************************************************************

    public void rotateY()
    {
        Globals.gl.glRotatef(yAngle, 0, 1, 0);
    }

    //**************************************************************************

    public void rotateZ()
    {
        Globals.gl.glRotatef(zAngle, 0, 0, 1);
    }

    //**************************************************************************

    public void nextInterval()
    {
        xAngle = xAngle + xIncrease;
        if(xAngle > 360) xAngle = 0;
        yAngle = yAngle + yIncrease;
        if(yAngle > 360) yAngle = 0;
        zAngle = zAngle + zIncrease;
        if(zAngle > 360) zAngle = 0;
    }

    //**************************************************************************

}
