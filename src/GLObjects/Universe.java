package GLObjects;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

public class Universe {

    public static ArrayList objectList = new ArrayList();
    SpacialCube sCube, sCube2;
    SpacialPlane plane1, plane2;
    JavaPackage package1, package2, package3;

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
        //objectList.add(plane2);
        //objectList.add(sphere1);
        //objectList.add(sphere2);

        addPackage(gl, glu, -1, 0, 0, "GLObjects", "/home/tbfoster/NetBeansProjects/SpacialDemo/src/GLObjects");
        addPackage(gl, glu, -1, 3, 0, "mri", "/home/tbfoster/NetBeansProjects/SpacialDemo/src/mri");


        //package2 = new JavaPackage(gl, glu, 0, 2, 0);
        //p/ackage2.packageName = "package2";
        //objectList.add(package2);
        //package3 = new JavaPackage(gl, glu, 0, 3, 0);
        //package3.packageName = "package3";
        //objectList.add(package3);

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

    public void addPackage(GL gl, GLU glu, float x, float y, float z, String packageName, String baseDirectory)
    {
        float xx = x;
        objectList.add(new JavaPackage(gl, glu, x, y, z, packageName));
        xx = xx - .5f;
        try
        {
            List<File> fileList = Util.getFiles(baseDirectory);
            for (File file : fileList)
        {
            System.out.println(file);
            file.getName();
            objectList.add(new JavaFunctionPanel(gl, glu, xx, y, z, file.getName()));
            xx = xx - .5f;
        }
        } catch (FileNotFoundException ex)
        {
            Logger.getLogger(Universe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //**************************************************************************
}
