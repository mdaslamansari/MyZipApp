package com.toolshop.myzipapp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class ClsZip extends Activity {
	
	private static final int BUFFER = 2048;
	private File[] _files;
	private String _ZipfileName;
	private Context _context;
	
	public ClsZip(File[] fileslist, String ZipfileName, Context context )
	{
		_files=fileslist;
		_ZipfileName=ZipfileName;
		_context=context;
	}
	
	public void ZipFiles(){
		new AsyncTask<Void, Integer, Integer>(){

			ProgressDialog pdia;
			
			@Override
			protected void onPreExecute() {
				// TODO Auto-generated method stub
				super.onPreExecute();
				pdia= new ProgressDialog(_context);
				pdia.setMessage("File Zipping in progress...");
				pdia.setMax(0);
				pdia.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
				pdia.setCancelable(false);
				pdia.show();					
			}

			@Override
			protected Integer doInBackground(Void... arg0) {
				// TODO Auto-generated method stub			
				try 
				{
					pdia.setMax(_files.length);
					BufferedInputStream bis_origin = null;
					FileOutputStream dest = new FileOutputStream(_ZipfileName);
					ZipOutputStream zipoutput = new ZipOutputStream(new BufferedOutputStream(dest));
					byte data[] = new byte[BUFFER];
					for(int i=0; i < _files.length; i++) 
					{ 					
						FileInputStream fi = new FileInputStream(_files[i]); 
				        bis_origin = new BufferedInputStream(fi, BUFFER); 
				        ZipEntry entry = new ZipEntry(_files[i].toString().substring(_files[i].toString().lastIndexOf("/") + 1)); 
				        zipoutput.putNextEntry(entry); 
				        publishProgress(i);	
				        int count; 
				        while ((count = bis_origin.read(data, 0, BUFFER)) != -1) 
				        { 
				        	zipoutput.write(data, 0, count); 
				        } 
				        bis_origin.close(); 
				      } 				 
					zipoutput.closeEntry(); 
					zipoutput.close();					
				}catch (Exception e)
				{
					e.printStackTrace();
				}
				return 1;
				
			}
			
			@Override
			protected void onPostExecute(Integer result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);					
				pdia.dismiss();	
				Toast.makeText(_context, "File Zipping done successfully.", Toast.LENGTH_LONG).show();			
			}

			@Override
			protected void onProgressUpdate(Integer... values) {
				// TODO Auto-generated method stub
				super.onProgressUpdate(values);
				pdia.setProgress(values[0]);
			}			
		}.execute();
	}
}

