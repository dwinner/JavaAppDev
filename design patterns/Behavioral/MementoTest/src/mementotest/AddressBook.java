package mementotest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AddressBook
{
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

   public List<Contact> getContacts()
   {
      return Collections.unmodifiableList(contacts);
   }

   @Override
   public String toString()
   {
      return contacts.toString();
   }

   public Object getMemento()
   {
      return new AddressBookMemento(contacts);
   }

   public void setMemento(Object anObject)
   {
      if (anObject instanceof AddressBookMemento)
      {
         AddressBookMemento memento = (AddressBookMemento) anObject;
         contacts = memento.state;
      }
   }
   
   public void removeAllContacts()
   {
      contacts.clear();
   }
   
   private List<Contact> contacts = new ArrayList<>();

   private static class AddressBookMemento
   {
      AddressBookMemento(List<Contact> contacts)
      {
         state = contacts;
      }
      private List<Contact> state;
   }

}
