package com.yangjie.demospeech;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

class LocalCacheUtils {
    private static final String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/demo";


    /**
     * 从本地读取图片
     *
     * @param url
     * @return
     */
    Bitmap getBitmapFromLocal(String url){
     try {
         String fileName = MD5Encoder.encode(url);
         File file = new File(path,fileName);

         if (file.exists()){
             BitmapFactory.Options options = new BitmapFactory.Options();
             options.inPreferredConfig = Bitmap.Config.RGB_565;

             return BitmapFactory.decodeStream(new FileInputStream(file),null,options);
         }else {
             return null;
         }
     } catch (Exception e) {
         e.printStackTrace();
     }
     return null;
    }

    /**
     * 向本地存图片
     *
     * @param url
     * @param bitmap
     */
    void putBitmapToLocal(String url,Bitmap bitmap){
        try {
            String filename = MD5Encoder.encode(url);
            File file = new File(path,filename);
            File parent = file.getParentFile();
            if (!parent.exists()){
                parent.mkdir();
            }

            bitmap.compress(Bitmap.CompressFormat.PNG,100,new FileOutputStream(file));
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("LogDebug" ,  "本地圖片讀取失敗=> " + e.getMessage());
        }
    }

}
