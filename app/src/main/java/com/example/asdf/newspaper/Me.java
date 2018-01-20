package com.example.asdf.newspaper;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.asdf.newspaper.Activity.MyInformation;

/**
 * Created by Mr.Han on 2018/1/7.
 */

public class Me extends Fragment {
    private LinearLayout linearLayout;
    private TextView textView;
    private String s;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_me,null);
        SharedPreferences sp=getActivity().getSharedPreferences("data",getActivity().MODE_PRIVATE);
        s=sp.getString("nickname",null);
        textView=(TextView)view.findViewById(R.id.me_nickname);
        textView.setText(s);
        linearLayout=(LinearLayout)view.findViewById(R.id.chuangyebang);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), MyInformation.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
