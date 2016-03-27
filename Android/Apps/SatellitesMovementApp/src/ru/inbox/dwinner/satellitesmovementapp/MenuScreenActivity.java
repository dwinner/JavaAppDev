package ru.inbox.dwinner.satellitesmovementapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Активность для экрана главного меню.
 *
 * @author dwinner@inbox.ru
 * @version 1.0.0 06-10-2012
 */
public class MenuScreenActivity extends Activity
{
   @Override
   protected void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_menu_screen);
      setupMenuListView();
   }

   /**
    * Установка ListView в экране главного меню.
    */
   private void setupMenuListView()
   {
      ListView menuListView = (ListView) findViewById(R.id.MenuListViewId);
      String[] menuItems =
      {
         getResources().getString(R.string.menu_item_start),
         getResources().getString(R.string.menu_item_settings),
         getResources().getString(R.string.menu_item_about)
      };
      ArrayAdapter<String> listAdapter =
        new ArrayAdapter<String>(this, R.layout.main_menu_item, menuItems);
      menuListView.setAdapter(listAdapter);
      menuListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
         public void onItemClick(AdapterView<?> adapterView, View itemClicked, int position, long id)
         {
            String clickedText = ((TextView) itemClicked).getText().toString();
            Intent intent = null;
            if (clickedText.equalsIgnoreCase(getResources().getString(R.string.menu_item_start)))
            {
               intent = new Intent(MenuScreenActivity.this, StartScreenActivity.class);
            }
            else if (clickedText.equalsIgnoreCase(getResources().getString(R.string.menu_item_settings)))
            {
               intent = new Intent(MenuScreenActivity.this, SettingsScreenActivity.class);
            }
            else if (clickedText.equalsIgnoreCase(getResources().getString(R.string.menu_item_about)))
            {
               intent = new Intent(MenuScreenActivity.this, AboutScreenActivity.class);
            }
            if (intent != null)
            {
               startActivity(intent);
            }
         }

      });
   }

}
