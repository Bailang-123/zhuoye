package com.example.bailang20191029;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private ArrayList<Fragment> f_list ;
    private List<String>s_list = new ArrayList<>();
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void initView() {
        tabLayout = findViewById(R.id.tablayout01);
        viewPager = findViewById(R.id.view_page01);
        s_list.add("首页");
        s_list.add("婆媳");
        s_list.add("房产");
        s_list.add("中介");
        s_list.add("北京");
    }

    @Override
    protected void initData() {
        f_list = new ArrayList<>();
        BlankFragment01 blankFragment01 = new BlankFragment01();
        f_list.add(blankFragment01);
        BlankFragment02 blankFragment02 = new BlankFragment02();
        f_list.add(blankFragment02);
        BlankFragment02 blankFragment03 = new BlankFragment02();
        f_list.add(blankFragment03);
        BlankFragment02 blankFragment04 = new BlankFragment02();
        f_list.add(blankFragment04);
        BlankFragment02 blankFragment05 = new BlankFragment02();
        f_list.add(blankFragment05);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return f_list.get(i);
            }

            @Override
            public int getCount() {
                return f_list.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return s_list.get(position);
            }
        });
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected int layoutoId() {
        return R.layout.activity_main;
    }
}
