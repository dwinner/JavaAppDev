package searchintableviewtest;

import javafx.beans.property.SimpleStringProperty;

/**
 * Сущностный класс для модели таблицы.
 *
 * @author Denis
 */
public class PersonEntity
{
   private final SimpleStringProperty firstName;
   private final SimpleStringProperty lastName;
   private final SimpleStringProperty email;

   public PersonEntity(String fName, String lName, String email)
   {
      firstName = new SimpleStringProperty(fName);
      lastName = new SimpleStringProperty(lName);
      this.email = new SimpleStringProperty(email);
   }

   public String getFirstName()
   {
      return firstName.get();
   }

   public void setFirstName(String fName)
   {
      firstName.set(fName);
   }

   public String getLastName()
   {
      return lastName.get();
   }

   public void setLastName(String lName)
   {
      lastName.set(lName);
   }

   public String getEmail()
   {
      return email.get();
   }

   public void setEmail(String email)
   {
      this.email.set(email);
   }
}
