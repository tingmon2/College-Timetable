/**
 * @author Taekmin Jeong, Yeseom Choe
 * @Version 1.0
 * file name: EditDeleteFragment.java
 **/
package com.timeTable21.myapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.myapplication.R;

import java.util.List;

public class EditDeleteFragment extends Fragment {

    private Spinner editSpinner;
    private Button btnEdit, btnCancel, btnDelete;
    private EditText txtCourseCode, txtCourseName, txtProfName, txtStartTime, txtEndTime, txtClassRoom, txtNote;
    int position;
    String selectedDay;
    int id;
    String[] courseValues;
    int initialStartTime, initialEndTime;

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


    public EditDeleteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_delete, container, false);
    }

    /**This method apply the change in the input fields when user select different course**/
    @Override
    public void onResume() {
        super.onResume();
        switch (courseValues[4]){
            case "Mon":
                editSpinner.setSelection(0);
                break;
            case "Tue":
                editSpinner.setSelection(1);
                break;
            case "Wed":
                editSpinner.setSelection(2);
                break;
            case "Thu":
                editSpinner.setSelection(3);
                break;
            case "Fri":
                editSpinner.setSelection(4);
                break;
        }

        txtCourseCode.setText(courseValues[1]);
        txtCourseName.setText(courseValues[2]);
        txtProfName.setText(courseValues[3]);
        txtStartTime.setText(courseValues[5]);
        txtEndTime.setText(courseValues[6]);
        txtClassRoom.setText(courseValues[7]);
        txtNote.setText(courseValues[8]);

        initialStartTime = Integer.parseInt(txtStartTime.getText().toString().trim());
        initialEndTime = Integer.parseInt(txtEndTime.getText().toString().trim());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /**Generate day spinner**/
        editSpinner = (Spinner) getView().findViewById(R.id.spinnerEditDay);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.Day,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editSpinner.setAdapter(adapter);
        editSpinner.setSelection(0);

        position = editSpinner.getSelectedItemPosition();
        selectedDay = (String) editSpinner.getSelectedItem();

        txtCourseCode = (EditText) getView().findViewById(R.id.txtEditCourseCode);
        txtCourseName = (EditText) getView().findViewById(R.id.txtEditCourseName);
        txtProfName = (EditText) getView().findViewById(R.id.txtEditProfName);
        txtStartTime = (EditText) getView().findViewById(R.id.txtEditStartTime);
        txtEndTime = (EditText) getView().findViewById(R.id.txtEditEndTime);
        txtClassRoom = (EditText) getView().findViewById(R.id.txtEditClassRoom);
        txtNote = (EditText) getView().findViewById(R.id.txtEditNote);

        btnEdit = (Button) getView().findViewById(R.id.btnEdit);
        btnDelete = (Button) getView().findViewById(R.id.btnDelete);
        btnCancel = (Button) getView().findViewById(R.id.btnEditCancel);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity());

        /**Selected course Id information is stored in main activity**/
        id = activity.courseID;
        /**Get the values of selected course**/
        courseValues = dataBaseHelper.findCourse(id);

        switch (courseValues[4]){
            case "Mon":
                editSpinner.setSelection(0);
                break;
            case "Tue":
                editSpinner.setSelection(1);
                break;
            case "Wed":
                editSpinner.setSelection(2);
                break;
            case "Thu":
                editSpinner.setSelection(3);
                break;
            case "Fri":
                editSpinner.setSelection(4);
                break;
        }

        txtCourseCode.setText(courseValues[1]);
        txtCourseName.setText(courseValues[2]);
        txtProfName.setText(courseValues[3]);
        txtStartTime.setText(courseValues[5]);
        txtEndTime.setText(courseValues[6]);
        txtClassRoom.setText(courseValues[7]);
        txtNote.setText(courseValues[8]);

        initialStartTime = Integer.parseInt(txtStartTime.getText().toString().trim());
        initialEndTime = Integer.parseInt(txtEndTime.getText().toString().trim());

        editSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                position = editSpinner.getSelectedItemPosition();
                selectedDay = (String) editSpinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /**Get the values of selected course**/
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity());
                String errorMessage = "";
                boolean timeOverlap = false;
                try {
                    /**When one or more required input field is empty**/
                    if(txtCourseCode.getText().toString().trim().equals("")
                            || txtCourseName.getText().toString().trim().equals("")
                            || txtProfName.toString().trim().equals("")
                            || txtStartTime.getText().toString().trim().equals("")
                            || txtEndTime.getText().toString().trim().equals(""))
                    {
                        errorMessage += "Enter Every Course Information\n";
                    }

                    int newStartTime = Integer.parseInt(txtStartTime.getText().toString().trim());
                    int newEndTime = Integer.parseInt(txtEndTime.getText().toString().trim());

                    /**Time validation execute when user modify time input**/
                    if(initialStartTime != newStartTime || initialEndTime != newEndTime){
                        /**When start time is bigger than end time**/
                        if(newStartTime > newEndTime){
                            errorMessage += "Start time is later than end time\n";
                        }

                        /**When start time and end time is out of range**/
                        if(newStartTime < 8 || newEndTime > 20){
                            errorMessage += "Enter time between 08~20\n";
                        }

                        /**This code is for detecting time overlap **/
                        List<CourseModel> records = dataBaseHelper.viewRecords();
                        if(records != null){
                            for (int i = 0; i < records.size(); i++){
                                CourseModel existingRecord = records.get(i);
                                int recordId = existingRecord.getId();
                                String day = existingRecord.getDay();
                                int startTime = existingRecord.getStart_time();
                                int endTime = existingRecord.getEnd_time();
                                /**Skip own record**/
                                if(recordId == id){
                                    continue;
                                }
                                // base : mon 10~13, mon 15~17
                                if(day.equals(selectedDay)){
                                    // 11~12
                                    if(newStartTime >= startTime && newEndTime <= endTime){
                                        timeOverlap = true;
                                    }
                                    // 9~12
                                    if(newStartTime <= startTime && newEndTime >= startTime){
                                        timeOverlap = true;
                                    }
                                    // 11~15
                                    if(newStartTime <= endTime && newEndTime >= endTime){
                                        timeOverlap = true;
                                    }
                                    // 8~16
                                    if(newStartTime <= startTime && newEndTime >= endTime){
                                        timeOverlap = true;
                                    }
                                }
                            }
                            if(timeOverlap){
                                errorMessage += "Your time is overlapping with existing course";
                            }
                        }
                    }
                    if(errorMessage.equals("")){
                        dataBaseHelper.updateRecord(id, txtCourseCode.getText().toString(), txtCourseName.getText().toString(),txtProfName.getText().toString(),selectedDay, Integer.parseInt(txtStartTime.getText().toString()),Integer.parseInt(txtEndTime.getText().toString()),txtClassRoom.getText().toString(),txtNote.getText().toString());
                        Toast.makeText(getActivity(), "Edit Course Success", Toast.LENGTH_SHORT).show();
                        activity.changeFragment(0,id);
                    }
                    else {
                        Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_LONG).show();
                    }
                }
                catch (Exception e){
                    Toast.makeText(getActivity(), "Edit Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /**Cancel button navigate user to time table page**/
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.changeFragment(0,id);
            }
        });

        /**Delete button event handler**/
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper dataBaseHelper = new DataBaseHelper(getActivity());
                try {
                    dataBaseHelper.deleteRecord(id);
                    Toast.makeText(getActivity(), "Delete Course Success", Toast.LENGTH_SHORT).show();
                    activity.changeFragment(0,id);
                }
                catch (Exception e){
                    Toast.makeText(getActivity(), "Delete Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}