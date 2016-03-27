package ru.inbox.dwinner.satellitesmovementapp.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import ru.inbox.dwinner.satellitesmovementapp.model.Satellite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedMap;

/**
 * Менеджер расположения узлов View, упорядоченных по орбите включающего контейнера, начиная с центра.
 *
 * @author dwinner@inbox.ru
 * @version 1.0.0 07-10-2012
 */
public class OrbitLayout extends ViewGroup
{
   public OrbitLayout(Context context)
   {
      super(context);
   }

   public OrbitLayout(Context context, AttributeSet attrs)
   {
      super(context, attrs);
   }

   public OrbitLayout(Context context, AttributeSet attrs, int defStyle)
   {
      super(context, attrs, defStyle);
   }

   /**
    * Создание расположения по орбите
    *
    * @param aSatelliteMap Карта соответствия параметров спутника с его View
    * @param aContext Контекст раположения
    */
   public OrbitLayout(SortedMap<Satellite, View> aSatelliteMap, Context aContext)
   {
      super(aContext);
      setSatelliteMap(aSatelliteMap);
   }

   /**
    * Получение карты соответствия параметров спутника с его View
    *
    * @return Карта соответствия параметров спутника с его View
    */
   public SortedMap<Satellite, View> getSatelliteMap()
   {
      return Collections.unmodifiableSortedMap(satelliteMap);
   }

   /**
    * Установка карты соответствия параметров спутника с его View
    *
    * @param satelliteMap Карта соответствия параметров спутника с его View
    */
   public final void setSatelliteMap(SortedMap<Satellite, View> satelliteMap)
   {
      this.satelliteMap = satelliteMap;
      LayoutParams params =
        new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
      for (View aView : satelliteMap.values())
      {
         addView(aView, params);
      }
      satelliteList = new ArrayList<Satellite>(satelliteMap.keySet());
   }

   /**
    * Получение списка параметров спутников
    *
    * @return Список параметров спутников
    */
   public List<Satellite> getSatelliteList()
   {
      return Collections.unmodifiableList(satelliteList);
   }

   @Override
   protected void onLayout(boolean changed, int left, int top, int right, int bottom)
   {
      int availableWidth = right - left;
      int availableHeight = bottom - top;
      int count = getChildCount();
      int[] max = measureChildrenSizes(availableWidth, availableHeight);
      availableWidth -= max[0];
      availableHeight -= max[1];
      int centerX = availableWidth / 2;
      int centerY = availableHeight / 2;

      for (int i = 0; i < count; i++)
      {
         View child = getChildAt(i);

         int x, y;
         if (i == 0)
         {
            x = left + centerX;
            y = top + centerY;
         }
         else
         {
            float distance = satelliteList.get(i).getDistance();
            float angle = satelliteList.get(i).getAngle();
            x = (int) (left + centerX - distance * Math.cos(angle));
            y = (int) (top + centerY - distance * Math.sin(angle));
         }

         child.layout(
           x,
           y,
           x + child.getMeasuredWidth(),
           y + child.getMeasuredHeight());
      }
   }

   /**
    * Вычисление максимальных значений ширины и высоты дочерних узлов View во ViewGroup
    *
    * @param sw Ширина контейнера
    * @param sh Высота контейнера
    * @return Массив максимальных значений: [0] => ширина, [1] => высота
    */
   private int[] measureChildrenSizes(int sw, int sh)
   {
      int maxWidth = 0;
      int maxHeight = 0;

      for (int i = 0; i < getChildCount(); i++)
      {
         View child = getChildAt(i);
         measureChild(child, sw, sh);

         maxWidth = Math.max(maxWidth, child.getMeasuredWidth());
         maxHeight = Math.max(maxHeight, child.getMeasuredHeight());
      }

      return new int[]
        {
           maxWidth, maxHeight
        };
   }

   private SortedMap<Satellite, View> satelliteMap;
   private List<Satellite> satelliteList;
}
