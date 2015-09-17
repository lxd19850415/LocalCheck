package com.sandy.localcheck;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

import com.demo.localcheck.R;
import com.sandy.localcheck.map.ViewMap;
import com.sandy.localcheck.path.ControllerPath;
import com.sandy.localcheck.path.ViewPath;
import com.sandy.localcheck.patrol.ViewPatrol;
import com.sandy.localcheck.pole.ControllerPole;
import com.sandy.localcheck.pole.ViewPole;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.format.DateFormat;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {

	public static Context context;

	private static FragmentManager fMgr;
	public final static OkHttpClient client = new OkHttpClient();
	public static final MediaType MEDIA_TYPE_TEXT
	  = MediaType.parse("text/html; charset=utf-8");
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		context = this;
		Utils.context = this;
		
		//获取FragmentManager实例
		fMgr = getSupportFragmentManager();
		
		initFragment();
		dealBottomButtonsClickEvent();
		
		//////

		
		
		///////////////////
		  SharedPreferences share = getSharedPreferences(Configure.StoreDataName, MODE_PRIVATE);   
		  Boolean isFirstEntry = share.getBoolean(Configure.StoreKeyFirstEntry, true);
		  
		  if(isFirstEntry == true){
			  
				ControllerPath.createPathTable(context);
				ControllerPole.createPoleTable(context);

				ControllerPole.addPoleToDB(context, "2015-9-8 22:33:54", "111", 34.25881 , 108.817822);
				ControllerPole.addPoleToDB(context, "2015-9-8 22:33:54", "222", 34.35881 , 108.847822);
				ControllerPole.addPoleToDB(context, "2015-9-8 22:33:54", "333", 34.45881 , 108.867822);
				ControllerPole.addPoleToDB(context, "2015-9-8 22:33:54", "444", 34.55881 , 108.897822);
				
				String pathList = "34.15881,108.417822;"
						+"33.35881,108.747822;"
						+"34.85881,108.897822;"
						+"31.55881,108.297822;";
				
				ControllerPath.addPathToDB(context, "2015-9-8 22:33:54", "2015-9-8 22:33:54", "1111", pathList);
				
		  }
		
//	    SharedPreferences share = getSharedPreferences(Configure.StoreDataName, MODE_PRIVATE);   
	    SharedPreferences.Editor edit = share.edit(); //编辑文件  
	    edit.putBoolean(Configure.StoreKeyFirstEntry, false);  
	    edit.commit();  //保存数据信息  
	    
	    ////////////////
		
    }


    /**
	 * 初始化首个Fragment
	 */
	private void initFragment() {
		FragmentTransaction ft = fMgr.beginTransaction();
		ViewMap weiXinFragment = new ViewMap();
		ft.add(R.id.fragmentRoot, weiXinFragment, "weiXinFragment");
		ft.addToBackStack("weiXinFragment");
		ft.commit();		
	}
	/**
	 * 处理底部点击事件
	 */
	private void dealBottomButtonsClickEvent() { 
		findViewById(R.id.rbWeiXin).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(fMgr.findFragmentByTag("weiXinFragment")!=null && fMgr.findFragmentByTag("weiXinFragment").isVisible()) {
					return;
				}
				popAllFragmentsExceptTheBottomOne();

			}
		});
		findViewById(R.id.rbAddress).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				popAllFragmentsExceptTheBottomOne();
				FragmentTransaction ft = fMgr.beginTransaction();
				ft.hide(fMgr.findFragmentByTag("weiXinFragment"));
				ViewPath sf = new ViewPath();
				ft.add(R.id.fragmentRoot, sf, "AddressFragment");
				ft.addToBackStack("AddressFragment");
				ft.commit();
				
			}
		});
		findViewById(R.id.rbFind).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				popAllFragmentsExceptTheBottomOne();
				FragmentTransaction ft = fMgr.beginTransaction();
				ft.hide(fMgr.findFragmentByTag("weiXinFragment"));
				ViewPole sf = new ViewPole();
				ft.add(R.id.fragmentRoot, sf, "AddressFragment");
				ft.addToBackStack("FindFragment");
				ft.commit();
			}
		});
		findViewById(R.id.rbMe).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				popAllFragmentsExceptTheBottomOne();
				FragmentTransaction ft = fMgr.beginTransaction();
				ft.hide(fMgr.findFragmentByTag("weiXinFragment"));
				ViewPatrol sf = new ViewPatrol();
				ft.add(R.id.fragmentRoot, sf, "MeFragment");
				ft.addToBackStack("MeFragment");
				ft.commit();
			}
		});
	}
	
	/**
	 * 从back stack弹出所有的fragment，保留首页的那个
	 */
	public static void popAllFragmentsExceptTheBottomOne() {
		for (int i = 0, count = fMgr.getBackStackEntryCount() - 1; i < count; i++) {
			fMgr.popBackStack();
		}
	}
	//点击返回按钮
	@Override
	public void onBackPressed() {
		if(fMgr.findFragmentByTag("weiXinFragment")!=null && fMgr.findFragmentByTag("weiXinFragment").isVisible()) {
			MainActivity.this.finish();
		} else {
			super.onBackPressed();
		}
	}
    
	public static void showMap()
	{
		if(fMgr.findFragmentByTag("weiXinFragment")!=null && fMgr.findFragmentByTag("weiXinFragment").isVisible()) {
			return;
		}
		popAllFragmentsExceptTheBottomOne();
	}
    
	public void startCamera()
	{
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);  
        startActivityForResult(intent, 1);  
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if (resultCode == Activity.RESULT_OK) {  
            String sdStatus = Environment.getExternalStorageState();  
            if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用  
                Log.i("TestFile",  
                        "SD card is not avaiable/writeable right now.");  
                return;  
            }  
            new DateFormat();  
            String name = DateFormat.format("yyyyMMdd_hhmmss",Calendar.getInstance(Locale.CHINA)) + ".jpg";   
            Toast.makeText(this, name, Toast.LENGTH_LONG).show();  
            Bundle bundle = data.getExtras();  
            Bitmap bitmap = (Bitmap) bundle.get("data");// 获取相机返回的数据，并转换为Bitmap图片格式  
          
            FileOutputStream b = null;      
            String dirPath = Environment.getExternalStorageDirectory().getPath() + "/LocalCheck/";
            File file = new File(dirPath);  
            file.mkdirs();// 创建文件夹  
            String fileName = dirPath +name;  
  
            try {  
                b = new FileOutputStream(fileName);  
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件  
            } catch (FileNotFoundException e) {  
                e.printStackTrace();  
            } finally {  
                try {  
                    b.flush();  
                    b.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
            try  
            {  
                ViewPatrol.picture.setImageBitmap(bitmap);// 将图片显示在ImageView里  
                ViewPatrol.picture.setVisibility(View.VISIBLE);
                ViewPatrol.btnPhotograph.setVisibility(View.GONE);
                ViewPatrol.btnUpLoad.setVisibility(View.VISIBLE);
                ViewPatrol.hintString.setVisibility(View.GONE);
            }catch(Exception e)  
            {  
                Log.e("error", e.getMessage());  
            }  
              
        }  
		
	}
}
