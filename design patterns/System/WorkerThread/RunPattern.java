import java.io.IOException;
public class RunPattern{
    private static final String WORKER_SERVER_URL = "//localhost/workerThreadServer";
    public static void main(String [] arguments){
        System.out.println("Example for the WorkerThread pattern");
        System.out.println("In this example, a ConcreteQueue object which uses a");
        System.out.println(" worker thread, will retrieve a number of objects from");
        System.out.println(" the server.");
        System.out.println();
        
        System.out.println("Running the RMI compiler (rmic)");
        System.out.println();
        try{
            Process p1 = Runtime.getRuntime().exec("rmic ServerDataStoreImpl");
            p1.waitFor();
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
        
        System.out.println("Creating the queue, which will be managed by the worker thread");
        System.out.println();
        ConcreteQueue workQueue = new ConcreteQueue();
        
        System.out.println("Creating the RMI server object, ServerDataStoreImpl");
        System.out.println();
        ServerDataStore server = new ServerDataStoreImpl();
        
        System.out.println("Creating AddressRetrievers and ContactRetreivers.");
        System.out.println(" These will placed in the queue, as tasks to be");
        System.out.println(" performed by the worker thread.");
        System.out.println();
        AddressRetriever firstAddr = new AddressRetriever(5280L, WORKER_SERVER_URL);
        AddressRetriever secondAddr = new AddressRetriever(2010L, WORKER_SERVER_URL);
        ContactRetriever firstContact = new ContactRetriever(5280L, WORKER_SERVER_URL);
        
        workQueue.put(firstAddr);
        workQueue.put(firstContact);
        workQueue.put(secondAddr);
        
        while (!secondAddr.isAddressAvailable()){
            try{
                Thread.sleep(1000);
            }
            catch (InterruptedException exc){}
        }
        
        System.out.println("WorkerThread completed the processing of its Tasks");
        System.out.println("Printing out the retrieved objects now:");
        System.out.println();
        System.out.println(firstAddr.getAddress());
        System.out.println(firstContact.getContact());
        System.out.println(secondAddr.getAddress());
        
    }
    
}