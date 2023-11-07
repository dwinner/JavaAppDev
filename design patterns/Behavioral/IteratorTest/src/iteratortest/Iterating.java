package iteratortest;

import java.io.Serializable;
import java.util.Iterator;

public interface Iterating<T> extends Serializable
{
   Iterator<T> getIterator();
}
