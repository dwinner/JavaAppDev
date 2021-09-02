package synchronizers;

import java.util.concurrent.Phaser;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 15.11.12
 * Time: 23:26
 * Реализация фазовых потоков выполнения с заданным количеством фаз.
 */
public class PhaserOnAdvanceTest
{
   public static void main(String[] args)
   {
      MyPhaser phaser = new MyPhaser(1, 4);
      System.out.println("Starting\n");
      new PartyThread(phaser, "A");
      new PartyThread(phaser, "B");
      new PartyThread(phaser, "C");
      // Ожидать завершения определенного количества фаз
      while (!phaser.isTerminated())
         phaser.arriveAndAwaitAdvance();
      System.out.println("The phaser is terminated");
   }
}

class MyPhaser extends Phaser
{
   MyPhaser(int parties, int phaseCount)
   {
      super(parties);
      numParties = phaseCount - 1;
   }

   @Override
   protected boolean onAdvance(int phase, int registeredParties)
   {
      System.out.println("Phase " + phase + " completed.\n");
      if (phase == numParties || registeredParties == 0)
         return true;   // Все фазы завершены
      return false;
   }

   private int numParties;
}

class PartyThread implements Runnable
{
   PartyThread(Phaser aPhaser, String aPartyName)
   {
      phaser = aPhaser;
      partyName = aPartyName;
      phaser.register();
      new Thread(this).start();
   }

   @Override
   public void run()
   {
      while (!phaser.isTerminated())
      {
         System.out.println("Thread " + partyName + " beginning phase " + phaser.getPhase());
         phaser.arriveAndAwaitAdvance();
         try { Thread.sleep(10); }
         catch (InterruptedException intEx) { System.out.println(intEx); }
      }
   }

   private Phaser phaser;
   private String partyName;
}
