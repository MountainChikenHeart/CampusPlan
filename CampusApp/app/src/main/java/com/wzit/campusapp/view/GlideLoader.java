package com.wzit.campusapp.view;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.lcw.library.imagepicker.utils.ImageLoader;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 实现自定义图片加载
 */
public class GlideLoader implements ImageLoader {

    private RequestOptions mOptions = new RequestOptions()
            .centerCrop()
            .format(DecodeFormat.PREFER_RGB_565)
            .override(400,400);//设置后大部分解决了OOM
//            .placeholder(R.mipmap.icon_image_default);


    private RequestOptions mPreOptions = new RequestOptions()
            .skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.NONE);//跳过内存缓存


    @Override
    public void loadImage(ImageView imageView, String imagePath) {
        //正常加载
        Glide.with(imageView.getContext()).load(imagePath).apply(mOptions).into(imageView);
    }

    public void loadImage(CircleImageView imageView, String imagePath) {
        //正常加载
        Glide.with(imageView.getContext()).load(imagePath).apply(mOptions).into(imageView);
    }

    public void loadSmallImage(ImageView imageView, String imagePath) {
        //小图加载
        Glide.with(imageView.getContext()).load(imagePath).into(imageView);
    }

    public void loadSmallImage(CircleImageView imageView, String imagePath) {
        //小图加载
        Glide.with(imageView.getContext()).load(imagePath).into(imageView);
    }
    public void loadSmallImage(ImageView imageView, int imagePath) {
        //小图加载
        Glide.with(imageView.getContext()).load(imagePath).into(imageView);
    }

    @Override
    public void loadPreImage(ImageView imageView, String imagePath) {
        //大图加载
        Glide.with(imageView.getContext()).load(imagePath).apply(mPreOptions).into(imageView);

    }

    @Override
    public void clearMemoryCache() {
        //清理缓存
    }

    /**
     * //清理缓存
     * @param context
     */
    public  static void ClearMemoryCache(Context context){
        Glide.get(context).clearMemory();
    }
}
