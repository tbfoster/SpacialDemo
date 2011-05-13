package GLObjects;

import com.thoughtworks.qdox.JavaDocBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaField;
import com.thoughtworks.qdox.model.JavaMethod;
import com.thoughtworks.qdox.model.JavaPackage;
import com.thoughtworks.qdox.model.JavaSource;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ParseJava {

    public JavaSource[] javaSource;
    public JavaPackage javaPackage;
    public JavaClass[] javaClasses;
    public JavaMethod[] javaMethods;
    public String methodBlock;
    String currentMethodName;
    private int methodIndex = 0;
    private int methodMax = 0;

    //**************************************************************************
    public void parseFile(String parseFileName, String className)
    {
        methodIndex = 0;
        methodMax = 0;
        JavaDocBuilder builder = new JavaDocBuilder();
        try
        {
            builder.addSource(new FileReader(parseFileName));
        } catch (FileNotFoundException ex)
        {
            Logger.getLogger(SpacialMethodSource.class.getName()).log(Level.SEVERE, null, ex);
        }

        JavaClass cls = builder.getClassByName(className);

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
        javaMethods = cls.getMethods();

        methodMax = javaMethods.length;
        methodBlock = javaMethods[methodIndex].getCodeBlock();
        currentMethodName = javaMethods[methodIndex].getName();
        methodIndex = methodIndex + 1;

        JavaMethod getNumber = cls.getMethods()[1];
        JavaSource javaSource = cls.getParentSource();

    }
    //**************************************************************************

    public String getNextMethodBlock()
    {
        String returnMethod = javaMethods[methodIndex].getCodeBlock();
        currentMethodName = javaMethods[methodIndex].getName();
        methodIndex = methodIndex + 1;
        return returnMethod;
    }
    //**************************************************************************

    public boolean moreMethods()
    {
        if (methodIndex < methodMax)
        {
            return true;
        }
        return false;
    }
    //**************************************************************************
}
