package com.yjhh.common.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.yjhh.common.BaseApplication;

public class ImageLoaderUtils {

    private static final String TAG = "ImageLoaderUtils";

    /**
     * 圆角图片加载
     * @author leibing
     * @createTime 2016/8/15
     * @lastModify 2016/8/15
     * @param context 上下文
     * @param imageView 图片显示控件
     * @param url 图片链接
     * @param defaultImage 默认占位图片
     * @param errorImage 加载失败后图片
     * @param radius 图片圆角半径
     * @return
     */
    public static void load(Context context, ImageView imageView, String url, int defaultImage,
                            int errorImage , int radius){
        //RequestOptions 设置请求参数，通过apply方法设置
        RequestOptions options = new RequestOptions()
                // 但不保证所有图片都按序加载
                // 枚举Priority.IMMEDIATE，Priority.HIGH，Priority.NORMAL，Priority.LOW
                // 默认为Priority.NORMAL
                // 如果没设置fallback，model为空时将显示error的Drawable，
                // 如果error的Drawable也没设置，就显示placeholder的Drawable
                .priority(Priority.NORMAL) //指定加载的优先级，优先级越高越优先加载，
                .placeholder(defaultImage)
                .error(errorImage)
                // 缓存原始数据
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .centerCrop();
               // .transform(new CornersTranform(context, radius));
        // 图片加载库采用Glide框架
        Glide.with(BaseApplication.getIns()).load(url).apply(options)
                .transition(new DrawableTransitionOptions().crossFade())
                .into(imageView);

    }

    /**
     * 加载resoures下的文件
     * @param context
     * @param imageView
     * @param url
     * @param defaultImage
     * @param errorImage
     */
    public static void loadImgId(Context context, final ImageView imageView, int url, int defaultImage,
                                 int errorImage, int radius) {
        RequestOptions options = new RequestOptions()
                // 但不保证所有图片都按序加载
                // 枚举Priority.IMMEDIATE，Priority.HIGH，Priority.NORMAL，Priority.LOW
                // 默认为Priority.NORMAL
                // 如果没设置fallback，model为空时将显示error的Drawable，
                // 如果error的Drawable也没设置，就显示placeholder的Drawable
                .priority(Priority.NORMAL) //指定加载的优先级，优先级越高越优先加载，
                .placeholder(defaultImage)
                .error(errorImage)
                // 缓存原始数据
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .centerCrop()
                .transform(new CornersTranform(context, radius));
        // 图片加载库采用Glide框架
        Glide.with(context).load(url)
                .apply(options)
                .transition(new DrawableTransitionOptions().crossFade())
                .into(imageView);
    }

    /**
     * 加载圆形头像
     * @param context
     * @param imageView
     * @param url
     * @param defaultImage
     * @param errorImage
     */
    public static void loadCircle(Context context, final ImageView imageView, String url, int defaultImage,
                                  int errorImage) {
        RequestOptions options = new RequestOptions()
                // 但不保证所有图片都按序加载
                // 枚举Priority.IMMEDIATE，Priority.HIGH，Priority.NORMAL，Priority.LOW
                // 默认为Priority.NORMAL
                // 如果没设置fallback，model为空时将显示error的Drawable，
                // 如果error的Drawable也没设置，就显示placeholder的Drawable
                .priority(Priority.NORMAL) //指定加载的优先级，优先级越高越优先加载，
                .dontAnimate() //防止设置placeholder导致第一次不显示网络图片,只显示默认图片的问题
                .placeholder(defaultImage)
                .error(errorImage)
                // 缓存原始数据
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .centerCrop()
                .transform(new GlideCircleTransform(context));
        // 图片加载库采用Glide框架
        if (url!=null){
            Glide.with(context).load(url)
                    .apply(options)
                    .transition(new DrawableTransitionOptions().crossFade())
                    .into(new SimpleTarget<Drawable>() {
                        @Override
                        public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                            imageView.setImageDrawable(resource);
                        }
                    });
        }else {

        }

    }
}

