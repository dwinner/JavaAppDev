package ru.inbox.dwinner.satellitesmovementapp.animation;

import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

/**
 * Генератор объектов для анимации представлений.
 *
 * @author dwinner@inbox.ru
 * @version 1.0.0 07-10-2012
 */
public class ViewAnimationFactory
{
   /**
    * Вращение View относительно центра.
    *
    * @param distance Расстояние от центра
    * @param angle Угол расположения относительно горизонтали
    * @param duration Время полного оборота
    * @return Объект Animation
    */
   public static Animation createOrbitalRotation(final float distance, final float angle, final long duration)
   {
      return new Animation()
      {
         @Override
         public void initialize(int width, int height, int parentWidth, int parentHeight)
         {
            super.initialize(width, height, parentWidth, parentHeight);
            centerX = (float) (distance * Math.cos(angle)) + width / 2.0f;
            centerY = (float) (distance * Math.sin(angle)) + height / 2.0f;
            setDuration(duration);
            setRepeatMode(Animation.INFINITE);
            setRepeatCount(Animation.INFINITE);
            setInterpolator(new LinearInterpolator());
         }

         @Override
         protected void applyTransformation(float interpolatedTime, Transformation t)
         {
            Matrix matrix = t.getMatrix();
            matrix.setRotate(interpolatedTime * 360);
            matrix.preTranslate(-centerX, -centerY);
            matrix.postTranslate(centerX, centerY);
         }

         private float centerX;
         private float centerY;
      };
   }

   private ViewAnimationFactory()
   {
   }

}