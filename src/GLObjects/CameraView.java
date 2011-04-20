package GLObjects;

import javax.vecmath.Matrix3d;
import processing.core.PVector;

public final class CameraView extends SpacialObject {

    DirectionVector dirVector = new DirectionVector(0, 0, 0);
    //gluLookAt(double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ)

    public Matrix3d mRotateX;

    public CameraView(float vX, float vY, float vZ)
    {
        super(vX, vY, vZ);
    }

    //**************************************************************************

    @Override
    public void draw()
    {
        //target.x = (float) (eye.x + direction.x);
        //target.y = (float) (eye.y + direction.y);
        //target.z = (float) (eye.z + direction.z);
        //Globals.glu.gluLookAt(eye.x, eye.y, eye.z, target.x, target.y, target.z, upX, upY, upZ);
        
        PVector look = new PVector();
        look.x = DirectionVector.position.x - (DirectionVector.nTarget.x * dirVector.zoomFactor);
        look.y = DirectionVector.position.y - (DirectionVector.nTarget.y * dirVector.zoomFactor);
        look.z = DirectionVector.position.z - (DirectionVector.nTarget.z * dirVector.zoomFactor);
        PVector center = new PVector();
        center.x = DirectionVector.position.x + DirectionVector.nTarget.x;
        center.y = DirectionVector.position.y + DirectionVector.nTarget.y;
        center.z = DirectionVector.position.z + DirectionVector.nTarget.z;

        Globals.glu.gluLookAt(look.x, look.y, look.z,
                center.x, center.y, center.z,
                DirectionVector.nUp.x, DirectionVector.nUp.y, DirectionVector.nUp.z);
    }

    //**************************************************************************
    public void rotateDirectionX(float amt)
    {
    }
    //**************************************************************************

    public void strafeRight(float amt)
    {
        dirVector.strafe(0.8f);
    }

    //**************************************************************************
    public void strafeLeft(float amt)
    {
        dirVector.strafe(-0.8f);
    }

    //**************************************************************************
    public void moveForward()
    {
        dirVector.moveForward();
    }
    //**************************************************************************

    public void moveBackward()
    {
        dirVector.moveBackward();
    }
    //**************************************************************************
}
