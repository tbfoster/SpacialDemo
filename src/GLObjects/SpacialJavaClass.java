package GLObjects;

import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;
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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
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
    public static ArrayList textureList = new ArrayList();
    //public Texture[] textures = new Texture[1];
    public Texture textures1, textures2, textures3;
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

        init();
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
        Globals.renderer.draw3D(className, 0, 0, 0, Globals.textScaleFactor);
        Globals.renderer.end3DRendering();
        Globals.renderer.begin3DRendering();
        Globals.renderer.draw3D("TEST", 5, 5, 5, Globals.textScaleFactor);
        Globals.renderer.end3DRendering();

        gl.glEndList();
    }
    //**************************************************************************

    @Override
    public void draw()
    {
        float size = 3;
        float vX=0f, vY=0f, vZ=0f;
        float width=8, height = 6;
        gl.glPushMatrix();

        gl.glTranslatef(X, Y, Z);
        gl.glColor3f(R, G, B);
        rotateX();
        rotateY();
        rotateZ();

        for (int index = 0; index < textureList.size(); index++)
        {
            Texture tempTexture = (Texture) textureList.get(index);

            tempTexture.setTexParameteri(gl.GL_TEXTURE_MIN_FILTER, gl.GL_LINEAR);
            tempTexture.setTexParameteri(gl.GL_TEXTURE_MAG_FILTER, gl.GL_LINEAR);
            tempTexture.setTexParameterf(gl.GL_TEXTURE_WRAP_S, gl.GL_CLAMP);
            tempTexture.setTexParameterf(gl.GL_TEXTURE_WRAP_T, gl.GL_CLAMP);
            gl.glTexEnvf(gl.GL_TEXTURE_ENV, gl.GL_TEXTURE_ENV_MODE, gl.GL_DECAL);
            tempTexture.enable();

            gl.glBegin(GL.GL_QUADS);
            gl.glTexCoord2f(0.0f,0.0f);   gl.glVertex3f(vX,  vY,  0);
            gl.glTexCoord2f(1.0f,0.0f);   gl.glVertex3f(vX+width, vY,  0);
            gl.glTexCoord2f(1.0f,1.0f);   gl.glVertex3f(vX+width, vY+height,  0);
            gl.glTexCoord2f(0.0f,1.0f);   gl.glVertex3f(vX,  vY+height,  0);
            tempTexture.disable();
            vY = vY + height;
        }

        gl.glEnd();
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
        String name = cls.getName();                     // "MyClass"
        String fullName = cls.getFullyQualifiedName();   // "com.blah.foo.MyClass";
        boolean isInterface = cls.isInterface();

        boolean isPublic = cls.isPublic();
        boolean isAbstract = cls.isAbstract();
        boolean isFinal = cls.isFinal();

        //Type superClass = (Type) cls.getSuperClass(); // "com.base.SubClass";
        //Type[] imps     = (Type[]) cls.getImplements(); // {"java.io.Serializable",
        //  "com.custom.CustomInterface"}
        JavaField nameField = cls.getFields()[0];
        JavaMethod doStuff[] = cls.getMethods();
        System.out.println("completed");
        textToImage(doStuff[1].getCodeBlock());
        System.out.println("completed");
        JavaMethod getNumber = cls.getMethods()[1];
        JavaSource javaSource = cls.getParentSource();

    }
    //**************************************************************************

    public BufferedImage textToImage(String sourceText)
    {
        Font font = new Font("Courier", Font.PLAIN, 24);
        FontRenderContext frc = new FontRenderContext(null, true, true);
        Rectangle2D bounds = font.getStringBounds(sourceText.toString(), frc);
        int w = (int) bounds.getWidth();
        int h = (int) bounds.getHeight();
        image = new BufferedImage(1024, 256, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, w, h);
        g.setColor(Color.GREEN);
        g.setFont(font);
        g.drawString(sourceText, 0, 25);
        g.dispose();
        return image;
    }
    //**************************************************************************

    public void init()
    {
        Texture tempTexture;
        BufferedImage img = new BufferedImage(1024, 256, BufferedImage.TYPE_INT_RGB);
        img = textToImage("public void main(int hexaware);");
        tempTexture = TextureIO.newTexture(img, false);
        textureList.add(tempTexture);

        img = textToImage("public void main(int hexaware);");
        tempTexture = TextureIO.newTexture(img, false);
        textureList.add(tempTexture);

        img = textToImage("xxxxxxxxx1xxxxxxxxx2xxxxxxxxx3xxxxxxxxx4xxxxxxxxx5xxxxxxxxx6xxxxxxxxx7xxxxxxxxx8xxxxxxxxx9xxxxxxxxx0");
        tempTexture = TextureIO.newTexture(img, false);
        textureList.add(tempTexture);
    }
}
