package com.lanyoumobility.mobility_webview.glide;


import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.module.AppGlideModule;
import com.lanyoumobility.mobility_webview.utils.L;

@GlideModule
public class GlideCache extends AppGlideModule {
    private final String TAG = "GlideCache";
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        int diskCacheSizeBytes = 1024 * 1024 * 100; // 100 MB
        //手机app路径
        appRootPath = context.getCacheDir().getPath();
        L.i(TAG,"appRootPath::::::::::::"+appRootPath);
        String diskCacheFolder = getStorageDirectory()+"/GlideDisk";
        L.i(TAG,"diskCacheFolder::::::::::::"+diskCacheFolder);
        builder.setDiskCache( new DiskLruCacheFactory(diskCacheFolder , diskCacheSizeBytes ) );
        super.applyOptions(context, builder);
    }

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        super.registerComponents(context, glide, registry);
    }

    //外部路径
    private String sdRootPath = Environment.getExternalStorageDirectory().getPath();
    private String appRootPath = null;

    private String getStorageDirectory(){
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ?
                sdRootPath : appRootPath;
    }

}