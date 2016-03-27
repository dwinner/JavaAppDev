package com.corejsf;

import java.io.Serializable; // or @ManagedBean(name="user")
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

   public void setName(String newValue)
   {
      name = newValue;
   }

   public String getPassword()
   {
      return password;
   }

   public void setPassword(String newValue)
   {
      password = newValue;
   }

   private String name;
   private String password;
}