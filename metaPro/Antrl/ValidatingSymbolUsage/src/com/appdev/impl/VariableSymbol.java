package com.appdev.impl;

import com.appdev.base.Symbol;

/**
 * Represents a variable definition (name,type) in symbol table
 */
public class VariableSymbol extends Symbol
{
   public VariableSymbol(String name, Type type)
   {
      super(name, type);
   }
}
