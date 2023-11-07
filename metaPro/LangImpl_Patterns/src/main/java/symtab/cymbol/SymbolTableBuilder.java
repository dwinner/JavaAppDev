package symtab.cymbol;

import org.antlr.v4.runtime.tree.ParseTreeProperty;
import symtab.symboltable.Scope;
import symtab.symboltable.*;

public class SymbolTableBuilder extends CymbolBaseListener
{
   Scope currentScope;
   ParseTreeProperty<Scope> scopes = new ParseTreeProperty<>();

   @Override
   public void enterProg(CymbolParser.ProgContext ctx)
   {
      GlobalScope globalScope = new GlobalScope(null);
      pushScope(globalScope);
   }

   @Override
   public void exitProg(CymbolParser.ProgContext ctx)
   {
      popScope();
   }

   @Override
   public void enterStructDecl(CymbolParser.StructDeclContext ctx)
   {
      StructSymbol structSymbol = new StructSymbol(ctx.ID().getText());
      structSymbol.setEnclosingScope(currentScope);
      currentScope.define(structSymbol);
      pushScope(structSymbol);
   }

   @Override
   public void exitStructDecl(CymbolParser.StructDeclContext ctx)
   {
      popScope();
   }

   @Override
   public void enterField(CymbolParser.FieldContext ctx)
   {
      FieldSymbol fieldSymbol = new FieldSymbol(ctx.ID().getText());
      currentScope.define(fieldSymbol);
   }

   @Override
   public void enterFunctionDecl(CymbolParser.FunctionDeclContext ctx)
   {
      // FunctionSymbol(??)
      FunctionSymbol funcSymbol = new FunctionSymbol(ctx.ID().getText(), ctx);
      funcSymbol.setEnclosingScope(currentScope);
      currentScope.define(funcSymbol);
      pushScope(funcSymbol);
   }

   @Override
   public void exitFunctionDecl(CymbolParser.FunctionDeclContext ctx)
   {
      popScope();
   }

   @Override
   public void enterVarDecl(CymbolParser.VarDeclContext ctx)
   {
      VariableSymbol varSymbol = new VariableSymbol(ctx.ID().getText(), currentScope);
      currentScope.define(varSymbol);
   }

   @Override
   public void enterPara(CymbolParser.ParaContext ctx)
   {
      ParameterSymbol paraSymbol = new ParameterSymbol(ctx.ID().getText(), currentScope);
      currentScope.define(paraSymbol);
   }

   @Override
   public void enterPrimitiveType(CymbolParser.PrimitiveTypeContext ctx)
   {
      // TODO
   }

   @Override
   public void enterIdType(CymbolParser.IdTypeContext ctx)
   {
      // TODO
   }

   @Override
   public void enterFuncCallExpr(CymbolParser.FuncCallExprContext ctx)
   {
      Symbol symbol = currentScope.resolve(ctx.ID().getText());
      System.out.println("Line " + ctx.ID().getSymbol().getLine() + ": ref " + symbol);
   }

   @Override
   public void enterIdExpr(CymbolParser.IdExprContext ctx)
   {
      Symbol symbol = currentScope.resolve(ctx.ID().getText());
      System.out.println("Line " + ctx.ID().getSymbol().getLine() + ": ref " + symbol);
   }

   @Override
   public void enterMemberAccess(CymbolParser.MemberAccessContext ctx)
   {
      StructSymbol scope = (StructSymbol) scopes.get(ctx);
      Symbol symbol = scope.resolveMember(ctx.ID().getText());
      System.out.println("Line " + ctx.ID().getSymbol().getLine()
        + ": ref " + ctx.ID().getText()
        + " = " + symbol);
      scopes.put(ctx, (Scope) symbol.getType());
   }

   @Override
   public void enterMemberID(CymbolParser.MemberIDContext ctx)
   {
      Symbol structSymbol = currentScope.resolve(ctx.ID().getText());
      System.out.println("Line " + ctx.ID().getSymbol().getLine()
        + ": ref " + ctx.ID().getText()
        + " = " + structSymbol);
      scopes.put(ctx, (StructSymbol) structSymbol);
   }

   @Override
   public void enterBlock(CymbolParser.BlockContext ctx)
   {
      LocalScope localScope = new LocalScope(currentScope);
      pushScope(localScope);
   }

   @Override
   public void exitBlock(CymbolParser.BlockContext ctx)
   {
      popScope();
   }

   private void pushScope(Scope scope)
   {
      currentScope = scope;
      System.out.println("Entering " + currentScope.getName() + ": " + scope);
   }

   private void popScope()
   {
      System.out.println("Leaving " + currentScope.getName() + ": " + currentScope);
      currentScope = currentScope.getEnclosingScope();
   }

}
