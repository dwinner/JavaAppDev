package flyweight;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: oracle_pr1
 * Date: 22.08.12
 * Time: 13:50
 * To change this template use File | Settings | File Templates.
 */
public interface State
{
   void save(File file, Serializable serializable, int type) throws IOException;
   void edit(int type);

   int CONTACTS = 1;
   int ADDRESSES = 2;
   int MAXIMUM_STATE_VALUE = 2;
}
