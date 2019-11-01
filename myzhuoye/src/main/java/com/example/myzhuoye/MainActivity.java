package com.example.myzhuoye;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    GridView gridView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = findViewById(R.id.grid_view01);
        NetUtil.getInstance().doGet("http://blog.zhaoliang5156.cn/api/news/lawyer.json", new NetUtil.MyUtil() {
            @Override
            public void setSrcess(String json) {
                Gson gson = new Gson();
                JsonBean jsonBean = gson.fromJson(json, JsonBean.class);
                List<JsonBean.ListdataBean> listdata = jsonBean.getListdata();
                gridView.setAdapter(new MyAdapter(listdata,MainActivity.this));
            }

            @Override
            public void setSrcessPhoto(Bitmap bitmap) {

            }
        });
    }
}
