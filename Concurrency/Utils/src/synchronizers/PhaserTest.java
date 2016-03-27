package synchronizers;

import java.util.concurrent.Phaser;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 05.11.12
 * Time: 20:45
 * Фазовый синхронизатор.
 */
public class PhaserTest
{
   public static void main(String[] args)
   {
      Phaser phaser = new Phaser(1);
      int curPhase;
      System.out.println("Starting");
      new PhaserThread(phaser, "A");
      new PhaserThread(phaser, "B");
      new PhaserThread(phaser, "C");

      // Ожидать завершения всеми потоками первой фазы.
      curPhase = phaser.getPhase();
      phaser.arriveAndAwaitAdvance();
      System.out.println("Phase " + curPhase + " complete");
      // Ожидать завершения всеми потоками второй фазы.
      curPhase = phaser.getPhase();
      phaser.arriveAndAwaitAdvance();
      System.out.println("Phase " + curPhase + " complete");

      curPhase = phaser.getPhase();
      phaser.arriveAndAwaitAdvance();
      System.out.println("Phase " + curPhase + " complete");

      // Отмена регистрации основного потока.
      phaser.arriveAndDeregister();
      if (phaser.isTerminated())
         System.out.println("The phaser is terminated.");
   }
}

/**
 * Поток выполнения, использующий фазовый синхронизатор.
 */
class PhaserThread implements Runnable
{
   PhaserThread(Phaser aPhaser, String aName)
   {
      phaser = aPhaser;
      name = aName.intern();
      phaser.register();
      new Thread(this).start();
   }

   @Override
   public void run()
   {
      System.out.println("Thread " + name + " beginning phase one");
      phaser.arriveAndAwaitAdvance();  // Сигнал завершения

      try { Thread.sleep(10); } catch (InterruptedException e) { }

      System.out.println("Thread " + name + " beginning phase two.");
      phaser.arriveAndAwaitAdvance();  // Сигнал завершения.

      try { Thread.sleep(10); } catch (InterruptedException e) { }

      System.out.println("Thread " + name + " beginning phase three");
      phaser.arriveAndDeregister(); // Сигнал завершения и отмена регистрации
   }

   private Phaser phaser;
   private String name;
}
