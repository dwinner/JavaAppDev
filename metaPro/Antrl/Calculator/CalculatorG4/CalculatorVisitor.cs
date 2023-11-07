using System;
using System.Collections.Generic;
using System.Linq;
using Antlr4.Runtime.Misc;
using Antlr4.Runtime.Tree;

namespace CalculatorG4
{
   internal class CalculatorVisitor : CalculatorBaseVisitor<int>
   {
      #region Member Variables

      private readonly Dictionary<string, Func<int, int, int>> _funcMap =
         new()
         {
            {"+", (a, b) => a + b},
            {"-", (a, b) => a - b},
            {"*", (a, b) => a * b},
            {"/", (a, b) => a / b}
         };

      #endregion

      #region Utility Methods

      private int HandleGroup(CalculatorParser.OperandContext[] operandCtxs, ITerminalNode[] operatorNodes)
      {
         var operands = operandCtxs.Select(Visit).ToList();
         var operators = new Queue<string>(operatorNodes.Select(o => o.GetText()));

         return operands.Aggregate((a, c) => _funcMap[operators.Dequeue()](a, c));
      }

      #endregion

      #region Base Class Overrides

      public override int VisitExpression([NotNull] CalculatorParser.ExpressionContext context) =>
         HandleGroup(context.operand(), context.OPERATOR());

      public override int VisitOperand([NotNull] CalculatorParser.OperandContext context)
      {
         var digit = context.DIGIT();

         return digit != null
            ? int.Parse(digit.GetText())
            : HandleGroup(context.operand(), context.OPERATOR());
      }

      #endregion
   }
}