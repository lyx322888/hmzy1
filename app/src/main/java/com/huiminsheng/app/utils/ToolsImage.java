package com.huiminsheng.app.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.huiminsheng.app.R;
import com.huiminsheng.app.net.ApiUrls;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ToolsImage {
    /**
     * 加载图片
     */
    public static void loader(Context context, String imageUrl, ImageView imageView) {
        if (TextUtils.isEmpty(imageUrl)) {
            return;
        }
        Glide.with(context).load(imageUrl).
                asBitmap().thumbnail(0.5f).
                placeholder(R.mipmap.mrbc).error(R.mipmap.mrbc).
                into(imageView);
    }
    public static void loader(Context context, String imageUrl, ImageView imageView,int resourceId) {
        if (TextUtils.isEmpty(imageUrl)) {
            return;
        }
        Glide.with(context).load(imageUrl).
                asBitmap().thumbnail(0.5f).
                placeholder(resourceId).error(resourceId).
                into(imageView);
    }
    /**
     * 加载图片
     */
    public static void loaderYt(Context context, String imageUrl, ImageView imageView) {
        if (TextUtils.isEmpty(imageUrl)) {
            return;
        }
        Glide.with(context).load(imageUrl).
                asBitmap().thumbnail(0.5f).
                placeholder(R.mipmap.mryt).error(R.mipmap.mryt).
                into(imageView);
    }
    /**
     * 加载图片头像
     */
    public static void loaderTx(Context context, String imageUrl, ImageView imageView) {
        if (TextUtils.isEmpty(imageUrl)) {
            return;
        }
        Glide.with(context).load(imageUrl).
                asBitmap().thumbnail(0.5f).
                placeholder(R.mipmap.mrtx).error(R.mipmap.mrtx).
                into(imageView);
    }

    /**
     * 将view以图片形式存到手机
     */
    public static boolean saveView(Context context, View view) {
        return saveView(context, view, "");
    }

    public static boolean saveView(Context context, View view, String name) {
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap b1 = view.getDrawingCache();
        final Bitmap bitmap = Bitmap.createBitmap(b1);
        view.destroyDrawingCache();

        FileOutputStream fos = null;
        try {
            if (TextUtils.isEmpty(name)) {
                Date date = new Date(System.currentTimeMillis());
                SimpleDateFormat dateFormat = new SimpleDateFormat("'hmzy'_yyyyMMdd-HHmm");
                name = dateFormat.format(date);
            }
            String filePath = ApiUrls.KEY.PATH_SAVE + name + ".png";
            File file = new File(filePath);
            if (!file.exists()) {
                try {
                    file.getParentFile().mkdirs();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
            fos = new FileOutputStream(filePath);
            try {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            }catch (Exception e){
                e.printStackTrace();
            }
            fos.flush();

            Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri uri = Uri.fromFile(file);
            intent.setData(uri);
            context.sendBroadcast(intent);

            ToastUtils.showToast(context,"二维码已成功保存到手机,路径:\n" + filePath);
            if (bitmap != null && !bitmap.isRecycled()) {
                bitmap.recycle();
                System.gc();
            }
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            ToastUtils.showToast(context,"二维码保存失败_FileNotFoundException");
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            ToastUtils.showToast(context,"二维码保存失败_IOException");
            return false;
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void savaUrl(final Context context, final String imageUrl){
        Glide.with(context).load(imageUrl).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                // 创建目录
                File appDir = new File(ApiUrls.KEY.PATH_SAVE );
                if (!appDir.exists()) {
                    appDir.mkdirs();
                }

                String fileName =  System.currentTimeMillis() + ".png" ;
                File file = new File(appDir, fileName);
                if (!file.exists()) {
                    //保存图片
                    try {
                        FileOutputStream fos = new FileOutputStream(file);
                        resource.compress(Bitmap.CompressFormat.PNG, 100, fos);
                        fos.flush();
                        fos.close();
                        ToastUtils.showToast(context, "保存成功");
                        ToastUtils.showToast(context,"二维码已成功保存到手机,路径:\n" + file.getPath());
                    } catch (FileNotFoundException e) {
                        if (e.toString().endsWith(("(Permission denied)"))) {
                            ToastUtils.showToast(context, "请前往应用权限管理打开储存权限");
                        }


                    } catch (IOException e) {
                        Log.e("dfdf", "onResourceReady: ");

                    }

                    // 其次把文件插入到系统图库
                    try {
                        MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), fileName, null);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    // 最后通知图库更新
                    context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file.getPath())));
                } else {
                    ToastUtils.showToast(context, "图片已存在");
                }

            }
        });
    }
    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}