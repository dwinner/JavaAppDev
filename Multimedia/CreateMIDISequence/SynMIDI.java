// —оздание MIDI-последовательности
import javax.sound.midi.*;
import java.io.*;

public class SynMIDI
{
    public SynMIDI() { play(synth()); }

    public Sequence synth()
    {
        Sequence seq = null;
        try
        {
            // ѕоследовательность будет отсчитывать по 10 MIDI-событий на звук длительностью в четверть
            seq = new Sequence(Sequence.PPQ, 10);
            
            // —оздаем в последовательности одну дорожку
            Track tr = seq.createTrack();
            for (int k = 0; k < 100; k++)
            {
                ShortMessage msg = new ShortMessage();
                // ѕробегаем MIDI-ноты от номера 10 до 109
                msg.setMessage(ShortMessage.NOTE_ON, 10 + k, 93);
                // Ѕудем проигрывать ноты через каждые 5 отсчетов
                tr.add(new MidiEvent(msg, 5*k));
                msg = null;
            }
        }
        catch (Exception e)
        {
            System.err.println("From synth(): " + e);
            System.exit(0);
        }
        return seq;
    }

    public void play(Sequence seq)
    {
        try
        {
            Sequencer sequencer = MidiSystem.getSequencer();
            if (sequencer == null)
            {
                System.err.println("Sequencer is not supported");
                System.exit(0);
            }
            sequencer.open();
            sequencer.setSequence(seq);
            sequencer.startRecording();
            int[] type = MidiSystem.getMidiFileTypes(seq);
            MidiSystem.write(seq, type[0], new File("gammas.mid"));
        }
        catch (Exception e)
        {
            System.err.println("From play(): " + e);
        }
    }

    public static void main(String[] args)
    {
        new SynMIDI();
    }
}