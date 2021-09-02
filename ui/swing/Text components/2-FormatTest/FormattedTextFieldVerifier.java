import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;

/**
 * �������� �������� ���������������� ���� ���������������� ������.
 */
public class FormattedTextFieldVerifier extends InputVerifier
{
    @Override
    public boolean verify(JComponent jc)
    {
        if (jc instanceof JFormattedTextField)
        {
            JFormattedTextField field = (JFormattedTextField) jc;
            return field.isEditValid();
        }
        return true;
    }
}
