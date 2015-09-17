package com.sandy.localcheck.map;

import com.demo.localcheck.R;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ViewInputPathDialog extends Dialog {
    private EditText editName;
    private EditText editLat;
    private EditText editLon;
    private Button positiveButton, negativeButton;
    private TextView title;
 
    public ViewInputPathDialog(Context context) {
//        super(context,R.style.CustomDialog);
        super(context);
        setCustomDialog();
    }
 
    private void setCustomDialog() {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_input_path_name, null);
        title = (TextView) mView.findViewById(R.id.title);
        editName = (EditText) mView.findViewById(R.id.number);
        editLat = (EditText) mView.findViewById(R.id.editText1);
        editLon = (EditText) mView.findViewById(R.id.editText2);
        positiveButton = (Button) mView.findViewById(R.id.positiveButton);
        negativeButton = (Button) mView.findViewById(R.id.negativeButton);
        super.setContentView(mView);
    }

    public TextView getEditTitle(){
        return title;
    }

    public EditText getEditName(){
        return editName;
    }

    public EditText getEditLat(){
        return editLat;
    }

    public EditText getEditLon(){
        return editLon;
    }
     
     @Override
    public void setContentView(int layoutResID) {
    }
 
    @Override
    public void setContentView(View view, LayoutParams params) {
    }
 
    @Override
    public void setContentView(View view) {
    }
 
    /**
     * 确定键监听器
     * @param listener
     */ 
    public void setOnPositiveListener(View.OnClickListener listener){ 
        positiveButton.setOnClickListener(listener); 
    } 
    /**
     * 取消键监听器
     * @param listener
     */ 
    public void setOnNegativeListener(View.OnClickListener listener){ 
        negativeButton.setOnClickListener(listener); 
    }
}