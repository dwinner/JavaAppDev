package audioplayer.model;

import audioplayer.model.mp3manipulator.ID3Wrapper;
import audioplayer.model.mp3manipulator.InvalidDataException;
import audioplayer.model.mp3manipulator.Mp3File;
import audioplayer.model.mp3manipulator.UnsupportedTagException;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс, интегрирующий извлеченную информацию из mp3-файла.
 *
 * @version 1.0.0 25.06.2012
 * @author oracle_pr1
 */
public class Mp3InfoRetriever implements Cloneable
{
   private String artist;  // Исполнитель
   private String trackName;   // Название трека
   private int playTime;   // Продолжительность записи в секундах
   private String playTimeString;  // Строка для отображения продолжительности записи
   private File audioFile; // Объект аудио-файла
   private ID3Wrapper id3Wrapper;  // Объект-оболочка для хранения мета-информации id3-тегов
   private int samplingFrequency;  // Частота дискретизации
   private String samplingFrequencyString; // Строка отображения частоты дискретизации
   private int bitrate;    // Битрейт
   private String bitrateString;   // Строка отображения битрейта
   private String channelMode; // Канальный режим записи
   private int trackNumber;    // Номер трека в альбоме
   private int trackGenre; // Идентификатор жанра
   private String trackGenreDescription;   // Описание жанра
   private String albumName;   // Название альбома
   private String albumYear;   // Строка с годом выпуска
   private byte[] albumPhoto;  // Ссылка на "сырой" массив байтов с изображением
   private String trackUrl;    // URL для трека.

   private Mp3InfoRetriever()
   {
      assert false;
   }

   public Mp3InfoRetriever(String fileName) throws IOException,
                                                   UnsupportedTagException,
                                                   InvalidDataException
   {
      audioFile = new File(fileName);

      // Извлечение информации из тегов

      Mp3File mp3File = new Mp3File(fileName);

      // Битрейт

      bitrate = mp3File.getBitrate();
      bitrateString = bitrate <= 0 ? "Unknown": Integer.toString(bitrate) + " bps";

      // Частота дискретизации

      samplingFrequency = mp3File.getSampleRate();
      samplingFrequencyString = (samplingFrequency <= 0)
        ? "Unknown"
        : Integer.toString(Math.round(samplingFrequency / 1000)) + " kHz";

      // Режим аудио

      channelMode = mp3File.getModeExtension();
      channelMode = (channelMode == null || channelMode.trim().isEmpty())
        ? "Unknown"
        : channelMode.trim();

      id3Wrapper = new ID3Wrapper(mp3File.getId3v1Tag(), mp3File.getId3v2Tag());

      // Артист

      artist = id3Wrapper.getArtist();
      artist = (artist == null || artist.trim().isEmpty()) ? "Unknown" : artist.trim();

      // Название трека

      trackName = id3Wrapper.getTitle();
      trackName = (trackName == null || trackName.trim().isEmpty()) ? "Unknown" : trackName.trim();

      // Время проигрывания в секундах

      playTime = (int) mp3File.getLengthInSeconds();
      playTimeString = playTime <= 0 ? "Unknown" : Integer.toString(playTime);    // TODO: Провести форматирование

      // Название альбома

      albumName = id3Wrapper.getAlbum();
      albumName = (albumName == null || albumName.trim().isEmpty()) ? "Unknown" : albumName.trim();

      // Год альбома

      albumYear = id3Wrapper.getYear();   // TODO: Провести форматирование года
      albumYear = (albumYear == null || albumYear.trim().isEmpty()) ? "Unknown" : albumYear.trim();

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
      trackUrl = (trackUrl == null || trackUrl.trim().isEmpty()) ? "Unknown" : trackUrl.trim();

      trackGenre = id3Wrapper.getGenre();
      trackGenreDescription = id3Wrapper.getGenreDescription();
      trackGenreDescription =
        (trackGenreDescription == null || trackGenreDescription.trim().isEmpty())
        ? "Unknown"
        : trackGenreDescription.trim();
   }

   public String getArtist()
   {
      return artist;
   }

   public String getTrackName()
   {
      return trackName;
   }

   public int getPlayTime()
   {
      return playTime;
   }

   public String getPlayTimeString()
   {
      return playTimeString;
   }

   public File getAudioFile()
   {
      return audioFile;
   }

   public ID3Wrapper getId3Wrapper()
   {
      return id3Wrapper;
   }

   public int getSamplingFrequency()
   {
      return samplingFrequency;
   }

   public String getSamplingFrequencyString()
   {
      return samplingFrequencyString;
   }

   public int getBitrate()
   {
      return bitrate;
   }

   public String getBitrateString()
   {
      return bitrateString;
   }

   public String getChannelMode()
   {
      return channelMode;
   }

   public int getTrackNumber()
   {
      return trackNumber;
   }

   public int getTrackGenre()
   {
      return trackGenre;
   }

   public String getTrackGenreDescription()
   {
      return trackGenreDescription;
   }

   public String getAlbumName()
   {
      return albumName;
   }

   public String getAlbumYear()
   {
      return albumYear;
   }

   public byte[] getAlbumPhoto(boolean clone)
   {
      return clone ? Arrays.copyOf(albumPhoto, albumPhoto.length) : albumPhoto;
   }

   public byte[] getAlbumPhoto()
   {
      return getAlbumPhoto(false);
   }

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
      // FIXME: Делать сравнение строк с учетом локальных установок
      if (!Objects.equals(artist.toLowerCase(), other.getArtist().toLowerCase()))
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

   public Mp3InfoRetriever copyInstance(boolean deepClone)
   {
      if (deepClone)
      {
         try
         {
            // TODO: Сгенерировать абсолютную копию объекта
            return new Mp3InfoRetriever(audioFile.getAbsolutePath());
         }
         catch (IOException | UnsupportedTagException | InvalidDataException ex)
         {
            Logger.getLogger(Mp3InfoRetriever.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Mp3InfoRetriever.class.getName()).log(Level.SEVERE, null, ex);
            return null;
         }
      }
   }
}
