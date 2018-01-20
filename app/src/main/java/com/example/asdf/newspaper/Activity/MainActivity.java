package com.example.asdf.newspaper.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.asdf.newspaper.Article;
import com.example.asdf.newspaper.Me;
import com.example.asdf.newspaper.R;

public class MainActivity extends AppCompatActivity {
    private boolean open=false;
    private String flag="success";
    private ImageView [] imageViews=new ImageView[2];
    private TextView [] textViews=new TextView[2];
    private PopupWindow popupWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            init();
            arti();
    }
    public void arti(){
        Article ariticle=new Article();
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction ft=fragmentManager.beginTransaction();
        ft.replace(R.id.container,ariticle);
        ft.commit();
    }
    public void meti(){
        Me me=new Me();
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction ft=fragmentManager.beginTransaction();
        ft.replace(R.id.container,me);
        ft.commit();
    }
    @Override
    protected void onRestart() {
        Log.i("aaaa123",flag);
        if(flag.equals("fail")){
            popupWindow.dismiss();
            imageViews[1].setSelected(true);
            textViews[1].setTextColor(Color.parseColor("#2979f6"));
            imageViews[0].setSelected(false);
            textViews[0].setTextColor(Color.parseColor("#a5aab2"));
            meti();
        }else if(flag.equals("success")){
            init();
            arti();
            if(open){
                flag="fail";
            }
        }
        super.onRestart();
    }

    public void init(){
        imageViews[0]=(ImageView) findViewById(R.id.main_iv1);
        imageViews[1]=(ImageView) findViewById(R.id.main_iv2);
        textViews[0]=(TextView)findViewById(R.id.main_tv1);
        textViews[1]=(TextView)findViewById(R.id.main_tv2);
        imageViews[0].setSelected(true);
        textViews[0].setTextColor(Color.parseColor("#2979f6"));
    }
    public void click(View v){
        switch (v.getId()){
            case R.id.main_linear1:
                arti();
                imageViews[0].setSelected(true);
                textViews[0].setTextColor(Color.parseColor("#2979f6"));
                imageViews[1].setSelected(false);
                textViews[1].setTextColor(Color.parseColor("#a5aab2"));
                break;
            case R.id.main_linear2:
                if(flag.equals("fail")){
                    popupWindow.dismiss();
                    imageViews[1].setSelected(true);
                    textViews[1].setTextColor(Color.parseColor("#2979f6"));
                    imageViews[0].setSelected(false);
                    textViews[0].setTextColor(Color.parseColor("#a5aab2"));
                    meti();
                }else{
                    imageViews[1].setSelected(true);
                    textViews[1].setTextColor(Color.parseColor("#2979f6"));
                    imageViews[0].setSelected(false);
                    textViews[0].setTextColor(Color.parseColor("#a5aab2"));
                    showPopupWindow();
                }
                break;
        }
    }
    public void showPopupWindow(){
        View view= LayoutInflater.from(this).inflate(R.layout.popupwindow,null);
        popupWindow=new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        ColorDrawable dw = new ColorDrawable(0000000000);
        popupWindow.setBackgroundDrawable(dw);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(findViewById(R.id.main_layout), Gravity.CENTER,0,0);
        setBackgroundAlpha(0.5f);
        view.findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        view.findViewById(R.id.unlogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivityForResult(intent,1);
            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                // popupWindow隐藏时恢复屏幕正常透明度
                setBackgroundAlpha(1.0f);
                imageViews[0].setSelected(true);
                textViews[0].setTextColor(Color.parseColor("#2979f6"));
                imageViews[1].setSelected(false);
                textViews[1].setTextColor(Color.parseColor("#a5aab2"));
            }
        });
    }
    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = ((Activity)this).getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        ((Activity) this).getWindow().setAttributes(lp);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case 1:
                flag= data.getStringExtra("aa");
                open=true;
                break;
            case 2:
                flag=data.getStringExtra("bb");
                break;
        }
    }
}
