/**
 * @author Taekmin Jeong, Yeseom Choe
 * @Version 1.0
 * file name: MainActivity.java
 **/
package com.timeTable21.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    EditDeleteFragment editDeleteFragment;
    TimeTableFragment timeTableFragment;
    AddCourseFragment addCourseFragment;
    AboutFragment aboutFragment;

    int courseID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /**Run foreground service on created**/
        Intent serviceIntent = new Intent(this, ForegroundService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(serviceIntent);
        }

        /**Main display frame of application**/
        tabLayout=(TabLayout)findViewById(R.id.tabLayout);
        viewPager=(ViewPager)findViewById(R.id.viewPager);

        /**Add tabs for tab layout**/
        tabLayout.addTab(tabLayout.newTab().setText("Time Table"));
        tabLayout.addTab(tabLayout.newTab().setText("Add Course"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        /**Make a fragment adapter for view pager**/
        final MyAdapter adapter = new MyAdapter(this,getSupportFragmentManager(), 4);

        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuMain:
                Toast.makeText(this, "Main", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
                return true;
            case R.id.menuAbout:
                Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, AboutActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * change the fragment
     * @param index indicate the fragment page element inside view pager
     * @param id when it is 0 no course selected. if not clicked course id will be saved
     * **/
    public void changeFragment(int index, int id){
        courseID = id;
        switch(index){
            case 0:
                viewPager.setCurrentItem(0);
                break;
            case 1:
                viewPager.setCurrentItem(1);
                break;
            case 2:
                viewPager.setCurrentItem(2);
                break;
            case 3:
                viewPager.setCurrentItem(3);
                break;
        }
    }
}