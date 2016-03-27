package app;

import strategytest.ContactList;
import strategytest.SummaryStrategyFactory;

import java.io.File;

public class StrategyTest
{
   public static void main(String[] args)
   {
      System.out.println("Example for the Strategy pattern");
      System.out.println();
      System.out.println("This code uses two Strategy classes, NameSummarizer and OrganizationSummarizer,");
      System.out.println(" to provide a sorted, summarized list for a ContactList. The ContactList object");
      System.out.println(" maintains a collection of Contacts, and delegates the task of representing");
      System.out.println(" its information to an associated object which implements SummarizingStrategy.");
      System.out.println();

      System.out.println("Deserializing stored ContactList from the data.ser file");
      System.out.println();
      if (!(new File("data.ser").exists())){
         DataCreator.serialize("data.ser");
      }
      ContactList list = (ContactList)(DataRetriever.deserializeData("data.ser"));

      System.out.println("Creating NameSummarizer for the ContactList");
      System.out.println("(this Strategy displays only the last and first name,");
      System.out.println(" and sorts the list by last name, followed by the first)");
      list.setStrategy(SummaryStrategyFactory.createNameSummarizingStrategy());

      System.out.println("Name Summarizer Output for the ContactList:");
      System.out.println(list.summarize());
      System.out.println();

      System.out.println("Creating OrganizationSummarizer for the ContactList");
      System.out.println("(this Strategy displays the organization, followed by the first");
      System.out.println(" and last name. It sorts by the organization, followed by last name)");
      list.setStrategy(SummaryStrategyFactory.createOrganizationSummarizingStrategy());

      System.out.println("Organization Summarizer Output for the ContactList:");
      System.out.println(list.summarize());
      System.out.println();
   }
}
