package ru.inbox.dwinner.droidtest;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class DroidActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_droid);
        try {
			String descX = readAppDescription();
			Log.v(DroidActivity.class.getName(), descX);
		} catch (IOException ioEx) {			
			Log.e(DroidActivity.class.getName(), ioEx.getMessage(), ioEx);
		}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    private String readAppDescription() throws IOException
    {
    	InputStream appDescFile = null;
    	try
    	{
    		appDescFile = getResources().openRawResource(R.raw.app_description_doc);
    		byte[] readBytes = new byte[1024];
    		appDescFile.read(readBytes);
    		StringBuilder strBuilder = new StringBuilder();
    		for (int i = 0; i < readBytes.length; i++)
    		{
				if (readBytes[i] != 0)
					strBuilder.append((char) readBytes[i]);				
			}
    		strBuilder.trimToSize();
    		return strBuilder.toString();
    	}
    	finally
    	{
    		if (appDescFile != null)
    			appDescFile.close();
    	}	    
	}
}
