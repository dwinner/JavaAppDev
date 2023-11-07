package strategytest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContactList implements Serializable
{
   public List<Contact> getContacts()
   {
      return Collections.unmodifiableList(contacts.get());
   }
   
   public Contact[] getContactsAsArray()
   {
      return getContacts().toArray(new Contact[contacts.get().size()]);
   }

   public void setStrategy(SummarizingStrategy newStrategy)
   {
      strategy = newStrategy;
   }

   public void setContacts(List<Contact> newContacts)
   {
      contacts.set(newContacts);
   }
   
   public void addContact(Contact aContact)
   {
      if (!contacts.get().contains(aContact))
      {
         contacts.get().add(aContact);
      }
   }
   
   public void removeContact(Contact aContact)
   {
      contacts.get().remove(aContact);
   }

   public String summarize()
   {
      return strategy.summarize(getContactsAsArray());
   }

   public String[] makeSummarizedList()
   {
      return strategy.makeSummarizedList(getContactsAsArray());
   }

   
   private ThreadLocal<List<Contact>> contacts = new ThreadLocal<List<Contact>>()
   {
      @Override
      protected List<Contact> initialValue()
      {
         return new ArrayList<>();
      }
   };
   private SummarizingStrategy strategy;
}
