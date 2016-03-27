package com.corejsf;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("user")
@SessionScoped
public class UserBean implements Serializable
{
   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public String getPassword()
   {
      return password;
   }

   public void setPassword(String password)
   {
      this.password = password;
   }

   public String getAboutYourself()
   {
      return aboutYourself;
   }

   public void setAboutYourself(String aboutYourself)
   {
      this.aboutYourself = aboutYourself;
   }

   private String name;
   private String password;
   private String aboutYourself;
}
