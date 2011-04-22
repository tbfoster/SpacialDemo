package GLObjects;

import java.util.ArrayList;
import java.util.Iterator;

public class Universe {

    public static ArrayList objectList = new ArrayList();
    SpacialCube sCube, sCube2;
    SpacialPlane plane1, plane2;

//**************************************************************************
    public void createObjects()
    {
        SpacialSphere sphere1 = new SpacialSphere(3, 2, 3);
        sphere1.xIncrease = .03f;

        sCube = new SpacialCube(-3, -1, 0);
        objectList.add(sCube);
        sCube2 = new SpacialCube(3, 1, 0);
        sCube2.xIncrease = .25f;
        objectList.add(sCube2);
        plane1 = new SpacialPlane(-2, -1, 0);
        plane1.setColor(.7f, .8f, .2f);
        objectList.add(plane1);
        plane2 = new SpacialPlane(-2, 2, 0);
        plane2.setColor(.3f, .4f, .6f);
        plane2.xAngle = 90;
        plane2.zIncrease = .13f;
        objectList.add(plane2);
        objectList.add(sphere1);

        for (int i = 1; i < 10; i++)
        {
            SpacialSphere tSphere = new SpacialSphere(i, 0, 0);
            objectList.add(tSphere);
        }
        for (int i = 1; i < 10; i++)
        {
            SpacialSphere tSphere = new SpacialSphere(0, i, 0);
            objectList.add(tSphere);
        }
        for (int i = 1; i < 10; i++)
        {
            SpacialSphere tSphere = new SpacialSphere(0, 0, i);
            objectList.add(tSphere);
        }
    }
    //**************************************************************************

    public void draw()
    {
        int index = 0;
        SpacialObject obj;
        Iterator itr = objectList.iterator();
        while (itr.hasNext())
        {
            obj = (SpacialObject) objectList.get(index);
            obj.draw();
            index = index + 1;
            itr.next();
        }
    }
    //**************************************************************************

    public static void nextInterval()
    {
        SpacialObject obj;
        int index = 0;
        Iterator itr = objectList.iterator();
        while (itr.hasNext())
        {
            obj = (SpacialObject) objectList.get(index);
            obj.nextInterval();
            index = index + 1;
            itr.next();
        }
    }
    //**************************************************************************
}
