package pkg02.jfx_filedialogretrievingtest;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.inbox.dwinner.id3library.ID3Wrapper;
import ru.inbox.dwinner.id3library.InvalidDataException;
import ru.inbox.dwinner.id3library.Mp3File;
import ru.inbox.dwinner.id3library.UnsupportedTagException;

/**
 * Класс, интегрирующий извлеченную информацию из mp3-файла.
 *
 * @author dwinner@inbox.ru
 * @version 1.0.0 25.06.2012
 */
public class Mp3InfoRetriever implements Cloneable
{
   private static final Logger LOG = Logger.getLogger(Mp3InfoRetriever.class.getName());
   private String artist;                       // Исполнитель
   private String trackName;                    // Название трека
   private int playTime;                        // Продолжительность записи в секундах
   private String playTimeString;               // Строка для отображения продолжительности записи
   private File audioFile;                      // Объект аудио-файла
   private ID3Wrapper id3Wrapper;               // Объект-оболочка для хранения мета-информации id3-тегов
   private int samplingFrequency;               // Частота дискретизации
   private String samplingFrequencyString;      // Строка отображения частоты дискретизации
   private int bitrate;                         // Битрейт
   private String bitrateString;                // Строка отображения битрейта
   private String channelMode;                  // Канальный режим записи
   private int trackNumber;                     // Номер трека в альбоме
   private int trackGenre;                      // Идентификатор жанра
   private String trackGenreDescription;        // Описание жанра
   private String albumName;                    // Название альбома
   private String albumYear;                    // Строка с годом выпуска
   private byte[] albumPhoto;                   // Ссылка на "сырой" массив байтов с изображением
   private String trackUrl;                     // URL для трека.

   /**
    * Создание объекта извлечения информации из mp3-файла
    *
    * @param fileName Имя файла
    * @throws IOException
    * @throws UnsupportedTagException
    * @throws InvalidDataException
    */
   public Mp3InfoRetriever(String fileName)
     throws IOException, UnsupportedTagException, InvalidDataException
   {
      audioFile = new File(fileName);

      // Извлечение информации из тегов

      Mp3File mp3File = new Mp3File(fileName);

      // Битрейт

      bitrate = mp3File.getBitrate();
      bitrateString = bitrate <= 0 ? "Unknown" : Integer.toString(bitrate) + " Kbps";   // TODO: Locate

      // Частота дискретизации

      samplingFrequency = mp3File.getSampleRate();
      samplingFrequencyString = (samplingFrequency <= 0)
        ? "Unknown" // TODO: Locate
        : Integer.toString(Math.round(samplingFrequency / 1000)) + " kHz";

      // Режим аудио

      channelMode = mp3File.getModeExtension();
      channelMode = (channelMode == null || channelMode.trim().isEmpty())
        ? "Unknown" // TODO: Locate
        : channelMode.trim();

      id3Wrapper = new ID3Wrapper(mp3File.getId3v1Tag(), mp3File.getId3v2Tag());

      // Артист

      artist = id3Wrapper.getArtist();
      artist = (artist == null || artist.trim().isEmpty()) ? "Unknown" : artist.trim();   // TODO: Locate

      // Название трека

      trackName = id3Wrapper.getTitle();
      trackName = (trackName == null || trackName.trim().isEmpty()) ? "Unknown" : trackName.trim();   // TODO: Locate

      // Время проигрывания в секундах

      playTime = (int) mp3File.getLengthInSeconds();
      playTimeString = playTime <= 0 ? "Unknown" : Integer.toString(playTime);    // TODO: Locate and format

      // Название альбома

      albumName = id3Wrapper.getAlbum();
      albumName = (albumName == null || albumName.trim().isEmpty()) ? "Unknown" : albumName.trim();   // TODO: Locate

      // Год альбома

      albumYear = id3Wrapper.getYear();   // TODO: Провести форматирование года
      albumYear = (albumYear == null || albumYear.trim().isEmpty()) ? "Unknown" : albumYear.trim();   // TODO: Locate

      // Номер трека в альбоме

      String trackNoString = id3Wrapper.getTrack();
      if (trackNoString == null || trackNoString.trim().isEmpty())
      {
         trackNumber = -1;
      }
      else
      {
         try
         {
            trackNumber = Integer.parseInt(trackNoString.trim());
         }
         catch (NumberFormatException nfEx)
         {
            trackNumber = -1;
         }
      }

      // Байтовый массив с изображением альбома

      albumPhoto = id3Wrapper.getAlbumImage();
      if (albumPhoto == null || albumPhoto.length == 0)
      {
         albumPhoto = new byte[0]/*(byte[]) Array.newInstance(byte.class, 0)*/;
      }

      // Url трека

      trackUrl = id3Wrapper.getUrl(); // TODO: Прицепить поисковый механизм (Google, Yahoo,...)
      trackUrl = (trackUrl == null || trackUrl.trim().isEmpty()) ? "Unknown" : trackUrl.trim(); // TODO: Locate

      trackGenre = id3Wrapper.getGenre();
      trackGenreDescription = id3Wrapper.getGenreDescription();
      trackGenreDescription = (trackGenreDescription == null || trackGenreDescription.trim().isEmpty())
        ? "Unknown" // TODO: Locate
        : trackGenreDescription.trim();
   }

   /**
    * Имя артиста
    *
    * @return Имя артиста
    */
   public String getArtist()
   {
      return artist;
   }

   /**
    * Название трека
    *
    * @return Название трека
    */
   public String getTrackName()
   {
      return trackName;
   }

   /**
    * Время проигрывания в секундах
    *
    * @return Время проигрывания в секундах
    */
   public int getPlayTime()
   {
      return playTime;
   }

   /**
    * Строка времени проигрывания
    *
    * @return Строка времени проигрывания
    */
   public String getPlayTimeString()
   {
      return playTimeString;
   }

   /**
    * Исходный объект аудио-файла
    *
    * @return Исходный объект аудио-файла
    */
   public File getAudioFile()
   {
      return audioFile;
   }

   /**
    * Объект информации ID3-тегов
    *
    * @return Объект информации ID3-тегов
    */
   public ID3Wrapper getId3Wrapper()
   {
      return id3Wrapper;
   }

   /**
    * Частота дискретизации
    *
    * @return Частота дискретизации
    */
   public int getSamplingFrequency()
   {
      return samplingFrequency;
   }

   /**
    * Строка частоты дискретизации
    *
    * @return Строка частоты дискретизации
    */
   public String getSamplingFrequencyString()
   {
      return samplingFrequencyString;
   }

   /**
    * Битрейт (в килобитах/секенду)
    *
    * @return Битрейт (в килобитах/секенду)
    */
   public int getBitrate()
   {
      return bitrate;
   }

   /**
    * Строка битрейта (в килобитах/секенду)
    *
    * @return Строка битрейта (в килобитах/секенду)
    */
   public String getBitrateString()
   {
      return bitrateString;
   }

   /**
    * Канальный режим записи
    *
    * @return Канальный режим записи
    */
   public String getChannelMode()
   {
      return channelMode;
   }

   /**
    * Оригинальный номер трека в альбоме
    *
    * @return Оригинальный номер трека в альбоме
    */
   public int getTrackNumber()
   {
      return trackNumber;
   }

   /**
    * Идентификатор жанра
    *
    * @return Идентификатор жанра
    */
   public int getTrackGenre()
   {
      return trackGenre;
   }

   /**
    * Строка идентификатора жанра
    *
    * @return Строка идентификатора жанра
    */
   public String getTrackGenreDescription()
   {
      return trackGenreDescription;
   }

   /**
    * Наименование альбома
    *
    * @return Наименование альбома
    */
   public String getAlbumName()
   {
      return albumName;
   }

   /**
    * Год выпуска альбома
    *
    * @return Год выпуска альбома
    */
   public String getAlbumYear()
   {
      return albumYear;
   }

   /**
    * Байтовый массив с изображением альбома
    *
    * @param clone Если true, то вернуть копию массива
    * @return Байтовый массив с изображением альбома
    */
   public byte[] getAlbumPhoto(boolean clone)
   {
      return clone ? Arrays.copyOf(albumPhoto, albumPhoto.length) : albumPhoto;
   }

   /**
    * Байтовый массив с изображением альбома
    *
    * @return Байтовый массив с изображением альбома
    */
   public byte[] getAlbumPhoto()
   {
      return getAlbumPhoto(false);
   }

   /**
    * URL трека
    *
    * @return URL трека
    */
   public String getTrackUrl()
   {
      return trackUrl;
   }

   @Override
   public int hashCode()
   {
      int hash = 3;
      hash = 13 * hash + Objects.hashCode(artist);
      hash = 13 * hash + Objects.hashCode(trackName);
      return hash;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (obj == null)
      {
         return false;
      }
      if (getClass() != obj.getClass())
      {
         return false;
      }
      final Mp3InfoRetriever other = (Mp3InfoRetriever) obj;      
      if (!Objects.equals(artist.toLowerCase(Locale.getDefault()),
                          other.getArtist().toLowerCase(Locale.getDefault())))
      {
         return false;
      }
      if (!Objects.equals(trackName.toLowerCase(), other.getTrackName().toLowerCase()))
      {
         return false;
      }
      return true;
   }

   @Override
   public String toString()
   {
      return "Mp3InfoRetriever{" + "artist=" + artist
        + ", trackName=" + trackName
        + ", playTime=" + playTime
        + ", playTimeString=" + playTimeString
        + ", audioFile=" + audioFile
        + ", id3Wrapper=" + id3Wrapper
        + ", samplingFrequency=" + samplingFrequency
        + ", samplingFrequencyString=" + samplingFrequencyString
        + ", bitrate=" + bitrate
        + ", bitrateString=" + bitrateString
        + ", channelMode=" + channelMode
        + ", trackNumber=" + trackNumber
        + ", trackGenre=" + trackGenre
        + ", trackGenreDescription=" + trackGenreDescription
        + ", albumName=" + albumName
        + ", albumYear=" + albumYear
        + ", albumPhoto=" + albumPhoto
        + ", trackUrl=" + trackUrl + '}';
   }

   @Override
   protected Object clone() throws CloneNotSupportedException
   {
      return super.clone();
   }

   /**
    * Точный прототип объекта
    *
    * @param deepClone Если true, то вернуть точный прототип, false вызывает стандартный метод Object.clone()
    * @return Прототип объекта
    */
   public Mp3InfoRetriever copyInstance(boolean deepClone)
   {
      if (deepClone)
      {
         try
         {
            return new Mp3InfoRetriever(audioFile.getAbsolutePath());
         }
         catch (IOException | UnsupportedTagException | InvalidDataException ex)
         {
            LOG.log(Level.SEVERE, null, ex);
            return null;
         }
      }
      else
      {
         try
         {
            return (Mp3InfoRetriever) clone();
         }
         catch (CloneNotSupportedException ex)
         {
            LOG.log(Level.SEVERE, null, ex);
            return null;
         }
      }
   }

   /**
    * Точный прототип объекта
    *
    * @return Точный прототип объекта
    */
   public Mp3InfoRetriever copyInstance()
   {
      return copyInstance(true);
   }
}
