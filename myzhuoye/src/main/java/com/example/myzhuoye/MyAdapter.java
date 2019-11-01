package com.example.myzhuoye;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * date:2019/11/1
 * author:白烺(24184)
 * function:
 */
public class MyAdapter extends BaseAdapter {

    List<JsonBean.ListdataBean> listdataBeans ;
    Context context;

    public MyAdapter(List<JsonBean.ListdataBean> listdataBeans, Context context) {
        this.listdataBeans = listdataBeans;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listdataBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return listdataBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler viewHodler = null;
        if(convertView == null){
            viewHodler = new ViewHodler();
            convertView = View.inflate(context,R.layout.activity_name,null);
            viewHodler.imageView = convertView.findViewById(R.id.image01);
            viewHodler.textView01 = convertView.findViewById(R.id.text01);
            viewHodler.textView02 = convertView.findViewById(R.id.text02);
            convertView.setTag(viewHodler);
        }else{
            viewHodler = (ViewHodler)convertView.getTag();
        }
        viewHodler.textView01.setText(listdataBeans.get(position).getName());
        viewHodler.textView02.setText(listdataBeans.get(position).getPublishedAt());
        final ViewHodler viewHodler1 = viewHodler;
        NetUtil.getInstance().doGetPhoto(listdataBeans.get(position).getAvatar(), new NetUtil.MyUtil() {
            @Override
            public void setSrcess(String json) {

            }

            @Override
            public void setSrcessPhoto(Bitmap bitmap) {
                viewHodler1.imageView.setImageBitmap(bitmap);
            }
        });
        return convertView;
    }
    class ViewHodler{
        ImageView imageView;
        TextView textView01;
        TextView textView02;
    }
}
