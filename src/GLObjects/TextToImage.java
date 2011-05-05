package GLObjects;

import com.thoughtworks.qdox.JavaDocBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaPackage;
import com.thoughtworks.qdox.model.JavaSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

class TextToImageDemo {

    //**************************************************************************
    public static void main(String[] args)
    {
        try
        {
            //ParseJava pj = new ParseJava();
            parseFile("/home/tbfoster/NetBeansProjects/SpacialDemo/SpacialDemo/src/GLObjects/Demo.java");
            /*
            StringBuffer test = new StringBuffer();
            //StringBuffer test = StringBuffer();
            test = test.append("public static void main(String[] args) throws IOException will be too wide");
            test = test.append("{");
            test = test.append("    printf();");
            test = test.append("}");
            String sampleText = "public static void main(String[] args) throws IOException will be too wide";
            File newFile = new File("test.jpg");
            Font font = new Font("Courier", Font.PLAIN, 16);
            FontRenderContext frc = new FontRenderContext(null, true, true);
            System.out.println(test.toString());
            Rectangle2D bounds = font.getStringBounds(test.toString(), frc);
            int w = (int) bounds.getWidth();
            int h = (int) bounds.getHeight();
            BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = image.createGraphics();
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, w, h);
            g.setColor(Color.GREEN);
            g.setFont(font);
            //g.drawString(sampleText, (float) bounds.getX(), (float) -bounds.getY());
            g.drawString(test.toString(), (float) bounds.getX(), (float) -bounds.getY());
            g.dispose();
            boolean write = ImageIO.write(image, "jpg", newFile);
            System.out.println(write);
             *
             */
        } catch (FileNotFoundException ex)
        {
            Logger.getLogger(TextToImageDemo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    //**************************************************************************

    public static void parseFile(String filename) throws FileNotFoundException
    {
        JavaDocBuilder builder = new JavaDocBuilder();
        builder.addSource(new FileReader(filename));

        JavaSource[] src = builder.getSources();
        JavaPackage pkg = src[0].getPackage();
        System.out.println(pkg.getName());

        JavaClass[] classes = pkg.getClasses();
        for (int i = 0; i < classes.length; i++)
        {
            System.out.println(classes[i].toString());
            JavaMethod[] methods = classes[i].getMethods();
            for (int j = 0; j < methods.length; j++)
            {
                //System.out.println(methods[j].toString());
                System.out.println(methods[j].getCodeBlock());

            }
            String name = pkg.getName();
            String toString = pkg.toString();
            //JavaPackage parent = pkg.getParentPackage();
        }
    }
}
