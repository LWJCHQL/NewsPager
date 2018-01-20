package com.example.asdf.newspaper.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.asdf.newspaper.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by asdf on 2018/1/5.
 */

public class LoginActivity extends Activity {
    private EditText number, password;
    private ImageView imageView;
    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        imageView = (ImageView) findViewById(R.id.xx);
        button = (Button) findViewById(R.id.llogin);
        number = (EditText) findViewById(R.id.tele_number);
        password = (EditText) findViewById(R.id.password);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String numb = number.getText().toString();
                final String pass = password.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String path = "http://interview.jbangit.com/user/login";
                        try {
                            URL url = new URL(path);
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            conn.setRequestMethod("POST");
                            conn.setConnectTimeout(5000);
                            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                            String data = "username=" + numb + "&password=" + md5(pass);
                            Log.i("哈哈",md5(pass));
                            conn.setRequestProperty("Content-Length", data.length() + "");
                            conn.getOutputStream().write(data.getBytes());
                            int code = conn.getResponseCode();
                            if (code == 200) {
                                String cookie=conn.getHeaderField("Set-Cookie");
                                String sessionId=cookie.substring(0, cookie.indexOf(";"));
                                Log.i("senssionId",sessionId);
                                InputStream in = conn.getInputStream();
                                String result = readStream(in);
                                Log.i("msgmsg",result);
                                JSONObject jsonObject=new JSONObject(result);
                                String jsonObjectString=jsonObject.getString("code");
                                Log.i("msgmsg",jsonObjectString);
                                if(jsonObjectString.equals("0")){
                                    String json=jsonObject.getString("data");
                                    JSONObject jsonObject1=new JSONObject(json);
                                    SharedPreferences sp=getSharedPreferences("data",MODE_PRIVATE);
                                    SharedPreferences.Editor editor=sp.edit();
                                    editor.putString("cookie",sessionId);
                                    editor.putString("nickname",jsonObject1.getString("nickname"));
                                    editor.apply();
                                    Log.i("mmm",jsonObject1.getString("nickname"));
                                    Intent intent=new Intent();
                                    intent.putExtra("aa","fail");
                                    intent.putExtra("open",true);
                                    setResult(1,intent);
                                    finish();
                                }
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

    public static String md5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            String result = "";
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
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
