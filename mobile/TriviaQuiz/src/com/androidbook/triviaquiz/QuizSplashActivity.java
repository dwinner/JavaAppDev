package com.androidbook.triviaquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class QuizSplashActivity extends QuizActivity
{
   @Override
   protected void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.splash);
      startAnimating();
   }

   @Override
   protected void onResume()
   {
      super.onResume();
      startAnimating(); // Возобновление анимации.
   }

   @Override
   protected void onPause()
   {
      super.onPause();

      // Остановить анимацию.

      TextView logo1 = (TextView) findViewById(R.id.TextViewTopTitle);
      logo1.clearAnimation();
      TextView logo2 = (TextView) findViewById(R.id.TextViewBottomTitle);
      logo2.clearAnimation();
      TableLayout table = (TableLayout) findViewById(R.id.TableLayout01);
      for (int i = 0; i < table.getChildCount(); i++)
      {
         TableRow row = (TableRow) table.getChildAt(i);
         row.clearAnimation();
      }
   }

   /**
    * Вспомогательный метод для начала анимации на экране-заставке.
    */
   private void startAnimating()
   {
      // Затемнение верхнего заголовка.

      TextView logo1 = (TextView) findViewById(R.id.TextViewTopTitle);
      Animation fade1 = AnimationUtils.loadAnimation(this, R.anim.fade_in);
      logo1.startAnimation(fade1);

      // Затемнение нижнего заголовка после встроенной задержки.

      TextView logo2 = (TextView) findViewById(R.id.TextViewBottomTitle);
      Animation fade2 = AnimationUtils.loadAnimation(this, R.anim.fade_in2);
      logo2.startAnimation(fade2);

      // Переход к главному меню, когда нижний заголовок завершит анимацию.

      fade2.setAnimationListener(new AnimationListener()
         {
            public void onAnimationEnd(Animation animation)
            {
               // Анимация закончилась, переходим к экрану главного меню.

               startActivity(new Intent(QuizSplashActivity.this, QuizMenuActivity.class));
               QuizSplashActivity.this.finish();
            }

            public void onAnimationStart(Animation animation)
            {
            }

            public void onAnimationRepeat(Animation animation)
            {
            }

         });

      // Загрузка анимаций для всех дочерних элементов табличного расположения.

      Animation spinin = AnimationUtils.loadAnimation(this, R.anim.custom_anim);
      LayoutAnimationController controller = new LayoutAnimationController(spinin);
      controller.setOrder(2);
      TableLayout table = (TableLayout) findViewById(R.id.TableLayout01);

      // table.setLayoutAnimation(controller);

      for (int i = 0; i < table.getChildCount(); i++)
      {
         TableRow row = (TableRow) table.getChildAt(i);
         row.setLayoutAnimation(controller);
      }
   }

}
