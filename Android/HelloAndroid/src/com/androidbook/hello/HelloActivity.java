package com.androidbook.hello;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class HelloActivity extends Activity
{
   @Override
   public void onCreate(Bundle savedInstanceState)
   {
      super.onCreate(savedInstanceState);
      TextView tv = new TextView(this);
      tv.setText("Hello, Android world!");
      setContentView(tv);
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu)
   {      
      getMenuInflater().inflate(R.menu.activity_hello, menu);
      return true;
   }
}
