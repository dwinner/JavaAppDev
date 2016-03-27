package userlibs.swt.utils;

import org.eclipse.swt.widgets.Composite;

/**
 * ѕриложение управл€етс€ объектом Composite (Shell его подкласс)
 * и должно использовать его дл€ создани€ содержимого внутри
 * createContents
 * @author oracle_pr1
 *
 */
public interface SWTApplication
{
	void createContents(Composite parent);
}
