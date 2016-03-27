import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class ThreadedServer{
    private static final int DEFAULT_SERVER_PORT = 2001;
    
    private boolean shutdown;
    private int serverPort = DEFAULT_SERVER_PORT;
    
    public void runServer(){
        try{
            ServerSocket mainServer = new ServerSocket(serverPort);
            while (!shutdown){
                Socket requestSocket = mainServer.accept();
                new ServerWorkThread(requestSocket);
            }
        }
        catch (IOException exc){
        }
    }
    
    public int getServerPort(){
        return serverPort;
    }
    
    public boolean isShutdown(){
        return shutdown;
    }
    
    public void setShutdown(boolean isShutdown){
        shutdown = isShutdown;
    }
    
    public void setServerPort(int newServerPort){
        serverPort = newServerPort;
    }
}