package com.sandy.localcheck;

import java.util.ArrayList;

import com.demo.localcheck.R;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PolePresetListItemAdapter  extends BaseAdapter{

	private Context mContext;
	private ArrayList<PolePresetItemEntity> items;

	public PolePresetListItemAdapter(Context ctx, ArrayList<PolePresetItemEntity> items) {
		this.mContext = ctx;
		this.items = items;
	}

	@Override
	public int getCount() {
		return items == null ? 0 : items.size();
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

//		if (convertView == null) {
			convertView = View.inflate(mContext, R.layout.pole_preset_list_item, null);
			
			TextView tvIndex = (TextView) convertView
					.findViewById(R.id.itemIndex);

			TextView tvPoleName = (TextView) convertView
					.findViewById(R.id.itemTitle);
			
			TextView tvLat = (TextView) convertView
					.findViewById(R.id.itemSubTitle1);
			

			TextView tvLon = (TextView) convertView
					.findViewById(R.id.itemSubTitle2);
			
//			convertView.setTag(holder);
//		} else {
//			holder = (ViewHolder) convertView.getTag();
//		}
		PolePresetItemEntity itemEntity = items.get(position);
		
		tvIndex.setText(itemEntity.getIndex() + "");
		tvPoleName.setText(itemEntity.getName());
		tvLat.setText(itemEntity.getLat());
		tvLon.setText(itemEntity.getLon());
		
		
		if (position == selectItem) {  
            convertView.setBackgroundColor(Color.GREEN);  
        }   
        else {  
            convertView.setBackgroundColor(Color.TRANSPARENT);  
        }  
		
		return convertView;
	}
	
	public  void setSelectIdx(int selectItem) {  
        this.selectItem = selectItem;  
   }  
   private int  selectItem=-1; 


	/**
	 * listview组件复用，防止�?�卡顿�??
	 * 
	 * @author Administrator
	 * 
	 */

    
    
	public static class PolePresetItemEntity {

		private int index;
		private String poleName;
		private String lat;
		private String lon;

		public PolePresetItemEntity(int index, String poleName, String lat, String lon) {
			super();
			this.index = index;
			this.poleName = poleName;
			this.lat = lat;
			this.lon = lon;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}

		public String getName() {
			return poleName;
		}

		public void setName(String poleName) {
			this.poleName = poleName;
		}

		public String getLat() {
			return lat;
		}

		public void setLat(String lat) {
			this.lat = lat;
		}

		public String getLon() {
			return lon;
		}

		public void setLon(String lon) {
			this.lon = lon;
		}

		@Override
		public String toString() {
			return this.index + ":" + this.poleName;
		}

	}
}
