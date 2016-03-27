public class SessionException extends Exception{
    public static final int CONTACT_BEING_EDITED = 1;
    public static final int SESSION_ID_REQUIRED = 2;
    public static final int CONTACT_SELECT_REQUIRED = 3;
    public static final int ADDRESS_DOES_NOT_EXIST = 4;
    private int errorCode;
    
    public SessionException(String cause, int newErrorCode){
        super(cause);
        errorCode = newErrorCode;
    }
    public SessionException(String cause){ super(cause); }
    
    public int getErrorCode(){ return errorCode; }
}