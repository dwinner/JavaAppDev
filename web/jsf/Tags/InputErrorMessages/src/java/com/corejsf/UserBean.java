package com.corejsf;

import java.io.Serializable;

import javax.inject.Named;
// или import javax.faces.bean.ManagedBean;
import javax.enterprise.context.SessionScoped;
// или import javax.faces.bean.SessionScoped;

@Named("user") // или @ManagedBean(name="user")
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

   public int getAge()
   {
      return age;
   }

   public void setAge(int newValue)
   {
      age = newValue;
   }

   private String name;
   private int age;
}