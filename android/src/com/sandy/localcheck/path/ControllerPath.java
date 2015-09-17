package com.sandy.localcheck.path;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.sandy.localcheck.Configure;
import com.sandy.localcheck.MainActivity;
import com.sandy.localcheck.map.ViewMap;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class ControllerPath {

	public static String currentRecord;
	public static boolean isRecord;

	public static OkHttpClient client = MainActivity.client;
	public static MediaType MEDIA_TYPE_TEXT = MainActivity.MEDIA_TYPE_TEXT;

	public static String recordTime;
	public static String pathList;
	public static String name;

	public static void inputLatLon(double lat, double lon) {
		if (isRecord == false) {
			return;
		}

		// java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
		// lat = Double.valueOf(String.format("%.2f",pLat)) ;
		// lon = Double.valueOf(String.format("%.2f",pLon)) ;

		String str = lat + "," + lon + ";";
		pathList = pathList + str;

	}

	public static void startRecord(String name) {
		if (isRecord == true) {
			return;
		}
		isRecord = true;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss",
				Locale.getDefault());
		recordTime = sdf.format(new java.util.Date());

		pathList = "";
		ControllerPath.name = name;
	}

	public static void stopRecord() {
		if (isRecord == false) {
			return;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss",
				Locale.getDefault());
		String date = sdf.format(new java.util.Date());

		currentRecord = currentRecord + "@" + date + "#";

		addPathToDB(MainActivity.context, recordTime, date, name, pathList);
		
//		Thread thread = new Thread(new UpLoadPath());
//		thread.start();
	}

	static class UpLoadPath implements Runnable {

		@Override
		public void run() {

			String postBody = currentRecord;

			Request request = new Request.Builder()
					.url(Configure.URL_UPLOAD_PATH)
					.post(RequestBody.create(MEDIA_TYPE_TEXT, postBody))
					.build();

			Response response;
			try {
				response = client.newCall(request).execute();

				if (!response.isSuccessful())
					throw new IOException("Unexpected code " + response);

				System.out.println(response.body().string());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
	
	public static void createPathTable(Context context)
	{
		//打开或创建test.db数据库  
        SQLiteDatabase db = context.openOrCreateDatabase("data.db", Context.MODE_PRIVATE, null);  
        db.execSQL("DROP TABLE IF EXISTS path");  
        //创建person表  
        db.execSQL("CREATE TABLE path (_id INTEGER PRIMARY KEY AUTOINCREMENT, stattime VARCHAR,endtime VARCHAR,name VARCHAR,pathlist VARCHAR)");  
       
        db.close(); 
	}
	
	public static void addPathToDB(Context context,String starttime,String endtime,String name,String pathlist)
	{
		//打开或创建test.db数据库  
        SQLiteDatabase db = context.openOrCreateDatabase("data.db", Context.MODE_PRIVATE, null);  
        ModelPath pathMode = new ModelPath(0,starttime,endtime,name,pathlist);

        //插入数据  
        db.execSQL("INSERT INTO path VALUES (NULL, ?, ?, ?, ?)"
        		, new Object[]{pathMode.startTime,pathMode.endTime, pathMode.name,pathMode.pathList});  

        db.close(); 
        
	}
	
	public static ArrayList<ModelPath> getPathListFromDB(Context context)
	{
		ArrayList<ModelPath> pathList = new ArrayList<ModelPath>();

        SQLiteDatabase db = context.openOrCreateDatabase("data.db", Context.MODE_PRIVATE, null);  
        
		Cursor c = db.rawQuery("SELECT * FROM path ", null);  
        while (c.moveToNext()) {  
            int _id = c.getInt(c.getColumnIndex("_id"));  
            String stattime = c.getString(c.getColumnIndex("stattime")); 
            String endtime = c.getString(c.getColumnIndex("endtime"));  
            String name = c.getString(c.getColumnIndex("name"));  
            String pathlist = c.getString(c.getColumnIndex("pathlist"));  
            Log.i("db", "_id=>" + _id + ", name=>" + name + ", stattime=>" + stattime);  
            
            pathList.add(new ModelPath(_id,stattime,endtime,name,pathlist));
        }  
        c.close();  

        db.close(); 
        
		return pathList;
	}
	

	public static void updatePathNameFromDB(Context context,int id,String name)
	{
        SQLiteDatabase db = context.openOrCreateDatabase("data.db", Context.MODE_PRIVATE, null);  
		String sqlString = "UPDATE path set name = '" + name + "' WHERE _id =  " + id;
		//db.execSQL("UPDATE path set name = '?' WHERE _id = ? ", new Object[]{name,id});  
		db.execSQL(sqlString);  

        db.close(); 
        
	}


	public static void deletePathListFromDB(Context context,int id)
	{
        SQLiteDatabase db = context.openOrCreateDatabase("data.db", Context.MODE_PRIVATE, null);  

        db.execSQL("DELETE FROM path  WHERE _id = ? ", new Object[]{id});  

        db.close(); 
        
	}
	



	public static void showPath(int selectIdx) {
		ArrayList<ModelPath> pathList = ControllerPath
				.getPathListFromDB(MainActivity.context);
		ArrayList<String> posList = new ArrayList<String>();
		for (int i = 0; i < pathList.size(); i++) {
			String[] latlonArray = pathList.get(i).getPathList().split(";");
			if (latlonArray.length > 1) {
				for (int j = 0; j < latlonArray.length; j++) {
					posList.add(latlonArray[j]);
				}
				ViewMap.mapView.drawPathLine(posList);
			}
		}
		

		int idx = selectIdx;
		
		if(idx < 0 )
		{
			idx = 0;
		}
		
		
		String pathListString = pathList.get(idx).getPathList();
		String firstLatLon = pathListString.split(";")[0];
		Double lat = Double.valueOf(firstLatLon.split(",")[0]).doubleValue();
		Double lon = Double.valueOf(firstLatLon.split(",")[1]).doubleValue();
				
		ViewMap.mapView.setMapLatLonInCenter(lat,lon);
		
	}
	

}
