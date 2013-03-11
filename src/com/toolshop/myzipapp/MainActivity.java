package com.toolshop.myzipapp;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);		

		String rootsdcard = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download";
		File filelist = new File(rootsdcard);
		File[] fileslist=filelist.listFiles(filefilter);
	    		
		String strZipfile=rootsdcard+"/myzip4.zip";
		File strFile = new File(rootsdcard, "myzip4.zip");	

		try
		{
		if (!strFile.exists())
		{		
				strFile.createNewFile();
		} 
		else
		{			
		//	boolean movestatus = strFile.renameTo(new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Zipfiles", "myzip4.zip"));
		//	if (movestatus)
		//	{
		//		Toast.makeText(this, "zipfile successfully moved to " + Environment.getExternalStorageDirectory().getAbsolutePath()+"/Zipfiles" , Toast.LENGTH_LONG).show();
		//	}
			strFile.delete();
			strFile.createNewFile();
		}
		}catch (IOException e) {
			// TODO Auto-generated catch block
			Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
		}
		final ClsZip zip = new ClsZip(fileslist, strZipfile,strFile, this);
		
		Button btnZip = (Button) findViewById(R.id.btnZip);
		btnZip.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				zip.ZipFiles();
			}
		});
		
		Button btnUnZip = (Button) findViewById(R.id.btnUnzip);
		btnUnZip.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				zip.UnZipFiles(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Zipfiles/", "myzip4.zip");
			}
		});
		
		
		
	
	}
	
	FileFilter filefilter = new FileFilter() {
		
		@Override
		public boolean accept(File file) {
			// TODO Auto-generated method stub
			if(file.getName().endsWith(".zip"))
			{
			return false;
			}
			return true;
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
