package parser.rd;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import lexer.ListLexer;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import parser.exception.ListRecognitionException;

public class ListParserTest
{
   private ListParser parser;

   @BeforeMethod
   public void setUp() throws IOException
   {
      String input = Files.readString(Path.of("src/test/resources/parser/rd/NameList0.txt"));
      ListLexer lexer = new ListLexer(input);
      parser = new ListParser(lexer);
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
