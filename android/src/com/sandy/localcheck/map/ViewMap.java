package com.sandy.localcheck.map;

import com.demo.localcheck.R;
import com.sandy.localcheck.GaoDeMapView;
import com.sandy.localcheck.MainActivity;
import com.sandy.localcheck.path.ControllerPath;
import com.sandy.localcheck.pole.ControllerPole;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ScrollView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class ViewMap extends Fragment {

	public static GaoDeMapView mapView;
	public static Context context;
	public static ScrollView scrollView;

	public static EditText poleName;
	public static ToggleButton recordPathSwitchBtn;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.map_fragment, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		context = MainActivity.context;
		View view = getView();

		mapView = (GaoDeMapView) view.findViewById(R.id.map);
		mapView.onCreate(savedInstanceState);

		recordPathSwitchBtn = (ToggleButton) view
				.findViewById(R.id.toggleButton1);

		recordPathSwitchBtn
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton arg0,
							boolean arg1) {
						// TODO Auto-generated method stub
						if (arg1 == true) {

							showInputPathDialog();
						} else {
							ControllerPath.stopRecord();
						}
					}
				});

		Button btnClean = (Button) view.findViewById(R.id.btnClean);

		btnClean.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				mapView.cleanPath();
				mapView.cleanPole();
			}
		});

		Button recordPoleBtn = (Button) view.findViewById(R.id.button1);
		recordPoleBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				showInputPoleDialog1();
			}
		});

		poleName = (EditText) view.findViewById(R.id.editText1);

	}

	private void showInputPoleDialog1() {

		final ViewInputPathDialog dialog = new ViewInputPathDialog(context);
		final EditText editName = (EditText) dialog.getEditName();// 方法在CustomDialog中实现
		final EditText editLat = (EditText) dialog.getEditLat();// 方法在CustomDialog中实现
		final EditText editLon = (EditText) dialog.getEditLon();// 方法在CustomDialog中实现

		editLat.setText(String.valueOf(ControllerPole.lat).toString());
		editLon.setText(String.valueOf(ControllerPole.lon).toString());

		dialog.setOnPositiveListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				String name = editName.getText().toString();
				String latStr = editLat.getText().toString();
				String lonStr = editLon.getText().toString();

				if (name.length() == 0) {
					Toast.makeText(context, "请输入塔杆名称", Toast.LENGTH_SHORT)
							.show();
					return;
				}

				if (latStr.length() == 0) {
					Toast.makeText(context, "请输入经度", Toast.LENGTH_SHORT).show();
					return;
				}

				if (lonStr.length() == 0) {
					Toast.makeText(context, "请输入纬度", Toast.LENGTH_SHORT).show();
					return;
				}

				ControllerPole.recordAndUpLoad(name, Double.valueOf(latStr)
						.doubleValue(), Double.valueOf(lonStr).doubleValue());
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

	protected void showInputPathDialog() {
		AlertDialog.Builder builder = new Builder(context);
		builder.setMessage("请输入路径名称：");
		builder.setTitle("提示");
		final EditText pathName = new EditText(context);

		builder.setView(pathName);

		builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

				if (pathName.getText().toString().length() > 0) {
					ControllerPath.startRecord(pathName.getText().toString());
					dialog.dismiss();
				} else {
					Toast.makeText(context, "请输入路径名称", Toast.LENGTH_SHORT)
							.show();
					return;
				}

			}
		});

		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

				if (recordPathSwitchBtn.isChecked()) {
					recordPathSwitchBtn.setChecked(false);
				}
				dialog.dismiss();
			}
		});

		builder.create().show();

	}

	protected void showInputPoleDialog() {
		AlertDialog.Builder builder = new Builder(context);
		builder.setMessage("请输入塔杆名称：");
		builder.setTitle("提示");
		final EditText poleName = new EditText(context);

		builder.setView(poleName);

		builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

				if (poleName.getText().toString().length() > 0) {
					ControllerPole.recordAndUpLoad(poleName.getText()
							.toString());
					dialog.dismiss();
				} else {
					Toast.makeText(context, "请输入塔杆名称", Toast.LENGTH_SHORT)
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
