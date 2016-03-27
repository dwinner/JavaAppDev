package ru.inbox.dwinner.satellitesmovementapp.model;

import java.io.Serializable;

/**
 * Класс-модель для параметров спутника.
 *
 * @author dwinner@inbox.ru
 * @version 1.0.0 06-10-2012
 */
public class Satellite implements Serializable
{
   /**
    * Конструктор создания параметров спутника
    *
    * @param aRoundingTime Время, в миллисекундах, за которое спутник делает один оборот вокруг земли
    * @param aDistance Расстояние спутника, в dp, от центра земли
    * @param anAngle Начальное положение спутника на орбите, в радианах
    */
   public Satellite(long aRoundingTime, float aDistance, float anAngle)
   {
      roundingTime = aRoundingTime;
      distance = aDistance;
      angle = anAngle;
   }

   /**
    * Конструктор создания параметров спутника со случайным углом
    *
    * @param roundingTime Время, в миллисекундах, за которое спутник делает один оборот вокруг земли
    * @param distance Расстояние спутника, в dp, от центра земли
    */
   public Satellite(long roundingTime, float distance)
   {
      this(roundingTime, distance, randomAngle());
   }

   /**
    * Конструктор по умолчанию.
    */
   public Satellite()
   {
      roundingTime = DEFAULT_ROUNDING_TIME;
      distance = DEFAULT_DISTANCE;
      angle = DEFAULT_ANGLE;
   }

   /**
    * Время, в миллисекундах, за которое спутник делает один оборот вокруг земли
    *
    * @return Время в миллисекундах
    */
   public long getRoundingTime()
   {
      return roundingTime;
   }

   /**
    * Установка времени, в миллисекундах, за которое спутник делает один оборот вокруг земли
    *
    * @param aRoundingTime Время в миллисекундах
    */
   public void setRoundingTime(long aRoundingTime)
   {
      roundingTime = aRoundingTime;
   }

   /**
    * Расстояние спутника, в dp, от центра земли
    *
    * @return Расстояние спутника от центра земли
    */
   public float getDistance()
   {
      return distance;
   }

   /**
    * Установка расстояния спутника, в dp, от центра земли
    *
    * @param aDistance Расстояние спутника в dp
    */
   public void setDistance(float aDistance)
   {
      distance = aDistance;
   }

   /**
    * Начальное положение спутника на орбите, в радианах
    *
    * @return Начальное положение спутника на орбите
    */
   public float getAngle()
   {
      return angle;
   }

   /**
    * Установка начального положения спутника на орбите
    *
    * @param anAngle Начальное положение спутника на орбите, в радианах
    */
   public void setAngle(float anAngle)
   {
      angle = anAngle;
   }

   @Override
   public int hashCode()
   {
      int hash = 5;
      hash = 61 * hash + (int) (roundingTime ^ (roundingTime >>> 32));
      hash = 61 * hash + Float.floatToIntBits(distance);
      hash = 61 * hash + Float.floatToIntBits(angle);
      return hash;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (obj == null)
      {
         return false;
      }
      if (getClass() != obj.getClass())
      {
         return false;
      }
      final Satellite other = (Satellite) obj;
      if (roundingTime != other.getRoundingTime())
      {
         return false;
      }
      if (Float.floatToIntBits(distance) != Float.floatToIntBits(other.getDistance()))
      {
         return false;
      }
      if (Float.floatToIntBits(angle) != Float.floatToIntBits(other.getAngle()))
      {
         return false;
      }
      return true;
   }

   @Override
   public String toString()
   {
      return "Satellite{" + "roundingTime=" + roundingTime
        + ", distance=" + distance
        + ", angle=" + angle + '}';
   }

   public static float randomAngle()
   {
      return (float) (2 * Math.PI * Math.random());
   }

   private long roundingTime;
   private float distance;
   private float angle;
   private static final long serialVersionUID = -1101121520854880773L;
   private static final long DEFAULT_ROUNDING_TIME = 0;
   private static final float DEFAULT_DISTANCE = .0F;
   private static final float DEFAULT_ANGLE = .0F;
}