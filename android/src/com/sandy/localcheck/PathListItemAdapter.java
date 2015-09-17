package com.sandy.localcheck;

import java.util.ArrayList;

import com.demo.localcheck.R;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;



public class PathListItemAdapter extends BaseAdapter {

	private Context mContext;
	private ArrayList<ItemEntity> items;

	public PathListItemAdapter(Context ctx, ArrayList<ItemEntity> items) {
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
			convertView = View.inflate(mContext, R.layout.list_item, null);
			TextView tvIndex = (TextView) convertView
					.findViewById(R.id.itemIndex);

			TextView tvName = (TextView) convertView
					.findViewById(R.id.itemTitle);
			
			TextView tvDate = (TextView) convertView
					.findViewById(R.id.itemSubTitle1);
			
//			convertView.setTag(holder);
//		} else {
//			holder = (ViewHolder) convertView.getTag();
//		}
		ItemEntity itemEntity = items.get(position);
		
		tvIndex.setText(itemEntity.getIndex() + "");
		tvName.setText(itemEntity.getName());
		tvDate.setText(itemEntity.getDate());

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

    
    
	public static class ItemEntity {

		private int index;
		private String name;
		private String date;

		public ItemEntity(int index, String name,String date) {
			super();
			this.index = index;
			this.name = name;
			this.date = date;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		@Override
		public String toString() {
			return this.index + ":" + this.date;
		}

	}
}
