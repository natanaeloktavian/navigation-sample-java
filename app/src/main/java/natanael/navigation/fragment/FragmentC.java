package natanael.navigation.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import natanael.navigation.R;
import natanael.navigation.activity.DrawerActivity;

public class FragmentC extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_c, container, false);

        return view;
    }

    @Override
    public void onResume()
    {
        AppCompatActivity activity = (AppCompatActivity)getActivity();
        if(activity!=null)
            activity.getSupportActionBar().setTitle("Fragment C");

        super.onResume();
    }
}