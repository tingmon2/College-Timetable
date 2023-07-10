/**
 * @author Taekmin Jeong, Yeseom Choe
 * @Version 1.0
 * file name: AddCourseFragment.java
 **/
package com.timeTable21.myapplication;

import android.os.Bundle;

import android.content.Context;
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

public class AddCourseFragment extends Fragment {

    private Spinner addSpinner;
    private Button btnAdd, btnCancel;
    private EditText txtCourseCode, txtCourseName, txtProfName, txtStartTime, txtEndTime, txtClassRoom, txtNote;
    int position;
    String selectedDay;
    int id;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_course, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /** Connect spinner and day array by using adapter **/
        addSpinner = (Spinner) getView().findViewById(R.id.spinnerAddDay);
        ArrayAdapter<CharSequence>adapter = ArrayAdapter.createFromResource(getActivity(), R.array.Day,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        addSpinner.setAdapter(adapter);
        addSpinner.setSelection(0);

        // value of spinner
        position = addSpinner.getSelectedItemPosition();
        selectedDay = (String) addSpinner.getSelectedItem();


        txtCourseCode = (EditText) getView().findViewById(R.id.txtAddCourseCode);
        txtCourseName = (EditText) getView().findViewById(R.id.txtAddCourseName);
        txtProfName = (EditText) getView().findViewById(R.id.txtAddProfName);
        txtStartTime = (EditText) getView().findViewById(R.id.txtAddStartTime);
        txtEndTime = (EditText) getView().findViewById(R.id.txtAddEndTime);
        txtClassRoom = (EditText) getView().findViewById(R.id.txtAddClassRoom);
        txtNote = (EditText) getView().findViewById(R.id.txtAddNote);

        btnAdd = (Button) getView().findViewById(R.id.btnAdd);
        btnCancel = (Button) getView().findViewById(R.id.btnAddCancel);

        id = activity.courseID;

        addSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                position = addSpinner.getSelectedItemPosition();
                selectedDay = (String) addSpinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /**Add button clicked, insert transaction fire**/
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CourseModel courseModel;
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
                            String day = existingRecord.getDay();
                            int startTime = existingRecord.getStart_time();
                            int endTime = existingRecord.getEnd_time();

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

                    /**Insert start if there is no error message **/
                    if(errorMessage.equals("")){
                        courseModel = new CourseModel(txtCourseCode.getText().toString(), txtCourseName.getText().toString(),
                                txtProfName.getText().toString(), selectedDay, Integer.parseInt(txtStartTime.getText().toString()),
                                Integer.parseInt(txtEndTime.getText().toString()), txtClassRoom.getText().toString(), txtNote.getText().toString());
                        /**Add record**/
                        dataBaseHelper.addRecord(courseModel);
                        Toast.makeText(getActivity(), "Add Course Success", Toast.LENGTH_SHORT).show();
                        txtCourseCode.setText("");
                        txtCourseName.setText("");
                        txtProfName.setText("");
                        txtStartTime.setText("");
                        txtEndTime.setText("");
                        txtClassRoom.setText("");
                        txtNote.setText("");
                        addSpinner.setSelection(0);
                    }
                    else {
                        Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_LONG).show();
                    }

                }
                catch (Exception e){
                    Toast.makeText(getActivity(), "Add Failed", Toast.LENGTH_SHORT).show();

                }
            }
        });

        /**Clear input fields **/
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtCourseCode.setText("");
                txtCourseName.setText("");
                txtProfName.setText("");
                txtStartTime.setText("");
                txtEndTime.setText("");
                txtClassRoom.setText("");
                txtNote.setText("");
                addSpinner.setSelection(0);
            }
        });
    }
}