package audioplayer.model.mp3manipulator;

public class InvalidDataException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public InvalidDataException()
    {
        super();
    }

    public InvalidDataException(String message)
    {
        super(message);
    }

    public InvalidDataException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
