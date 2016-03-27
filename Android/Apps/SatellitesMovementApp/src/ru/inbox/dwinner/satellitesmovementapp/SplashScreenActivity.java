package ru.inbox.dwinner.satellitesmovementapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Активность для экрана заставки
 *
 * @author dwinner@inbox.ru
 * @version 1.0.0 05-10-2012
 */
public class SplashScreenActivity extends Activity
{
   @Override
   public void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_splash_screen);
      runningAnimation();
   }

   @Override
   protected void onResume()
   {  // Восстановление анимации
      super.onResume();
      runningAnimation();
   }

   @Override
   protected void onPause()
   {  // Прекращение анимации
      super.onPause();
      TextView topTitle = (TextView) findViewById(R.id.splashTopTitleTextView);
      topTitle.clearAnimation();
      TextView bottomTitle = (TextView) findViewById(R.id.splashBottomTitleTextView);
      bottomTitle.clearAnimation();
      TableLayout tableLayout = (TableLayout) findViewById(R.id.TableLayoutId);
      for (int i = 0; i < tableLayout.getChildCount(); i++)
      {
         View rowView = tableLayout.getChildAt(i);
         rowView.clearAnimation();
      }
   }

   /**
    * Запуск анимации компоновки на экране заставки.
    */
   private void runningAnimation()
   {
      TextView topTitle = (TextView) findViewById(R.id.splashTopTitleTextView);
      Animation firstFadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
      topTitle.startAnimation(firstFadeIn);

      TextView bottomTitle = (TextView) findViewById(R.id.splashBottomTitleTextView);
      Animation secondFadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in_after_delay);
      bottomTitle.startAnimation(secondFadeIn);

      secondFadeIn.setAnimationListener(new AnimationListener()
      {
         public void onAnimationStart(Animation animation)
         {
         }

         public void onAnimationRepeat(Animation animation)
         {
         }

         public void onAnimationEnd(Animation animation)
         {
            startActivity(new Intent(SplashScreenActivity.this, MenuScreenActivity.class));
            SplashScreenActivity.this.finish();
         }

      });

      Animation spinIn = AnimationUtils.loadAnimation(this, R.anim.gradual_approach);
      LayoutAnimationController controller = new LayoutAnimationController(spinIn);
      controller.setOrder(2);
      TableLayout tableLayout = (TableLayout) findViewById(R.id.TableLayoutId);
      for (int i = 0; i < tableLayout.getChildCount(); i++)
      {
         TableRow rowView = (TableRow) tableLayout.getChildAt(i);
         rowView.setLayoutAnimation(controller);
      }
   }

}
