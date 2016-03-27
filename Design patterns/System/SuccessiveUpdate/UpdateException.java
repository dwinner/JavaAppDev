public class UpdateException extends Exception{
    public static final int TASK_UNCHANGED = 1;
    public static final int TASK_OUT_OF_DATE = 2;
    private int errorCode;
    
    public UpdateException(String cause, int newErrorCode){
        super(cause);
        errorCode = newErrorCode;
    }
    public UpdateException(String cause){ super(cause); }
    
    public int getErrorCode(){ return errorCode; }
}