package com.sandy.localcheck.patrol;

import com.demo.localcheck.R;
import com.sandy.localcheck.GaoDeMapView;
import com.sandy.localcheck.MainActivity;
import com.sandy.localcheck.map.ViewMap;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewPatrol extends Fragment{

	public static Context context;
	public EditText poleName;

	public static GaoDeMapView mapView;

	public static ImageView picture;
	public static TextView hintString;
	public static Button btnUpLoad;
	public static Button btnPhotograph;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.patrol_fragment, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
//		((TextView)getView().findViewById(R.id.textView1)).setText("中国电力西安分公司智能巡杆系统");

		View view = getView();
		context = MainActivity.context;
		mapView = ViewMap.mapView;

		picture  = (ImageView) view.findViewById(R.id.imageView1);
		

		hintString  = (TextView) view.findViewById(R.id.textView2);

		btnUpLoad  = (Button) view.findViewById(R.id.button2);
		btnPhotograph  = (Button) view.findViewById(R.id.button1);
		picture.setVisibility(View.GONE);
		btnUpLoad.setVisibility(View.GONE);
		
		btnPhotograph.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				((MainActivity)context).startCamera();
			}
		});
		
		btnUpLoad.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(mapView.lat  != 0 && mapView.lon != 0){

					Toast.makeText(context,  "正在上传中。。。，经度：" + String.format("%.2f",mapView.lat)  + "，纬度：" + String.format("%.2f",mapView.lon) , Toast.LENGTH_LONG).show();
				}
				else{
					Toast.makeText(context, "定位失败，请在地图界面定位成功后，再使用此功能！", Toast.LENGTH_LONG).show();
					
				}
			}
		});
	}
}
