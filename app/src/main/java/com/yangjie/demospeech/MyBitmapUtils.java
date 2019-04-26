package com.yangjie.demospeech;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

public class MyBitmapUtils {

    private NetCachesUtils netCachesUtils;
    private LocalCacheUtils localCacheUtils;
    private MemoryCacheUtils memoryCacheUtils;

    MyBitmapUtils(){
        memoryCacheUtils = new MemoryCacheUtils();
        localCacheUtils = new LocalCacheUtils();
        netCachesUtils = new NetCachesUtils(localCacheUtils,memoryCacheUtils);
    }

    public void display(ImageView imageView,String url){

        Bitmap bitmap = memoryCacheUtils.getMemoryCache(url);
        if (bitmap !=null){
            imageView.setImageBitmap(bitmap);
            Log.d("LogDebug" ,  "緩存讀取成功");
            return;
        }

        bitmap = localCacheUtils.getBitmapFromLocal(url);
        if (bitmap !=null){
            imageView.setImageBitmap(bitmap);
            Log.d("LogDebug" ,  "本地讀取成功");
            return;
        }

        netCachesUtils.getBitmapFromNet(imageView,url);
    }
}
