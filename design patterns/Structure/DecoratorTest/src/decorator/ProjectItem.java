package decorator;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: oracle_pr1
 * Date: 20.08.12
 * Time: 15:08
 * To change this template use File | Settings | File Templates.
 */
public interface ProjectItem extends Serializable
{
   double getTimeRequired();

   String EOL_STRING = System.getProperty("line.separator");
}
