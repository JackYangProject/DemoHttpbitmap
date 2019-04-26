package com.yangjie.demospeech;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

class NetCachesUtils {

    private LocalCacheUtils localCacheUtils;
    private MemoryCacheUtils memoryCacheUtils;

    /**
     * 網路緩存工具類
     *
     *
     */

    public NetCachesUtils(LocalCacheUtils local, MemoryCacheUtils memory){
        this.localCacheUtils = local;
        this.memoryCacheUtils = memory;
    }

     void getBitmapFromNet(ImageView view,String url){
        BitmapTask task = new BitmapTask();
        task.execute(view,url);
    }

    @SuppressLint("StaticFieldLeak")
    class BitmapTask extends AsyncTask<Object, Void, Bitmap>{
        private ImageView view;
        private String url;

        @Override
        protected Bitmap doInBackground(Object... objects) {
            view  = (ImageView)objects[0];
            url = (String)objects[1];
            view.setTag(url);
            return dowloadBitmap(url);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap !=null){
                String ivurl = (String) view.getTag();
                if (url.equals(ivurl)){
                    view.setImageBitmap(bitmap);
                    Log.d("LogDebug" ,  "網路圖片讀取成功");

                    localCacheUtils.putBitmapToLocal(url,bitmap);
                    memoryCacheUtils.setmMemoryCache(url,bitmap);
                }
            }else {
                Log.d("LogDebug" ,  "網路圖片讀取失敗");
            }
            super.onPostExecute(bitmap);
        }
    }

    /**
     * 下载图片
     *
     * @param url
     * @return
     */

    private Bitmap dowloadBitmap(String url){
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);
            conn.setRequestMethod("GET");
            conn.connect();

            int resopn = conn.getResponseCode();
            if (resopn == 200){
                InputStream inputStream = conn.getInputStream();
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.RGB_565;
                return BitmapFactory.decodeStream(inputStream,null,options);
            }

        } catch (IOException e) {
            e.printStackTrace();
            Log.d("LogDebug" ,  "網路下載圖片失敗=> " + e.getMessage());
        }finally {
            assert conn != null;
            conn.disconnect();
        }
        return null;
    }

}
