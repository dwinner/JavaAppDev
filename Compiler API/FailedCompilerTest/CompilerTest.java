import javax.swing.*;
import javax.tools.*;
import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * @version 1.00 2007-11-02
 * @author Cay Horstmann
 */
public class CompilerTest
{
   public static void main(final String[] args) throws IOException
   {
      JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

      final List<ByteArrayJavaClass> classFileObjects = new ArrayList<>();

      DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();

      JavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
      fileManager = new ForwardingJavaFileManager<JavaFileManager>(fileManager)
      {
         @Override
         public JavaFileObject getJavaFileForOutput(Location location,
                                                    String className,
                                                    JavaFileObject.Kind kind,
                                                    FileObject sibling)
           throws IOException
         {
            if (className.startsWith("x."))
            {
               ByteArrayJavaClass fileObject = new ByteArrayJavaClass(className);
               classFileObjects.add(fileObject);
               return fileObject;
            }
            else
            {
               return super.getJavaFileForOutput(location, className, kind, sibling);
            }
         }
      };

      JavaFileObject source = buildSource("com.horstmann.corejava.ButtonFrame");
      JavaCompiler.CompilationTask task =
        compiler.getTask(null, fileManager, diagnostics, null, null, Arrays.asList(source));
      Boolean result = task.call();

      for (Diagnostic<? extends JavaFileObject> d : diagnostics.getDiagnostics())
         System.out.println(d.getKind() + ": " + d.getMessage(null));
      fileManager.close();

      if (!result)
      {
         System.out.println("Compilation failed.");   // Компиляция не удалась
         System.exit(1);
      }

      EventQueue.invokeLater(new Runnable()
      {
         @Override
         public void run()
         {
            try
            {
               Map<String, byte[]> byteCodeMap = new HashMap<>();
               for (ByteArrayJavaClass cl : classFileObjects)
                  byteCodeMap.put(cl.getName().substring(1), cl.getBytes());
               ClassLoader loader = new MapClassLoader(byteCodeMap);
               Class<?> cl = loader.loadClass("x.Frame");
               JFrame frame = (JFrame) cl.newInstance();
               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame.setTitle("CompilerTest");
               frame.setVisible(true);
            }
            catch (Exception ex)
            {
               ex.printStackTrace();
            }
         }
      });
   }

   /**
    * Создает исходный код для подкласса, который реализует метод addEventHandlers.
    * @param superclassName Имя суперкласса
    * @return Объект JavaFileObject
    * @throws IOException
    */
   static JavaFileObject buildSource(String superclassName) throws IOException
   {
      StringBuilderJavaSource source = new StringBuilderJavaSource("x.Frame");
      source.append("package x;\n");
      source.append("public class Frame extends " + superclassName + " {");
      source.append("protected void addEventHandlers() {");
      Properties props = new Properties();
      props.load(new FileReader("action.properties"));
      for (Map.Entry<Object, Object> e : props.entrySet())
      {
         String beanName = (String) e.getKey();
         String eventCode = (String) e.getValue();
         source.append(beanName + ".addActionListener(new java.awt.event.ActionListener() {)");
         source.append("public void actionPerformed(java.awt.event.ActionEvent event) {");
         source.append(eventCode);
         source.append("} } );");
      }
      source.append("} }");
      return source;
   }
}
