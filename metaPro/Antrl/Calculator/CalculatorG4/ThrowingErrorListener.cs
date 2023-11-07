using System;
using System.IO;
using Antlr4.Runtime;

namespace CalculatorG4
{
   internal class ThrowingErrorListener<TSymbol> : IAntlrErrorListener<TSymbol>
   {
      #region IAntlrErrorListener<TSymbol> Implementation
      public void SyntaxError(TextWriter output, IRecognizer recognizer, TSymbol offendingSymbol, int line, int charPositionInLine, string msg, RecognitionException e)
      {
         throw new Exception($"line {line}:{charPositionInLine} {msg}");
      }
      #endregion
   }
}