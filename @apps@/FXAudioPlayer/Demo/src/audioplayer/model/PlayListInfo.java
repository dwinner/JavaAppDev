package audioplayer.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PlayListInfo
{
   private Mp3InfoRetriever objectRetriever; // Lazy, Part-Immutable-объект извлечения id3-тегов
   private final StringProperty artist;   // Исполнитель
   private final StringProperty track; // Трек
   private final StringProperty duration; // Продолжительность в секундах
   private final StringProperty bitrate;  // Битрейт
   private final StringProperty frequency;   // Частота дискретизации

   /**
    * Инициализация мета-информации для одного трека
    *
    * @param artistName Исполнитель
    * @param trackName Трек
    * @param playTime Продолжительность в секундах
    * @param trackBitrate Битрейт
    * @param freq Частота дискретизации
    */
   public PlayListInfo(String artistName,
                       String trackName,
                       String playTime,
                       String trackBitrate,
                       String freq)
   {
      artist = new SimpleStringProperty(artistName);
      track = new SimpleStringProperty(trackName);
      duration = new SimpleStringProperty(playTime);
      bitrate = new SimpleStringProperty(trackBitrate);
      frequency = new SimpleStringProperty(freq);
   }

   public Mp3InfoRetriever getObjectRetriever(boolean deepCopy)
   {
      return objectRetriever.copyInstance(deepCopy);
   }
   
   public Mp3InfoRetriever getObjectRetriever()
   {
      return getObjectRetriever(false);
   }

   public void setObjectRetriever(Mp3InfoRetriever objectRetriever)
   {
      if (this.objectRetriever == null && objectRetriever != null)
      {
         this.objectRetriever = objectRetriever;
         return;
      }
      assert false;
   }

   public String getArtist()
   {
      return artist.get();
   }

   public void setArtist(String anAtrist)
   {
      artist.set(anAtrist);
   }

   public StringProperty artistProperty()
   {
      return artist;
   }

   public String getTrack()
   {
      return track.get();
   }

   public void setTrack(String aTrack)
   {
      track.set(aTrack);
   }

   public StringProperty trackProperty()
   {
      return track;
   }

   public String getDuration()
   {
      return duration.get();
   }

   public void setDuration(String value)
   {
      duration.set(value);
   }

   public StringProperty durationProperty()
   {
      return duration;
   }

   public String getBitrate()
   {
      return bitrate.get();
   }

   public void setBitrate(String value)
   {
      bitrate.set(value);
   }

   public StringProperty bitrateProperty()
   {
      return bitrate;
   }

   public String getFrequency()
   {
      return frequency.get();
   }

   public void setFrequency(String value)
   {
      frequency.set(value);
   }

   public StringProperty frequencyProperty()
   {
      return frequency;
   }
}
