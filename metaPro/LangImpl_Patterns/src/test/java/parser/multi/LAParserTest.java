package parser.multi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import lexer.Lexer;
import lexer.ListLexer;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import parser.exception.ListRecognitionException;

public class LAParserTest
{
   private LAParser parser;

   @BeforeMethod
   public void setUp() throws IOException
   {
      String input = Files.readString(Path.of("src/test/resources/parser/multi/NameListWithAssign0.txt"));
      Lexer lexer = new ListLexer(input);
      parser = new LAParser(lexer, 2);
   }

   @Test
   public void testList()
   {
      try
      {
         parser.list();
      }
      catch (ListRecognitionException lre)
      {
         lre.printStackTrace();
      }
   }

}
