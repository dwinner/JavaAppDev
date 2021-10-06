package com.appdev.impl;

import com.appdev.base.BaseScope;
import com.appdev.base.Scope;

public class GlobalScope extends BaseScope
{
   public GlobalScope(Scope enclosingScope)
   {
      super(enclosingScope);
   }

   public String getScopeName()
   {
      return "globals";
   }
}
