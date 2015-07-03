package com.mhollerweger.sampleproject.view;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.mhollerweger.sampleproject.LocationActivity;
import com.mhollerweger.sampleproject.MainActivity;
import com.mhollerweger.sampleproject.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Martin Hollerweger on 31.07.14.
 * Copyrighted by NXP.
 */
public class Fragment extends android.app.Fragment {

    static final int REQUEST_IMAGE_CAPTURE = 1;


    public static Fragment fragmentImplementation;
    private final static String fragmentResults = "fragment_results";
    private final static String fragmentTap = "fragment_tap";

    private final static String fragmentLogs = "fragment_logs";


    public static final String positionName = "positionName";
    private LocationActivity locationActivity;
    private Button requestLocationUpdate;

    public Fragment() {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        locationActivity = new LocationActivity(this.getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        int pos = getArguments().getInt(positionName);
        if (pos == 0)
            view = inflater.inflate(R.layout.fragment_standard_ui_elements, container, false);
        else if (pos == 1) {
            view = inflater.inflate(R.layout.fragment_camera, container, false);
            // create Intent to take a picture and return control to the calling application
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);


            }
        }
        else {
            view = inflater.inflate(R.layout.fragment_map, container, false);

            requestLocationUpdate = (Button)  view.findViewById(R.id.requestLocationUpdate);
            requestLocationUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    locationActivity.requestLocationUpdate();
                }
            });
        }

        //SimpleAdapter simpleAdapter = new SimpleAdapter(this,
        //Set if Fragment Result is Active
        MainActivity.isResultFragmentActive = pos == 1;
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == getActivity().RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageView imageView = (ImageView) getActivity().findViewById(R.id.imageView);
            imageView.setImageBitmap(imageBitmap);
        }
    }

    public static void selectFragment (int position, Activity activity) {
        Drawer.position = position;
        String fragmentTag = getFragmentIdByPosition(position);

        FragmentManager manager = activity.getFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate (fragmentTag, 0);

        //fragment not in back stack, create it.
        if (!fragmentPopped && manager.findFragmentByTag(fragmentTag) == null){
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.container, newFragmentByPosition(position), fragmentTag);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(fragmentTag);
            ft.commit();
        }
    }

    private static Fragment newFragmentByPosition(int position){
        Fragment fragment = new Fragment();
        Bundle args = new Bundle();
        args.putInt(Fragment.positionName, position);
        fragment.setArguments(args);
        fragment.setRetainInstance(true);
        return fragment;
    }

    private static String getFragmentIdByPosition(int position) {
        if (position == 0)
            return fragmentTap;
        else if (position == 1)
            return fragmentResults;
        else
            return fragmentLogs;

    }

    public static void addBackStackChangeListener(final Drawer drawer) {
        MainActivity.mainActivity.getFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {

            @Override
            public void onBackStackChanged() {
                FragmentManager.BackStackEntry backStackEntry = MainActivity.mainActivity.getFragmentManager().getBackStackEntryAt(MainActivity.mainActivity.getFragmentManager().getBackStackEntryCount() - 1);
                int position;
                if (backStackEntry.getName() == Fragment.fragmentTap) {
                    position = 0;
                } else if   (backStackEntry.getName() == Fragment.fragmentResults){
                    position = 1;
                } else {
                    position = 2;
                }
                MainActivity.mainActivity.setTitle(position);
                drawer.mDrawerList.setItemChecked(position, true);
            }
        });
    }

    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;

    /** Create a file Uri for saving an image or video */
    private static Uri getOutputMediaFileUri(int type){
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /** Create a File for saving an image or video */
    private static File getOutputMediaFile(int type){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraApp");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".jpg");
        } else if(type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_"+ timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }

}
