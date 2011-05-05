package GLObjects;

import com.thoughtworks.qdox.JavaDocBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaField;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaPackage;
import com.thoughtworks.qdox.model.JavaSource;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.media.j3d.Texture;
import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

public class SpacialJavaClass extends SpacialObject {

    public static DirectionVector dirVector = new DirectionVector(0, 0, 0);
    String className = "test";
    String parseFileName = "";
    public JavaSource[] javaSource;
    public JavaPackage javaPackage;
    public JavaClass[] javaClasses;
    public JavaMethod[] javaMethods;
    public boolean viewFunction = false;
    public static Texture text;
    BufferedImage image;

    //**************************************************************************
    public SpacialJavaClass(GL vgl, GLU vglu, float vX, float vY, float vZ)
    {
        super(vgl, vglu, vX, vY, vZ);
        //LoadGLTextures();
    }
    //**************************************************************************

    public SpacialJavaClass(GL vgl, GLU vglu, float vX, float vY, float vZ, String vName, String filePath)
    {
        super(vgl, vglu, vX, vY, vZ);
        className = vName;
        parseFileName = filePath;

        parseFile();
        //System.out.println(javaSource);
        compile();
        //LoadGLTextures();
    }

    //**************************************************************************
    @Override
    public void compile()
    {
        float faceSize = 04f;
        float halfFaceSize = faceSize / 2;

        genListID = gl.glGenLists(Globals.genListIndex);
        gl.glNewList(genListID, gl.GL_COMPILE);

        gl.glColor3f(R, G, B);
        Globals.renderer.begin3DRendering();
        gl.glDisable(GL.GL_DEPTH_TEST);
        //gl.glEnable(GL.GL_CULL_FACE);
        //Rectangle2D bounds = Globals.renderer.getBounds(className);
        //float w = (float) bounds.getWidth();
        //float h = (float) bounds.getHeight();
        //Globals.renderer.draw3D(className, w / -2.0f * Globals.textScaleFactor, h / -2.0f * Globals.textScaleFactor, halfFaceSize, Globals.textScaleFactor);
        Globals.renderer.draw3D(className, 0, 0, 0, Globals.textScaleFactor);
        Globals.renderer.end3DRendering();
        Globals.renderer.begin3DRendering();
        Globals.renderer.draw3D("TEST", 5, 5, 5, Globals.textScaleFactor);

        //Globals.renderer.draw3D(className, w / -2.0f * Globals.textScaleFactor, h / -2.0f * Globals.textScaleFactor, halfFaceSize, Globals.textScaleFactor);

        if (viewFunction)
        {
            for (int i = 0; i < javaSource.length; i++)
            {
                //Globals.renderer.draw3D(javaSource[i].toString(), -2f,5f,0,Globals.textScaleFactor);
                //bounds = Globals.renderer.getBounds(javaClasses[i].toString());
                //w = (float) bounds.getWidth();
                //h = (float) bounds.getHeight();
                //Globals.renderer.draw3D(className, w / -2.0f * Globals.textScaleFactor, h / -2.0f * Globals.textScaleFactor, halfFaceSize, Globals.textScaleFactor);
            }

        }

        Globals.renderer.end3DRendering();

        gl.glEndList();
    }
    //**************************************************************************

    @Override
    public void draw()
    {
        if (viewFunction)
        {
            drawFunction();
        } else
        {
            gl.glPushMatrix();
            gl.glTranslatef(X, Y, Z);
            rotateX();
            rotateY();
            rotateZ();
            gl.glCallList(genListID);
            gl.glPopMatrix();
        }
    }

    //**************************************************************************    
    public void drawFunction()
    {
        float faceSize = 04f;
        float halfFaceSize = faceSize / 2;

        gl.glPushMatrix();
        gl.glTranslatef(X, Y, Z);
        rotateX();
        rotateY();
        rotateZ();
        gl.glColor3f(R, G, B);
        Globals.renderer.begin3DRendering();
        gl.glDisable(GL.GL_DEPTH_TEST);
        //gl.glEnable(GL.GL_CULL_FACE);

        for (int i = 0; i < javaClasses.length; i++)
        {
            Rectangle2D bounds = Globals.renderer.getBounds(javaClasses[i].toString());
            float w = (float) bounds.getWidth();
            float h = (float) bounds.getHeight();

            Globals.renderer.draw3D(className, w / -2.0f * Globals.textScaleFactor, h / -2.0f * Globals.textScaleFactor, halfFaceSize, Globals.textScaleFactor);
        }

        Globals.renderer.end3DRendering();

        gl.glPopMatrix();
    }

    //**************************************************************************
    public void parseFile()
    {
        JavaDocBuilder builder = new JavaDocBuilder();
        try
        {
            builder.addSource(new FileReader(parseFileName));
        } catch (FileNotFoundException ex)
        {
            Logger.getLogger(SpacialJavaClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    JavaClass cls = builder.getClassByName("GLObjects.Demo");
    
    //String pkg      = cls.getPackage();            // "com.blah.foo"
    String name     = cls.getName();               // "MyClass"
    String fullName = cls.getFullyQualifiedName(); // "com.blah.foo.MyClass";
    boolean isInterface = cls.isInterface();       // false
    
    boolean isPublic   = cls.isPublic();   // true
    boolean isAbstract = cls.isAbstract(); // true
    boolean isFinal    = cls.isFinal();    // false
    
    //Type superClass = (Type) cls.getSuperClass(); // "com.base.SubClass";
    //Type[] imps     = (Type[]) cls.getImplements(); // {"java.io.Serializable",
                                           //  "com.custom.CustomInterface"}
    JavaField nameField = cls.getFields()[0];
    JavaMethod doStuff[] = cls.getMethods();
    System.out.println(doStuff[1].getCodeBlock());
    image = textToImage(doStuff[1].getCodeBlock());
    //for(int i = 0;i<doStuff.length;i++)
    //{
    //System.out.println(doStuff[i].getName());
    //}
    JavaMethod getNumber = cls.getMethods()[1];
    JavaSource javaSource = cls.getParentSource();
        
    }
    //**************************************************************************

    public BufferedImage textToImage(String text)
    {
        
        Font font = new Font("Courier", Font.PLAIN, 16);
        FontRenderContext frc = new FontRenderContext(null, true, true);
        Rectangle2D bounds = font.getStringBounds(text.toString(), frc);
        int w = (int) bounds.getWidth();
        int h = (int) bounds.getHeight();
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, w, h);
        g.setColor(Color.GREEN);
        g.setFont(font);
        //g.drawString(sampleText, (float) bounds.getX(), (float) -bounds.getY());
        g.drawString(text.toString(), (float) bounds.getX(), (float) -bounds.getY());
        g.dispose();
        return image;
        
        //boolean write = false;
        //try
       // {
       //     write = ImageIO.write(image, "jpg", newFile);
       // } catch (IOException ex)
       // {
       //     Logger.getLogger(SpacialJavaClass.class.getName()).log(Level.SEVERE, null, ex);
       // }
       // System.out.println(write);
    }
    //**************************************************************************
}
