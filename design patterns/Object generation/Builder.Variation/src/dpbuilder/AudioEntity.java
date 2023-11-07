package dpbuilder;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 28.12.12
 * Time: 23:56
 * Строитель объектов.
 */
public class AudioEntity
{
   private AudioEntity(Builder aBuilder)
   {
      name = aBuilder.name;
      duration = aBuilder.duration;
      bitrate = aBuilder.bitrate;
      url = aBuilder.url;
      group = aBuilder.group;
      year = aBuilder.year;
      recordFormat = aBuilder.recordFormat;
      album = aBuilder.album;
      size = aBuilder.size;
      genre = aBuilder.genre;
   }

   // <editor-fold defaultstate="collapsed" desc="Свойства">
   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public int getDuration()
   {
      return duration;
   }

   public void setDuration(int duration)
   {
      this.duration = duration;
   }

   public int getBitrate()
   {
      return bitrate;
   }

   public void setBitrate(int bitrate)
   {
      this.bitrate = bitrate;
   }

   public String getUrl()
   {
      return url;
   }

   public void setUrl(String url)
   {
      this.url = url;
   }

   public String getGroup()
   {
      return group;
   }

   public void setGroup(String group)
   {
      this.group = group;
   }

   public int getYear()
   {
      return year;
   }

   public void setYear(int year)
   {
      this.year = year;
   }

   public String getRecordFormat()
   {
      return recordFormat;
   }

   public void setRecordFormat(String recordFormat)
   {
      this.recordFormat = recordFormat;
   }

   public String getAlbum()
   {
      return album;
   }

   public void setAlbum(String album)
   {
      this.album = album;
   }

   public int getSize()
   {
      return size;
   }

   public void setSize(int size)
   {
      this.size = size;
   }

   public String getGenre()
   {
      return genre;
   }

   public void setGenre(String genre)
   {
      this.genre = genre;
   }

   public int getRate()
   {
      return rate;
   }

   public void setRate(int rate)
   {
      this.rate = rate;
   }
   // </editor-fold>

   private String name;          // Название композиции
   private int duration;         // Время композиции в секундах
   private int bitrate;          // Качество звучания
   private String url;           // Ссылка на композицию
   private String group;         // Группа или испольнитель
   private int year;             // Год записи
   private String recordFormat;  // Формат записи
   private String album;         // Название альбома
   private int size;             // Размер на диске
   private String genre;         // Жанр
   private int rate;             // Оценка исполнения

   /**
    * Генератор полей объекта.
    */
   public static class Builder
   {
      public Builder(String aName, int aDuration, int aBitrate, int aSize)
      {
         name = aName;
         duration = aDuration;
         bitrate = aBitrate;
         size = aSize;
      }

      public Builder buildUrl(String aUrl)
      {
         url = aUrl;
         return this;
      }

      public Builder buildGroup(String aGroup)
      {
         group = aGroup;
         return this;
      }

      public Builder buildYear(int aRecordYear)
      {
         year = aRecordYear;
         return this;
      }

      public Builder buildRecordFormat(String aRecordFormat)
      {
         recordFormat = aRecordFormat;
         return this;
      }

      public Builder buildAlbum(String anAlbum)
      {
         album = anAlbum;
         return this;
      }

      public Builder buildGenre(String aGenre)
      {
         genre = aGenre;
         return this;
      }

      public Builder buildRate(int aRate)
      {
         rate = aRate;
         return this;
      }

      public AudioEntity build()
      {
         return new AudioEntity(this);
      }

      // Обязательные поля
      private final String name;
      private final int duration;
      private final int bitrate;
      private final int size;

      // Необязательные поля
      private String url = "";
      private String group = "";
      private int year = -1;
      private String recordFormat = "";
      private String album = "";
      private String genre = "";
      private int rate = -1;
   }
}
