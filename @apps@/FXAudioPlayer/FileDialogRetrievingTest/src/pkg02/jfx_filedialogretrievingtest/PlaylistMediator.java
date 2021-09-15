package pkg02.jfx_filedialogretrievingtest;

import java.util.Objects;

/**
 * Сущностный класс-посредник, адаптированный для корректного сохранения плей-листа.
 *
 * @author dwinner@inbox.ru
 * @version 1.0.0 11-07-2012
 */
public class PlaylistMediator
{
   private String artist;
   private String track;
   private String playTime;
   private String bitrate;
   private String frequency;
   private String audioFileName;

   public PlaylistMediator(final PlaylistInfo outer)
   {
      artist = outer.getArtist();
      track = outer.getTrack();
      playTime = outer.getDuration();
      bitrate = outer.getBitrate();
      frequency = outer.getFrequency();
      audioFileName = outer.getObjectRetriever().getAudioFile().getAbsolutePath();
   }

   public PlaylistMediator(String artist, String track, String playTime, String bitrate, String frequency,
                        String audioFileName)
   {
      this.artist = artist;
      this.track = track;
      this.playTime = playTime;
      this.bitrate = bitrate;
      this.frequency = frequency;
      this.audioFileName = audioFileName;
   }

   public String getArtist()
   {
      return artist;
   }

   public void setArtist(String artist)
   {
      this.artist = artist;
   }

   public String getTrack()
   {
      return track;
   }

   public void setTrack(String track)
   {
      this.track = track;
   }

   public String getPlayTime()
   {
      return playTime;
   }

   public void setPlayTime(String playTime)
   {
      this.playTime = playTime;
   }

   public String getBitrate()
   {
      return bitrate;
   }

   public void setBitrate(String bitrate)
   {
      this.bitrate = bitrate;
   }

   public String getFrequency()
   {
      return frequency;
   }

   public void setFrequency(String frequency)
   {
      this.frequency = frequency;
   }

   public String getAudioFileName()
   {
      return audioFileName;
   }

   public void setAudioFileName(String audioFileName)
   {
      this.audioFileName = audioFileName;
   }

   @Override
   public int hashCode()
   {
      int hash = 7;
      hash = 37 * hash + Objects.hashCode(this.artist);
      hash = 37 * hash + Objects.hashCode(this.track);
      hash = 37 * hash + Objects.hashCode(this.playTime);
      hash = 37 * hash + Objects.hashCode(this.bitrate);
      hash = 37 * hash + Objects.hashCode(this.frequency);
      hash = 37 * hash + Objects.hashCode(this.audioFileName);
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
      final PlaylistMediator other = (PlaylistMediator) obj;
      if (!Objects.equals(this.artist, other.artist))
      {
         return false;
      }
      if (!Objects.equals(this.track, other.track))
      {
         return false;
      }
      if (!Objects.equals(this.playTime, other.playTime))
      {
         return false;
      }
      if (!Objects.equals(this.bitrate, other.bitrate))
      {
         return false;
      }
      if (!Objects.equals(this.frequency, other.frequency))
      {
         return false;
      }
      if (!Objects.equals(this.audioFileName, other.audioFileName))
      {
         return false;
      }
      return true;
   }

   @Override
   public String toString()
   {
      return "PlayListSaver{" + "artist=" + artist + ", track=" + track + ", playTime=" + playTime + ", bitrate=" + bitrate + ", frequency=" + frequency + ", audioFileName=" + audioFileName + '}';
   }
}
