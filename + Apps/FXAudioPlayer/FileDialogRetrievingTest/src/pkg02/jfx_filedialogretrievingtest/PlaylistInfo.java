package pkg02.jfx_filedialogretrievingtest;

import java.io.Serializable;
import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Сущностный класс для использования в TableView.
 *
 * @author dwinner@inbox.ru
 * @version 1.0.0 10-07-2012
 */
public class PlaylistInfo implements Serializable
{
   private Mp3InfoRetriever objectRetriever; // Объект извлечения мета-информации аудио
   private final StringProperty artist;      // Исполнитель
   private final StringProperty track;       // Трек
   private final StringProperty duration;    // Продолжительность в секундах
   private final StringProperty bitrate;     // Битрейт
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
   public PlaylistInfo(String artistName, String trackName, String playTime, String trackBitrate, String freq)
   {
      artist = new SimpleStringProperty(artistName);
      track = new SimpleStringProperty(trackName);
      duration = new SimpleStringProperty(playTime);
      bitrate = new SimpleStringProperty(trackBitrate);
      frequency = new SimpleStringProperty(freq);
   }

   /**
    * Объект извлечения мета-информации аудио
    *
    * @param deepCopy Если true, то вернуть точный прототип объекта
    * @return Объект извлечения мета-информации аудио
    */
   public Mp3InfoRetriever getObjectRetriever(boolean deepCopy)
   {
      return objectRetriever.copyInstance(deepCopy);
   }

   /**
    * Объект извлечения мета-информации аудио
    *
    * @return Объект извлечения мета-информации аудио
    */
   public Mp3InfoRetriever getObjectRetriever()
   {
      return getObjectRetriever(false);
   }

   /**
    * Установка объекта извлечения мета-информации аудио
    *
    * @param objectRetriever объекта извлечения мета-информации аудио
    */
   public void setObjectRetriever(Mp3InfoRetriever objectRetriever)
   {
      if (this.objectRetriever == null && objectRetriever != null)
      {
         this.objectRetriever = objectRetriever;
         return;
      }
      throw new AssertionError("This case must be unreachable");
   }

   /**
    * Получение исполнителя
    *
    * @return Исполнитель
    */
   public String getArtist()
   {
      return artist.get();
   }

   /**
    * Установка исполнителя
    *
    * @param anAtrist Исполнитель
    */
   public void setArtist(String anAtrist)
   {
      artist.set(anAtrist);
   }

   /**
    * JavaFX-свойство исполнителя
    *
    * @return JavaFX-свойство исполнителя
    */
   public StringProperty artistProperty()
   {
      return artist;
   }

   /**
    * Получение трека
    *
    * @return Трек
    */
   public String getTrack()
   {
      return track.get();
   }

   /**
    * Установка трека.
    *
    * @param aTrack Трек
    */
   public void setTrack(String aTrack)
   {
      track.set(aTrack);
   }

   /**
    * JavaFX-свойство трека
    *
    * @return JavaFX-свойство трека
    */
   public StringProperty trackProperty()
   {
      return track;
   }

   /**
    * Получение продолжительности трека в секундах
    *
    * @return Продолжительность трека в секундах
    */
   public String getDuration()
   {
      return duration.get();
   }

   /**
    * Установка продолжительности трека в секундах
    *
    * @param value Продолжительность трека в секундах
    */
   public void setDuration(String value)
   {
      duration.set(value);
   }

   /**
    * JavaFX-свойство продолжительности трека в секундах
    *
    * @return JavaFX-свойство продолжительности трека в секундах
    */
   public StringProperty durationProperty()
   {
      return duration;
   }

   /**
    * Получение битрейта
    *
    * @return Битрейт
    */
   public String getBitrate()
   {
      return bitrate.get();
   }

   /**
    * Установка битрейта
    *
    * @param value Битрейт
    */
   public void setBitrate(String value)
   {
      bitrate.set(value);
   }

   /**
    * JavaFX-свойство битрейта
    *
    * @return JavaFX-свойство битрейта
    */
   public StringProperty bitrateProperty()
   {
      return bitrate;
   }

   /**
    * Получение частоты дискретизации
    *
    * @return Частота дискретизации
    */
   public String getFrequency()
   {
      return frequency.get();
   }

   /**
    * Установка частоты дискретизации
    *
    * @param value Частота дискретизации
    */
   public void setFrequency(String value)
   {
      frequency.set(value);
   }

   /**
    * JavaFX-свойство частоты дискретизации
    *
    * @return JavaFX-свойство частоты дискретизации
    */
   public StringProperty frequencyProperty()
   {
      return frequency;
   }

}
