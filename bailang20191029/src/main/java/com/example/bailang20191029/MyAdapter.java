package com.example.bailang20191029;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * date:2019/10/29
 * author:白烺(24184)
 * function:
 */
public class MyAdapter extends BaseAdapter {
    List<JsonBean.ListdataBean> list;
    Context context;

    public MyAdapter(List<JsonBean.ListdataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    int type0 = 0;
    int type1 = 1;

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        int type = list.get(position).getType();
        if(type==1){
            return type0;
        }else if(type==2){
            return type1;
        }
        return type0;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        ViewHodler viewHodler = null;
        if(convertView==null){
            int itemViewType = getItemViewType(position);
            if(itemViewType==type0){
               viewHodler  = new ViewHodler();
               convertView = View.inflate(context,R.layout.activity_name01,null);
               viewHodler.imageView = convertView.findViewById(R.id.image02);
               viewHodler.textView01 = convertView.findViewById(R.id.text01);
               viewHodler.textView02 = convertView.findViewById(R.id.text02);
               convertView.setTag(viewHodler);
            }else if(itemViewType==type1){
                viewHodler  = new ViewHodler();
                convertView = View.inflate(context,R.layout.activity_name01,null);
                viewHodler.imageView = convertView.findViewById(R.id.image03);
                viewHodler.textView01 = convertView.findViewById(R.id.text03);
                viewHodler.textView02 = convertView.findViewById(R.id.text04);
                convertView.setTag(viewHodler);
            }
        }else{
            viewHodler = (ViewHodler)convertView.getTag();
        }
        viewHodler.textView01.setText(list.get(position).getName());
        viewHodler.textView02.setText(list.get(position).getInfo());
        String avatar = list.get(position).getAvatar();
        final ViewHodler viewHodler1 = viewHodler;
        NetUtil.getInstance().doGetPhoto(avatar, new NetUtil.MyUtil() {
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
        private ImageView imageView;
        private TextView textView01,textView02;
    }
}
