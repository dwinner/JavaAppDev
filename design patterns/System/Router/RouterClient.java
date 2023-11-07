import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
public class RouterClient implements OutputChannel{
    private static final String ROUTER_CLIENT_SERVICE_PREFIX = "routerClient";
    private static final String ROUTER_SERVER_MACHINE_NAME = "localhost";
    private static final String ROUTER_SERVER_SERVICE_NAME = "router";
    private static int clientIndex = 1;
    private String routerClientServiceName = ROUTER_CLIENT_SERVICE_PREFIX + clientIndex++;
    private OutputChannel router;
    private Receiver receiver;
    
    public RouterClient(Receiver newReceiver){
        receiver = newReceiver;
        try {
            UnicastRemoteObject.exportObject(this);
            Naming.rebind(routerClientServiceName, this);
            String url = "//" + ROUTER_SERVER_MACHINE_NAME + "/" + ROUTER_SERVER_SERVICE_NAME;
            router = (OutputChannel)Naming.lookup(url);
        }
        catch (Exception exc){
            System.err.println("Error using RMI to register the Router " + exc);
        }
        
    }
    
    public void sendMessageToRouter(Message message){
        try{
            router.sendMessage(message);
        }
        catch (RemoteException exc){}
    }
    
    public void sendMessage(Message message){
        receiver.receiveMessage(message);
    }
    
    public String toString(){
        return routerClientServiceName;
    }
}
