/**
 * @author Taekmin Jeong, Yeseom Choe
 * @Version 1.0
 * file name: TimeTableFragment.java
 **/
package com.timeTable21.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class TimeTableFragment extends Fragment implements View.OnClickListener{

    private TextView mon8, mon9, mon10, mon11, mon12, mon13, mon14, mon15, mon16, mon17, mon18, mon19, mon20,
            tue8, tue9, tue10, tue11, tue12, tue13, tue14, tue15, tue16, tue17, tue18, tue19, tue20,
            wed8, wed9, wed10, wed11, wed12, wed13, wed14, wed15, wed16, wed17, wed18, wed19, wed20,
            thu8, thu9, thu10, thu11, thu12, thu13, thu14, thu15, thu16, thu17, thu18, thu19, thu20,
            fri8, fri9, fri10, fri11, fri12, fri13, fri14, fri15, fri16, fri17, fri18, fri19, fri20;
    MainActivity activity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        activity = (MainActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();

        activity = null;
    }

    public TimeTableFragment() {
        // Required empty public constructor
    }

    /**This method apply the change in the timetable when user change course record**/
    @Override
    public void onResume() {
        super.onResume();
        populateTimeTable();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_time_table, container, false);

        /**Connect all cells inside table layout**/
        mon8 = (TextView) view.findViewById(R.id.mon8);
        mon9 = (TextView) view.findViewById(R.id.mon9);
        mon10 = (TextView) view.findViewById(R.id.mon10);
        mon11 = (TextView) view.findViewById(R.id.mon11);
        mon12 = (TextView) view.findViewById(R.id.mon12);
        mon13 = (TextView) view.findViewById(R.id.mon13);
        mon14 = (TextView) view.findViewById(R.id.mon14);
        mon15 = (TextView) view.findViewById(R.id.mon15);
        mon16 = (TextView) view.findViewById(R.id.mon16);
        mon17 = (TextView) view.findViewById(R.id.mon17);
        mon18 = (TextView) view.findViewById(R.id.mon18);
        mon19 = (TextView) view.findViewById(R.id.mon19);
        mon20 = (TextView) view.findViewById(R.id.mon20);

        tue8 = (TextView) view.findViewById(R.id.tue8);
        tue9 = (TextView) view.findViewById(R.id.tue9);
        tue10 = (TextView) view.findViewById(R.id.tue10);
        tue11 = (TextView) view.findViewById(R.id.tue11);
        tue12 = (TextView) view.findViewById(R.id.tue12);
        tue13 = (TextView) view.findViewById(R.id.tue13);
        tue14 = (TextView) view.findViewById(R.id.tue14);
        tue15 = (TextView) view.findViewById(R.id.tue15);
        tue16 = (TextView) view.findViewById(R.id.tue16);
        tue17 = (TextView) view.findViewById(R.id.tue17);
        tue18 = (TextView) view.findViewById(R.id.tue18);
        tue19 = (TextView) view.findViewById(R.id.tue19);
        tue20 = (TextView) view.findViewById(R.id.tue20);

        wed8 = (TextView) view.findViewById(R.id.wed8);
        wed9 = (TextView) view.findViewById(R.id.wed9);
        wed10 = (TextView) view.findViewById(R.id.wed10);
        wed11 = (TextView) view.findViewById(R.id.wed11);
        wed12 = (TextView) view.findViewById(R.id.wed12);
        wed13 = (TextView) view.findViewById(R.id.wed13);
        wed14 = (TextView) view.findViewById(R.id.wed14);
        wed15 = (TextView) view.findViewById(R.id.wed15);
        wed16 = (TextView) view.findViewById(R.id.wed16);
        wed17 = (TextView) view.findViewById(R.id.wed17);
        wed18 = (TextView) view.findViewById(R.id.wed18);
        wed19 = (TextView) view.findViewById(R.id.wed19);
        wed20 = (TextView) view.findViewById(R.id.wed20);

        thu8 = (TextView) view.findViewById(R.id.thu8);
        thu9 = (TextView) view.findViewById(R.id.thu9);
        thu10 = (TextView) view.findViewById(R.id.thu10);
        thu11 = (TextView) view.findViewById(R.id.thu11);
        thu12 = (TextView) view.findViewById(R.id.thu12);
        thu13 = (TextView) view.findViewById(R.id.thu13);
        thu14 = (TextView) view.findViewById(R.id.thu14);
        thu15 = (TextView) view.findViewById(R.id.thu15);
        thu16 = (TextView) view.findViewById(R.id.thu16);
        thu17 = (TextView) view.findViewById(R.id.thu17);
        thu18 = (TextView) view.findViewById(R.id.thu18);
        thu19 = (TextView) view.findViewById(R.id.thu19);
        thu20 = (TextView) view.findViewById(R.id.thu20);

        fri8 = (TextView) view.findViewById(R.id.fri8);
        fri9 = (TextView) view.findViewById(R.id.fri9);
        fri10 = (TextView) view.findViewById(R.id.fri10);
        fri11 = (TextView) view.findViewById(R.id.fri11);
        fri12 = (TextView) view.findViewById(R.id.fri12);
        fri13 = (TextView) view.findViewById(R.id.fri13);
        fri14 = (TextView) view.findViewById(R.id.fri14);
        fri15 = (TextView) view.findViewById(R.id.fri15);
        fri16 = (TextView) view.findViewById(R.id.fri16);
        fri17 = (TextView) view.findViewById(R.id.fri17);
        fri18 = (TextView) view.findViewById(R.id.fri18);
        fri19 = (TextView) view.findViewById(R.id.fri19);
        fri20 = (TextView) view.findViewById(R.id.fri20);

        populateTimeTable();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /**Get the course records and find which table cells need to be painted**/
    public void populateTimeTable(){
        DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity());
        List<CourseModel> records = dataBaseHelper.viewRecords();
        /**To prevent painted data cell affected by another irrelevant course id**/
        List<Integer> ids = new ArrayList<>();

        int colorCode = Color.parseColor("#E6f1f1") ;

        if(records != null){
            /**Get the ids of course records**/
            for (int j = 0; j < records.size(); j++){
                CourseModel courseModel = records.get(j);
                ids.add(courseModel.getId());
            }
            /**Find the corresponding cell of each record**/
            for (int i = 0; i < records.size(); i++){
                CourseModel courseModel = records.get(i);
                int id = courseModel.getId();
                String progName = courseModel.getCourse_name();
                String day = courseModel.getDay();
                int startTime = courseModel.getStart_time();
                int endTime = courseModel.getEnd_time();
                /**This variable is for sequential painting**/
                int timeOffset = endTime - startTime;

                //Monday
                if(day.equals("Mon")){
                    if (startTime == 8){
                        /**Go to edit page when colored cell is clicked**/
                        mon8.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        mon8.setBackgroundColor(colorCode);
                        mon8.setText(progName);
                        mon8.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    /**Cell that has no applicable record is empty with border line**/
                    else if(!ids.contains(id)){
                        mon8.setBackgroundResource(R.drawable.border);
                        mon8.setOnClickListener(null);
                    }
                    if (startTime == 9){
                        mon9.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        mon9.setBackgroundColor(colorCode);
                        mon9.setText(progName);
                        mon9.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        mon9.setBackgroundResource(R.drawable.border);
                        mon9.setOnClickListener(null);
                    }
                    if (startTime == 10){
                        mon10.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        mon10.setBackgroundColor(colorCode);
                        mon10.setText(progName);
                        mon10.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        mon10.setBackgroundResource(R.drawable.border);
                        mon10.setOnClickListener(null);
                    }
                    if (startTime == 11){
                        mon11.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        mon11.setBackgroundColor(colorCode);
                        mon11.setText(progName);
                        mon11.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        mon11.setBackgroundResource(R.drawable.border);
                        mon11.setOnClickListener(null);
                    }
                    if (startTime == 12){
                        mon12.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        mon12.setBackgroundColor(colorCode);
                        mon12.setText(progName);
                        mon12.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        mon12.setBackgroundResource(R.drawable.border);
                        mon12.setOnClickListener(null);
                    }
                    if (startTime == 13){
                        mon13.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        mon13.setBackgroundColor(colorCode);
                        mon13.setText(progName);
                        mon13.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        mon13.setBackgroundResource(R.drawable.border);
                        mon13.setOnClickListener(null);
                    }
                    if (startTime == 14){
                        mon14.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        mon14.setBackgroundColor(colorCode);
                        mon14.setText(progName);
                        mon14.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        mon14.setBackgroundResource(R.drawable.border);
                        mon14.setOnClickListener(null);
                    }
                    if (startTime == 15){
                        mon15.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        mon15.setBackgroundColor(colorCode);
                        mon15.setText(progName);
                        mon15.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        mon15.setBackgroundResource(R.drawable.border);
                        mon15.setOnClickListener(null);
                    }
                    if (startTime == 16){
                        mon16.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        mon16.setBackgroundColor(colorCode);
                        mon16.setText(progName);
                        mon16.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        mon16.setBackgroundResource(R.drawable.border);
                        mon16.setOnClickListener(null);
                    }
                    if (startTime == 17){
                        mon17.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        mon17.setBackgroundColor(colorCode);
                        mon17.setText(progName);
                        mon17.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        mon17.setBackgroundResource(R.drawable.border);
                        mon17.setOnClickListener(null);
                    }
                    if (startTime == 18){
                        mon18.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        mon18.setBackgroundColor(colorCode);
                        mon18.setText(progName);
                        mon18.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        mon18.setBackgroundResource(R.drawable.border);
                        mon18.setOnClickListener(null);
                    }
                    if (startTime == 19){
                        mon19.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        mon19.setBackgroundColor(colorCode);
                        mon19.setText(progName);
                        mon19.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        mon19.setBackgroundResource(R.drawable.border);
                        mon19.setOnClickListener(null);
                    }
                    if (startTime == 20){
                        mon20.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        mon20.setBackgroundColor(colorCode);
                        mon20.setText(progName);
                        mon20.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        mon20.setBackgroundResource(R.drawable.border);
                        mon20.setOnClickListener(null);
                    }
                }

                 //Tuesday
                if(day.equals("Tue")){
                    if (startTime == 8){
                        tue8.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        tue8.setBackgroundColor(colorCode);
                        tue8.setText(progName);
                        tue8.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        tue8.setBackgroundResource(R.drawable.border);
                        tue8.setOnClickListener(null);
                    }
                    if (startTime == 9){
                        tue9.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        tue9.setBackgroundColor(colorCode);
                        tue9.setText(progName);
                        tue9.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        tue9.setBackgroundResource(R.drawable.border);
                        tue9.setOnClickListener(null);
                    }
                    if (startTime == 10){
                        tue10.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        tue10.setBackgroundColor(colorCode);
                        tue10.setText(progName);
                        tue10.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        tue10.setBackgroundResource(R.drawable.border);
                        tue10.setOnClickListener(null);
                    }
                    if (startTime == 11){
                        tue11.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        tue11.setBackgroundColor(colorCode);
                        tue11.setText(progName);
                        tue11.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        tue11.setBackgroundResource(R.drawable.border);
                        tue11.setOnClickListener(null);
                    }
                    if (startTime == 12){
                        tue12.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        tue12.setBackgroundColor(colorCode);
                        tue12.setText(progName);
                        tue12.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        tue12.setBackgroundResource(R.drawable.border);
                        tue12.setOnClickListener(null);
                    }
                    if (startTime == 13){
                        tue13.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        tue13.setBackgroundColor(colorCode);
                        tue13.setText(progName);
                        tue13.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        tue13.setBackgroundResource(R.drawable.border);
                        tue13.setOnClickListener(null);
                    }
                    if (startTime == 14){
                        tue14.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        tue14.setBackgroundColor(colorCode);
                        tue14.setText(progName);
                        tue14.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        tue14.setBackgroundResource(R.drawable.border);
                        tue14.setOnClickListener(null);
                    }
                    if (startTime == 15){
                        tue15.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        tue15.setBackgroundColor(colorCode);
                        tue15.setText(progName);
                        tue15.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        tue15.setBackgroundResource(R.drawable.border);
                        tue15.setOnClickListener(null);
                    }
                    if (startTime == 16){
                        tue16.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        tue16.setBackgroundColor(colorCode);
                        tue16.setText(progName);
                        tue16.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        tue16.setBackgroundResource(R.drawable.border);
                        tue16.setOnClickListener(null);
                    }
                    if (startTime == 17){
                        tue17.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        tue17.setBackgroundColor(colorCode);
                        tue17.setText(progName);
                        tue17.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        tue17.setBackgroundResource(R.drawable.border);
                        tue17.setOnClickListener(null);
                    }
                    if (startTime == 18){
                        tue18.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        tue18.setBackgroundColor(colorCode);
                        tue18.setText(progName);
                        tue18.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        tue18.setBackgroundResource(R.drawable.border);
                        tue18.setOnClickListener(null);
                    }
                    if (startTime == 19){
                        tue19.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        tue19.setBackgroundColor(colorCode);
                        tue19.setText(progName);
                        tue19.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        tue19.setBackgroundResource(R.drawable.border);
                        tue19.setOnClickListener(null);
                    }
                    if (startTime == 20){
                        tue20.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        tue20.setBackgroundColor(colorCode);
                        tue20.setText(progName);
                        tue20.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        tue20.setBackgroundResource(R.drawable.border);
                        tue20.setOnClickListener(null);
                    }
                }

                //Wednesday
                if(day.equals("Wed")){
                    if (startTime == 8){
                        wed8.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        wed8.setBackgroundColor(colorCode);
                        wed8.setText(progName);
                        wed8.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        wed8.setBackgroundResource(R.drawable.border);
                        wed8.setOnClickListener(null);
                    }
                    if (startTime == 9){
                        wed9.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        wed9.setBackgroundColor(colorCode);
                        wed9.setText(progName);
                        wed9.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        wed9.setBackgroundResource(R.drawable.border);
                        wed9.setOnClickListener(null);
                    }
                    if (startTime == 10){
                        wed10.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        wed10.setBackgroundColor(colorCode);
                        wed10.setText(progName);
                        wed10.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        wed10.setBackgroundResource(R.drawable.border);
                        wed10.setOnClickListener(null);
                    }
                    if (startTime == 11){
                        wed11.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        wed11.setBackgroundColor(colorCode);
                        wed11.setText(progName);
                        wed11.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        wed11.setBackgroundResource(R.drawable.border);
                        wed11.setOnClickListener(null);
                    }
                    if (startTime == 12){
                        wed12.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        wed12.setBackgroundColor(colorCode);
                        wed12.setText(progName);
                        wed12.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        wed12.setBackgroundResource(R.drawable.border);
                        wed12.setOnClickListener(null);
                    }
                    if (startTime == 13){
                        wed13.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        wed13.setBackgroundColor(colorCode);
                        wed13.setText(progName);
                        wed13.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        wed13.setBackgroundResource(R.drawable.border);
                        wed13.setOnClickListener(null);
                    }
                    if (startTime == 14){
                        wed14.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        wed14.setBackgroundColor(colorCode);
                        wed14.setText(progName);
                        wed14.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        wed14.setBackgroundResource(R.drawable.border);
                        wed14.setOnClickListener(null);
                    }
                    if (startTime == 15){
                        wed15.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        wed15.setBackgroundColor(colorCode);
                        wed15.setText(progName);
                        wed15.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        wed15.setBackgroundResource(R.drawable.border);
                        wed15.setOnClickListener(null);
                    }
                    if (startTime == 16){
                        wed16.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        wed16.setBackgroundColor(colorCode);
                        wed16.setText(progName);
                        wed16.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        wed16.setBackgroundResource(R.drawable.border);
                        wed16.setOnClickListener(null);
                    }
                    if (startTime == 17){
                        wed17.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        wed17.setBackgroundColor(colorCode);
                        wed17.setText(progName);
                        wed17.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        wed17.setBackgroundResource(R.drawable.border);
                        wed17.setOnClickListener(null);
                    }
                    if (startTime == 18){
                        wed18.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        wed18.setBackgroundColor(colorCode);
                        wed18.setText(progName);
                        wed18.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        wed18.setBackgroundResource(R.drawable.border);
                        wed18.setOnClickListener(null);
                    }
                    if (startTime == 19){
                        wed19.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        wed19.setBackgroundColor(colorCode);
                        wed19.setText(progName);
                        wed19.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        wed19.setBackgroundResource(R.drawable.border);
                        wed19.setOnClickListener(null);
                    }
                    if (startTime == 20){
                        wed20.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        wed20.setBackgroundColor(colorCode);
                        wed20.setText(progName);
                        wed20.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        wed20.setBackgroundResource(R.drawable.border);
                        wed20.setOnClickListener(null);
                    }
                }

                //Thursday
                if(day.equals("Thu")){
                    if (startTime == 8){
                        thu8.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        thu8.setBackgroundColor(colorCode);
                        thu8.setText(progName);
                        thu8.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        thu8.setBackgroundResource(R.drawable.border);
                        thu8.setOnClickListener(null);
                    }
                    if (startTime == 9){
                        thu9.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        thu9.setBackgroundColor(colorCode);
                        thu9.setText(progName);
                        thu9.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        thu9.setBackgroundResource(R.drawable.border);
                        thu9.setOnClickListener(null);
                    }
                    if (startTime == 10){
                        thu10.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        thu10.setBackgroundColor(colorCode);
                        thu10.setText(progName);
                        thu10.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        thu10.setBackgroundResource(R.drawable.border);
                        thu10.setOnClickListener(null);
                    }
                    if (startTime == 11){
                        thu11.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        thu11.setBackgroundColor(colorCode);
                        thu11.setText(progName);
                        thu11.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        thu11.setBackgroundResource(R.drawable.border);
                        thu11.setOnClickListener(null);
                    }
                    if (startTime == 12){
                        thu12.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        thu12.setBackgroundColor(colorCode);
                        thu12.setText(progName);
                        thu12.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        thu12.setBackgroundResource(R.drawable.border);
                        thu12.setOnClickListener(null);
                    }
                    if (startTime == 13){
                        thu13.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        thu13.setBackgroundColor(colorCode);
                        thu13.setText(progName);
                        thu13.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        thu13.setBackgroundResource(R.drawable.border);
                        thu13.setOnClickListener(null);
                    }
                    if (startTime == 14){
                        thu14.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        thu14.setBackgroundColor(colorCode);
                        thu14.setText(progName);
                        thu14.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        thu14.setBackgroundResource(R.drawable.border);
                        thu14.setOnClickListener(null);
                    }
                    if (startTime == 15){
                        thu15.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        thu15.setBackgroundColor(colorCode);
                        thu15.setText(progName);
                        thu15.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        thu15.setBackgroundResource(R.drawable.border);
                        thu15.setOnClickListener(null);
                    }
                    if (startTime == 16){
                        thu16.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        thu16.setBackgroundColor(colorCode);
                        thu16.setText(progName);
                        thu16.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        thu16.setBackgroundResource(R.drawable.border);
                        thu16.setOnClickListener(null);
                    }
                    if (startTime == 17){
                        thu17.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        thu17.setBackgroundColor(colorCode);
                        thu17.setText(progName);
                        thu17.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        thu17.setBackgroundResource(R.drawable.border);
                        thu17.setOnClickListener(null);
                    }
                    if (startTime == 18){
                        thu18.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        thu18.setBackgroundColor(colorCode);
                        thu18.setText(progName);
                        thu18.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        thu18.setBackgroundResource(R.drawable.border);
                        thu18.setOnClickListener(null);
                    }
                    if (startTime == 19){
                        thu19.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        thu19.setBackgroundColor(colorCode);
                        thu19.setText(progName);
                        thu19.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        thu19.setBackgroundResource(R.drawable.border);
                        thu19.setOnClickListener(null);
                    }
                    if (startTime == 20){
                        thu20.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        thu20.setBackgroundColor(colorCode);
                        thu20.setText(progName);
                        thu20.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        thu20.setBackgroundResource(R.drawable.border);
                        thu20.setOnClickListener(null);
                    }
                }

                //Friday
                if(day.equals("Fri")){
                    if (startTime == 8){
                        fri8.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        fri8.setBackgroundColor(colorCode);
                        fri8.setText(progName);
                        fri8.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        fri8.setBackgroundResource(R.drawable.border);
                        fri8.setOnClickListener(null);
                    }
                    if (startTime == 9){
                        fri9.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        fri9.setBackgroundColor(colorCode);
                        fri9.setText(progName);
                        fri9.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        fri9.setBackgroundResource(R.drawable.border);
                        fri9.setOnClickListener(null);
                    }
                    if (startTime == 10){
                        fri10.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        fri10.setBackgroundColor(colorCode);
                        fri10.setText(progName);
                        fri10.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        fri10.setBackgroundResource(R.drawable.border);
                        fri10.setOnClickListener(null);
                    }
                    if (startTime == 11){
                        fri11.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        fri11.setBackgroundColor(colorCode);
                        fri11.setText(progName);
                        fri11.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        fri11.setBackgroundResource(R.drawable.border);
                        fri11.setOnClickListener(null);
                    }
                    if (startTime == 12){
                        fri12.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        fri12.setBackgroundColor(colorCode);
                        fri12.setText(progName);
                        fri12.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        fri12.setBackgroundResource(R.drawable.border);
                        fri12.setOnClickListener(null);
                    }
                    if (startTime == 13){
                        fri13.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        fri13.setBackgroundColor(colorCode);
                        fri13.setText(progName);
                        fri13.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        fri13.setBackgroundResource(R.drawable.border);
                        fri13.setOnClickListener(null);
                    }
                    if (startTime == 14){
                        fri14.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        fri14.setBackgroundColor(colorCode);
                        fri14.setText(progName);
                        fri14.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        fri14.setBackgroundResource(R.drawable.border);
                        fri14.setOnClickListener(null);
                    }
                    if (startTime == 15){
                        fri15.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        fri15.setBackgroundColor(colorCode);
                        fri15.setText(progName);
                        fri15.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        fri15.setBackgroundResource(R.drawable.border);
                        fri15.setOnClickListener(null);
                    }
                    if (startTime == 16){
                        fri16.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        fri16.setBackgroundColor(colorCode);
                        fri16.setText(progName);
                        fri16.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        fri16.setBackgroundResource(R.drawable.border);
                        fri16.setOnClickListener(null);
                    }
                    if (startTime == 17){
                        fri17.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        fri17.setBackgroundColor(colorCode);
                        fri17.setText(progName);
                        fri17.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        fri17.setBackgroundResource(R.drawable.border);
                        fri17.setOnClickListener(null);
                    }
                    if (startTime == 18){
                        fri18.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        fri18.setBackgroundColor(colorCode);
                        fri18.setText(progName);
                        fri18.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        fri18.setBackgroundResource(R.drawable.border);
                        fri18.setOnClickListener(null);
                    }
                    if (startTime == 19){
                        fri19.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        fri19.setBackgroundColor(colorCode);
                        fri19.setText(progName);
                        fri19.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        fri19.setBackgroundResource(R.drawable.border);
                        fri19.setOnClickListener(null);
                    }
                    if (startTime == 20){
                        fri20.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activity.changeFragment(3, id);
                            }
                        });
                        fri20.setBackgroundColor(colorCode);
                        fri20.setText(progName);
                        fri20.setTextSize(14);
                        if(timeOffset > 1){
                            timeOffset = timeOffset - 1;
                            startTime = startTime + 1;
                        }
                    }
                    else if(!ids.contains(id)){
                        fri20.setBackgroundResource(R.drawable.border);
                        fri20.setOnClickListener(null);
                    }
                }
                ids.add(id);
            }
        }
    }

    @Override
    public void onClick(View v) {

    }
}