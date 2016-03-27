package ru.inbox.dwinner.satellitesmovementapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import static ru.inbox.dwinner.satellitesmovementapp.utils.RawReaderUtil.inputStreamToString;

/**
 * Активность для экрана About
 *
 * @author dwinner@inbox.ru
 * @version 1.0.0 05-10-2012
 */
public class AboutScreenActivity extends Activity
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
      setContentView(R.layout.activity_about_screen);

      // -- Читаем файл в строку и заполняем TextView
      InputStream inputStream = getResources().openRawResource(R.raw.about);
      try
      {
         TextView helpText = (TextView) findViewById(R.id.HelpTextTextViewId);
         helpText.setText(inputStreamToString(inputStream));
      }
      catch (IOException ioEx)
      {
         Log.e(AboutScreenActivity.class.getName(),
               ioEx.getLocalizedMessage(),
               ioEx);
      }
   }

}
