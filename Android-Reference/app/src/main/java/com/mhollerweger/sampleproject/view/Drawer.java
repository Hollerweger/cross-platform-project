package com.mhollerweger.sampleproject.view;


import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mhollerweger.sampleproject.MainActivity;
import com.mhollerweger.sampleproject.R;


/**
 * Created by Martin Hollerweger on 26.11.14.
 * Copyrighted by NXP.
 */
public class Drawer {

    // Drawer variables
    public DrawerLayout mDrawerLayout;
    public ListView mDrawerList;
    public ActionBarDrawerToggle mDrawerToggle;
    public CharSequence mDrawerTitle;
    public CharSequence mTitle;
    public String[] navTitles;
    public static int position = 0;

    public Drawer()  {
        mTitle = mDrawerTitle = MainActivity.mainActivity.getTitle();
        navTitles =  MainActivity.mainActivity.getResources().getStringArray(R.array.nav_items);
        mDrawerLayout = (DrawerLayout) MainActivity.mainActivity.findViewById(R.id.drawer_layout);
        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

        // set up the drawer's list view with items and click listener
        mDrawerList = (ListView) MainActivity.mainActivity.findViewById(R.id.left_drawer);
        mDrawerList.setAdapter(new ArrayAdapter<String>(MainActivity.mainActivity,
                R.layout.drawer_list_item,navTitles));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());



        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                MainActivity.mainActivity,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                //R.drawable.ic_drawer,
                R.string.navigation_drawer_open,  /* "open drawer" description for accessibility */
                R.string.navigation_drawer_close  /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
                MainActivity.mainActivity.getSupportActionBar().setTitle(mTitle);
                //MainActivity.mainActivity.invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
            public void onDrawerOpened(View drawerView) {
                MainActivity.mainActivity.getSupportActionBar().setTitle(mDrawerTitle);
                //MainActivity.mainActivity.invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        // enable ActionBar app icon to behave as action to toggle nav drawer
//        MainActivity.mainActivity.getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_drawer);
        MainActivity.mainActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        MainActivity.mainActivity.getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerToggle.syncState();
    }

    /**
     * If Drawer item selected, then change Title and close Drawer
     * @param position
     */
    public void selectItem(int position) {
        Drawer.position = position;
        // Select Fragment
        Fragment.selectFragment(Drawer.position, MainActivity.mainActivity);
        // Update Item and Title in the Drawer and close it
        setTitle(navTitles[position]);
        mDrawerList.setItemChecked(position, true);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    /* The click listener for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    public void setTitle(CharSequence title) {
        mTitle = title;
        MainActivity.mainActivity.getSupportActionBar().setTitle(mTitle);
    }

    public void setTitle(int position) {
        mTitle = navTitles[position];
        MainActivity.mainActivity.getSupportActionBar().setTitle(mTitle);
    }

}
