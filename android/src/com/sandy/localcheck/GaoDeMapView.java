package com.sandy.localcheck;

import java.util.ArrayList;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.LocationManagerProxy;
import com.amap.api.location.LocationProviderProxy;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdate;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.amap.api.maps2d.model.Polyline;
import com.amap.api.maps2d.model.PolylineOptions;
import com.demo.localcheck.R;
import com.sandy.localcheck.path.ControllerPath;
import com.sandy.localcheck.pole.ControllerPole;

import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.AttributeSet;

public class GaoDeMapView extends MapView implements LocationSource,
AMapLocationListener
{
	private AMap aMap;
	private OnLocationChangedListener mListener;
	private LocationManagerProxy mAMapLocationManager;
	private static Context mContext;
	public double lat;
	public double lon;
	public static ArrayList<Polyline> allPolyLine = new ArrayList<Polyline>();
	public static ArrayList<Marker> allMarker = new ArrayList<Marker>();
	 
	
	public GaoDeMapView(Context context) {
		super(context);
		
		mContext = context;
		init();
	}
	
	public GaoDeMapView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		init();
	}

	public GaoDeMapView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
		init();
	}

	/**
	 * ��ʼ��AMap����
	 */
	private void init() {
		if (aMap == null) {
			aMap = this.getMap();
			setUpMap();
		}
	}

	/**
	 * ����һЩamap������
	 */
	private void setUpMap() {
		// �Զ���ϵͳ��λС����
		MyLocationStyle myLocationStyle = new MyLocationStyle();
		myLocationStyle.myLocationIcon(BitmapDescriptorFactory
				.fromResource(R.drawable.location_marker));// ����С�����ͼ��
		myLocationStyle.strokeColor(Color.BLACK);// ����Բ�εı߿���ɫ
		myLocationStyle.radiusFillColor(Color.argb(100, 0, 0, 180));// ����Բ�ε������ɫ
		// myLocationStyle.anchor(int,int)//����С�����ê��
		myLocationStyle.strokeWidth(1.0f);// ����Բ�εı߿��ϸ
		aMap.setMyLocationStyle(myLocationStyle);
		aMap.setLocationSource(this);// ���ö�λ����
		aMap.getUiSettings().setMyLocationButtonEnabled(true);// ����Ĭ�϶�λ��ť�Ƿ���ʾ
		aMap.setMyLocationEnabled(true);// ����Ϊtrue��ʾ��ʾ��λ�㲢�ɴ�����λ��false��ʾ���ض�λ�㲢���ɴ�����λ��Ĭ����false
	   // aMap.setMyLocationType()
	}


	/**
	 * �˷����Ѿ�����
	 */
	@Override
	public void onLocationChanged(Location location) {
	}

	@Override
	public void onProviderDisabled(String provider) {
	}

	@Override
	public void onProviderEnabled(String provider) {
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}
	

	/**
	 * ��λ�ɹ���ص�����
	 */
	@Override
	public void onLocationChanged(AMapLocation aLocation) {
		if (mListener != null && aLocation != null) {
			mListener.onLocationChanged(aLocation);// ��ʾϵͳС����

			lat = aLocation.getLatitude();
			lon = aLocation.getLongitude();

			ControllerPath.inputLatLon(lat,lon);
			ControllerPole.inputLatLon(lat,lon);
			
			
		}
	}

	
	/**
	 * ���λ
	 */
	@Override
	public void activate(OnLocationChangedListener listener) {
		mListener = listener;
		if (mAMapLocationManager == null) {
			mAMapLocationManager = LocationManagerProxy.getInstance(mContext);
			/*
			 * mAMapLocManager.setGpsEnable(false);
			 * 1.0.2�汾��������������true��ʾ��϶�λ�а���gps��λ��false��ʾ�����綨λ��Ĭ����true Location
			 * API��λ����GPS�������϶�λ��ʽ
			 * ����һ�������Ƕ�λprovider���ڶ�������ʱ�������2000���룬������������������λ���ף����ĸ������Ƕ�λ������
			 */
			mAMapLocationManager.requestLocationData(
					LocationProviderProxy.AMapNetwork, 2000, 10, this);
		}
	}

	/**
	 * ֹͣ��λ
	 */
	@Override
	public void deactivate() {
		mListener = null;
		if (mAMapLocationManager != null) {
			mAMapLocationManager.removeUpdates(this);
			mAMapLocationManager.destroy();
		}
		mAMapLocationManager = null;
		
		ControllerPath.stopRecord();
		
	}
	
//	public void startRecordPath()
//	{
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss@");    
//		String date=sdf.format(new java.util.Date());
//		Utils.write(Configure.PathFileName,date);
//		isRecording = true;
//		lat=0;
//		lon=0;
//	}
//
//	public void stopRecordPath()
//	{
//		SimpleDateFormat sdf=new SimpleDateFormat("@yyyy-MM-dd hh:mm:ss#");    
//		String date=sdf.format(new java.util.Date());
//		Utils.write(Configure.PathFileName,date);
//		
//		isRecording = false;
//	}

	public void drawPathLine(ArrayList<String> posList)
	{
		PolylineOptions posListOpt = new PolylineOptions();
		
		ArrayList<LatLng> latlonList = new ArrayList<LatLng>();
		for (int i = 0; i < posList.size(); i++) {
			double lat = Double.valueOf(posList.get(i).split(",")[0]);
			double lon = Double.valueOf(posList.get(i).split(",")[1]);
			
			latlonList.add( new LatLng(lat, lon));
			
		}
		
		posListOpt.addAll(latlonList);
		posListOpt.color(Color.RED);

		Polyline polyLine = aMap.addPolyline(posListOpt);
		
		allPolyLine.add(polyLine);
	}

	public void cleanPath()
	{

		for (int i = 0; i < allPolyLine.size(); i++) {
//			allPolyLine.get(i).setVisible(isVisiable);
			allPolyLine.get(i).remove();
		}
		allPolyLine.clear();
		aMap.invalidate();//刷新地图
	}
	
	public void drawMaker(double lat,double lon,String name,String Des)
	{
		Marker marker= aMap.addMarker(new MarkerOptions().anchor(0.5f, 0.5f)
				.position( new LatLng(lat,lon)).title(name)
				.snippet(Des).draggable(true));
		
		allMarker.add(marker);
	}

	public void cleanPole()
	{
		for (int i = 0; i < allMarker.size(); i++) {
//			allMarker.get(i).setVisible(isVisiable);
			
			allMarker.get(i).destroy();
		}
		
		allMarker.clear();
		
		aMap.invalidate();//刷新地图
	}
	
	public void setMapLatLonInCenter(Double lat,Double lon)
	{
		CameraUpdate update = CameraUpdateFactory.newCameraPosition(new CameraPosition(
				new LatLng(lat, lon) , 18, 0, 30));
				
		aMap.moveCamera(update);
	}
}
