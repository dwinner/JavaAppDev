package interpretertest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ContactList implements Serializable
{
   public List<Contact> getContacts()
   {
      return Collections.unmodifiableList(contacts);
   }

   public Contact[] getContactsAsArray()
   {
      return contacts.toArray(new Contact[contacts.size()]);
   }

   public List<Contact> getContactsMatchingExpression(Expression anExpression, Context aContext, Object key)
   {
      List<Contact> results = new ArrayList<>();
      Iterator<Contact> contactIt = contacts.iterator();
      while (contactIt.hasNext())
      {
         Contact currentObject = contactIt.next();
         aContext.addVariable(key, currentObject);
         anExpression.interpret(aContext);
         Object interpretResult = aContext.get(anExpression);
         if (interpretResult != null && interpretResult.equals(true))
         {
            results.add(currentObject);
         }
      }
      return results;
   }

   public void setContacts(List<Contact> newContacts)
   {
      contacts = newContacts;
   }

   public void addContact(Contact aContact)
   {
      if (!contacts.contains(aContact))
      {
         contacts.add(aContact);
      }
   }

   public void removeContact(Contact aContact)
   {
      contacts.remove(aContact);
   }

   @Override
   public String toString()
   {
      return contacts.toString();
   }
   
   private List<Contact> contacts = new ArrayList<>();
}
