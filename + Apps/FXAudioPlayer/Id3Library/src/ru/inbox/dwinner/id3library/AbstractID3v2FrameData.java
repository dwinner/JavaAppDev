package ru.inbox.dwinner.id3library;

import java.beans.PropertyChangeSupport;

public abstract class AbstractID3v2FrameData
{
   public static final String PROP_UNSYNCHRONISATION = "PROP_UNSYNCHRONISATION";
   private final transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(
     this);
   private boolean unsynchronisation;

   public AbstractID3v2FrameData(boolean unsynchronisation)
   {
      this.unsynchronisation = unsynchronisation;
   }

   protected void synchroniseAndUnpackFrameData(byte[] bytes) throws InvalidDataException
   {
      if (isUnsynchronisation() && BufferTools.sizeSynchronisationWouldSubtract(bytes) > 0)
      {
         byte[] synchronisedBytes = BufferTools.synchroniseBuffer(bytes);
         unpackFrameData(synchronisedBytes);
      }
      else
      {
         unpackFrameData(bytes);
      }
   }

   protected byte[] packAndUnsynchroniseFrameData()
   {
      byte[] bytes = packFrameData();
      if (isUnsynchronisation() && BufferTools.sizeUnsynchronisationWouldAdd(bytes) > 0)
      {
         return BufferTools.unsynchroniseBuffer(bytes);
      }
      return bytes;
   }

   protected byte[] toBytes()
   {
      return packAndUnsynchroniseFrameData();
   }

   @Override
   public boolean equals(Object obj)
   {
      if (!(obj instanceof AbstractID3v2FrameData))
      {
         return false;
      }
      AbstractID3v2FrameData other = (AbstractID3v2FrameData) obj;
      if (isUnsynchronisation() != other.isUnsynchronisation())
      {
         return false;
      }
      return true;
   }

   @Override
   public int hashCode()
   {
      int hash = 3;
      hash = 17 * hash + (this.isUnsynchronisation() ? 1 : 0);
      return hash;
   }

   protected abstract void unpackFrameData(byte[] bytes) throws InvalidDataException;

   protected abstract byte[] packFrameData();

   protected abstract int getLength();

   /**
    * @return the unsynchronisation
    */
   public boolean isUnsynchronisation()
   {
      return unsynchronisation;
   }

   /**
    * @param unsynchronisation the unsynchronisation to set
    */
   public void setUnsynchronisation(boolean unsynchronisation)
   {
      boolean oldUnsynchronisation = unsynchronisation;
      this.unsynchronisation = unsynchronisation;
      propertyChangeSupport.firePropertyChange(PROP_UNSYNCHRONISATION, oldUnsynchronisation,
                                               unsynchronisation);
   }
}
