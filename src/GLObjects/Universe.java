package GLObjects;

import com.sun.opengl.util.GLUT;
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
    SpacialPlane plane1, plane2, plane3;

//**************************************************************************
    public void createObjects(GL gl, GLU glu, GLUT glut)
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
        plane1.LoadGLTextures("/home/tbfoster/NetBeansProjects/SpacialDemo/data/KAMEN.jpg");
        plane1.setColor(.7f, .8f, .2f);
        plane1.setSize(3,3);
        objectList.add(plane1);
        plane2 = new SpacialPlane(gl, glu, 0, 0, 0);
        plane2.LoadGLTextures("/home/tbfoster/NetBeansProjects/SpacialDemo/data/KAMEN.jpg");
        plane2.setColor(1f, 1f, 0f);
        plane2.setSize(2,2);
        objectList.add(plane2);
        
        SpacialClass sc = new SpacialClass(3, 0, 0, "/home/tbfoster/NetBeansProjects/SpacialDemo/src/GLObjects/Demo.java");
        sc.addAllMethods(gl, glu, glut, objectList);

        ParseJava parser = new ParseJava();
        parser.parseFile("/home/tbfoster/NetBeansProjects/SpacialDemo/src/GLObjects/Demo.java");
        int x = 0;
        int y = 0;
        while(parser.moreMethods())
        {
            
            
            SpacialMethodSource jc = new SpacialMethodSource(gl, glu, glut, x, y, 2, parser.getNextMethodBlock());
            jc.compile();
            //jc.active = true;
            //objectList.add(jc);
            
            SpacialMethod sm = new SpacialMethod(gl, glu, glut, x, y, 0, parser.currentMethodName);
            sm.compile();
            sm.active = true;
            //objectList.add(sm);
            
            //x = x + 60;
            y = y - 1;
        }
        /*
        parser.parseFile("/home/tbfoster/NetBeansProjects/SpacialDemo/src/GLObjects/Universe.java");
        x = 0;
        while(parser.moreMethods())
        {
            SpacialJavaMethod jc = new SpacialJavaMethod(gl, glu, glut, x, 5, -5, parser.getNextMethodBlock());
            jc.compile();
            jc.active = true;
            objectList.add(jc);
            x = x + 60;
        }
         * 
         */

        for (int i = 1; i < 25; i++)
        {
            SpacialSphere tSphere = new SpacialSphere(gl, glu, i, 0, 0);
            tSphere.setColor(1f, 0, 0);
            tSphere.compile();
            objectList.add(tSphere);
        }
        for (int i = 1; i < 25; i++)
        {
            SpacialSphere tSphere = new SpacialSphere(gl, glu, 0, i, 0);
            tSphere.setColor(0, 1f, 0);
            tSphere.compile();
            objectList.add(tSphere);
        }
        for (int i = 1; i < 25; i++)
        {
            SpacialSphere tSphere = new SpacialSphere(gl, glu, 0, 0, i);
            tSphere.setColor(0, 0, 1f);
            tSphere.compile();
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

    public void addJavaClasses(GL gl, GLU glu, GLUT glut, float x, float y, float z, String packageName, String baseDirectory)
    {
        float xx = x;
        int i = 0;
        try
        {
            List<File> fileList = Util.getFiles(baseDirectory);
            for (File file : fileList)
            {
                System.out.println(file);
                file.getName();
                //SpacialJavaMethod jc = new SpacialJavaMethod(gl, glu, glut, xx, y, z, file.getName(), file.getAbsolutePath());
                if (i == 0)
                {
                    //jc.viewFunction = true;
                }
                //objectList.add(jc);
                xx = xx - .5f;
            }
        } catch (FileNotFoundException ex)
        {
            Logger.getLogger(Universe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //**************************************************************************
}
