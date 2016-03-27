package com.corejsf;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class TableData implements Serializable
{
   public List<Name> getNames()
   {
      return Collections.unmodifiableList(names);
   }

   public String deleteRow(Name nameToDelete)
   {
      names.remove(nameToDelete);
      return null;
   }

   public String addAbove(Name nameToAddAbove)
   {
      names.add(names.indexOf(nameToAddAbove), new Name("Demo", "Demo"));
      return null;
   }

   public String addAfter(Name nameToAddAfter)
   {
      names.add(names.indexOf(nameToAddAfter) + 1, new Name("Demo", "Demo"));
      return null;
   }

   public String save()
   {
      return null;
   }

   private List<Name> names = new LinkedList<Name>(Arrays.asList(
     new Name("Anna", "Keeney"),
     new Name("John", "Wilson"),
     new Name("Mariko", "Randor"),
     new Name("William", "Dupont")));
}
