package com.corejsf;

import java.awt.Color;
import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.util.*;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import static com.corejsf.RegisterForm.Education.*;

@Named("form")
@SessionScoped
public class RegisterForm implements Serializable
{
   public static enum Education
   {
      HIGH_SCHOOL("High School"),
      BACHELOR("Bachelor's"),
      MASTER("Master's"),
      DOCTOR("Doctorate");

      @Override
      public String toString()
      {
         return desc;
      }

      public String getDesc()
      {
         return desc;
      }

      private Education(String description)
      {
         desc = description;
      }

      private String desc;
   }

   public static class Weekday
   {
      public Weekday(int dayOfWeek)
      {
         this.dayOfWeek = dayOfWeek;
      }

      public String getDayName()
      {
         DateFormatSymbols symbols = new DateFormatSymbols();
         String[] weekdays = symbols.getWeekdays();
         return weekdays[dayOfWeek];
      }

      public int getDayNumber()
      {
         return dayOfWeek;
      }

      private int dayOfWeek;
   }

   // <editor-fold defaultstate="collapsed" desc="Свойства">
   public String getName()
   {
      return name;
   }

   public void setName(String newName)
   {
      name = newName;
   }

   public boolean isContactMe()
   {
      return contactMe;
   }

   public void setContactMe(boolean contactMe)
   {
      this.contactMe = contactMe;
   }

   public int[] getBestDaysToContact()
   {
      return bestDaysToContact;
   }

   public void setBestDaysToContact(int[] newBestDaysToContact)
   {
      bestDaysToContact = newBestDaysToContact;
   }

   public Integer getYearOfBirth()
   {
      return yearOfBirth;
   }

   public void setYearOfBirth(Integer newYearOfBirth)
   {
      yearOfBirth = newYearOfBirth;
   }

   public int[] getColors()
   {
      return colors;
   }

   public void setColors(int[] newColors)
   {
      colors = newColors;
   }

   public Set<String> getLanguages()
   {
      return languages;
   }

   public void setLanguages(Set<String> newLanguagesSet)
   {
      languages = newLanguagesSet;
   }

   public Education getEducation()
   {
      return education;
   }

   public void setEducation(Education newEducation)
   {
      education = newEducation;
   }
   // </editor-fold>

   // <editor-fold defaultstate="expanded" desc="Свойства элементов выбора">
   public Collection<SelectItem> getYearItems()
   {
      return Collections.unmodifiableCollection(birthYears);
   }

   public Weekday[] getDaysOfTheWeek()
   {
      return daysOfTheWeek;
   }

   public SelectItem[] getLanguageItems()
   {
      return languageItems;
   }

   public SelectItem[] getColotItems()
   {
      return colorItems;
   }

   public Map<String, Education> getEducationItems()
   {
      return educationItems;
   }

   public String getBestDaysConcatenated()
   {
      return Arrays.toString(bestDaysToContact);
   }

   public String getColorsConcatenated()
   {
      StringBuilder result = new StringBuilder();
      for (int color : colors)
      {
         result.append(String.format("%06x", color));
      }
      return result.toString();
   }
   // </editor-fold>

   private String name;
   private boolean contactMe;
   private int[] bestDaysToContact;
   private Integer yearOfBirth;
   private int[] colors;
   private Set<String> languages = new TreeSet<String>();
   private Education education = BACHELOR;
   // <editor-fold defaultstate="expanded" desc="Значения элементов выбора">
   private SelectItem[] colorItems =
   {  // Значение, метка
      new SelectItem(Color.RED.getRGB(), "Red"),
      new SelectItem(Color.GREEN.getRGB(), "Green"),
      new SelectItem(Color.BLUE.getRGB(), "Blue"),
      new SelectItem(Color.YELLOW.getRGB(), "Yellow"),
      new SelectItem(Color.ORANGE.getRGB(), "Orange", "", true)   // Отменено
   };
   private final static Map<String, Education> educationItems;

   static
   {  // FIXME: Use EnumMap<K, V>
      educationItems = new LinkedHashMap<String, Education>();
      educationItems.put(HIGH_SCHOOL.getDesc(), HIGH_SCHOOL);
      educationItems.put(BACHELOR.getDesc(), BACHELOR);
      educationItems.put(MASTER.getDesc(), MASTER);
      educationItems.put(DOCTOR.getDesc(), DOCTOR);
   }

   private final static SelectItem[] languageItems =
   {  // FIXME: Use EnumSet<E>
      new SelectItem("English"),
      new SelectItem("French"),
      new SelectItem("Russian"),
      new SelectItem("Italian"),
      new SelectItem("Esperanto", "Esperanto", "", true) // Отменено
   };
   private final static Collection<SelectItem> birthYears;

   static
   {
      birthYears = new ArrayList<SelectItem>();
      // Первым элементом является "вариант пустого выбора"
      birthYears.add(new SelectItem(null, "Pick a year:", "", false, false, true));
      for (int i = 1900; i < 2020; ++i)
      {
         birthYears.add(new SelectItem(i));
      }
   }

   private final static Weekday[] daysOfTheWeek;

   static
   {
      daysOfTheWeek = new Weekday[7];
      for (int i = Calendar.SUNDAY; i <= Calendar.SATURDAY; i++)
      {
         daysOfTheWeek[i - Calendar.SUNDAY] = new Weekday(i);
      }
   }
   // </editor-fold>
}
