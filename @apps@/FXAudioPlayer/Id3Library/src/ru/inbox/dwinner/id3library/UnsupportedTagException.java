package ru.inbox.dwinner.id3library;

public class UnsupportedTagException extends BaseException
{
   private static final long serialVersionUID = 1L;

   public UnsupportedTagException()
   {
      super();
   }

   public UnsupportedTagException(String message)
   {
      super(message);
   }

   public UnsupportedTagException(String message, Throwable cause)
   {
      super(message, cause);
   }
}
