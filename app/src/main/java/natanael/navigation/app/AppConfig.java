package natanael.navigation.app;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class AppConfig
{
    public static void loadImage(Context context, ImageView imageView, String url)
    {
        if(context!=null && imageView!=null && url!=null)
        {
            Glide.with(context).load(url)
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
        }
    }
}