package strategytest;

public interface SummarizingStrategy
{
   String summarize(Contact[] contacts);
   String[] makeSummarizedList(Contact[] contacts);
   
   String EOL_STRING = System.getProperty("line.separator");
   String DELIMETER = ":";
   String COMMA = ",";
   String SPACE = " ";
}
