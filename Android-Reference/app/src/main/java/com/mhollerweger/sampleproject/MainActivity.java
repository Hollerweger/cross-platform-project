/*
 * Copyright 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mhollerweger.sampleproject;


import android.content.Context;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.mhollerweger.sampleproject.view.Drawer;

import com.mhollerweger.sampleproject.view.Fragment;

/**
 * @author  <a href="mailto:martin.hollerweger@nxp.com">Martin Hollerweger</a>
 * @version 0.9
 */
public class MainActivity extends ActionBarActivity {

    private Drawer drawer;

    public static boolean isResultFragmentActive = false;

    private final int readFragmentPosition = 0;

    private final String restartCondition = "restarted";


    private static Context context;
    public static ActionBarActivity mainActivity;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        MainActivity.mainActivity = this;
        MainActivity.context = getApplicationContext();

        super.onCreate(savedInstanceState);

        // Init Navigation Drawer
        setContentView(R.layout.activity_nav_drawer);
        this.drawer = new Drawer();



            // If no Item is selected (at Startup)
            if (savedInstanceState == null) {
                drawer.selectItem(readFragmentPosition);
            }
            // If App has been restarted due to licence accept change
            else  {
                super.onRestoreInstanceState(savedInstanceState);
                if(savedInstanceState.getBoolean(restartCondition)) {
                    drawer.selectItem(readFragmentPosition);
                }
            }

            //Register back press listener
            Fragment.addBackStackChangeListener(drawer);




    }

    public static Context getAppContext() {
        return MainActivity.context;
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_drawer, menu);
        return true;
    }
    //-----------------------------------------  END of NFC Functions


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // If Drawer button pressed then return true
        if (drawer.mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.action_about:
                return true;
        }

        return super.onOptionsItemSelected(item);

    }



    @Override
    public void setTitle(CharSequence title) {
        drawer.setTitle(title);
    }

    @Override
    public void setTitle(int position) {
        drawer.setTitle(position);
    }


}