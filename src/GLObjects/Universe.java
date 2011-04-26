package GLObjects;

import java.util.ArrayList;
import java.util.Iterator;
import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

public class Universe {

    public static ArrayList objectList = new ArrayList();
    SpacialCube sCube, sCube2;
    SpacialPlane plane1, plane2;
    JavaPackage package1;

//**************************************************************************
    public void createObjects(GL gl, GLU glu)
    {
        SpacialSphere sphere1 = new SpacialSphere(gl, glu, 3, 2, 3);
        sphere1.xIncrease = .13f;
        sphere1.LoadGLTextures("/home/tbfoster/NetBeansProjects/SpacialDemo/data/NeHe.png");
        sphere1.compile();

        SpacialSphere sphere2 = new SpacialSphere(gl, glu, -3, -2, 3);
        sphere2.yIncrease = .33f;
        sphere2.LoadGLTextures("/home/tbfoster/NetBeansProjects/SpacialDemo/data/vrata_kr.jpg");
        sphere2.compile();

        sCube = new SpacialCube(gl, glu, -8, -1, -3);
        objectList.add(sCube);
        sCube2 = new SpacialCube(gl, glu, 5, 3, -5);
        sCube2.xIncrease = .25f;
        objectList.add(sCube2);
        plane1 = new SpacialPlane(gl, glu, -2, -6, 0);
        plane1.setColor(.7f, .8f, .2f);
        objectList.add(plane1);
        plane2 = new SpacialPlane(gl, glu, -2, 2, -8);
        plane2.setColor(.3f, .4f, .6f);
        plane2.xAngle = 90;
        plane2.zIncrease = .13f;
        objectList.add(plane2);
        objectList.add(sphere1);
        objectList.add(sphere2);

        package1 = new JavaPackage(gl, glu, -15, 5, 5);
        objectList.add(package1);

        /*

        for (int i = 1; i < 1; i++)
        {
            SpacialSphere tSphere = new SpacialSphere(gl, glu, i, 0, 0);
            tSphere.compile();
            objectList.add(tSphere);
        }
        for (int i = 1; i < 1; i++)
        {
            SpacialSphere tSphere = new SpacialSphere(gl, glu, 0, i, 0);
            tSphere.compile();
            objectList.add(tSphere);
        }
        for (int i = 1; i < 1; i++)
        {
            SpacialSphere tSphere = new SpacialSphere(gl, glu, 0, 0, i);
            tSphere.compile();
            objectList.add(tSphere);
        }
         * 
         */
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
