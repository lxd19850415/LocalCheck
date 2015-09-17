package com.sandy.localcheck.pole;

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

public class ControllerPole {

	public static String currentRecord;

	public static double lat;
	public static double lon;

	public static OkHttpClient client = MainActivity.client;
	public static MediaType MEDIA_TYPE_TEXT = MainActivity.MEDIA_TYPE_TEXT;

	public static ArrayList<ModelPole> poleList = null;

	public static void inputLatLon(double _lat, double _lon) {
		lat = _lat;
		lon = _lon;

		// String str = lat + "," + lon + ";";
		// currentRecord = currentRecord + str;

	}

	public static boolean recordAndUpLoad(String name, Double lat, Double lon) {
		if (lat == 0 || lon == 0) {
			return false;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss",
				Locale.getDefault());
		String date = sdf.format(new java.util.Date());

		addPoleToDB(MainActivity.context, date, name, lat, lon);

		// Thread thread = new Thread(new UpLoadPole());
		// thread.start();
		return true;

	}

	public static boolean recordAndUpLoad(String name) {
		if (lat == 0 || lon == 0) {
			return false;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss",
				Locale.getDefault());
		String date = sdf.format(new java.util.Date());

		addPoleToDB(MainActivity.context, date, name, lat, lon);

		// Thread thread = new Thread(new UpLoadPole());
		// thread.start();
		return true;

	}

	static class UpLoadPole implements Runnable {

		@Override
		public void run() {
			String postBody = currentRecord;

			Request request = new Request.Builder()
					.url(Configure.URL_UPLOAD_POLE)
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

	public static void createPoleTable(Context context) {
		SQLiteDatabase db = context.openOrCreateDatabase("data.db",
				Context.MODE_PRIVATE, null);

		db.execSQL("DROP TABLE IF EXISTS pole");
		db.execSQL("CREATE TABLE pole (_id INTEGER PRIMARY KEY AUTOINCREMENT, time VARCHAR,name VARCHAR,lat VARCHAR,lon VARCHAR)");

		db.close();
	}

	public static void addPoleToDB(Context context, String time, String name,
			double lat, double lon) {
		SQLiteDatabase db = context.openOrCreateDatabase("data.db",
				Context.MODE_PRIVATE, null);

		ModelPole poleMode = new ModelPole(0, time, name, lat, lon);

		db.execSQL("INSERT INTO pole VALUES (NULL, ?, ?, ?, ?)", new Object[] {
				poleMode.time, poleMode.name, String.valueOf(poleMode.lat),
				String.valueOf(poleMode.lon) });

		db.close();
	}

	public static ArrayList<ModelPole> getPoleListFromDB(Context context) {
		ArrayList<ModelPole> poleList = new ArrayList<ModelPole>();
		;

		SQLiteDatabase db = context.openOrCreateDatabase("data.db",
				Context.MODE_PRIVATE, null);

		Cursor c = db.rawQuery("SELECT * FROM pole ", null);
		while (c.moveToNext()) {
			int _id = c.getInt(c.getColumnIndex("_id"));
			String time = c.getString(c.getColumnIndex("time"));
			String name = c.getString(c.getColumnIndex("name"));
			double lat = c.getDouble(c.getColumnIndex("lat"));
			double lon = c.getDouble(c.getColumnIndex("lon"));
			Log.i("db", "_id=>" + _id + ", name=>" + name + ", time=>" + time);

			poleList.add(new ModelPole(_id, time, name, lat, lon));
		}
		c.close();

		db.close();
		return poleList;
	}

	public static void deletePoleFromDB(Context context, int id) {
		SQLiteDatabase db = context.openOrCreateDatabase("data.db",
				Context.MODE_PRIVATE, null);

		db.execSQL("DELETE FROM pole  WHERE _id = ? ", new Object[] { id });

		db.close();
	}

	public static void updatePoleNameFromDB(Context context, int id,
			String name, Double lat, Double lon) {
		SQLiteDatabase db = context.openOrCreateDatabase("data.db",
				Context.MODE_PRIVATE, null);

		String sqlString = "UPDATE pole set name = '" + name + "' ,lat = '"
				+ lat + "' ,lon = '" + lon + "' WHERE _id =  " + id;
		db.execSQL(sqlString);

		db.close();

	}

	public static void showPole(int selectIdx) {
		ArrayList<ModelPole> poleList = ControllerPole
				.getPoleListFromDB(MainActivity.context);
		for (int i = 0; i < poleList.size(); i++) {
			ViewMap.mapView.drawMaker(poleList.get(i).lat, poleList.get(i).lon,
					poleList.get(i).name, "描述");
		}
		
		int idx = selectIdx;
		
		if(idx < 0 )
		{
			idx = 0;
		}
		Double lat = poleList.get(idx).getLat();
		Double lon = poleList.get(idx).getLon();
				
		ViewMap.mapView.setMapLatLonInCenter(lat,lon);
		
	}

}
