package GLObjects;

import com.sun.opengl.util.GLUT;
import com.thoughtworks.qdox.model.JavaMethod;
import java.util.ArrayList;
import java.util.Iterator;
import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

public class SpacialClass extends SpacialObject {

    public ArrayList methodObjects = new ArrayList();
    String className = "test";
    public JavaMethod[] methods;
    public int selectedIndex = 0;

    //**************************************************************************
    public SpacialClass(GL vgl, GLU vglu, GLUT vglut, float vX, float vY, float vZ, String fileName, String vClassName)
    {
        super(vgl, vglu, vglut, vX, vY, vZ);
        float x = vX;
        float y = vY;
        float z = vZ;
        className = vClassName;
        ParseJava parser = new ParseJava();
        parser.parseFile(fileName, className);
        methods = parser.javaMethods;
        X = vX;
        Y = vY;
        Z = vZ;
        for (int i = 0; i < methods.length; i++)
        {
            SpacialMethodSource sm = new SpacialMethodSource(gl, glu, glut, x, y, z, methods[i].getName(), methods[i].getCodeBlock());
            sm.compile();
            sm.active = true;
            if(i==selectedIndex) sm.selected = true;
            methodObjects.add(sm);
            
            y = y - 2f;
            x = x + 1f;
            z = z + 1f;
        }
        System.out.println("Class: " + className + " has: " + methods.length);
    }
    //**************************************************************************

    public void activate()
    {
        SpacialMethodSource obj;
        int index = 0;
        active = true;
        Iterator itr = methodObjects.iterator();
        while (itr.hasNext())
        {
            obj = (SpacialMethodSource) methodObjects.get(index);
            obj.active = true;
            index = index + 1;
            itr.next();
        }
        active = true;
    }
    //**************************************************************************

    public void deactivate()
    {
        SpacialMethodSource obj;
        int index = 0;
        active = true;
        Iterator itr = methodObjects.iterator();
        while (itr.hasNext())
        {
            obj = (SpacialMethodSource) methodObjects.get(index);
            obj.active = false;
            index = index + 1;
            itr.next();
        }
        active = false;
    }

    //**************************************************************************
    @Override
    public void draw()
    {
        if (!active)
        {
            return;
        }

        SpacialMethodSource obj;
        int index = 0;
        Iterator itr = methodObjects.iterator();
        while (itr.hasNext())
        {
            obj = (SpacialMethodSource) methodObjects.get(index);
            obj.draw();
            index = index + 1;
            itr.next();
        }
    }
    //**************************************************************************
    public void nextIndex()
    {
        SpacialMethodSource obj;
        obj = (SpacialMethodSource) methodObjects.get(selectedIndex);
        obj.selected = false;
        selectedIndex = selectedIndex + 1;
        if(selectedIndex > methodObjects.size() - 1)
        {
            selectedIndex = 0;
        }
        obj = (SpacialMethodSource) methodObjects.get(selectedIndex);
        obj.selected = true;
    }

    //**************************************************************************
}
