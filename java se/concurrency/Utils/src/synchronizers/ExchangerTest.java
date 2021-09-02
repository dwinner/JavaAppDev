package synchronizers;

import java.util.concurrent.Exchanger;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 04.11.12
 * Time: 20:33
 * Синхронный обмен данными между потоками.
 */
public class ExchangerTest
{
   public static void main(String[] args)
   {
      Exchanger<String> exchanger = new Exchanger<>();
      new UseString(exchanger);
      new MakeString(exchanger);
   }
}

/**
 * Поток, формирующий данные.
 */
class MakeString implements Runnable
{
   MakeString(Exchanger<String> exchanger)
   {
      this.exchanger = exchanger;
      string = "";
      new Thread(this).start();
   }

   @Override
   public void run()
   {
      char ch = 'A';
      for (int i = 0; i < 3; i++)
      {
         // Заполнение буфера
         for (int j = 0; j < 5; j++)
            string += ch++;

         // Очистка буфера через обмен
         try
         {
            string = exchanger.exchange(string);
         }
         catch (InterruptedException e)
         {
            Thread.currentThread().interrupt();
         }
      }
   }

   private Exchanger<String> exchanger;
   private String string;
}

/**
 * Поток, использующий данные.
 */
class UseString implements Runnable
{
   UseString(Exchanger<String> exchanger)
   {
      this.exchanger = exchanger;
      new Thread(this).start();
   }

   @Override
   public void run()
   {
      for (int i = 0; i < 3; i++)
      {
         try
         {
            // Пустой буфер становится заполненным.
            string = exchanger.exchange("");
            System.out.println("Got: " + string);
         }
         catch (InterruptedException e)
         {
            Thread.currentThread().interrupt();
         }
      }
   }

   private Exchanger<String> exchanger;
   private String string = "";
}
