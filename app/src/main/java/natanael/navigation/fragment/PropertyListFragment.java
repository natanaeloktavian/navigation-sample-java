package natanael.navigation.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.util.ArrayList;

import natanael.navigation.R;
import natanael.navigation.activity.DrawerActivity;
import natanael.navigation.adapter.PropertyAdapter;
import natanael.navigation.model.Property;
import natanael.navigation.model.RecyclerItemClickListener;
import natanael.navigation.presenter.IPropertyListPresenter;
import natanael.navigation.presenter.PropertyListPresenter;
import natanael.navigation.repository.PropertyListRepository;
import natanael.navigation.view.IPropertyListView;

public class PropertyListFragment extends Fragment implements IPropertyListView
{
    private IPropertyListPresenter mPresenter;
    private PropertyAdapter mPropertyAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;

    private View mView;
    private ProgressDialog pDialog;
    protected String city;

    public PropertyListFragment()
    {
        city = "brighton";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        if(mView==null)
        {
            mView = inflater.inflate(R.layout.fragment_property_list, container, false);
            setReference();
            initialize();
        }

        return mView;
    }

    private void setReference()
    {
        if(mView!=null)
        {
            mRecyclerView = (RecyclerView) mView.findViewById(R.id.recycler_view);
            pDialog = new ProgressDialog(getContext());
            pDialog.setMessage("Loading...");
            pDialog.setCancelable(false);
        }
    }

    private void initialize()
    {
        //showDialog();
        mPresenter = new PropertyListPresenter(this, new PropertyListRepository());
        mPresenter.loadListings(city);
    }

    private void initializeRecyclerView(final RecyclerView recyclerView, final RecyclerView.Adapter adapter)
    {
        recyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity())
        {
            @Override
            public boolean canScrollVertically()
            {
                return  true;
            }
        };

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this.getActivity(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener()
                {
                    @Override public void onItemClick(View view, int position)
                    {
                        mPresenter.onItemClicked(position);
                    }

                    @Override public void onLongItemClick(View view, int position)
                    {

                    }
                })
        );

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDataLoaded(ArrayList<Property> properties)
    {
        //hideDialog();
        if(properties!=null)
        {
            //Toast.makeText(getContext(), "Loaded " + properties.size() + " items", Toast.LENGTH_SHORT).show();
            mPropertyAdapter = new PropertyAdapter(getContext(), properties);
            if (mRecyclerView != null)
                this.initializeRecyclerView(mRecyclerView, mPropertyAdapter);
        }
    }

    @Override
    public void onItemClicked(Property property)
    {
        Gson gson = new Gson();
        String propertyData = gson.toJson(property);

        DrawerActivity activity =(DrawerActivity) getActivity();
        if(activity!=null)
            activity.navigateToPropertyDetailFragment(propertyData);
    }

    @Override
    public void onFailure(String message)
    {

    }

    private void showDialog()
    {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog()
    {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}