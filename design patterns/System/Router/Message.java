import java.io.Serializable;
public class Message implements Serializable{
    private InputChannel source;
    private String message;
    
    public Message(InputChannel source, String message){
        this.source = source;
        this.message = message;
    }
    
    public InputChannel getSource(){ return source; }
    public String getMessage(){ return message; }
}