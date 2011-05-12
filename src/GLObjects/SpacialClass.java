package GLObjects;

import com.sun.opengl.util.GLUT;
import com.sun.org.apache.xerces.internal.xs.StringList;
import com.thoughtworks.qdox.model.JavaMethod;
import java.util.ArrayList;
import java.util.Iterator;
import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

public class SpacialClass {

    String className = "test";
    public JavaMethod[] methods;
    public float X = 0, Y = 0, Z = 0;

    //**************************************************************************
    SpacialClass(float vX, float vY, float vZ, String fileName)
    {
        ParseJava parser = new ParseJava();
        parser.parseFile(fileName);
        methods = parser.javaMethods;
        X = vX;
        Y = vY;
        Z = vZ;
    }
    //**************************************************************************

    public void addAllMethods(GL gl, GLU glu, GLUT glut, ArrayList vObjectList)
    {
        float y = Y;
        for (int i = 0; i < methods.length; i++)
        {
            SpacialMethod jc = new SpacialMethod(gl, glu, glut, X, y, Z, methods[i].getName());
            jc.compile();
            jc.active = true;
            vObjectList.add(jc);
            y = y - 1;
        }
    }
    //**************************************************************************
}
