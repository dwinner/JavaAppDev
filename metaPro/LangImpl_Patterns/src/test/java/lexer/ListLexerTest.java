package lexer;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ListLexerTest
{
   ListLexer lexer = new ListLexer("");  // TODO

   @BeforeMethod
   public void setUp()
   {
   }

   @Test
   public void testNextToken()
   {
      Token token = lexer.nextToken();
      while (token.type != Lexer.EOF_TYPE)
      {
         System.out.println(token);
         token = lexer.nextToken();
      }

      System.out.println(token); // EOF
   }

}
