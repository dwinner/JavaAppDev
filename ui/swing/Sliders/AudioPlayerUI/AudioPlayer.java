// PLaF-��������� ������������.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.text.*;
import java.util.*;

/**
 * ����� ��� �������� ��������� ���������.
 * @author oracle_pr1
 */
class Presets
{
    private int bass;
    private int midrange;
    private int treble;
    private int balance;
    private int volume;
    
    
    public int getBass()            { return bass;  }
    public void setBass(int aBass)  { bass = aBass; }
    
    public int getMidrange()    { return midrange;  }
    public void setMidrange(int aMidrange) { midrange = aMidrange; }
    
    public int getTreble()      { return treble;    }
    public void setTreble(int aTreble) { treble = aTreble; }
    
    public int getBalance()     { return balance;   }
    public void setBalance(int aBalance) { balance = aBalance; }
    
    public int getVolume()      { return volume;    }
    public void setVolume(int aVolume) { volume = aVolume; }
    
    Presets(int b, int m, int t, int bl, int v)
    {
        bass = b;
        midrange = m;
        treble = t;
        balance = bl;
        volume = v;
    }
}

/**
 * �������� �����, ������������ ��� �������� ���������� ������������
 * @author oracle_pr1
 */
public class AudioPlayer implements ChangeListener
{
    private JLabel jlabBass;
    private JLabel jlabMidrange;
    private JLabel jlabTreble;
    private JLabel jlabBalance;
    private JLabel jlabVolume;
    private JLabel jlabInfo;
    private JSlider jsldrBass;
    private JSlider jsldrMidrange;
    private JSlider jsldrTreble;
    private JSlider jsldrBalance;
    private JSlider jsldrVolume;
    private JRadioButton jrbPreset1;
    private JRadioButton jrbPreset2;
    private JRadioButton jrbDefaults;
    private JButton jbtnStore;
    private DecimalFormat df;
    private Presets[] presets;

    public AudioPlayer()
    {
        // �������� ������ ���������� JFrame.
        JFrame jfrm = new JFrame("A Simple Audio Player Interface");

        // ��������� ���������� ���������� FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // ��������� ��������� �������� ������.
        jfrm.setSize(340, 520);
        jfrm.setResizable(false);

        // ���������� ��������� ��� �������� ���������� �������������.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �������� ������� ��������������, ������� ����� ���������� ����� + � -.
        df = new DecimalFormat("+#;-#");

        // ��������� ���������������� ��������.
        setupPresets();

        // �������� �������� �����������.
        setupSliders();

        // �������� �����, ����������� �������� ����������.
        setupLabels();

        // �������� ������ ������������� ����� ��� �������� ���������.
        setupRButtons();

        // �������� ������ Store Settings.
        jbtnStore = new JButton("Store Settings");

        // ���������� ������������ �������, ������������ ��������� ������������.
        jsldrBass.addChangeListener(this);
        jsldrMidrange.addChangeListener(this);
        jsldrTreble.addChangeListener(this);
        jsldrBalance.addChangeListener(this);
        jsldrVolume.addChangeListener(this);

        // ��������� �������, ��������� � ������� Store Settings.
        jbtnStore.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                if (jrbPreset1.isSelected())
                {
                    storePreset(presets[1]);
                }
                else if (jrbPreset2.isSelected())
                {
                    storePreset(presets[2]);
                }
            }
        });

        // ��������� ������� ������ ������������� ����� Defaults.
        jrbDefaults.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                loadPreset(presets[0]);
            }
        });

        // ��������� ������� ������ ������������� ����� Preset 1.
        jrbPreset1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                loadPreset(presets[1]);
            }
        });

        // ��������� ������� ������ ������������� ����� Preset 2.
        jrbPreset2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                loadPreset(presets[2]);
            }
        });

        // ���������� ����������� � ������ �����������
        Container cp = jfrm.getContentPane();

        cp.add(jlabBass);
        cp.add(jsldrBass);
        cp.add(jlabMidrange);
        cp.add(jsldrMidrange);
        cp.add(jlabTreble);
        cp.add(jsldrTreble);
        cp.add(jlabBalance);
        cp.add(jsldrBalance);
        cp.add(jlabVolume);
        cp.add(jsldrVolume);
        cp.add(jrbDefaults);
        cp.add(jrbPreset1);
        cp.add(jrbPreset2);
        cp.add(jbtnStore);
        cp.add(new JLabel(""));
        cp.add(jlabInfo);

        // ����������� ������.
        jfrm.setVisible(true);
    }

    // ��������� ������� ��������� ����������
    public void stateChanged(ChangeEvent ce)
    {
        // ���������� ������������ ����������.
        showSettings();
    }

    // ����������� ������� ���������.
    void showSettings()
    {
        String bal;

        // ��������� ��������� �������.
        int b = jsldrBalance.getValue();
        if (b > 0)
        {
            bal = "Right " + df.format(jsldrBalance.getValue());
        }
        else if (b == 0)
        {
            bal = "Center";
        }
        else
        {
            bal = "Left " + df.format(-jsldrBalance.getValue());
        }

        jlabInfo.setText("<html>Treble: "
                + df.format(jsldrTreble.getValue())
                + "<br />Midrange: "
                + df.format(jsldrMidrange.getValue())
                + "<br />Base: "
                + df.format(jsldrBass.getValue())
                + "<br />Balance: " + bal
                + "<br />Volume: "
                + jsldrVolume.getValue());
    }

    // �������� � ������������� �������� �����������.
    void setupSliders()
    {
        // �������� �������� �����������.
        jsldrBass = new JSlider(-10, 10);
        jsldrMidrange = new JSlider(-10, 10);
        jsldrTreble = new JSlider(-10, 10);
        jsldrVolume = new JSlider(0, 10, 0);
        jsldrBalance = new JSlider(-5, 5);

        // ���������� �������� ��������.
        jsldrBass.setMajorTickSpacing(2);
        jsldrMidrange.setMajorTickSpacing(2);
        jsldrTreble.setMajorTickSpacing(2);
        jsldrVolume.setMajorTickSpacing(1);
        jsldrBalance.setMajorTickSpacing(1);

        // ���������� ��������������� ��������.
        jsldrBass.setMinorTickSpacing(1);
        jsldrMidrange.setMinorTickSpacing(1);
        jsldrTreble.setMinorTickSpacing(1);

        // �������� ����� ��� ���� �������� �����������.
        Hashtable<Integer, JLabel> table = new Hashtable<Integer, JLabel>();
        for (int i = -10; i <= 0; i += 2)
        {
            table.put(new Integer(i), new JLabel("" + i));
        }
        for (int i = 2; i <= 10; i += 2)
        {
            table.put(new Integer(i), new JLabel("+" + i));
        }

        jsldrTreble.setLabelTable(table);
        jsldrMidrange.setLabelTable(table);
        jsldrBass.setLabelTable(table);

        // �������� ����� ��� ��������� ���������� Balance.
        table = new Hashtable<Integer, JLabel>();
        table.put(new Integer(0), new JLabel("Center"));
        table.put(new Integer(-5), new JLabel("L"));
        table.put(new Integer(5), new JLabel("R"));
        jsldrBalance.setLabelTable(table);

        // �������� ����������� �������� ����� ��� ��������� ���������� Volume.
        jsldrVolume.setLabelTable(jsldrVolume.createStandardLabels(1));

        // ���������� ����������� ��������.
        jsldrBass.setPaintTicks(true);
        jsldrMidrange.setPaintTicks(true);
        jsldrTreble.setPaintTicks(true);
        jsldrVolume.setPaintTicks(true);
        jsldrBalance.setPaintTicks(true);

        // ���������� ����������� �����.
        jsldrBass.setPaintLabels(true);
        jsldrMidrange.setPaintLabels(true);
        jsldrTreble.setPaintLabels(true);
        jsldrVolume.setPaintLabels(true);
        jsldrBalance.setPaintLabels(true);

        // ����������� � ���������� �������.
        jsldrBass.setSnapToTicks(true);
        jsldrMidrange.setSnapToTicks(true);
        jsldrTreble.setSnapToTicks(true);
        jsldrVolume.setSnapToTicks(true);
        jsldrBalance.setSnapToTicks(true);

        // ��������� ���������������� �������� �������� �����������.
        Dimension sldrSize = new Dimension(240, 60);
        jsldrBass.setPreferredSize(sldrSize);
        jsldrMidrange.setPreferredSize(sldrSize);
        jsldrTreble.setPreferredSize(sldrSize);
        jsldrVolume.setPreferredSize(sldrSize);
        jsldrBalance.setPreferredSize(sldrSize);
    }

    // �������� ����� ��� �������� �������� �����������
    void setupLabels()
    {
        // �������� �����.
        jlabTreble = new JLabel("Treble");
        jlabMidrange = new JLabel("Midrange");
        jlabBass = new JLabel("Bass");
        jlabVolume = new JLabel("Volume");
        jlabBalance = new JLabel("Balance");

        // ��������� �������� �����.
        Dimension labSize = new Dimension(60, 25);
        jlabTreble.setPreferredSize(labSize);
        jlabMidrange.setPreferredSize(labSize);
        jlabBass.setPreferredSize(labSize);
        jlabVolume.setPreferredSize(labSize);
        jlabBalance.setPreferredSize(labSize);

        // �������� �������������� ����� � ��������� �� ��������.
        jlabInfo = new JLabel("");
        jlabInfo.setPreferredSize(new Dimension(110, 100));

        // ���������� jlabInfo �����������, ��������������� ���������� �� ���������.
        showSettings();
    }

    // �������� � ������������� ������ ������������� �����.
    void setupRButtons()
    {
        // �������� ������.
        jrbDefaults = new JRadioButton("Defaults");
        jrbPreset1 = new JRadioButton("Preset 1");
        jrbPreset2 = new JRadioButton("Preset 2");

        // ���������� ������ � ������.
        ButtonGroup bg = new ButtonGroup();
        bg.add(jrbDefaults);
        bg.add(jrbPreset1);
        bg.add(jrbPreset2);

        // ����� ������ Defaults.
        jrbDefaults.setSelected(true);
    }

    // �������� ����� ������������ ���������.
    void loadPreset(Presets info)
    {
        jsldrBass.setValue(info.getBass());
        jsldrMidrange.setValue(info.getMidrange());
        jsldrTreble.setValue(info.getTreble());
        jsldrBalance.setValue(info.getBalance());
        jsldrVolume.setValue(info.getVolume());
    }

    // ���������� ���������.
    void storePreset(Presets info)
    {
        info.setBass(jsldrBass.getValue());
        info.setMidrange(jsldrMidrange.getValue());
        info.setTreble(jsldrTreble.getValue());
        info.setBalance(jsldrBalance.getValue());
        info.setVolume(jsldrVolume.getValue());
    }

    // ������������� ������� ��������������� ���������.
    void setupPresets()
    {
        presets = new Presets[3];
        presets[0] = new Presets(0, 0, 0, 0, 0);
        presets[1] = new Presets(2, -4, 7, 0, 4);
        presets[2] = new Presets(3, 3, -2, 1, 7);
    }

    public static void main(String args[])
    {
        // �������� ������ � ������ ��������� �������.
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new AudioPlayer();
            }
        });
    }
}