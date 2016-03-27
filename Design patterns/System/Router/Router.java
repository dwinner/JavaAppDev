import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
public class Router implements OutputChannel{
    private static final String ROUTER_SERVICE_NAME = "router";
    private HashMap links = new HashMap();
    
    public Router(){
        try {
            UnicastRemoteObject.exportObject(this);
            Naming.rebind(ROUTER_SERVICE_NAME, this);
        }
        catch (Exception exc){
            System.err.println("Error using RMI to register the Router " + exc);
        }
    }
    
    public synchronized void sendMessage(Message message) {
        Object key = message.getSource();
        OutputChannel[] destinations = (OutputChannel[])links.get(key);
        new RouterWorkThread(message, destinations);
    }
    
    public void addRoute(InputChannel source, OutputChannel[] destinations) {
        links.put(source, destinations);
    }
    
    private class RouterWorkThread implements Runnable{
        private OutputChannel [] destinations;
        private Message message;
        private Thread runner;
        
        private RouterWorkThread(Message newMessage, OutputChannel[] newDestinations){
            message = newMessage;
            destinations = newDestinations;
            runner = new Thread(this);
            runner.start();
        }
        
        public void run() {
            for (int i = 0; i < destinations.length; i++){
                try{
                    destinations[i].sendMessage(message);
                }
                catch(RemoteException exc){
                    System.err.println("Unable to send message to " + destinations[i]);
                }
            }
        }
    }
}