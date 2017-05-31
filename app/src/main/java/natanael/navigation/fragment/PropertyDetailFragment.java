package natanael.navigation.fragment;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import net.opacapp.multilinecollapsingtoolbar.CollapsingToolbarLayout;

import java.util.HashMap;

import natanael.navigation.R;
import natanael.navigation.activity.DrawerActivity;
import natanael.navigation.app.AppConfig;
import natanael.navigation.model.Property;

public class PropertyDetailFragment extends Fragment
{
    private View mView;
    private ImageView imageView;
    private TextView titleLabel,summaryLabel;
    private Property property;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        mView = inflater.inflate(R.layout.fragment_property_detail, container, false);

        Bundle bundle = getArguments();
        if(bundle!=null)
        {
            String data = bundle.getString(DrawerActivity.PROPERTY_DATA);

            Gson gson = new Gson();
            property = gson.fromJson(data, Property.class);
        }

        setReference();
        setData();
        return mView;
    }

    private void setReference()
    {
        if(mView!=null)
        {
            imageView = (ImageView) mView.findViewById(R.id.image_view);
            titleLabel = (TextView) mView.findViewById(R.id.title_label);
            summaryLabel = (TextView) mView.findViewById(R.id.summary_label);
        }
    }

    private void setData()
    {
        if(property!=null)
        {
            if (imageView != null)
                AppConfig.loadImage(getContext(), imageView, property.getImgUrl());
            if(titleLabel!=null)
                titleLabel.setText(property.getTitle());
            if(summaryLabel!=null)
                summaryLabel.setText(property.getSummary());

            HashMap<String,String> props = new HashMap<>();
            props.put("Title",property.getTitle());
            props.put("City",property.getCity());
            AppConfig.sendEvent(getActivity(),"Opened Property Detail",props);
        }
    }

    @Override
    public void onResume()
    {
        AppCompatActivity activity = (AppCompatActivity)getActivity();
        if(activity!=null)
        {
            activity.getSupportActionBar().setTitle("Property Detail");
        }
        super.onResume();
    }

    @Override
    public void onDestroy()
    {
        DrawerActivity activity = (DrawerActivity)getActivity();
        if(activity!=null)
            activity.animateIcon(false);
        super.onDestroy();
    }
}