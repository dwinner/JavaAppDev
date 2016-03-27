package mementotest;

import java.util.Objects;

public class ContactImpl implements Contact
{
   public ContactImpl()
   {
   }

   public ContactImpl(String firstName, String lastName, String title, String organization, Address address)
   {
      this.firstName = firstName;
      this.lastName = lastName;
      this.title = title;
      this.organization = organization;
      this.address = address;
   }

   @Override
   public String getFirstName()
   {
      return firstName;
   }

   @Override
   public void setFirstName(String firstName)
   {
      this.firstName = firstName;
   }

   @Override
   public String getLastName()
   {
      return lastName;
   }

   @Override
   public void setLastName(String lastName)
   {
      this.lastName = lastName;
   }

   @Override
   public String getTitle()
   {
      return title;
   }

   @Override
   public void setTitle(String title)
   {
      this.title = title;
   }

   @Override
   public String getOrganization()
   {
      return organization;
   }

   @Override
   public void setOrganization(String organization)
   {
      this.organization = organization;
   }

   public Address getAddress()
   {
      return address;
   }

   public void setAddress(Address address)
   {
      this.address = address;
   }

   @Override
   public String toString()
   {
      return "ContactImpl{" + "firstName=" + firstName + ", lastName="
        + lastName + ", title=" + title + ", organization="
        + organization + ", address=" + address + '}';
   }

   @Override
   public int hashCode()
   {
      int hash = 7;
      hash = 97 * hash + Objects.hashCode(this.firstName);
      hash = 97 * hash + Objects.hashCode(this.lastName);
      hash = 97 * hash + Objects.hashCode(this.title);
      hash = 97 * hash + Objects.hashCode(this.organization);
      hash = 97 * hash + Objects.hashCode(this.address);
      return hash;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (obj == null)
      {
         return false;
      }
      if (getClass() != obj.getClass())
      {
         return false;
      }
      final ContactImpl other = (ContactImpl) obj;
      if (!Objects.equals(firstName, other.getFirstName()))
      {
         return false;
      }
      if (!Objects.equals(lastName, other.getLastName()))
      {
         return false;
      }
      if (!Objects.equals(title, other.getTitle()))
      {
         return false;
      }
      if (!Objects.equals(organization, other.getOrganization()))
      {
         return false;
      }
      if (!Objects.equals(address, other.getAddress()))
      {
         return false;
      }
      return true;
   }
   
   private String firstName;
   private String lastName;
   private String title;
   private String organization;
   private Address address;
}
