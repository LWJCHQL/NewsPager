package com.example.asdf.newspaper;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.asdf.newspaper.Activity.UserActivity;
import com.example.asdf.newspaper.Adapter.MyAdapter;
import com.example.asdf.newspaper.JSON.New;
import com.example.asdf.newspaper.JSON.News;
import com.example.asdf.newspaper.JSON.Request;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asdf on 2018/1/5.
 */

public class Article extends Fragment {
    private static final int SUCCESS =1 ;
    private String string11 []=new String[]{"创业邦","头条问答","SandT","互联网黑天鹅"};
    private String path="http://interview.jbangit.com/news";
    private int number=1;
    private ListView lv;
    public List<New> list=new ArrayList<>();
    private MyAdapter myAdapter;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case SUCCESS:
                    New n=(New)msg.obj;
                    Log.i("HHH11",n.getTitle());
                    list.add(n);
                    number++;
                    if(number==5){
                        myAdapter=new MyAdapter(list,getActivity());
                        lv.setAdapter(myAdapter);
                        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                New ne=list.get(i);
                                Intent intent=new Intent(getActivity(),UserActivity.class);
                                intent.putExtra("NEW",ne);
                                startActivityForResult(intent,2);
                            }
                        });
                    }
                    break;
            }
        }

    };
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_article,null);
        lv=(ListView)view.findViewById(R.id.lv);
        RequstHttp(path);
        return view;
    }
    public void RequstHttp(final String s){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url=new URL(s);
                    HttpURLConnection conn=(HttpURLConnection)url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(5000);
                    int code=conn.getResponseCode();
                    if(code==200){
                        InputStream in=conn.getInputStream();
                        String data=StreamTool.decodeStream(in);
                        try {
                            JSONObject jsonObject=new JSONObject(data);
                            String string=jsonObject.toString();
                            Request request=new Gson().fromJson(string,Request.class);
                            int i=0;
                            for(News news:request.newsList){
                                New n=new New();
                                n.setTitle(news.title);
                                n.setContent(news.desc);
                                n.setImage(news.image);
                                n.setName(string11[i]);
                                Message message=Message.obtain();
                                message.obj=n;
                                message.what=SUCCESS;
                                handler.sendMessage(message);
                                Log.i("HHH",n.getTitle());
                                i++;
                                if(i==4)break;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
