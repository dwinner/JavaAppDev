package com.corejsf;

import java.io.File;
import java.io.Serializable;
import java.util.Arrays;

public class Problem implements Serializable
{
   public Problem(String question, String answer)
   {
      this.question = question;
      this.answer = answer;
   }

   public String getQuestion()
   {
      return question;
   }

   public String getAnswer()
   {
      return answer;
   }

   // TODO: Переопределить в целях более сложной проверки
   public boolean isCorrect(String response)
   {
      return response.trim().equalsIgnoreCase(answer);
   }

   private String question;
   private String answer;
}
