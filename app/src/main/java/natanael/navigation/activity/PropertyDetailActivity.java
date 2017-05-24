package natanael.navigation.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import net.opacapp.multilinecollapsingtoolbar.CollapsingToolbarLayout;

import natanael.navigation.R;
import natanael.navigation.app.AppConfig;
import natanael.navigation.model.Property;

public class PropertyDetailActivity extends AppCompatActivity
{
    private Toolbar toolbar;
    private TextView summaryLabel;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView toolbarImage;
    private Property property;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_property_detail);

        String propertyData = getIntent().getStringExtra(DrawerActivity.PROPERTY_DATA);
        Gson gson = new Gson();
        property = gson.fromJson(propertyData, Property.class);

        setReference();
        applyData();
    }

    private void setReference()
    {
        toolbar = (Toolbar) this.findViewById(R.id.toolbar);
        if(property!=null)
            toolbar.setTitle(property.getTitle());
        this.setSupportActionBar(toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_backspace_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                finish();
            }
        });

        summaryLabel = (TextView) this.findViewById(R.id.summary_label);
        toolbarImage = (ImageView) this.findViewById(R.id.toolbarImage);
        collapsingToolbarLayout = (net.opacapp.multilinecollapsingtoolbar.CollapsingToolbarLayout) this.findViewById(R.id.collapsingToolbar);
    }

    private void applyData()
    {
        if(property!=null)
        {
            if (summaryLabel != null)
                summaryLabel.setText(property.getSummary());
            if (toolbarImage != null)
                AppConfig.loadImage(getApplicationContext(), toolbarImage, property.getImgUrl());
        }
    }
}
