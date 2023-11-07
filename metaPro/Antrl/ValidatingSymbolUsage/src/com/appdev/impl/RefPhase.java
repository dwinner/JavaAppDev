package com.appdev.impl;

import com.appdev.CheckSymbols;
import com.appdev.base.Scope;
import com.appdev.base.Symbol;
import com.appdev.grammar.CymbolBaseListener;
import com.appdev.grammar.CymbolParser;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

public class RefPhase extends CymbolBaseListener
{
   ParseTreeProperty<Scope> scopes;
   GlobalScope globals;
   Scope currentScope; // resolve symbols starting in this scope

   public RefPhase(GlobalScope globals, ParseTreeProperty<Scope> scopes)
   {
      this.scopes = scopes;
      this.globals = globals;
   }

   public void enterFile(CymbolParser.FileContext ctx)
   {
      currentScope = globals;
   }

   public void enterFunctionDecl(CymbolParser.FunctionDeclContext ctx)
   {
      currentScope = scopes.get(ctx);
   }

   public void exitFunctionDecl(CymbolParser.FunctionDeclContext ctx)
   {
      currentScope = currentScope.getEnclosingScope();
   }

   public void enterBlock(CymbolParser.BlockContext ctx)
   {
      currentScope = scopes.get(ctx);
   }

   public void exitBlock(CymbolParser.BlockContext ctx)
   {
      currentScope = currentScope.getEnclosingScope();
   }

   public void exitVar(CymbolParser.VarContext ctx)
   {
      String name = ctx.ID().getSymbol().getText();
      Symbol var = currentScope.resolve(name);
      if (var == null)
      {
         Token token = ctx.ID().getSymbol();
         CheckSymbols.error(token, "no such variable: " + name);
      }

      if (var instanceof FunctionSymbol)
      {
         Token token = ctx.ID().getSymbol();
         CheckSymbols.error(token, name + " is not a variable");
      }
   }

   public void exitCall(CymbolParser.CallContext ctx)
   {
      // can only handle f(...) not expr(...)
      String funcName = ctx.ID().getText();
      Symbol meth = currentScope.resolve(funcName);
      if (meth == null)
      {
         CheckSymbols.error(ctx.ID().getSymbol(), "no such function: " + funcName);
      }

      if (meth instanceof VariableSymbol)
      {
         CheckSymbols.error(ctx.ID().getSymbol(), funcName + " is not a function");
      }
   }
}
