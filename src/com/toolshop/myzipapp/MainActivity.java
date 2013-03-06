package com.toolshop.myzipapp;

import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);		

		String rootsdcard = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download";
		File filelist = new File(rootsdcard);
		File[] fileslist=filelist.listFiles(filefilter);
	    		
		String strZipfile=rootsdcard+"/myzip.zip";
		File strFile = new File(rootsdcard, "myzip.zip");	

		try
		{
		if (!strFile.exists())
		{		
				strFile.createNewFile();
		} 
		else
		{
			strFile.delete();
			strFile.createNewFile();
		}
		}catch (IOException e) {
			// TODO Auto-generated catch block
			Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
		}
		ClsZip zip = new ClsZip(fileslist, strZipfile, this);
		zip.ZipFiles();		
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
