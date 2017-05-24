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

public class FragmentA5 extends PropertyListFragment
{
    public FragmentA5()
    {
        city = "birmingham";
    }
}