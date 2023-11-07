package com.appdev;

import com.propertyGrammar.PropertyFileBaseVisitor;
import com.propertyGrammar.PropertyFileParser;

import org.antlr.v4.misc.OrderedHashMap;

import java.util.Map;

public final class PropertyFileVisitor extends PropertyFileBaseVisitor<Void>
{
   Map<String, String> props = new OrderedHashMap<>();

   public Void visitProp(PropertyFileParser.PropContext ctx)
   {
      String id = ctx.ID().getText(); // prop : ID '=' STRING '\n' ;
      String value = ctx.STRING().getText();
      props.put(id, value);

      return null;
   }
}
