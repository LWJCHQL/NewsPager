package com.example.asdf.newspaper.Adapter;

import android.content.Context;
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
 * Created by asdf on 2018/1/5.
 */

public class MyAdapter extends BaseAdapter {
    List<New> list;
    Context context;
    public MyAdapter(List<New> newList, Context con){
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View v=View.inflate(context, R.layout.listview,null);
        TextView title=(TextView)v.findViewById(R.id.title);
        TextView content=(TextView)v.findViewById(R.id.content);
        ImageView imageView=(ImageView)v.findViewById(R.id.imageview);
//        LinearLayout linearLayout=(LinearLayout)v.findViewById(R.id.come_from);
        TextView comename=(TextView)v.findViewById(R.id.come_name);
        comename.setText(list.get(i).getName());
//        final String s=comename.getText().toString();
        title.setText(list.get(i).getTitle());
        content.setText(list.get(i).getContent());
        Glide.with(context).load(list.get(i).getImage()).into(imageView);
//        linearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(context, UserActivity.class);
//                intent.putExtra("name",s);
//                intent.putExtra("title",list.get(i).getTitle());
//                intent.putExtra("content",list.get(i).getContent());
//                intent.putExtra("image",list.get(i).getImage());
//
//                context.startActivity(intent);
//            }
//        });
        return v;
    }
}
