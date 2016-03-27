// Использование do-while для обработки выборки из меню
// -- простая справочная (help) система.
public class Menu
{
    public static void main(String args[]) throws java.io.IOException
    {
        char choice;

        do
        {
            System.out.println("Manual:");
            System.out.println("\t1. if");
            System.out.println("\t2. switch");
            System.out.println("\t3. while");
            System.out.println("\t4. do while");
            System.out.println("\t5. for\n");
            System.out.println("Choice a topic: ");
            choice = (char) System.in.read();
        }
        while (choice < '1' || choice > '5');
        System.out.println("\n");

        switch (choice)
        {
            case '1':
                System.out.println("Conditional statement if:\n");
                System.out.println("if(condition) statement1;");
                System.out.println("else statement2;");
                break;
            case '2':
                System.out.println("Switch operator:\n");
                System.out.println("switch(expression) {");
                System.out.println("\tcase value:");
                System.out.println("\toperator's sequence");
                System.out.println("break;");
                System.out.println("\t// ...");
                System.out.println("}");
                break;
            case '3':
                System.out.println("While cycle operator:\n");
                System.out.println("while (condition) cycle-body");
                break;
            case '4':
                System.out.println("do while cycle operator:\n");
                System.out.println(" do {");
                System.out.println("\tcycle body;");
                System.out.println("} while (condition);");
                break;
            case '5':
                System.out.println("Cycle operator for:\n");
                System.out.println("for (init; condition; iteration)");
                System.out.println("\tcycle body");
                break;
        }
    }
}
