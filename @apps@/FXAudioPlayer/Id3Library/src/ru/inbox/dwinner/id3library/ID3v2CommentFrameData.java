package ru.inbox.dwinner.id3library;

import java.io.UnsupportedEncodingException;
import java.util.Objects;

public class ID3v2CommentFrameData extends AbstractID3v2FrameData
{
   private static final String DEFAULT_LANGUAGE = "eng";
   private String language;
   private EncodedText description;
   private EncodedText comment;

   public ID3v2CommentFrameData(boolean unsynchronisation)
   {
      super(unsynchronisation);
   }

   public ID3v2CommentFrameData(boolean unsynchronisation,
                                String language,
                                EncodedText description,
                                EncodedText comment)
   {
      super(unsynchronisation);
      this.language = language;
      this.description = description;
      this.comment = comment;
   }

   public ID3v2CommentFrameData(boolean unsynchronisation, byte[] bytes) throws
     InvalidDataException
   {
      super(unsynchronisation);
      synchroniseAndUnpackFrameData(bytes);
   }

   @Override
   protected void unpackFrameData(byte[] bytes) throws InvalidDataException
   {
      try
      {
         language = BufferTools.byteBufferToString(bytes, 1, 3);
      }
      catch (UnsupportedEncodingException e)
      {
         language = "";
      }
      int marker;
      for (marker = 4; marker < bytes.length; marker++)
      {
         if (bytes[marker] == 0)
         {
            break;
         }
      }
      description = new EncodedText(bytes[0], BufferTools.copyBuffer(bytes, 4, marker - 4));
      marker += description.getTerminator().length;
      int length = 0;
      for (int i = marker; i < bytes.length; i++)
      {
         if (bytes[i] == 0)
         {
            break;
         }
         length++;
      }
      comment = new EncodedText(bytes[0], BufferTools.copyBuffer(bytes, marker, length));
   }

   @Override
   protected byte[] packFrameData()
   {
      byte[] bytes = new byte[getLength()];
      if (comment != null)
      {
         bytes[0] = comment.getTextEncoding();
      }
      else
      {
         bytes[0] = 0;
      }
      String langPadded;
      if (language == null)
      {
         langPadded = DEFAULT_LANGUAGE;
      }
      else if (language.length() > 3)
      {
         langPadded = language.substring(0, 3);
      }
      else
      {
         langPadded = BufferTools.padStringRight(language, 3, '\00');
      }
      try
      {
         BufferTools.stringIntoByteBuffer(langPadded, 0, 3, bytes, 1);
      }
      catch (UnsupportedEncodingException e)
      {
      }
      int marker = 4;
      if (description != null)
      {
         byte[] descriptionBytes = description.toBytes(true, true);
         BufferTools.copyIntoByteBuffer(descriptionBytes, 0, descriptionBytes.length, bytes,
                                        marker);
         marker += descriptionBytes.length;
      }
      else
      {
         bytes[marker++] = 0;
      }
      if (comment != null)
      {
         byte[] commentBytes = comment.toBytes(true, false);
         BufferTools.copyIntoByteBuffer(commentBytes, 0, commentBytes.length, bytes, marker);
      }
      return bytes;
   }

   @Override
   protected int getLength()
   {
      int length = 4;
      if (description != null)
      {
         length += description.toBytes(true, true).length;
      }
      else
      {
         length++;
      }
      if (comment != null)
      {
         length += comment.toBytes(true, false).length;
      }
      return length;
   }

   public String getLanguage()
   {
      return language;
   }

   public void setLanguage(String language)
   {
      this.language = language;
   }

   public EncodedText getComment()
   {
      return comment;
   }

   public void setComment(EncodedText comment)
   {
      this.comment = comment;
   }

   public EncodedText getDescription()
   {
      return description;
   }

   public void setDescription(EncodedText description)
   {
      this.description = description;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (!(obj instanceof ID3v2CommentFrameData))
      {
         return false;
      }
      if (!super.equals(obj))
      {
         return false;
      }
      ID3v2CommentFrameData other = (ID3v2CommentFrameData) obj;
      if (language == null)
      {
         if (other.language != null)
         {
            return false;
         }
      }
      else if (other.language == null)
      {
         return false;
      }
      else if (!language.equals(other.language))
      {
         return false;
      }
      if (description == null)
      {
         if (other.description != null)
         {
            return false;
         }
      }
      else if (other.description == null)
      {
         return false;
      }
      else if (!description.equals(other.description))
      {
         return false;
      }
      if (comment == null)
      {
         if (other.comment != null)
         {
            return false;
         }
      }
      else if (other.comment == null)
      {
         return false;
      }
      else if (!comment.equals(other.comment))
      {
         return false;
      }
      return true;
   }

   @Override
   public int hashCode()
   {
      int hash = 5;
      hash = 41 * hash + Objects.hashCode(this.language);
      hash = 41 * hash + Objects.hashCode(this.description);
      hash = 41 * hash + Objects.hashCode(this.comment);
      return hash;
   }
}
