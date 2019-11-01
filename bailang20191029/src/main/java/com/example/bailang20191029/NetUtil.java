package com.example.bailang20191029;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * date:2019/10/29
 * author:白烺(24184)
 * function:网络请求
 */
public class NetUtil {
    private NetUtil(){

    }
    public static NetUtil getInstance(){
        return netutil01.netUtil;
    }
    private static class netutil01{
        static NetUtil netUtil = new NetUtil();
    }
    public static  String  io2String(InputStream inputStream){
        String json = "";
        ByteArrayOutputStream byteArrayOutputStream = null;
        byte[] bytes = new byte[1024];
         byteArrayOutputStream= new ByteArrayOutputStream();
        int len = -1;
        try{
            while((len=inputStream.read(bytes))!=-1){
                byteArrayOutputStream.write(bytes,0,len);
            }
            byte[] bytes1 = byteArrayOutputStream.toByteArray();
            json= new String(bytes);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return json;
    }
    public void doGet(final String json , final MyUtil myUtil){
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                InputStream inputStream = null;
                HttpURLConnection httpURLConnection=null;
                String jsons = "";
                try {
                    URL url = new URL(json);
                     httpURLConnection= (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setReadTimeout(5000);
                    httpURLConnection.setReadTimeout(5000);
                    httpURLConnection.connect();
                    if(httpURLConnection.getResponseCode()==200){
                       inputStream =httpURLConnection.getInputStream();
                       jsons = io2String(inputStream);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }

                }
                return jsons;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                myUtil.setSrcess(s);
            }
        }.execute();
    }

    public static Bitmap io2Bitmap(InputStream inputStream){
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        return bitmap;
    }
    public void doGetPhoto(final String json , final MyUtil myUtil){
        new AsyncTask<String, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(String... strings) {
                InputStream inputStream = null;
                HttpURLConnection httpURLConnection=null;
                Bitmap jsons = null;
                try {
                    URL url = new URL(json);
                    httpURLConnection= (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setReadTimeout(5000);
                    httpURLConnection.setReadTimeout(5000);
                    httpURLConnection.connect();
                    if(httpURLConnection.getResponseCode()==200){
                        inputStream =httpURLConnection.getInputStream();
                        jsons = io2Bitmap(inputStream);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }

                }
                return jsons;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
                myUtil.setSrcessPhoto(bitmap);
            }
        }.execute();
    }
    interface MyUtil{
        void setSrcess(String json);
        void setSrcessPhoto(Bitmap bitmap);
    }
}
