package com.corejsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ConversationScoped
public class QuizBean implements Serializable
{
   public QuizBean()
   {
      problems.add(
        new ProblemBean(new int[]
        {
           3, 1, 4, 1, 5
        }, 9));   // Цифры числа pi
      problems.add(
        new ProblemBean(new int[]
        {
           1, 1, 2, 3, 5
        }, 8));   // Числа Фибоначчи
      problems.add(
        new ProblemBean(new int[]
        {
           1, 4, 9, 16, 25
        }, 36));   // Квадраты
      problems.add(
        new ProblemBean(new int[]
        {
           2, 3, 5, 7, 11
        }, 13)); // Простые числа
      problems.add(
        new ProblemBean(new int[]
        {
           1, 2, 4, 8, 16
        }, 32)); // Степени 2
   }

   public void setProblems(List<ProblemBean> newValue)
   {
      problems = newValue;
      currentIndex = 0;
      score = 0;
   }

   public int getScore()
   {
      return score;
   }

   public ProblemBean getCurrent()
   {
      return problems.get(currentIndex);
   }

   public String getAnswer()
   {
      return "";
   }

   public void setAnswer(String newValue)
   {
      try
      {
         if (currentIndex == 0)
         {
            conversation.begin();
         }
         int answer = Integer.parseInt(newValue.trim());
         if (getCurrent().getSolution() == answer)
         {
            score++;
         }
         currentIndex = (currentIndex + 1) % problems.size();
         if (currentIndex == 0)
         {
            conversation.end();
         }
      }
      catch (NumberFormatException ex)
      {
      }
   }

   private @Inject Conversation conversation;
   private List<ProblemBean> problems = new ArrayList<ProblemBean>();
   private int currentIndex;
   private int score;
}
