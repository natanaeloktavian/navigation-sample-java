package natanael.navigation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;

import natanael.navigation.app.AppConfig;
import natanael.navigation.model.Property;
import natanael.navigation.R;
import natanael.navigation.widget.RoundedImageView;

import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

public class PropertyAdapter extends RecyclerView.Adapter<PropertyAdapter.ViewHolder>
{
    public ArrayList<Property> items;
    public ArrayList<Property> selectedItems;
    public ViewHolder viewHolder;
    public Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public View view;
        public TextView titleLabel,summaryLabel,bedroomLabel,bathroomLabel,priceLabel;
        public RoundedImageView imageView;

        public ViewHolder(View v)
        {
            super(v);

            view = v;
            titleLabel = (TextView)v.findViewById(R.id.title_label);
            summaryLabel = (TextView)v.findViewById(R.id.summary_label);
            bedroomLabel = (TextView)v.findViewById(R.id.bedroom_label);
            bathroomLabel = (TextView)v.findViewById(R.id.bathroom_label);
            priceLabel = (TextView)v.findViewById(R.id.price_label);
            imageView = (RoundedImageView)v.findViewById(R.id.image_view);
        }
    }

    public PropertyAdapter(Context context,ArrayList<Property> items)
    {
        this.selectedItems = new ArrayList<>();
        this.items = items;
        this.context = context;
    }

    @Override
    public PropertyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.property_item_layout, parent, false);

        this.viewHolder = new ViewHolder(v);
        return this.viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        Property data = items.get(position);

        if (holder.titleLabel != null)
            holder.titleLabel.setText(data.getTitle());
        if (holder.summaryLabel != null)
            holder.summaryLabel.setText(data.getSummary());
        if (holder.bedroomLabel != null)
            holder.bedroomLabel.setText("Bedroom : "+data.getBedroomNumber());
        if (holder.bathroomLabel != null)
            holder.bathroomLabel.setText("Bathroom : "+data.getBathroomNumber());
        if (holder.priceLabel != null)
            holder.priceLabel.setText("Price : "+data.getPrice());
        if(holder.imageView!=null)
        {
            AppConfig.loadImage(context,holder.imageView,data.getThumbUrl());
            /*Glide.with(context).load(data.getThumbUrl())
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.imageView);*/
        }
    }

    @Override
    public int getItemCount()
    {
        if(items!=null)
            return items.size();
        else
            return 0;
    }
}