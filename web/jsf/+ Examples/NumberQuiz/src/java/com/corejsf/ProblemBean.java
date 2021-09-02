package com.corejsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProblemBean implements Serializable
{
   public ProblemBean()
   {
   }

   public ProblemBean(int[] values, int solution)
   {
      sequence = new ArrayList<Integer>(values.length);
      for (int i = 0; i < values.length; i++)
      {
         sequence.add(values[i]);
      }
      this.solution = solution;
   }

   public List<Integer> getSequence()
   {
      return Collections.unmodifiableList(sequence);
   }

   public void setSequence(ArrayList<Integer> newValue)
   {
      sequence = newValue;
   }

   public int getSolution()
   {
      return solution;
   }

   public void setSolution(int newValue)
   {
      solution = newValue;
   }

   private List<Integer> sequence;
   private int solution;
}
