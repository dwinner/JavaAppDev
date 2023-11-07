/*
 * Sample using of ANTLR G4 grammar in .NET context
 */

using System;
using Antlr4.Runtime;
using CalculatorG4;

try
{
   var input = GetInput();
   var result = EvaluateInput(input);

   DisplayResult(result);
}
catch (Exception ex)
{
   DisplayError(ex);
}

Console.Write($"{Environment.NewLine}Press ENTER to exit: ");
Console.ReadKey();

static string GetInput()
{
   Console.Write("Enter a value to evaluate: ");
   return Console.ReadLine();
}

static int EvaluateInput(string input)
{
   var lexer = new CalculatorLexer(new AntlrInputStream(input));

   lexer.RemoveErrorListeners();
   lexer.AddErrorListener(new ThrowingErrorListener<int>());

   var parser = new CalculatorParser(new CommonTokenStream(lexer));

   parser.RemoveErrorListeners();
   parser.AddErrorListener(new ThrowingErrorListener<IToken>());

   return new CalculatorVisitor().Visit(parser.expression());
}

static void DisplayResult(int result)
{
   Console.WriteLine($"Result: {result}");
}

static void DisplayError(Exception ex)
{
   Console.WriteLine("Something didn't go as expected:");
   Console.WriteLine(ex.Message);
}