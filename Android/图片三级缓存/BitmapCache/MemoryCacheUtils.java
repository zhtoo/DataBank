package com.hs.doubaobao.utils.BitmapCache;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * 内存缓存：通过HashMap来进行数据的存储
 */
public class MemoryCacheUtils {

    static {

        //因为从 Android 2.3 (API Level 9)开始，垃圾回收器会更倾向于回收持有软引用或弱引用的对象，这让软引用和弱引用变得不再可靠。
        long maxMemory = Runtime.getRuntime().maxMemory();//获取Dalvik 虚拟机最大的内存大小：16

        lruCache = new LruCache<String,Bitmap>((int) (maxMemory/8)){//指定内存缓存集合的大小
            //获取图片的大小
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes()*value.getHeight();
            }
        };
    }
    private static LruCache<String, Bitmap> lruCache;

    //写缓存
    public static void saveCache(Bitmap bitmap,String url){
        lruCache.put(url,bitmap);
    }
    //读缓存
    public static Bitmap readCache(String url){
        return lruCache.get(url);
    }
}
