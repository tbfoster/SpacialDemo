package GLObjects;

import javax.media.opengl.glu.GLU;
import processing.core.PVector;

public class CameraView 
{
    public DirectionVector dv = new DirectionVector(5, 1, 25f);

    //**************************************************************************
    public void draw(GLU glu)
    {
        PVector eye = new PVector();
        eye.x = dv.nPosition.x - (dv.nTarget.x * dv.zoomFactor);
        eye.y = dv.nPosition.y - (dv.nTarget.y * dv.zoomFactor);
        eye.z = dv.nPosition.z - (dv.nTarget.z * dv.zoomFactor);
        PVector center = new PVector();
        center.x = dv.nPosition.x + dv.nTarget.x;
        center.y = dv.nPosition.y + dv.nTarget.y;
        center.z = dv.nPosition.z + dv.nTarget.z;

        glu.gluLookAt(dv.nPosition.x, dv.nPosition.y, dv.nPosition.z,
                center.x, center.y, center.z,
                dv.nUp.x, dv.nUp.y, dv.nUp.z);
    }
    //**************************************************************************
}
