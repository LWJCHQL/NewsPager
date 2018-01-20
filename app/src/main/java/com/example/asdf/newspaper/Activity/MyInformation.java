package com.example.asdf.newspaper.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.asdf.newspaper.R;

/**
 * Created by Mr.Han on 2018/1/7.
 */

public class MyInformation extends Activity {
    private SharedPreferences sp;
    private String s;
    private TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.me_pan);
        sp=getSharedPreferences("data",MODE_PRIVATE);
        s=sp.getString("nickname",null);
        textView=(TextView)findViewById(R.id.in_nickname);
        textView.setText(s);
        findViewById(R.id.come_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
               finish();
            }
        });
        findViewById(R.id.mynickname).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MyInformation.this,MyNickName.class);
                intent.putExtra("nickname",s);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        s=sp.getString("nickname",null);
        textView.setText(s);
        super.onRestart();
    }
}
