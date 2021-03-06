package GLObjects;

import processing.core.PVector;

public final class DirectionVector {

    public PVector nPosition = new PVector();
    public PVector nRight = new PVector();
    public PVector nUp = new PVector();
    public PVector nTarget = new PVector();
    public PVector plotPosition = new PVector();
    public float zoomFactor = 2f;

    //**************************************************************************
    public DirectionVector(float vX, float vY, float vZ)
    {
        nPosition.x = vX;
        nPosition.y = vY;
        nPosition.z = vZ;
        resetNormals();
        resetTransformationMatrix();
    }
    //**************************************************************************

    public void setPosition(float vX, float vY, float vZ)
    {
        nPosition.x = vX;
        nPosition.y = vY;
        nPosition.z = vZ;
        resetNormals();
        resetTransformationMatrix();
    }
    //**************************************************************************

    public void resetNormals()
    {
        nRight.x = 1;
        nRight.y = 0;
        nRight.z = 0;

        nUp.x = 0;
        nUp.y = 1;
        nUp.z = 0;

        nTarget.x = 0;
        nTarget.y = 0;
        nTarget.z = -1;
    }

    //**************************************************************************
    public void resetTransformationMatrix()
    {
        // transform4D.setIdentity();
    }

    //**************************************************************************
    public void setTarget(PVector vPlotPosition)
    {
        PVector projectedTarget = new PVector();
        PVector vTarget = new PVector();

        plotPosition.x = vPlotPosition.x;
        plotPosition.y = vPlotPosition.y;
        plotPosition.z = vPlotPosition.z;

        vTarget = cgVecSub(plotPosition, nPosition);
        projectedTarget = vTarget;

        if ((Math.abs(vTarget.x) < 0.00001) && (Math.abs(vTarget.z) < 0.00001))
        {
            projectedTarget.x = 0;
            projectedTarget.normalize();
            nRight.x = 1.0f;
            nRight.y = 0f;
            nRight.z = 0f;
            nUp = cgCrossProduct(projectedTarget, nRight);
            nTarget = vTarget;
            PVector x = new PVector();
            x = cgCrossProduct(nTarget, nUp);
            nRight = cgVecScalarMult(x, -1);
        } else
        {
            projectedTarget.y = 0f;
            projectedTarget.normalize();
            nUp.x = 0f;
            nUp.y = 1.0f;
            nUp.z = 0f;
            PVector temp = new PVector();
            temp = cgCrossProduct(projectedTarget, nUp);
            nRight = cgVecScalarMult(temp, -1);
            nTarget = vTarget;
            nUp = cgCrossProduct(nTarget, nRight);
        }
        nTarget.normalize();
        nRight.normalize();
        nUp.normalize();
    }
    //**************************************************************************

    public void roll(float vAngle)
    {
        nRight = cgVecAdd(cgCosineAngle(nRight, (float) (vAngle / 180 * Math.PI)), cgSineAngle(nUp, (float) (vAngle / 180 * Math.PI)));
        nUp = cgCrossProduct(nRight, nTarget);
    }

    //**************************************************************************
    public void pitch(float vAngle)
    {
        nTarget = cgVecAdd(cgCosineAngle(nTarget, (float) (vAngle / 180 * Math.PI)), cgSineAngle(nUp, (float) (vAngle / 180 * Math.PI)));
        nUp = cgCrossProduct(nRight, nTarget);
    }
    //**************************************************************************

    public void yaw(float vAngle)
    {
        nRight = cgVecAdd(cgCosineAngle(nRight, (float) (vAngle / 180 * Math.PI)), cgSineAngle(nTarget, (float) (vAngle / 180 * Math.PI)));
        nTarget = cgCrossProduct(nUp, nRight);
    }
    //**************************************************************************

    public void strafeLeft()
    {
        nPosition = cgVecAdd(nPosition, cgVecScalarMult(nRight, -Globals.speed));
    }

    //**************************************************************************
    public void strafeRight()
    {
        nPosition = cgVecAdd(nPosition, cgVecScalarMult(nRight, Globals.speed));
    }

    //**************************************************************************
    public PVector cgCosineAngle(PVector v1, float a)
    {
        PVector retVector = new PVector();
        retVector.x = (float) (v1.x * Math.cos(a));
        retVector.y = (float) (v1.y * Math.cos(a));
        retVector.z = (float) (v1.z * Math.cos(a));
        return retVector;
    }
    //**************************************************************************

    public PVector cgSineAngle(PVector v1, float a)
    {
        PVector retVector = new PVector();
        retVector.x = (float) (v1.x * Math.sin(a));
        retVector.y = (float) (v1.y * Math.sin(a));
        retVector.z = (float) (v1.z * Math.sin(a));
        return retVector;
    }
    //**************************************************************************

    public PVector cgVecAdd(PVector v1, PVector v2)
    {
        PVector vVector = new PVector();
        vVector.x = v1.x + v2.x;
        vVector.y = v1.y + v2.y;
        vVector.z = v1.z + v2.z;

        return vVector;
    }
    //**************************************************************************

    public PVector cgVecSub(PVector v1, PVector v2)
    {
        PVector vVector = new PVector();
        vVector.x = v1.x - v2.x;
        vVector.y = v1.y - v2.y;
        vVector.z = v1.z - v2.z;

        return vVector;
    }
    //**************************************************************************

    public PVector cgVecMult(PVector v1, PVector v2)
    {
        //int i, j;
        PVector vVector = new PVector();
        //for(i=0; i<4; i++)
        // {
        //    vVector[i,j] = v1.[i] * v2[j];
        // }

        //function cgVecMult(v1, v2: TCGVector): TCGMatrix;
        //var
        //i, j: Integer;
        //begin
        // Multiply a row and a column vector, resulting in a 4x4 matrix.
        //for i := 0 to 3 do
        //begin
        //    for j := 0 to 3 do
        //begin
        //Result[i,j] := TAVector(v1)[i] * TAVector(v2)[j];
        return vVector;
    }

    //**************************************************************************
    public PVector cgCrossProduct(PVector v1, PVector v2)
    {
        PVector vVector = new PVector();
        vVector.x = v1.y * v2.z - v2.y * v1.z;
        vVector.y = v2.x * v1.z - v1.x * v2.z;
        vVector.z = v1.x * v2.y - v2.x * v1.y;

        return vVector;
    }
    //**************************************************************************

    public PVector cgVecScalarMult(PVector v, float s)
    {
        PVector vVector = new PVector();
        vVector.x = v.x * s;
        vVector.y = v.y * s;
        vVector.z = v.z * s;

        return vVector;
    }

    //**************************************************************************
    public void GetViewPositionMatrix()
    {
        //transform4D.m00 = SRScan.Camera.Dir.Right.x;
        //transform4D.m01 = SRScan.Camera.Dir.Right.x;
        //transform4D.m02 = SRScan.Camera.Dir.Right.x;
        //transform4D.m10 = SRScan.Camera.Dir.up.x;
        //transform4D.m11 = SRScan.Camera.Dir.up.x;
        //transform4D.m12 = SRScan.Camera.Dir.up.x;
        //transform4D.m20 = SRScan.Camera.Dir.nTarget.x;
        //transform4D.m21 = SRScan.Camera.Dir.nTarget.x;
        //transform4D.m22 = SRScan.Camera.Dir.nTarget.x;
        //transform4D.mul(transform4D);
        //glMultMatrixf(@Transform.M);
    }
    //**************************************************************************

    public void moveForward()
    {
        nPosition.x = nPosition.x + (nTarget.x * Globals.speed);
        nPosition.y = nPosition.y + (nTarget.y * Globals.speed);
        nPosition.z = nPosition.z + (nTarget.z * Globals.speed);
    }

    //**************************************************************************
    public void moveBackward()
    {
        nPosition.x = nPosition.x - (nTarget.x * Globals.speed);
        nPosition.y = nPosition.y - (nTarget.y * Globals.speed);
        nPosition.z = nPosition.z - (nTarget.z * Globals.speed);
    }
    //**************************************************************************

}
