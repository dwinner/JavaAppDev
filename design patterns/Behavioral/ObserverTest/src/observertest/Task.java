package observertest;

public class Task
{
   public Task()
   {
   }

   public Task(String newName, String newNotes, double newTimeRequired)
   {
      name = newName;
      notes = newNotes;
      timeRequired = newTimeRequired;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public String getNotes()
   {
      return notes;
   }

   public void setNotes(String notes)
   {
      this.notes = notes;
   }

   public double getTimeRequired()
   {
      return timeRequired;
   }

   public void setTimeRequired(double timeRequired)
   {
      this.timeRequired = timeRequired;
   }

   @Override
   public String toString()
   {
      return "Task{" + "name=" + name + ", notes=" + notes + ", timeRequired=" + timeRequired + '}';
   }
   
   private String name = "";
   private String notes = "";
   private double timeRequired;
}
