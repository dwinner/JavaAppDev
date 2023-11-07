package goff_factory;

import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 27.07.12
 * Time: 20:34
 * To change this template use File | Settings | File Templates.
 */
public interface ItemEditor
{
   JComponent getGUI();
   void commitChanges();
}
