package com.appdev;

import com.propertyGrammar.PropertyFileBaseListener;
import com.propertyGrammar.PropertyFileParser;

import org.antlr.v4.misc.OrderedHashMap;

import java.util.Map;

public final class PropertyFileLoader extends PropertyFileBaseListener
{
   Map<String, String> props = new OrderedHashMap<>();

   public void exitProp(PropertyFileParser.PropContext ctx)
   {
      String id = ctx.ID().getText(); // prop : ID '=' STRING '\n' ;
      String value = ctx.STRING().getText();
      props.put(id, value);
   }
}
