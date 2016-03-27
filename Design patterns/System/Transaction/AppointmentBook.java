import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;
import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
public class AppointmentBook implements AppointmentTransactionParticipant{
    private static final String TRANSACTION_SERVICE_PREFIX = "transactionParticipant";
    private static final String TRANSACTION_HOSTNAME = "localhost";
    private static int index = 1;
    private String serviceName = TRANSACTION_SERVICE_PREFIX + index++;
    private HashMap appointments = new HashMap();
    private long currentTransaction;
    private Appointment currentAppointment;
    private Date updateStartDate;
    
    public AppointmentBook(){
        try {
            UnicastRemoteObject.exportObject(this);
            Naming.rebind(serviceName, this);
        }
        catch (Exception exc){
            System.err.println("Error using RMI to register the AppointmentBook " + exc);
        }
    }
    
    public String getUrl(){
        return "//" + TRANSACTION_HOSTNAME + "/" + serviceName;
    }
    
    public void addAppointment(Appointment appointment){
        if (!appointments.containsValue(appointment)){
            if (!appointments.containsKey(appointment.getStartDate())){
                appointments.put(appointment.getStartDate(), appointment);
            }
        }
    }
    public void removeAppointment(Appointment appointment){
        if (appointments.containsValue(appointment)){
            appointments.remove(appointment.getStartDate());
        }
    }
    
    public boolean join(long transactionID){
        if (currentTransaction != 0){
            return false;
        } else {
            currentTransaction = transactionID;
            return true;
        }
    }
    public void commit(long transactionID) throws TransactionException{
        if (currentTransaction != transactionID){
            throw new TransactionException("Invalid TransactionID");
        } else {
            removeAppointment(currentAppointment);
            currentAppointment.setStartDate(updateStartDate);
            appointments.put(updateStartDate, currentAppointment);
        }
    }
    public void cancel(long transactionID){
        if (currentTransaction == transactionID){
            currentTransaction = 0;
            appointments.remove(updateStartDate);
        }
    }
    public boolean changeDate(long transactionID, Appointment appointment,
      Date newStartDate) throws TransactionException{
        if ((appointments.containsValue(appointment)) && (!appointments.containsKey(newStartDate))){
            appointments.put(newStartDate, null);
            updateStartDate = newStartDate;
            currentAppointment = appointment;
            return true;
        }
        return false;
    }
    
    public boolean changeAppointment(Appointment appointment, Date[] possibleDates,
      AppointmentTransactionParticipant[] participants, long transactionID){
        try{
            for (int i = 0; i < participants.length; i++){
                if (!participants[i].join(transactionID)){
                    return false;
                }
            }
            for (int i = 0; i < possibleDates.length; i++){
                if (isDateAvailable(transactionID, appointment, possibleDates[i], participants)){
                    try{
                        commitAll(transactionID, participants);
                        return true;
                    }
                    catch(TransactionException exc){ }
                }
            }
        }
        catch (RemoteException exc){ }
        try{
            cancelAll(transactionID, participants);
        }
        catch (RemoteException exc){}
        return false;
    }
    
    private boolean isDateAvailable(long transactionID, Appointment appointment,
      Date date, AppointmentTransactionParticipant[] participants){
        try{
            for (int i = 0; i < participants.length; i++){
                try{
                    if (!participants[i].changeDate(transactionID, appointment, date)){
                        return false;
                    }
                }
                catch (TransactionException exc){
                    return false;
                }
            }
        }
        catch (RemoteException exc){
            return false;
        }
        return true;
    }
    private void commitAll(long transactionID, AppointmentTransactionParticipant[] participants)
      throws TransactionException, RemoteException{
        for (int i = 0; i < participants.length; i++){
            participants[i].commit(transactionID);
        }
    }
    private void cancelAll(long transactionID, AppointmentTransactionParticipant[] participants)
      throws RemoteException{
        for (int i = 0; i < participants.length; i++){
            participants[i].cancel(transactionID);
        }
    }
    public String toString(){
        return serviceName + " " + appointments.values().toString();
    }
}