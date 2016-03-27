package ru.inbox.dwinner.satellitesmovementapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.ImageView;
import android.widget.Toast;
import ru.inbox.dwinner.satellitesmovementapp.animation.ViewAnimationFactory;
import ru.inbox.dwinner.satellitesmovementapp.layout.OrbitLayout;
import ru.inbox.dwinner.satellitesmovementapp.model.Satellite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.*;

/**
 * Активность для экрана вращения спутников вокруг земли.
 *
 * @author dwinner@inbox.ru
 * @version 1.0.0 07-10-2012
 */
public class StartScreenActivity extends Activity
{
   @Override
   public boolean onCreateOptionsMenu(Menu menu)
   {
      super.onCreateOptionsMenu(menu);
      getMenuInflater().inflate(R.menu.navigate_options, menu);
      menu.findItem(R.id.StartMenuItemId).
        setIntent(new Intent(this, StartScreenActivity.class));
      menu.findItem(R.id.SettingsMenuItemId).
        setIntent(new Intent(this, SettingsScreenActivity.class));
      menu.findItem(R.id.AboutMenuItemId).
        setIntent(new Intent(this, AboutScreenActivity.class));
      return true;
   }

   @Override
   public boolean onOptionsItemSelected(MenuItem item)
   {
      super.onOptionsItemSelected(item);
      startActivity(item.getIntent());
      return true;
   }

   @Override
   protected void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);

      List<Satellite> satelliteList = retrieveCurrentSettings(SETTINGS_FILE_NAME);
      if (satelliteList.isEmpty())
      {
         setContentView(R.layout.activity_start_screen);
         return;
      }

      int satCount = satelliteList.size() + 1;
      View[] views = createViewArray(satCount);

      SortedMap<Satellite, View> satSortedMap =
        new TreeMap<Satellite, View>(new Comparator<Satellite>()
      {
         public int compare(Satellite o1, Satellite o2)
         {
            return Float.floatToIntBits(o1.getDistance()) - Float.floatToIntBits(o2.getDistance());
         }

      });

      satSortedMap.put(new Satellite(), views[0]);
      for (int i = 1; i < satCount; i++)
      {
         satSortedMap.put(satelliteList.get(i - 1), views[i]);
      }
      satelliteViewSortedMap = satSortedMap;

      ViewGroup orbitLayout = new OrbitLayout(satSortedMap, this);
      orbitLayout.setBackgroundResource(R.drawable.background);
      setContentView(orbitLayout);
      startViewAnimation(satSortedMap);
   }

   @Override
   protected void onPause()
   {
      super.onPause();
      if (satelliteViewSortedMap == null || satelliteViewSortedMap.isEmpty())
      {
         return;
      }
      Collection<View> viewColl = satelliteViewSortedMap.values();
      for (View aView : viewColl)
      {
         aView.clearAnimation();
      }
   }

   @Override
   protected void onResume()
   {
      super.onResume();
      if (satelliteViewSortedMap != null && !satelliteViewSortedMap.isEmpty())
      {
         startViewAnimation(satelliteViewSortedMap);
      }
   }

   /**
    * Получение списка текущих параметров спутников.
    *
    * @param fileName Имя файла с текущими параметрами
    * @return Список объектов Satellite
    */
   @SuppressWarnings("unchecked")
   private List<Satellite> retrieveCurrentSettings(String fileName)
   {
      String toastMessage = getResources().getString(R.string.satellites_have_not);
      Toast helpToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);
      helpToast.setGravity(Gravity.CENTER, 0, 0);

      try
      {
         FileInputStream fileIn = openFileInput(fileName);
         ObjectInputStream objectIn = null;
         try
         {
            objectIn = new ObjectInputStream(fileIn);
            List<Satellite> satellites = (List<Satellite>) objectIn.readObject();
            if (satellites.isEmpty())
            {
               helpToast.show();
            }
            return satellites;
         }
         finally
         {
            if (objectIn != null)
            {
               objectIn.close();
            }
         }
      }
      catch (FileNotFoundException fnfEx)
      {
         helpToast.show();
         return Collections.<Satellite>emptyList();
      }
      catch (IOException ioEx)
      {
         Log.e(getClass().getName(), ioEx.getLocalizedMessage(), ioEx);
         throw new RuntimeException(ioEx);
      }
      catch (ClassNotFoundException cnfEx)
      {
         Log.e(getClass().getName(), cnfEx.getLocalizedMessage(), cnfEx);
         throw new RuntimeException(cnfEx);
      }
   }

   /**
    * Создание массива представлений земли и спутников
    *
    * @param size Размер массива
    * @return Массив представлений.
    */
   private View[] createViewArray(int size)
   {
      // assert size > 1;
      View[] views = new View[size];

      ImageView earthImageView = new ImageView(this);
      earthImageView.setImageResource(R.drawable.globe);
      views[0] = earthImageView;

      for (int i = 1; i < size; i++)
      {
         ImageView satelliteImageView = new ImageView(this);
         satelliteImageView.setImageResource(R.drawable.action_satellite);
         views[i] = satelliteImageView;
      }

      return views;
   }

   /**
    * Старт анимаций представления
    *
    * @param sortedMap Карта параметров анимации и представлений
    */
   private void startViewAnimation(SortedMap<Satellite, View> sortedMap)
   {
      if (sortedMap == null || sortedMap.isEmpty())
      {
         return;
      }
      int index = 0;
      for (Map.Entry<Satellite, View> entry : sortedMap.entrySet())
      {
         if (index++ == 0)
         {
            continue;
         }
         Satellite currentSat = entry.getKey();
         View currentView = entry.getValue();
         currentView.startAnimation(
           ViewAnimationFactory.createOrbitalRotation(currentSat.getDistance(),
                                                      currentSat.getAngle(),
                                                      currentSat.getRoundingTime()));
      }
   }

   private SortedMap<Satellite, View> satelliteViewSortedMap;
   private static final String SETTINGS_FILE_NAME = "satellites.dat";
}
