package com.example.asdf.newspaper.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.asdf.newspaper.Adapter.UserAdapter;
import com.example.asdf.newspaper.JSON.New;
import com.example.asdf.newspaper.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr.Han on 2018/1/8.
 */

public class UserActivity extends Activity {
    private ListView listView;
    private FrameLayout frameLayout;
    private TextView textView;
    private List<New> list;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_information);
        frameLayout=(FrameLayout)findViewById(R.id.come_back2);
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.putExtra("bb","success");
                setResult(2,intent);
                finish();
            }
        });
        textView=(TextView)findViewById(R.id.user_text);

        list=new ArrayList<>();
        for(int i=0;i<4;i++){
            New n=(New)getIntent().getSerializableExtra("NEW");
            textView.setText(n.getName());
            n.setTitle(n.getTitle());
            n.setContent(n.getContent());
            n.setImage(n.getImage());
            list.add(n);
        }
        UserAdapter userAdapter=new UserAdapter(list,this);
        listView=(ListView) findViewById(R.id.lv_user);
        listView.setAdapter(userAdapter);

    }
}
