package com.sandy.localcheck.path;

import java.util.ArrayList;

import com.demo.localcheck.R;
import com.sandy.localcheck.GaoDeMapView;
import com.sandy.localcheck.MainActivity;
import com.sandy.localcheck.PathListItemAdapter;
import com.sandy.localcheck.PathListItemAdapter.ItemEntity;
import com.sandy.localcheck.map.ViewMap;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class ViewPath extends Fragment {

	public static GaoDeMapView mapView;
	public static Context context;
	private ListView lv_pathList;


	private ArrayList<ItemEntity> itemEntities;

	public LinearLayout toolBar;
	public int selectIdx = -1;
	PathListItemAdapter mAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.path_fragment, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		View view = getView();

		context = MainActivity.context;
		mapView = ViewMap.mapView;

		lv_pathList = (ListView) view.findViewById(R.id.uiPathList);
		setPathList();
		lv_pathList
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

		toolBar = (LinearLayout) view.findViewById(R.id.toolBar);

		toolBar.setVisibility(View.GONE);
		Button btnViewInMap = (Button) view.findViewById(R.id.button1);

		Button btnModify = (Button) view.findViewById(R.id.button2);
		Button btnDelete = (Button) view.findViewById(R.id.button3);

		btnViewInMap.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				ViewMap.mapView.cleanPath();
				ViewMap.mapView.cleanPole();

				ControllerPath.showPath(selectIdx);
				MainActivity.showMap();

			}
		});

		btnModify.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				if (selectIdx == -1) {
					return;
				}

				showModifyPathDialog();
			}
		});

		btnDelete.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Toast.makeText(context, "删除成功！", Toast.LENGTH_SHORT).show();

				ArrayList<ModelPath> pathList = ControllerPath
						.getPathListFromDB(context);
				ControllerPath.deletePathListFromDB(context,
						pathList.get(selectIdx).getId());

				setPathList();
			}
		});

	}

	void setPathList() {

		ArrayList<ModelPath> pathList = ControllerPath
				.getPathListFromDB(context);

		if (itemEntities != null && itemEntities.size() > 0) {
			itemEntities.clear();
		} else {

			itemEntities = new ArrayList<ItemEntity>();
		}

		for (int i = 0; i < pathList.size(); i++) {
			ItemEntity entity1 = new ItemEntity(i + 1, pathList.get(i)
					.getName(), pathList.get(i).getStartTime());
			itemEntities.add(entity1);

		}

		mAdapter = new PathListItemAdapter(context,
				itemEntities);
		lv_pathList.setAdapter(mAdapter);

		MainActivity.showMap();

	}

	protected void showModifyPathDialog() {
		AlertDialog.Builder builder = new Builder(context);
		builder.setMessage("路径名称：");
		builder.setTitle("提示");
		final EditText pathName = new EditText(context);

		builder.setView(pathName);

		final ArrayList<ModelPath> pathList = ControllerPath
				.getPathListFromDB(context);
		String name = pathList.get(selectIdx).getName();

		pathName.setText(name);

		builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

				if (pathName.getText().toString().length() > 0) {

					ControllerPath.updatePathNameFromDB(context,
							pathList.get(selectIdx).getId(), pathName.getText()
									.toString());
					setPathList();
					dialog.dismiss();
				} else {
					Toast.makeText(context, "路径名称不能为空！", Toast.LENGTH_SHORT)
							.show();
					return;
				}

			}
		});

		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

				dialog.dismiss();
			}
		});

		builder.create().show();

	}

}
