package strategytest;

import java.text.Collator;
import java.util.Arrays;
import java.util.Comparator;

public class SummaryStrategyFactory
{
   public static SummarizingStrategy createNameSummarizingStrategy()
   {
      return new SummarizingStrategy()
      {
         @Override
         public String summarize(Contact[] contacts)
         {
            StringBuilder productBuilder = new StringBuilder();
            Arrays.sort(contacts, comparator);
            for (Contact contact : contacts)
            {
               productBuilder.append(contact.getLastName());
               productBuilder.append(COMMA);
               productBuilder.append(SPACE);
               productBuilder.append(contact.getFirstName());
               productBuilder.append(EOL_STRING);
            }
            return productBuilder.toString();
         }

         @Override
         public String[] makeSummarizedList(Contact[] contacts)
         {
            Arrays.sort(contacts, comparator);
            String[] products = new String[contacts.length];
            for (int i = 0; i < contacts.length; i++)
            {
               products[i] =
                 contacts[i].getLastName() + COMMA + SPACE + contacts[i].getFirstName() + EOL_STRING;
            }
            return products;
         }

         private Comparator<Contact> comparator = new Comparator<Contact>()
         {
            @Override
            public int compare(Contact firstContact, Contact secondContact)
            {
               int compareResult =
                 textCollator.compare(firstContact.getLastName(), secondContact.getLastName());
               if (compareResult == 0)
               {
                  compareResult =
                    textCollator.compare(firstContact.getFirstName(), secondContact.getFirstName());
               }
               return compareResult;
            }

            private Collator textCollator = Collator.getInstance();
         };
      };
   }

   public static SummarizingStrategy createOrganizationSummarizingStrategy()
   {
      return new SummarizingStrategy()
      {
         @Override
         public String summarize(Contact[] contacts)
         {
            StringBuilder productBuilder = new StringBuilder();
            Arrays.sort(contacts, comparator);
            for (Contact contact : contacts)
            {
               productBuilder.append(contact.getOrganization());
               productBuilder.append(DELIMETER);
               productBuilder.append(SPACE);
               productBuilder.append(contact.getFirstName());
               productBuilder.append(SPACE);
               productBuilder.append(contact.getLastName());
               productBuilder.append(EOL_STRING);
            }
            return productBuilder.toString();
         }

         @Override
         public String[] makeSummarizedList(Contact[] contacts)
         {
            Arrays.sort(contacts, comparator);
            String[] products = new String[contacts.length];
            for (int i = 0; i < products.length; i++)
            {
               products[i] = contacts[i].getOrganization() + DELIMETER + SPACE
                 + contacts[i].getFirstName() + SPACE
                 + contacts[i].getLastName() + EOL_STRING;               
            }
            return products;
         }

         private Comparator<Contact> comparator = new Comparator<Contact>()
         {
            @Override
            public int compare(Contact firstContact, Contact secondContact)
            {
               int compareResult =
                 textCollator.compare(firstContact.getOrganization(), secondContact.getOrganization());
               if (compareResult == 0)
               {
                  compareResult =
                    textCollator.compare(firstContact.getLastName(), secondContact.getLastName());
               }
               return compareResult;
            }

            private Collator textCollator = Collator.getInstance();
         };
      };
   }

   private SummaryStrategyFactory()
   {
   }
}
