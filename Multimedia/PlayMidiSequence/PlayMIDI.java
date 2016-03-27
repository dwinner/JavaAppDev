// Проигрывание MIDI-последовательностей
import javax.sound.midi.*;
import java.io.*;

public class PlayMIDI
{
    public PlayMIDI(String s) { play(s); }

    public void play(String file)
    {
        try
        {
            File f = new File(file);
            
            // Получаем секвенсор по-умолчанию
            Sequencer sequencer = MidiSystem.getSequencer();
            
            // Проверяем, получен ли секвенсор
            if (sequencer == null)
            {
                System.err.println("Sequencer is not supported");
                System.exit(0);
            }
            
            // Открываем секвенсор
            sequencer.open();
            
            // Получаем MIDI-последовательность из файла
            Sequence seq = MidiSystem.getSequence(f);
            
            // Направляем последовательность в секвенсор
            sequencer.setSequence(seq);
            
            // Начинаем проигрывание
            sequencer.start();
            
            // Затем надо сделать задержку на время проигрывания, а затем остановить
            Thread.sleep(sequencer.getTickLength());
            sequencer.stop();
        }
        catch (Exception e)
        {
            System.err.println(e);
        }
        finally
        {
            System.exit(0);
        }
    }

    public static void main(String[] args)
    {
        String s = "doom.mid";
        if (args.length > 0)
            s = args[0];
        new PlayMIDI(s);
    }
}