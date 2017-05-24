package natanael.navigation.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import natanael.navigation.R;

import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class NavigationListAdapter extends RecyclerView.Adapter<NavigationListAdapter.ViewHolder>
{
    private ArrayList<String> items;
    private ViewHolder viewHolder;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public View view;
        public TextView titleLabel;
        public ImageView imageView;

        public ViewHolder(View v)
        {
            super(v);

            view = v;
            titleLabel = (TextView)v.findViewById(R.id.titleLabel);
            imageView = (ImageView)v.findViewById(R.id.imageView);
        }
    }

    public NavigationListAdapter(ArrayList<String> items,Context context)
    {
        this.items = items;
        this.context = context;
    }

    @Override
    public NavigationListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType)
    {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.navigation_list_item_layout, parent, false);

        viewHolder = new ViewHolder(v);

        return this.viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        String data = items.get(position);

        if (holder.titleLabel != null)
            holder.titleLabel.setText(data);

        if(holder.imageView!=null)
        {
            if(position==0)
                holder.imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.placeholder));
            else if(position==1)
                holder.imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.placeholder));
            else if(position==2)
                holder.imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.placeholder));
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