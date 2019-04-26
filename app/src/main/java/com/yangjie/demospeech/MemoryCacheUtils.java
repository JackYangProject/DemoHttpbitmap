package com.yangjie.demospeech;

import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

class MemoryCacheUtils {

    private LruCache<String,Bitmap> mMemoryCache;

    MemoryCacheUtils(){
        long maxMemory = Runtime.getRuntime().maxMemory();

        mMemoryCache = new LruCache<String, Bitmap>((int)(maxMemory / 8)){

            @Override
            protected int sizeOf(String key, Bitmap value) {
                Log.d("LogDebug" ,  "緩存圖片讀取大小=> " +value.getRowBytes() * value.getHeight());
                return value.getRowBytes() * value.getHeight();
            }
        };
        Log.d("LogDebug" ,  "緩存圖片 maxMemory " +  maxMemory);
    }


    /**
     * 寫緩存
     */
    void setmMemoryCache(String url,Bitmap bitmap){
        mMemoryCache.put(url,bitmap);
    }

    /**
     * 讀緩存
     */
    Bitmap getMemoryCache(String url){
        return mMemoryCache.get(url);
    }
}
