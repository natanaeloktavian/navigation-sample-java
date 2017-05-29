package natanael.navigation.app;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mixpanel.android.mpmetrics.MixpanelAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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

    public static void sendEvent(Context context, String eventName,HashMap<String,String> additionalProperties)
    {
        /*Bundle bundle = new Bundle();
        bundle.putString("Device Name", android.os.Build.MODEL);
        bundle.putString("Device OS Version", String.valueOf(Build.VERSION.SDK_INT));

        if(additionalProperties!=null && additionalProperties.size()>0)
        {
            for(Map.Entry<String,String> property : additionalProperties.entrySet())
            {
                bundle.putString(property.getKey(),property.getValue());
            }
        }*/

        String projectToken = "dc1db7c63297e3f80dfb5d6b4b4d939d";
        MixpanelAPI mixpanel = MixpanelAPI.getInstance(context, projectToken);

        try
        {
            JSONObject props = new JSONObject();
            props.put("Device Name", android.os.Build.MODEL.toString());
            props.put("Device OS Version", String.valueOf(Build.VERSION.SDK_INT));
            if(additionalProperties!=null && additionalProperties.size()>0)
            {
                for(Map.Entry<String,String> property : additionalProperties.entrySet())
                {
                    props.put(property.getKey(),property.getValue());
                }
            }
            mixpanel.track(eventName, props);
        }
        catch (JSONException e)
        {
            Log.e("MYAPP", "Unable to add properties to JSONObject", e);
        }
    }
}