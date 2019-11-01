package com.example.bailang20191029;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * date:2019/10/29
 * author:白烺(24184)
 * function:
 */
public class MyDao {
    Sqlite sqlite;
    public MyDao() {
    }
    public void inserte(List<JsonBean.ListdataBean>listdataBeans){
        SQLiteDatabase writableDatabase = sqlite.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",listdataBeans.get(1).getName());
        contentValues.put("info",listdataBeans.get(1).getInfo());
        writableDatabase.insert("listdata",null,contentValues);
        writableDatabase.close();
    }
    public List<JsonBean.ListdataBean> select(){
        SQLiteDatabase writableDatabase = sqlite.getWritableDatabase();
        Cursor listdata = writableDatabase.query("listdata", null, null, null, null, null, null);
        while (listdata.moveToNext()){
            String name = listdata.getString(listdata.getColumnIndexOrThrow("name"));
            String info = listdata.getString(listdata.getColumnIndexOrThrow("info"));

        }
         JsonBean jsonBean = new JsonBean();
        List<JsonBean.ListdataBean> listdata1 = jsonBean.getListdata();
        return listdata1;
    }
}
