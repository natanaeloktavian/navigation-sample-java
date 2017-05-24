package natanael.navigation.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import natanael.navigation.R;
import natanael.navigation.activity.DrawerActivity;

public class FragmentB extends Fragment
{
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private ActionBar mToolbar;
    private int mCurrentTabPosition=0;
    private View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        if(mView==null)
        {
            mView = inflater.inflate(R.layout.fragment_b, container, false);
            setReference();
            initialize();
        }

        return mView;
    }

    @Override
    public void onResume()
    {
        this.setToolbarTitle();

        super.onResume();
    }

    private void setReference()
    {
        if(mView!=null)
        {
            mTabLayout = (TabLayout) mView.findViewById(R.id.tabs);
            mViewPager = (ViewPager) mView.findViewById(R.id.view_pager);

            AppCompatActivity activity = (AppCompatActivity)getActivity();
            if(activity!=null)
                mToolbar = activity.getSupportActionBar();
        }
    }

    private void initialize()
    {
        setupViewPager(mViewPager);
        mTabLayout.setupWithViewPager(mViewPager);
        //setupTabIcons();

        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager)
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                super.onTabSelected(tab);

                int tabPosition = tab.getPosition();

                /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP )
                {
                    mTabLayout.getTabAt(mCurrentTabPosition).getIcon().setTint(ContextCompat.getColor(getActivity(),R.color.colorBlack));
                    tab.getIcon().setTint(ContextCompat.getColor(getActivity(),R.color.colorAccent));
                }
                else
                {
                    if(mCurrentTabPosition==0)
                        mTabLayout.getTabAt(0).setIcon(R.drawable.home_icon___inactive);
                    else if(mCurrentTabPosition==1)
                        mTabLayout.getTabAt(1).setIcon(R.drawable.inspirasi_icon_inactive);
                    else if(mCurrentTabPosition==2)
                        mTabLayout.getTabAt(2).setIcon(R.drawable.notifikasi_icon_inactive);
                    else if(mCurrentTabPosition==3)
                        mTabLayout.getTabAt(3).setIcon(R.drawable.fav_icon_inactive);
                    else if(mCurrentTabPosition==4)
                        mTabLayout.getTabAt(4).setIcon(R.drawable.profile_icon_inactive);

                    if(tabPosition==0)
                        mTabLayout.getTabAt(0).setIcon(R.drawable.home_icon__active);
                    else if(tabPosition==1)
                        mTabLayout.getTabAt(1).setIcon(R.drawable.inspirasi_icon_active);
                    else if(tabPosition==2)
                        mTabLayout.getTabAt(2).setIcon(R.drawable.notifikasi_icon_active);
                    else if(tabPosition==3)
                        mTabLayout.getTabAt(3).setIcon(R.drawable.fav_icon_active);
                    else if(tabPosition==4)
                        mTabLayout.getTabAt(4).setIcon(R.drawable.profile_icon_active);
                }*/
                mCurrentTabPosition = tabPosition;

                setToolbarTitle();
            }
        });

        setToolbarTitle();
    }

    private void setToolbarTitle()
    {
        if(mCurrentTabPosition==0)
            mToolbar.setTitle("Brighton");
        else if(mCurrentTabPosition==1)
            mToolbar.setTitle("London");
        else if(mCurrentTabPosition==2)
            mToolbar.setTitle("Rome");
        else if(mCurrentTabPosition==3)
            mToolbar.setTitle("Oxford");
        else if(mCurrentTabPosition==4)
            mToolbar.setTitle("Birmingham");
    }

    private void setupViewPager(ViewPager viewPager)
    {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new FragmentB1(), "Brighton");
        adapter.addFragment(new FragmentB2(), "London");
        //adapter.addFragment(new FragmentB1(), "Rome");
        //adapter.addFragment(new FragmentB2(), "Oxford");
        //adapter.addFragment(new FragmentB1(), "Birmingham");
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(1);
    }

    private void setupTabIcons()
    {
        if(mTabLayout!=null)
        {
            mTabLayout.getTabAt(0).setIcon(R.drawable.home_icon___inactive);
            mTabLayout.getTabAt(1).setIcon(R.drawable.inspirasi_icon_inactive);
            mTabLayout.getTabAt(2).setIcon(R.drawable.notifikasi_icon_inactive);
            mTabLayout.getTabAt(3).setIcon(R.drawable.fav_icon_inactive);
            mTabLayout.getTabAt(4).setIcon(R.drawable.profile_icon_inactive);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            {
                mTabLayout.getTabAt(mCurrentTabPosition).getIcon().setTint(ContextCompat.getColor(getActivity(), R.color.colorAccent));
                for (int i = 0; i <= 4; i++)
                    if(i!=mCurrentTabPosition)
                        mTabLayout.getTabAt(i).getIcon().setTint(ContextCompat.getColor(getActivity(), R.color.colorBlack));
            }
            else
                mTabLayout.getTabAt(mCurrentTabPosition).setIcon(R.drawable.home_icon__active);
        }
    }

    class ViewPagerAdapter extends FragmentStatePagerAdapter
    {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager)
        {
            super(manager);
        }

        @Override
        public Fragment getItem(int position)
        {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount()
        {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title)
        {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position)
        {
            return mFragmentTitleList.get(position);
        }
    }
}