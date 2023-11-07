import java.io.IOException;
public class RunPattern{
    public static void main(String [] arguments){
        System.out.println("Example for the Router pattern");
        System.out.println("This code same will create a series of GUIs, and use");
        System.out.println(" the Router pattern to map message notifications between");
        System.out.println(" them. In this code example, the Router will send messages");
        System.out.println(" between the GUI clients based on the following mapping:");
        System.out.println();
        System.out.println("\tGUI # 1:\tGUI #2\tGUI #3");
        System.out.println("\tGUI # 2:\tGUI #1\tGUI #4");
        System.out.println("\tGUI # 3:\tGUI #1\tGUI #4");
        System.out.println("\tGUI # 4:\tGUI #1\tGUI #2\tGUI #3\tGUI #4");
        System.out.println();
        
        System.out.println("Running the RMI compiler (rmic)");
        try{
            Process p1 = Runtime.getRuntime().exec("rmic Router");
            Process p2 = Runtime.getRuntime().exec("rmic RouterClient");
            p1.waitFor();
            p2.waitFor();
        }
        catch (IOException exc){
            System.err.println("Unable to run rmic utility. Exiting application.");
            System.exit(1);
        }
        catch (InterruptedException exc){
            System.err.println("Threading problems encountered while using the rmic utility.");
        }
        
        
        System.out.println("Starting the rmiregistry");
        System.out.println();
        Process rmiProcess = null;
        try{
            rmiProcess = Runtime.getRuntime().exec("rmiregistry");
            Thread.sleep(15000);
        }
        catch (IOException exc){
            System.err.println("Unable to start the rmiregistry. Exiting application.");
            System.exit(1);
        }
        catch (InterruptedException exc){
            System.err.println("Threading problems encountered when starting the rmiregistry.");
        }
        
        System.out.println("Creating the Router object");
        System.out.println();
        Router mainRouter = new Router();
        
        InputKey keyOne = new InputKey();
        InputKey keyTwo = new InputKey();
        InputKey keyThree = new InputKey();
        InputKey keyFour = new InputKey();
        
        System.out.println("Creating the four RouterGui objects");
        System.out.println();
        RouterGui first = new RouterGui(keyOne);
        RouterGui second = new RouterGui(keyTwo);
        RouterGui third = new RouterGui(keyThree);
        RouterGui fourth = new RouterGui(keyFour);
        
        System.out.println("Creating GUI OutputChannel lists for the Router");
        System.out.println();
        OutputChannel [] subscriptionListOne = { second.getOutputChannel(), third.getOutputChannel() };
        OutputChannel [] subscriptionListTwo = { first.getOutputChannel(), fourth.getOutputChannel() };
        OutputChannel [] subscriptionListThree = { first.getOutputChannel(), second.getOutputChannel(),
                                                   third.getOutputChannel(), fourth.getOutputChannel() };
        
        mainRouter.addRoute(keyOne, subscriptionListOne);
        mainRouter.addRoute(keyTwo, subscriptionListTwo);
        mainRouter.addRoute(keyThree, subscriptionListTwo);
        mainRouter.addRoute(keyFour, subscriptionListThree);
        
        first.createGui();
        second.createGui();
        third.createGui();
        fourth.createGui();
    }
}