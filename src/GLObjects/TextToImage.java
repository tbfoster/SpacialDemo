package GLObjects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


class TextToImageDemo {

    //**************************************************************************
    public static void main(String[] args) throws IOException
    {
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
    }
    //**************************************************************************
}
