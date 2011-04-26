package GLObjects;

import javax.media.opengl.glu.GLU;
import processing.core.PVector;


public class CameraView 
{
    public static DirectionVector dirVector = new DirectionVector(0, 0, 0);

    //**************************************************************************
    public void draw(GLU glu)
    {
        PVector look = new PVector();
        look.x = DirectionVector.position.x - (DirectionVector.nTarget.x * dirVector.zoomFactor);
        look.y = DirectionVector.position.y - (DirectionVector.nTarget.y * dirVector.zoomFactor);
        look.z = DirectionVector.position.z - (DirectionVector.nTarget.z * dirVector.zoomFactor);
        PVector center = new PVector();
        center.x = DirectionVector.position.x + DirectionVector.nTarget.x;
        center.y = DirectionVector.position.y + DirectionVector.nTarget.y;
        center.z = DirectionVector.position.z + DirectionVector.nTarget.z;

        glu.gluLookAt(look.x, look.y, look.z,
                center.x, center.y, center.z,
                DirectionVector.nUp.x, DirectionVector.nUp.y, DirectionVector.nUp.z);
    }
    //**************************************************************************
}
