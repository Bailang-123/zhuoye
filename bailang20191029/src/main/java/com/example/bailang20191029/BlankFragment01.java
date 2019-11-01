package com.example.bailang20191029;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.qy.xlistview.XListView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment01 extends BaseFragment {


    private XListView xListView;
    List<JsonBean.ListdataBean> listdataBeans;
    int type = 1;
    boolean aBoolean = false;
    private MyDao myDao;

    @Override
    protected void initData() {
        getData();
    }

    @Override
    protected void initView(final View inflate) {
        xListView = inflate.findViewById(R.id.xlistview01);
        xListView.setPullRefreshEnable(true);
        xListView.setPullLoadEnable(true);
        xListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),Main2Activity.class);
                intent.putExtra("key",listdataBeans.get(position).getUrl());
                startActivity(intent);
            }
        });
        xListView.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                type = 1;
                aBoolean = false;
                getData();
            }

            @Override
            public void onLoadMore() {
                type++;
                aBoolean = true;
                getData();
            }
        });
    }

    private void getData() {
        final MyDao myDao = new MyDao();
        if(NetStart.getInstance().hesNet(getActivity())){
            String url="";
            if(type==1){
                url="http://blog.zhaoliang5156.cn/api/news/lawyer.json";
            }else if(type==2){
                url="http://blog.zhaoliang5156.cn/api/news/lawyer2.json";
            }else if(type==3){
                url="http://blog.zhaoliang5156.cn/api/news/lawyer3.json";
            }
            NetUtil.getInstance().doGet(url, new NetUtil.MyUtil() {
                @Override
                public void setSrcess(String json) {
                    Gson gson = new Gson();
                    JsonBean jsonBean = gson.fromJson(json, JsonBean.class);
                    List<JsonBean.ListdataBean> listdata = jsonBean.getListdata();
                    if(aBoolean){
                        listdataBeans.addAll(listdata);
                        xListView.stopRefresh();
                    }else{
                        listdataBeans.clear();
                        listdataBeans.addAll(listdata);
                        xListView.stopLoadMore();
                    }
                    myDao.inserte(listdataBeans);
                    xListView.setAdapter(new MyAdapter(listdataBeans,getActivity()));

                }
                @Override
                public void setSrcessPhoto(Bitmap bitmap) {

                }
            });
        }else{
            Toast.makeText(getActivity(), "联网失败", Toast.LENGTH_SHORT).show();
            List<JsonBean.ListdataBean> select = myDao.select();
            xListView.setAdapter(new MyAdapter(select,getActivity()));
        }
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_blank_fragment01;
    }
}
