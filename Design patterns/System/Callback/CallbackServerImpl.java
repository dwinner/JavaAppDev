import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;
public class CallbackServerImpl implements CallbackServer{
    private static final String CALLBACK_SERVER_SERVICE_NAME = "callbackServer";
    public CallbackServerImpl(){
        try {
            UnicastRemoteObject.exportObject(this);
            Naming.rebind(CALLBACK_SERVER_SERVICE_NAME, this);
        }
        catch (Exception exc){
            System.err.println("Error using RMI to register the CallbackServerImpl " + exc);
        }
    }
    
    public void getProject(String projectID, String callbackMachine,
      String callbackObjectName){
        new CallbackServerDelegate(projectID, callbackMachine, callbackObjectName);
    }
    
}