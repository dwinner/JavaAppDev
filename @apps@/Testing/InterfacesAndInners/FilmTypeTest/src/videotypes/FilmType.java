package videotypes;

import java.util.ArrayList;
import javax.swing.Timer;

import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * ����� ��� ������������ �������.
 * <p/>
 * @author dwinner@inbox.ru
 */
public class FilmType implements IVideo
{
    private final static int ASECOND = 1000;
    private Genre genre;
    private DurationType movieDuration;
    private Timer theTimer;
    private int elapsedTime;

    /**
     * @param genre         ���� ������
     * @param movieDuration ����������������� ������
     */
    public FilmType(Genre genre, int duration)
    {
        setGenre(genre);
        movieDuration = new DurationType(duration);
    }

    /**
     * @return the genre ���� ������
     */
    public Genre getGenre()
    {
        return genre;
    }

    /**
     * @param genre ��������� ����� ������
     */
    public void setGenre(Genre aGenre)
    {
        genre = aGenre;
    }

    /**
     * @return ����������������� ������
     */
    public int getDuration()
    {
        return movieDuration.getDuration();
    }

    /**
     * @param movieDuration ��������� ����������������� ������
     */
    public void setMovieDuration(int duration)
    {
        if (duration < 0)
        {
            duration = -duration;
        }
        movieDuration.setDuration(duration);
    }

    /**
     * @see videotypes.IVideo#startPlay(int)
     */
    @Override
    public void startPlay(int delay)
    {
        try
        {
            Thread.sleep(delay * 1000);
        }
        catch (InterruptedException e1)
        {
            e1.printStackTrace();
        }
        if (theTimer == null)
        {
            theTimer = new Timer(ASECOND, new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    if (elapsedTime >= getDuration())
                    {
                        theTimer.stop();
                        resetPlay();
                        Toolkit.getDefaultToolkit().beep();
                    }
                    else
                    {
                        elapsedTime += (int) ASECOND / 1000;
                        System.out.println("Spended time is: " + elapsedTime);
                    }
                }
            });
        }
        else
        {
            theTimer.start();
        }
    }

    public void startPlay()
    {
        startPlay(0);
    }

    /**
     * @see videotypes.IVideo#stopPlay()
     */
    @Override
    public void stopPlay()
    {
        if (theTimer != null)
        {
            theTimer.stop();
        }
    }

    /**
     * @see videotypes.IVideo#resetPlay()
     */
    @Override
    public void resetPlay()
    {
        if (theTimer.isRunning())
        {
            theTimer.stop();
        }
        elapsedTime = 0;
    }

    /**
     * ������������, ��������������� ���� ������
     * <p/>
     * @author dwinner@inbox.ru
     * <p/>
     */
    public static enum Genre
    {
        COMEDY,
        TRAGEDY,
        MELODRAMA,
        ACTIONS,
        HORRORS,
        THRILLER,
        MYSTIC;

        public String getDescription()
        {
            switch (this)
            {
                case COMEDY:
                    return "Comedy";
                case TRAGEDY:
                    return "Tragedy";
                case MELODRAMA:
                    return "Melodrama";
                case ACTIONS:
                    return "Action";
                case HORRORS:
                    return "Horrors";
                case THRILLER:
                    return "Thriller";
                case MYSTIC:
                    return "Mystic";
                default:
                    throw new EnumConstantNotPresentException(this.getDeclaringClass(), this.name());
            }
        }
    }

    /**
     * ����������������� ������
     * <p/>
     * @author dwinner@inbox.ru
     * <p/>
     */
    private class DurationType
    {
        private int duration;

        /**
         * @param duration ����������������� ������
         */
        DurationType(final int duration)
        {
            setDuration(duration);
        }

        /**
         * @return the duration ����������������� ������
         */
        public int getDuration()
        {
            return duration;
        }

        /**
         * @param duration ��������� ����������������� ������
         */
        public void setDuration(final int aDuration)
        {
            duration = aDuration;
        }
    }

    /**
     * ��������� ������
     * <p/>
     * @author dwinner@inbox.ru
     * <p/>
     */
    public class ProducerType
    {
        private ArrayList<String> producers;
        private static final int INITIAL_CAPACITY = 0x8;

        
        {
            producers = new ArrayList<String>(INITIAL_CAPACITY);
        }

        /**
         * �������� ��� ������������ �� ���������
         */
        public ProducerType()
        {
        }

        /**
         * ����������� ����
         * <p/>
         * @param prodCount ���������� ���������� ������
         */
        public ProducerType(int prodCount)
        {
            producers.ensureCapacity(prodCount);
        }

        /**
         * ����������� ����
         * <p/>
         * @param prodList ������ ����������
         */
        public ProducerType(ArrayList<String> prodList)
        {
            producers.addAll(prodList);
        }

        /**
         * ��������� ������� ����������
         * <p/>
         * @return ������ ����������
         */
        public String[] getProducers()
        {
            String[] producerArray = new String[producers.size()];
            for (int i = 0; i < producerArray.length; i++)
            {
                producerArray[i] = producers.get(i);
            }
            return producerArray;
        }

        /**
         * ������ ������ ���������� ����� �������
         * <p/>
         * @param prodGroup ����� ������ ����������
         */
        public void updateProducerGroup(String[] prodGroup)
        {
            producers.clear();
            for (String prod : prodGroup)
            {
                producers.add(prod);
            }
        }

        /**
         * ���������� ���������
         * <p/>
         * @param producer �������� ��� ����������
         */
        public void addProducer(String producer)
        {
            producers.add(producer);
        }

        /**
         * �������� ���������
         * <p/>
         * @param producer �������� ������
         */
        public void removeProducer(String producer)
        {
            if (producers.isEmpty())
            {
                return;
            }
            producers.remove(producer);
        }

        /**
         * �������� ���������
         * <p/>
         * @param atIndex ������ ��������� � ������
         */
        public void removeProducer(int atIndex)
        {
            if (producers.isEmpty())
            {
                return;
            }
            if (atIndex < 0 || atIndex > producers.size() - 1)
            {
                return;
            }
            producers.remove(atIndex);
        }

        @Override
        public String toString()
        {
            return "ProducerType [producers=" + producers + "]";
        }
    }

    @Override
    public String toString()
    {
        return "FilmType [genre=" + genre + ", movieDuration=" + movieDuration + "]";
    }
}
