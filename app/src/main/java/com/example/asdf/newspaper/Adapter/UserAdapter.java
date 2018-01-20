package com.example.asdf.newspaper.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asdf.newspaper.JSON.New;
import com.example.asdf.newspaper.R;

import java.util.List;

/**
 * Created by Mr.Han on 2018/1/8.
 */

public class UserAdapter extends BaseAdapter {
    List<New> list;
    Context context;
    public UserAdapter(List<New> newList, Context con){
        list=newList;
        context=con;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v=View.inflate(context, R.layout.listview_user,null);
        TextView title=(TextView)v.findViewById(R.id.title1);
        TextView content=(TextView)v.findViewById(R.id.content1);
        ImageView imageView=(ImageView)v.findViewById(R.id.imageview1);
        title.setText(list.get(i).getTitle());
        content.setText(list.get(i).getContent());
        Glide.with(context).load(list.get(i).getImage()).into(imageView);
        return v;
    }
}
