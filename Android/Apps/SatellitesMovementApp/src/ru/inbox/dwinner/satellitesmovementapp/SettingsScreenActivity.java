package ru.inbox.dwinner.satellitesmovementapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import ru.inbox.dwinner.satellitesmovementapp.model.Satellite;
import ru.inbox.dwinner.satellitesmovementapp.utils.ui.AlertDialogUtil;
import ru.inbox.dwinner.satellitesmovementapp.utils.ui.ToastHandlerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Активность для экрана установок параметров спутников.
 *
 * @author dwinner@inbox.ru
 * @version 1.0.0 06-10-2012
 */
public class SettingsScreenActivity extends Activity
{
   private final static String SETTINGS_FILE_NAME = "satellites.dat";
   private final static float MINIMUM_DISTANCE_BETWEEN = 5F;
   private TableLayout settingsTable;
   private int currentRecordId = -1;
   private List<Satellite> motionParams;

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
      setContentView(R.layout.activity_settings_screen);

      motionParams = new ArrayList<Satellite>(0x40);
      settingsTable = (TableLayout) findViewById(R.id.satellitesInfoTableLayoutId);

      setupHeaderRows();
      retrieveSettings(SETTINGS_FILE_NAME);
      setupAddButton();
      setupDeleteButton();
      setupSaveButton();
   }

   /**
    * Получение текущих установок для спутников.
    */
   private void retrieveSettings(String fileName)
   {
      try
      {
         FileInputStream fIn = openFileInput(fileName);
         ObjectInputStream in = null;
         try
         {
            in = new ObjectInputStream(fIn);
            @SuppressWarnings("unchecked")
            List<Satellite> list = (List<Satellite>) in.readObject();
            if (list.isEmpty())
            {
               return;
            }
            motionParams.addAll(list);

            for (Satellite motionParam : motionParams)
            {
               long roundingTime = motionParam.getRoundingTime();
               float distance = motionParam.getDistance();

               TableRow tableRow = new TableRow(this);

               EditText speedText = new EditText(this);
               speedText.setInputType(InputType.TYPE_CLASS_NUMBER);
               speedText.setMaxLines(1);
               speedText.setText("" + roundingTime);
               speedText.setId(UniqueIdGenerator.nextUniqueId());
               currentRecordId = speedText.getId();
               tableRow.addView(speedText);

               EditText distanceText = new EditText(this);
               distanceText.setInputType(
                  InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
               distanceText.setMaxLines(1);
               distanceText.setText("" + distance);
               distanceText.setId(speedText.getId() + 1);
               tableRow.addView(distanceText);

               settingsTable.addView(tableRow);
            }
         }
         finally
         {
            if (in != null)
            {
               in.close();
            }
         }
      }
      catch (FileNotFoundException fnfEx)
      {
         Log.i(getClass().getName(), fnfEx.getLocalizedMessage(), fnfEx);
      }
      catch (IOException ioEx)
      {
         Log.e(getClass().getName(), ioEx.getLocalizedMessage(), ioEx);
      }
      catch (ClassNotFoundException cnfEx)
      {
         Log.e(getClass().getName(), cnfEx.getLocalizedMessage(), cnfEx);
      }
   }

   /**
    * Установка заголовков таблицы.
    */
   private void setupHeaderRows()
   {
      TableRow headerRow = new TableRow(this);

      // Заголовок таблицы для скорости
      Button speedAboutButton = new Button(this);
      speedAboutButton.setText(getResources().getString(R.string.speedTableHeader));
      String speedHelpText = getResources().getString(R.string.speed_help_text);
      speedAboutButton.setOnClickListener(
         ToastHandlerFactory.createCenterToast(this, speedHelpText, 5000));
      headerRow.addView(speedAboutButton);

      // Заголовок таблицы для расстояния
      Button distanceAboutButton = new Button(this);
      distanceAboutButton.setText(getResources().getString(R.string.distanceTableHeader));
      String distanceHelpText = getResources().getString(R.string.distance_help_text);
      distanceAboutButton.setOnClickListener(
         ToastHandlerFactory.createCenterToast(this, distanceHelpText, 5000));
      headerRow.addView(distanceAboutButton);

      settingsTable.addView(headerRow);
   }

   /**
    * Установка кнопки добавления строки таблицы.
    */
   private void setupAddButton()
   {
      Button addButton = (Button) findViewById(R.id.addButtonId);
      final Button deleteButton = (Button) findViewById(R.id.deleteButtonId);
      if (currentRecordId == -1)
      {
         deleteButton.setEnabled(false);
      }

      addButton.setOnClickListener(new View.OnClickListener()
      {
         public void onClick(View v)
         {
            if (currentRecordId == -1)
            {
               deleteButton.setEnabled(true);
            }

            EditText speedEditText = new EditText(SettingsScreenActivity.this);
            speedEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
            speedEditText.setHint(R.string.speed_hint);
            speedEditText.setMaxLines(1);
            speedEditText.setId(UniqueIdGenerator.nextUniqueId());
            currentRecordId = speedEditText.getId();

            EditText distanceEditText = new EditText(SettingsScreenActivity.this);
            distanceEditText.setInputType(
               InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            distanceEditText.setHint(R.string.distance_hint);
            distanceEditText.setMaxLines(1);
            distanceEditText.setId(speedEditText.getId() + 1);

            TableRow row = new TableRow(SettingsScreenActivity.this);
            row.addView(speedEditText);
            row.addView(distanceEditText);

            settingsTable.addView(row);
         }

      });
   }

   /**
    * Установка кнопки удаления строки из таблицы.
    */
   private void setupDeleteButton()
   {
      final Button deleteButton = (Button) findViewById(R.id.deleteButtonId);
      deleteButton.setOnClickListener(new View.OnClickListener()
      {
         public void onClick(View v)
         {
            if (currentRecordId != -1)
            {
               settingsTable.removeViewAt(settingsTable.getChildCount() - 1);
               currentRecordId = UniqueIdGenerator.prevUniqueId();
            }
            if (currentRecordId == -1)
            {
               deleteButton.setEnabled(false);
            }
         }

      });
   }

   /**
    * Установка кнопки сохранения данных таблицы.
    */
   private void setupSaveButton()
   {
      Button saveButton = (Button) findViewById(R.id.saveButtonId);
      saveButton.setOnClickListener(new View.OnClickListener()
      {
         public void onClick(View view)
         {
            if (!motionParams.isEmpty())
            {
               motionParams.clear();
            }
            // -- Сбор информации из текстовых полей ввода
            nextTableRow:
            for (int i = 0; i < settingsTable.getChildCount(); i++)
            {
               TableRow tableRow = (TableRow) settingsTable.getChildAt(i);

               long currentTime = -1;
               float currentDistance = -1;

               for (int j = 0; j < tableRow.getChildCount(); j++)
               {
                  View currentView = tableRow.getChildAt(j);
                  if (!(currentView instanceof EditText))
                  {
                     continue nextTableRow;
                  }
                  final EditText editText = (EditText) currentView;
                  int editTextId = editText.getId();
                  String currentText = editText.getText().toString().trim();
                  if (currentText.length() == 0)
                  {
                     continue nextTableRow;
                  }

                  try
                  {
                     if (editTextId % 2 != 0)
                     {
                        currentTime = Long.parseLong(currentText);
                        if (currentTime <= 0)
                        {
                           String errorMessage = getResources().getString(R.string.negative_time_error);
                           AlertDialogUtil.showSimpleDialog(SettingsScreenActivity.this, errorMessage);
                           editText.requestFocus();
                           return;
                        }
                     }
                     else
                     {
                        currentDistance = Float.parseFloat(currentText);
                        if (currentDistance <= 0)
                        {
                           String errorMessage = getResources().getString(R.string.negative_distance_error);
                           AlertDialogUtil.showSimpleDialog(SettingsScreenActivity.this, errorMessage);
                           editText.requestFocus();
                           return;
                        }
                     }
                  }
                  catch (NumberFormatException nfEx)
                  {
                     AlertDialog errorDialog = new AlertDialog.Builder(SettingsScreenActivity.this).create();
                     errorDialog.setMessage(getResources().getString(R.string.type_mismatch_error));
                     errorDialog.setButton(DialogInterface.BUTTON_POSITIVE,
                                           getResources().getString(android.R.string.ok),
                                           new DialogInterface.OnClickListener()
                                           {
                                              public void onClick(DialogInterface dialogInterface, int i)
                                              {
                                                 editText.requestFocus();
                                              }

                                           });
                     errorDialog.show();
                     return;
                  }
               }

               motionParams.add(new Satellite(currentTime, currentDistance));
            }

            if (checkDistances())
            {
               saveSettings(SETTINGS_FILE_NAME);
            }
         }

      });
   }

   /**
    * Проверка спутников на столкновения с землей и друг с другом.
    *
    * @return true, если столкновений не будет, false в противном случае
    */
   private boolean checkDistances()
   {
      if (motionParams.isEmpty())
      {
         return true;
      }

      Drawable earth = getResources().getDrawable(R.drawable.globe);
      Drawable satellite = getResources().getDrawable(R.drawable.action_satellite);
      int maxEarth = Math.max(earth.getIntrinsicWidth(), earth.getIntrinsicHeight());
      int maxSatellite = Math.max(satellite.getIntrinsicWidth(), satellite.getIntrinsicHeight());

      Collections.sort(motionParams, new Comparator<Satellite>()
      {
         public int compare(Satellite o1, Satellite o2)
         {
            return Float.floatToIntBits(o1.getDistance()) - Float.floatToIntBits(o2.getDistance());
         }

      });

      Satellite satObject = motionParams.get(0);
      if (satObject.getDistance() < ((Math.sqrt(2) / 2) * (maxEarth + maxSatellite)))
      {  // Спутник столкнется с землей
         String errorMessage = String.format(getResources().getString(R.string.earth_collide_error),
                                             (Math.sqrt(2) / 2) * (maxEarth + maxSatellite));
         AlertDialogUtil.showSimpleDialog(this, errorMessage);
         return false;
      }

      for (int i = 1; i < motionParams.size(); i++)
      {
         Satellite curSatle = motionParams.get(i);
         Satellite prevSatle = motionParams.get(i - 1);
         float diffDistance = curSatle.getDistance() - prevSatle.getDistance();
         if (diffDistance < MINIMUM_DISTANCE_BETWEEN)
         {  // Расстояние между спутниками меньше минимально допустимого
            String errorMessage = String.format(getResources().getString(R.string.satellite_collide_error),
                                                curSatle.getDistance(),
                                                prevSatle.getDistance(),
                                                MINIMUM_DISTANCE_BETWEEN);
            AlertDialogUtil.showSimpleDialog(this, errorMessage);
            return false;
         }
         /*if ((curSatle.getDistance() - prevSatle.getDistance()) < (Math.sqrt(2) * maxSatellite))
          {  // -- Спутник столкнется со спутником
          String errorMessage = String.format(getResources().getString(R.string.satellite_collide_error),
          curSatle.getDistance(),
          prevSatle.getDistance(),
          Math.sqrt(2) * maxSatellite);
          AlertDialogUtil.showSimpleDialog(this, errorMessage);
          return false;
          }*/
      }

      return true;
   }

   /**
    * Сохранение настроек для параметров спутников
    *
    * @param fileName Имя файла
    */
   private void saveSettings(String fileName)
   {
      try
      {
         ObjectOutputStream out = null;
         try
         {
            FileOutputStream foStream = openFileOutput(fileName, Context.MODE_PRIVATE);
            out = new ObjectOutputStream(foStream);
            out.writeObject(motionParams);
         }
         finally
         {
            if (out != null)
            {
               out.close();
            }
         }
      }
      catch (IOException ioEx)
      {
         Log.e(getClass().getName(), ioEx.getLocalizedMessage(), ioEx);
      }
   }

   /**
    * Генератор уникальных целых положительных идентификаторов для виджетов EditText.
    */
   private static class UniqueIdGenerator
   {
      private static int currentId = -1;

      private UniqueIdGenerator()
      {
      }

      private static int nextUniqueId()
      {
         currentId += 2;
         return currentId;
      }

      private static int prevUniqueId()
      {
         currentId -= 2;
         return currentId;
      }
   }

}
