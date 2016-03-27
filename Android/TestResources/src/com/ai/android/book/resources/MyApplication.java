package com.ai.android.book.resources;

import android.app.Application;
import android.content.res.Configuration;
import android.util.Log;

public class MyApplication extends Application
{
   @Override
   public void onConfigurationChanged(Configuration newConfig)
   {
      super.onConfigurationChanged(newConfig);
      Log.d(TAG, "configuration changed");
   }

   @Override
   public void onCreate()
   {
      super.onCreate();
      Log.d(TAG, "oncreate");
   }

   @Override
   public void onLowMemory()
   {
      super.onLowMemory();
      Log.d(TAG, "onLowMemory");
   }

   @Override
   public void onTerminate()
   {
      super.onTerminate();
      Log.d(TAG, "onTerminate");
   }
   
   public final static String TAG = MyApplication.class.getName();
}
