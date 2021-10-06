package com.appdev.impl;

import com.appdev.base.BaseScope;
import com.appdev.base.Scope;

public class LocalScope extends BaseScope
{
   public LocalScope(Scope parent)
   {
      super(parent);
   }

   public String getScopeName()
   {
      return "locals";
   }
}
