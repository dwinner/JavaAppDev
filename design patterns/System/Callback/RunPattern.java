import java.io.IOException;
public class RunPattern{
    public static void main(String [] arguments){
        System.out.println("Example for the Callback pattern");
        System.out.println("This code will run two RMI objects to demonstrate");
        System.out.println(" callback capability. One will be CallbackClientImpl,");
        System.out.println(" which will request a project from the other remote");
        System.out.println(" object, CallbackServerImpl.");
        System.out.println("To demonstrate how the Callback pattern allows the");
        System.out.println(" client to perform independent processing, the main");
        System.out.println(" progam thread will go into a wait loop until the");
        System.out.println(" server sends the object to its client.");
        System.out.println();
        
        System.out.println("Running the RMI compiler (rmic)");
        System.out.println();
        try{
            Process p1 = Runtime.getRuntime().exec("rmic CallbackServerImpl");
            Process p2 = Runtime.getRuntime().exec("rmic CallbackClientImpl");
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
        
        System.out.println("Creating the client and server objects");
        System.out.println();
        CallbackServerImpl callbackServer = new CallbackServerImpl();
        CallbackClientImpl callbackClient = new CallbackClientImpl();
        
        System.out.println("CallbackClientImpl requesting a project");
        callbackClient.requestProject("New Java Project");
        
        try{
            while(!callbackClient.isProjectAvailable()){
                System.out.println("Project not available yet; sleeping for 2 seconds");
                Thread.sleep(2000);
            }
        }
        catch (InterruptedException exc){}
        System.out.println("Project retrieved: " + callbackClient.getProject());
    }
}