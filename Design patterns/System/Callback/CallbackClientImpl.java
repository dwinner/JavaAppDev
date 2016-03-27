import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
public class CallbackClientImpl implements CallbackClient{
    private static final String CALLBACK_CLIENT_SERVICE_NAME = "callbackClient";
    private static final String CALLBACK_SERVER_SERVICE_NAME = "callbackServer";
    private static final String CALLBACK_SERVER_MACHINE_NAME = "localhost";
    
    private Project requestedProject;
    private boolean projectAvailable;
    
    public CallbackClientImpl(){
        try {
            UnicastRemoteObject.exportObject(this);
            Naming.rebind(CALLBACK_CLIENT_SERVICE_NAME, this);
        }
        catch (Exception exc){
            System.err.println("Error using RMI to register the CallbackClientImpl " + exc);
        }
    }
    
    public void receiveProject(Project project){
        requestedProject = project;
        projectAvailable = true;
    }
    
    public void requestProject(String projectName){
        try{
            String url = "//" + CALLBACK_SERVER_MACHINE_NAME + "/" + CALLBACK_SERVER_SERVICE_NAME;
            Object remoteServer = Naming.lookup(url);
            if (remoteServer instanceof CallbackServer){
                ((CallbackServer)remoteServer).getProject(projectName,
                   InetAddress.getLocalHost().getHostName(),
                   CALLBACK_CLIENT_SERVICE_NAME);
            }
            projectAvailable = false;
        }
        catch (RemoteException exc){}
        catch (NotBoundException exc){}
        catch (MalformedURLException exc){}
        catch (UnknownHostException exc){}
    }
    
    public Project getProject(){ return requestedProject; }
    public boolean isProjectAvailable(){ return projectAvailable; }
}