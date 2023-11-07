import java.net.Socket;
import java.util.Date;
import java.io.*;

public class ServerWorkThread implements Runnable{
    private Thread processingThread;
    private Socket requestSocket;
    private Command command;
    
    public ServerWorkThread(Socket clientRequestSocket){
        requestSocket = clientRequestSocket;
        processingThread = new Thread(this);
        processingThread.start();
    }
    
    private void retrieveCommand(){
        try{
            ObjectInputStream in = new ObjectInputStream(requestSocket.getInputStream());
            Object request = in.readObject();
            requestSocket.close();
            if (request instanceof Command){
                command = (Command)request;
            }
        }
        catch (ClassNotFoundException exc){
        }
        catch (IOException exc){
        }
    }
    
    protected void processCommand(){
    }
    
    public void run(){
        retrieveCommand();
        processCommand();
    }
    
    public Command getCommand(){
        return command;
    }
    
    protected Socket getRequestSocket(){
        return requestSocket;
    } 
}