package interpretertest;

import java.util.HashMap;
import java.util.Map;

public class Context
{
   public Object get(Object key)
   {
      return map.get(key);
   }
   
   public void addVariable(Object key, Object value)
   {
      map.put(key, value);
   }
   
   private Map<Object,Object> map = new HashMap<>();
}
