import java.io.*;
import org.apache.bcel.*;
import org.apache.bcel.classfile.*;
import org.apache.bcel.generic.*;

/**
 * Добавляет журналы для записей ко всем методам класса.
 * @version 1.10 2007-10-27
 * @author Cay Horstmann
 */
public class EntryLogger
{
   /**
    * Добавляет код регистрации записей к заданному классу.
    * @param args Имя подлежащего исправлению класса.
    */
   public static void main(String[] args)
   {
      try
      {
         if (args.length == 0) System.out.println("USAGE: java EntryLogger classname");
         else
         {
            JavaClass jc = Repository.lookupClass(args[0]);
            ClassGen cg = new ClassGen(jc);
            EntryLogger el = new EntryLogger(cg);
            el.convert();
            File f = new File(Repository.lookupClassFile(cg.getClassName()).getPath());
            cg.getJavaClass().dump(f.getPath());
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   /**
    * Создает инструмент EntryLogger, который вставляет регистрационный код
    * в аннотированные методы заданного класса.
    * @param cg Класс
    */
   public EntryLogger(ClassGen cg)
   {
      this.cg = cg;
      cpg = cg.getConstantPool();
   }

   /**
    * Преобразует класс путем вставки регистрационных вызовов.
    */
   public void convert() throws IOException
   {
      for (Method m : cg.getMethods())
      {
         AnnotationEntry[] annotations = m.getAnnotationEntries();
         for (AnnotationEntry a : annotations)
         {
            if (a.getAnnotationType().equals("LLogEntry;"))
            {
               for (ElementValuePair p : a.getElementValuePairs())
               {
                  if (p.getNameString().equals("logger"))
                  {
                     String loggerName = p.getValue().stringifyValue();
                     cg.replaceMethod(m, insertLogEntry(m, loggerName));
                  }
               }
            }
         }
      }
   }

   /**
    * Добавляет вызов регистратора в начало метода.
    * @param m Метод
    * @param loggerName Имя подлежащего вызову регистратора.
    */
   private Method insertLogEntry(Method m, String loggerName)
   {
      MethodGen mg = new MethodGen(m, cg.getClassName(), cpg);
      String className = cg.getClassName();
      String methodName = mg.getMethod().getName();
      System.out.printf("Adding logging instructions to %s.%s%n", className, methodName);

      int getLoggerIndex = cpg.addMethodref("java.util.logging.Logger", "getLogger",
            "(Ljava/lang/String;)Ljava/util/logging/Logger;");
      int enteringIndex = cpg.addMethodref("java.util.logging.Logger", "entering",
            "(Ljava/lang/String;Ljava/lang/String;)V");

      InstructionList il = mg.getInstructionList();
      InstructionList patch = new InstructionList();
      patch.append(new PUSH(cpg, loggerName));
      patch.append(new INVOKESTATIC(getLoggerIndex));
      patch.append(new PUSH(cpg, className));
      patch.append(new PUSH(cpg, methodName));
      patch.append(new INVOKEVIRTUAL(enteringIndex));
      InstructionHandle[] ihs = il.getInstructionHandles();
      il.insert(ihs[0], patch);

      mg.setMaxStack();
      return mg.getMethod();
   }

   private ClassGen cg;
   private ConstantPoolGen cpg;
}