package GLObjects;

import com.thoughtworks.qdox.JavaDocBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaPackage;
import com.thoughtworks.qdox.model.JavaSource;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ParseJava {
   

    //**************************************************************************
    public void parseFile(String filename, SpacialJavaClass jc) throws FileNotFoundException
    {
        JavaDocBuilder builder = new JavaDocBuilder();
        builder.addSource(new FileReader(filename));

        jc.javaSource = builder.getSources();
        
        JavaSource[] src = builder.getSources();
        JavaPackage pkg = src[0].getPackage();
        System.out.println(pkg.getName());

        JavaClass[] classes  = pkg.getClasses();
        String name = pkg.getName();
        String toString = pkg.toString();
        JavaPackage parent = pkg.getParentPackage();

    }
    //**************************************************************************
}
