package com.example.asdf.newspaper.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.asdf.newspaper.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Mr.Han on 2018/1/7.
 */

public class MyNickName extends Activity {
    private SharedPreferences sp;
    private EditText editText;
    private String nickName=" ";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_nickname);
        sp=getSharedPreferences("data",MODE_PRIVATE);
        findViewById(R.id.come_back1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                finish();
            }
        });
        nickName=getIntent().getStringExtra("nickname");
        editText=(EditText)findViewById(R.id.et);
        editText.setText(nickName);
        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nickName=editText.getText().toString().trim();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String path = "http://interview.jbangit.com/user/nickname";
                        try {
                            URL url = new URL(path);
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            conn.setRequestMethod("POST");
                            conn.setConnectTimeout(5000);
                            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                            String cookie=sp.getString("cookie",null);
                            conn.setRequestProperty("Cookie",cookie);
                            String data ="nickname="+ URLEncoder.encode(nickName,"UTF-8");
                            conn.setRequestProperty("Content-Length", data.length() + "");
                            conn.setDoOutput(true);
                            conn.getOutputStream().write(data.getBytes());
                            int code = conn.getResponseCode();
                            if (code == 200) {
                                InputStream in = conn.getInputStream();
                                String result = readStream(in);
                                Log.i("msgmsg",result);
                                JSONObject jsonObject=new JSONObject(result);
                                String jsonObjectString=jsonObject.getString("code");
                                String jsonObjectString1=jsonObject.getString("message");
                                Log.i("msgmsg",jsonObjectString+"  "+jsonObjectString1);
                                SharedPreferences.Editor editor=sp.edit();
                                editor.putString("nickname",nickName);
                                Log.i("msgmsg",nickName);
                                editor.apply();
                                finish();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

    }
    public String readStream(InputStream is) {
        //先把流中的内容读到内存中
        ByteArrayOutputStream baos = new ByteArrayOutputStream();//内存的输出流
        int len = 0;
        byte[] buffer = new byte[1024];
        try {
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            is.close();
            baos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return baos.toString();
    }
}
