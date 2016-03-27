package audioplayer.model;

import javafx.beans.property.SimpleStringProperty;

/**
 * Класс сущности, адаптированный для отображения в TableView
 *
 * @version 1.0.0 26.06.2012
 * @author oracle_pr1
 */
public class PlayListDataEntity
{
    private Mp3InfoRetriever retriever;

    public final Mp3InfoRetriever getRetriever()
    {
        return retriever;
    }
    private final SimpleStringProperty artist;
    private final SimpleStringProperty trackName;
    private final SimpleStringProperty duration;
    private final SimpleStringProperty bitrate;
    private final SimpleStringProperty sampleFrequency;

    public PlayListDataEntity(Mp3InfoRetriever mp3InfoRetriever)
    {
        retriever = mp3InfoRetriever;

        artist = new SimpleStringProperty(mp3InfoRetriever.getArtist());
        trackName = new SimpleStringProperty(mp3InfoRetriever.getTrackName());
        duration = new SimpleStringProperty(mp3InfoRetriever.getPlayTimeString());
        bitrate = new SimpleStringProperty(mp3InfoRetriever.getBitrateString());
        sampleFrequency =
           new SimpleStringProperty(mp3InfoRetriever.getSamplingFrequencyString());
    }

    public String getArtistProperty()
    {
        return artist.get();
    }

    public void setArtistProperty(String artist)
    {
        this.artist.set(artist);
    }

    public String getTrackNameProperty()
    {
        return trackName.get();
    }

    public void setTrackNameProperty(String trackName)
    {
        this.trackName.set(trackName);
    }

    public String getDurationProperty()
    {
        return duration.get();
    }

    public void setDurationProperty(String duration)
    {
        this.duration.set(duration);
    }

    public String getBitrateProperty()
    {
        return bitrate.get();
    }

    public void setBitrateProperty(String bitrate)
    {
        this.bitrate.set(bitrate);
    }

    public String getSampleFrequencyProperty()
    {
        return sampleFrequency.get();
    }

    public void setSampleFrequencyProperty(String sampleFrequency)
    {
        this.sampleFrequency.set(sampleFrequency);
    }
}
