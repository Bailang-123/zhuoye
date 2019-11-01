package com.example.bailang20191029;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * date:2019/10/29
 * author:白烺(24184)
 * function:
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutoId());
        initView();
        initData();
        
    }

    protected abstract void initView();

    protected abstract void initData();

    protected abstract int layoutoId();
}
