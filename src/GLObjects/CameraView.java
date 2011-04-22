package GLObjects;

import processing.core.PVector;

public final class CameraView 
{
    DirectionVector dirVector = new DirectionVector(0, 0, 0);

    //**************************************************************************
    public void draw()
    {
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
}
