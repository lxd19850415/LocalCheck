package com.sandy.localcheck.pole;

import java.util.ArrayList;

import com.demo.localcheck.R;
import com.sandy.localcheck.GaoDeMapView;
import com.sandy.localcheck.MainActivity;
import com.sandy.localcheck.PolePresetListItemAdapter;
import com.sandy.localcheck.PolePresetListItemAdapter.PolePresetItemEntity;
import com.sandy.localcheck.map.ViewInputPathDialog;
import com.sandy.localcheck.map.ViewMap;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewPole extends Fragment {

	public static Context context;
	public EditText poleName;

	public static GaoDeMapView mapView;
	ArrayList<PolePresetItemEntity> itemEntities = null;

	public LinearLayout toolBar;
	public int selectIdx = -1;
	public ListView lvPoleList;
	PolePresetListItemAdapter mAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.pole_fragment, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		View view = getView();
		context = MainActivity.context;
		mapView = ViewMap.mapView;

		lvPoleList = (ListView) view.findViewById(R.id.listPolePreset);

		toolBar = (LinearLayout) view.findViewById(R.id.toolBar);
		toolBar.setVisibility(View.GONE);
		Button btnViewInMap = (Button) view.findViewById(R.id.button1);

		Button btnModify = (Button) view.findViewById(R.id.button2);
		Button btnDelete = (Button) view.findViewById(R.id.button3);

		setPoleList();

		lvPoleList
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						selectIdx = arg2;
						mAdapter.setSelectIdx(arg2);  
						mAdapter.notifyDataSetInvalidated();  
						toolBar.setVisibility(View.VISIBLE);
					}
				});
//		MainActivity.showMap();

		btnViewInMap.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				ViewMap.mapView.cleanPath();
				ViewMap.mapView.cleanPole();

				ControllerPole.showPole(selectIdx);
				
				MainActivity.showMap();
			}
		});

		btnModify.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (selectIdx == -1) {
					return;
				}

				showModifyPoleDialog();
			}
		});

		btnDelete.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Toast.makeText(context, "删除成功！", Toast.LENGTH_SHORT).show();

				ArrayList<ModelPole> pathList = ControllerPole
						.getPoleListFromDB(context);
				ControllerPole.deletePoleFromDB(context, pathList
						.get(selectIdx).getId());

				setPoleList();

			}
		});

	}

	private void showModifyPoleDialog() {

		final ViewInputPathDialog dialog = new ViewInputPathDialog(context);

		final TextView title = (TextView) dialog.getEditTitle();// 方法在CustomDialog中实现
		final EditText editName = (EditText) dialog.getEditName();// 方法在CustomDialog中实现
		final EditText editLat = (EditText) dialog.getEditLat();// 方法在CustomDialog中实现
		final EditText editLon = (EditText) dialog.getEditLon();// 方法在CustomDialog中实现

		title.setText("修改塔杆");

		final ArrayList<ModelPole> poleDataList = ControllerPole
				.getPoleListFromDB(context);

		editName.setText(poleDataList.get(selectIdx).getName());
		editLat.setText(poleDataList.get(selectIdx).getLat() + "");
		editLon.setText(poleDataList.get(selectIdx).getLon() + "");

		dialog.setOnPositiveListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				String name = editName.getText().toString();
				String latStr = editLat.getText().toString();
				String lonStr = editLon.getText().toString();

				if (name.length() == 0) {
					Toast.makeText(context, "塔杆名称不能为空", Toast.LENGTH_SHORT)
							.show();
					return;
				}

				if (latStr.length() == 0) {
					Toast.makeText(context, "经度不能为空", Toast.LENGTH_SHORT)
							.show();
					return;
				}

				if (lonStr.length() == 0) {
					Toast.makeText(context, "纬度不能为空", Toast.LENGTH_SHORT)
							.show();
					return;
				}

				ControllerPole.updatePoleNameFromDB(context,
						poleDataList.get(selectIdx).getId(), name, Double
								.valueOf(latStr).doubleValue(),
						Double.valueOf(lonStr).doubleValue());

				setPoleList();

				dialog.dismiss();

			}
		});
		dialog.setOnNegativeListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		dialog.show();
	}

	void setPoleList() {
		ArrayList<ModelPole> poleDataList = ControllerPole
				.getPoleListFromDB(context);

		if (itemEntities != null && itemEntities.size() > 0) {
			itemEntities.clear();
		} else {

			itemEntities = new ArrayList<PolePresetItemEntity>();
		}

		for (int i = 0; i < poleDataList.size(); i++) {
			PolePresetItemEntity entity1 = new PolePresetItemEntity(i + 1,
					poleDataList.get(i).getName(), poleDataList.get(i).getLat()
							+ "", poleDataList.get(i).getLon() + "");
			itemEntities.add(entity1);

		}

		mAdapter = new PolePresetListItemAdapter(
				context, itemEntities);
		lvPoleList.setAdapter(mAdapter);
	}

}
