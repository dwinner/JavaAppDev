import java.util.Vector;
public class ConcreteQueue implements Queue{
    private Vector tasks = new Vector();
    private boolean waiting;
    private boolean shutdown;
    
    public void setShutdown(boolean isShutdown){ shutdown = isShutdown; }
    
    public ConcreteQueue(){
        tasks = new Vector();
        waiting = false;
        new Thread(new Worker()).start();
    }
    
    public void put(RunnableTask r){
        tasks.add(r);
        if (waiting){
            synchronized (this){
                notifyAll();
            }
        }
    }
    
    public RunnableTask take(){
        if (tasks.isEmpty()){
            synchronized (this){
                waiting = true;
                try{
                    wait();
                } catch (InterruptedException ie){
                    waiting = false;
                }
            }
        }
        return (RunnableTask)tasks.remove(0);
    }
    
    private class Worker implements Runnable{
        public void run(){
            while (!shutdown){
                RunnableTask r = take();
                r.execute();
            }
        }
    }
}