package com.corejsf;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.inject.Named;
// or import javax.faces.bean.ManagedBean;
import javax.enterprise.context.SessionScoped;
// or import javax.faces.bean.SessionScoped;

@Named // or @ManagedBean
@SessionScoped
public class QuizBean implements Serializable
{
   public String getQuestion()
   {
      return problems.get(currentProblem).getQuestion();
   }

   public String getAnswer()
   {
      return correctAnswer;
   }

   public int getScore()
   {
      int score = 0;
      for (int s : scores)
      {
         score += s;
      }
      return score;
   }

   public String getResponse()
   {
      return response;
   }

   public void setResponse(String newValue)
   {
      response = newValue;
   }

   public int getCurrentProblem()
   {
      return currentProblem;
   }

   public void setCurrentProblem(int newValue)
   {
      currentProblem = newValue;
   }

   public String getSkipOutcome()
   {
      return (currentProblem < problems.size() - 1) ? "index" : "done";
   }

   public String answerAction()
   {
      tries++;
      if (problems.get(currentProblem).isCorrect(response))
      {
         scores[currentProblem] = 1;
         nextProblem();
         return currentProblem == problems.size() ? "done" : "success";
      }
      else
      {
         scores[currentProblem] = 0;
         if (tries == 1)
         {
            return "again";
         }
         else
         {
            nextProblem();
            return currentProblem == problems.size() ? "done" : "failure";
         }
      }
   }

   public String startOverAction()
   {
      Collections.shuffle(problems);
      currentProblem = 0;
      for (int i = 0; i < scores.length; i++)
      {
         scores[i] = 0;
      }
      tries = 0;
      response = "";
      return "startOver";
   }

   private void nextProblem()
   {
      correctAnswer = problems.get(currentProblem).getAnswer();
      currentProblem++;
      tries = 0;
      response = "";
   }

   private int currentProblem;
   private int tries;
   private String response = "";
   private String correctAnswer;
   // Here, we hardwire the problems. In a real application,
   // they would come from a database
   private ArrayList<Problem> problems = new ArrayList<Problem>(Arrays.asList(
     new Problem("What trademarked slogan describes Java development? Write once, ...",
                 "run anywhere"),
     new Problem("What are the first 4 bytes of every class file (in hexadecimal)?",
                 "CAFEBABE"),
     new Problem("What does this statement print? System.out.println(1+\"2\");",
                 "12"),
     new Problem("Which Java keyword is used to define a subclass?",
                 "extends"),
     new Problem("What was the original name of the Java programming language?",
                 "Oak"),
     new Problem("Which java.util class describes a point in time?",
                 "Date")));
   private int[] scores = new int[problems.size()];
}