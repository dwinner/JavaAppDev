import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
public class CallbackServerWorkThread implements Runnable{
    private Thread processingThread;
    private String projectID;
    private String callbackMachine;
    private String callbackObjectName;
    
    public CallbackServerWorkThread(String newProjectID, String newCallbackMachine,
      String newCallbackObjectName){
        projectID = newProjectID;
        callbackMachine = newCallbackMachine;
        callbackObjectName = newCallbackObjectName;
        processingThread = new Thread(this);
        processingThread.start();
    }
    
    public void run(){
        Project result = getProject();
        sendProjectToClient(result);
    }
    
    private Project getProject(){
        return new Project(projectID, "Test project");
    }
    
    private void sendProjectToClient(Project project){
        try{
            String url = "//" + callbackMachine + "/" + callbackObjectName;
            Object remoteClient = Naming.lookup(url);
            if (remoteClient instanceof CallbackClient){
                ((CallbackClient)remoteClient).receiveProject(project);
            }
        }
        catch (RemoteException exc){}
        catch (NotBoundException exc){}
        catch (MalformedURLException exc){}
    }
}