package com.huiminsheng.app.unify;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.huiminsheng.app.R;
import com.yanzhenjie.album.AlbumFile;
import com.yanzhenjie.album.AlbumLoader;

public class MediaLoader  implements AlbumLoader {
    @Override
    public void load(ImageView imageView, AlbumFile albumFile) {
        load(imageView, albumFile.getPath());
    }

    @Override
    public void load(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .error(R.mipmap.mrcfx)
                .placeholder(R.mipmap.mrcfx)
                .crossFade()
                .into(imageView);
    }
}
