/**
 * @author Taekmin Jeong, Yeseom Choe
 * @Version 1.0
 * file name: MyAdapter.java
 **/
package com.timeTable21.myapplication;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;

    public MyAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    /**Fragments are instantiated for view pager adapter**/
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                TimeTableFragment timeTableFragment = new TimeTableFragment();
                return timeTableFragment;
            case 1:
                AddCourseFragment courseFragment = new AddCourseFragment();
                return courseFragment;
            case 2:
                AboutFragment aboutFragment = new AboutFragment();
                return aboutFragment;
            case 3:
                EditDeleteFragment editDeleteFragment = new EditDeleteFragment();
                return editDeleteFragment;
            default:
                return null;
        }
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }
}
