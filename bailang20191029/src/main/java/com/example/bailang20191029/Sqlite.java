package com.example.bailang20191029;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * date:2019/10/29
 * author:白烺(24184)
 * function:
 */
public class Sqlite extends SQLiteOpenHelper {
    public Sqlite(Context context) {
        super(context,"yuekao.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table listdata(name text,info text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
