package natanael.navigation.activity;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import natanael.navigation.R;
import natanael.navigation.adapter.NavigationListAdapter;
import natanael.navigation.fragment.FragmentA;
import natanael.navigation.fragment.FragmentB;
import natanael.navigation.fragment.FragmentC;
import natanael.navigation.fragment.PropertyDetailFragment;
import natanael.navigation.model.Property;
import natanael.navigation.model.RecyclerItemClickListener;

public class DrawerActivity extends AppCompatActivity
{
    public static final String ARG_POSITION = "position";
    public static final String PROPERTY_DATA = "property_data";

    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private LinearLayout mFragmentContainer;

    private RecyclerView mNavigationRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mNavigationListAdapter;
    private ArrayList<String> mNavigationList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_drawer);
        setReference();
        syncActionBarDrawerToggle(this);
        initializeNavigationList();
        navigateToFirstFragment();
    }

    private void setReference()
    {
        mDrawerLayout = (DrawerLayout) this.findViewById(R.id.drawer_layout);
        mToolbar = (Toolbar) this.findViewById(R.id.toolbar);
        mNavigationRecyclerView = (RecyclerView) this.findViewById(R.id.navigation_recycler_view);
        mFragmentContainer = (LinearLayout) this.findViewById(R.id.fragment_container);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initializeNavigationList()
    {
        mNavigationList = new ArrayList<String>();
        mNavigationList.add("Menu 1");
        mNavigationList.add("Menu 2");
        mNavigationList.add("Menu 3");
        mNavigationListAdapter = new NavigationListAdapter(mNavigationList,this);
        if(mNavigationRecyclerView!=null)
            this.initializeRecyclerView(mNavigationRecyclerView,mNavigationListAdapter);
    }

    private void initializeRecyclerView(final RecyclerView recyclerView, final RecyclerView.Adapter adapter)
    {
        recyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this)
        {
            @Override
            public boolean canScrollVertically()
            {
                return  true;
            }
        };

        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener()
                {
                    @Override public void onItemClick(View view, int position)
                    {
                        if(mDrawerLayout!=null)
                            mDrawerLayout.closeDrawer(Gravity.LEFT);
                        switch (position)
                        {
                            case 0:
                                navigateToFragmentA();
                                break;
                            case 1:
                                navigateToFragmentB();
                                break;
                            case 2:
                                navigateToFragmentC();
                                return ;
                        }
                    }

                    @Override public void onLongItemClick(View view, int position)
                    {
                    }
                })
        );

        recyclerView.setAdapter(adapter);
    }

    ActionBarDrawerToggle mDrawerToggle;
    private void syncActionBarDrawerToggle(DrawerActivity activity)
    {
        mDrawerToggle = new ActionBarDrawerToggle(activity,activity.mDrawerLayout,mToolbar,R.string.drawer_open,R.string.drawer_close)
        {
            @Override
            public void onDrawerClosed(View v){
                super.onDrawerClosed(v);
            }

            @Override
            public void onDrawerOpened(View v) {
                super.onDrawerOpened(v);
            }
        };

        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    private void navigateToFirstFragment()
    {
        FragmentA newFragment = new FragmentA();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.commit();
    }

    private void navigateToFragmentA()
    {
        FragmentManager fragmentManageer = getSupportFragmentManager();
        if(fragmentManageer.findFragmentById(R.id.fragment_container) instanceof FragmentA)
        {
            Toast.makeText(getApplicationContext(),"Not Navigating",Toast.LENGTH_SHORT).show();
        }
        else
        {
            onBackPressed();
            /*FragmentA newFragment = new FragmentA();
            Bundle args = new Bundle();
            args.putString(DrawerActivity.ARG_POSITION, "A");
            newFragment.setArguments(args);

            replaceFragment(newFragment,false);*/
        }
    }

    private void navigateToFragmentB()
    {
        FragmentManager fragmentManageer = getSupportFragmentManager();
        if(fragmentManageer.findFragmentById(R.id.fragment_container) instanceof FragmentB)
        {
            Toast.makeText(getApplicationContext(),"Not Navigating",Toast.LENGTH_SHORT).show();
        }
        else
        {
            FragmentB newFragment = new FragmentB();
            Bundle args = new Bundle();
            args.putString(DrawerActivity.ARG_POSITION, "B");
            newFragment.setArguments(args);

            replaceFragment(newFragment, true);
        }
    }

    private void navigateToFragmentC()
    {
        FragmentManager fragmentManageer = getSupportFragmentManager();
        if(fragmentManageer.findFragmentById(R.id.fragment_container) instanceof FragmentC)
        {
            Toast.makeText(getApplicationContext(),"Not Navigating",Toast.LENGTH_SHORT).show();
        }
        else
        {
            FragmentC newFragment = new FragmentC();
            Bundle args = new Bundle();
            args.putString(DrawerActivity.ARG_POSITION, "C");
            newFragment.setArguments(args);

            replaceFragment(newFragment, true);
        }
    }

    private void replaceFragment(Fragment newFragment,Boolean addToBackStack)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, newFragment);
        if(addToBackStack)
            transaction.addToBackStack(null);
        transaction.commit();
    }

    public void navigateToPropertyDetailFragment(String propertyData)
    {
        animateIcon(true);
        PropertyDetailFragment detailFragment = new PropertyDetailFragment();
        Bundle args = new Bundle();
        args.putString(DrawerActivity.PROPERTY_DATA, propertyData);
        detailFragment.setArguments(args);

        replaceFragment(detailFragment,true);
    }

    public void animateIcon(Boolean state)
    {
        ValueAnimator va = ValueAnimator.ofFloat(0f, 1f);
        if(!state)
            va = ValueAnimator.ofFloat(1f, 0f);
        int mDuration = 300;
        va.setDuration(mDuration);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            public void onAnimationUpdate(ValueAnimator animation)
            {
                mDrawerToggle.onDrawerSlide(mDrawerLayout,(float)animation.getAnimatedValue());
            }
        });
        va.start();

        if(state)
        {
            mToolbar.setNavigationOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.popBackStack();
                }
            });
        }
        else
        {
            mToolbar.setNavigationOnClickListener(null);
            syncActionBarDrawerToggle(this);
        }
    }
}