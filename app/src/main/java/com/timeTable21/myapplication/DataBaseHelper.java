/**
 * @author Taekmin Jeong, Yeseom Choe
 * @Version 1.0
 * file name: DataBaseHelper.java
 **/
package com.timeTable21.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String COURSE_TABLE = "COURSE_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_COURSE_CODE = "COURSE_CODE";
    public static final String COLUMN_COURSE_NAME = "COURSE_NAME";
    public static final String COLUMN_PROF_NAME = "PROF_NAME";
    public static final String COLUMN_DAY = "DAY";
    public static final String COLUMN_START_TIME = "START_TIME";
    public static final String COLUMN_END_TIME = "END_TIME";
    public static final String COLUMN_CLASSROOM = "CLASSROOM";
    public static final String COLUMN_NOTE = "NOTE";


    public DataBaseHelper(@Nullable Context context) {
        super(context,"course.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createStudentTable = "CREATE TABLE " + COURSE_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_COURSE_CODE + " TEXT," + COLUMN_COURSE_NAME + " TEXT," + COLUMN_PROF_NAME + " TEXT," + COLUMN_DAY + " TEXT,"+ COLUMN_START_TIME + " INTEGER,"+ COLUMN_END_TIME + " INTEGER," + COLUMN_CLASSROOM + " TEXT," + COLUMN_NOTE + " TEXT)";

        db.execSQL(createStudentTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + COURSE_TABLE);
        onCreate(db);
    }

    public void addRecord(CourseModel courseModel){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(COLUMN_COURSE_CODE,courseModel.getCourse_code());
        cv.put(COLUMN_COURSE_NAME,courseModel.getCourse_name());
        cv.put(COLUMN_PROF_NAME,courseModel.getProf_name());
        cv.put(COLUMN_DAY,courseModel.getDay());
        cv.put(COLUMN_START_TIME, courseModel.getStart_time());
        cv.put(COLUMN_END_TIME, courseModel.getEnd_time());
        cv.put(COLUMN_CLASSROOM,courseModel.getClassRoom());
        cv.put(COLUMN_NOTE,courseModel.getNote());

        db.insert(COURSE_TABLE, null, cv);

        db.close();
    }

    public String[] findCourse(int ID){
        String queryString = "SELECT * FROM " + COURSE_TABLE + " WHERE ID = " + ID;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        cursor.moveToFirst();
        String courseValues[] = new String[9];
        courseValues[0] = cursor.getString(0); //id
        courseValues[1] = cursor.getString(1); //code
        courseValues[2] = cursor.getString(2); //name
        courseValues[3] = cursor.getString(3); //prof name
        courseValues[4] = cursor.getString(4); //day
        courseValues[5] = cursor.getString(5); //start time
        courseValues[6] = cursor.getString(6); //end time
        courseValues[7] = cursor.getString(7); //classroom
        courseValues[8] = cursor.getString(8); //note

        return courseValues;
    }


    public List<CourseModel> viewRecords(){

        List<CourseModel> viewList = new ArrayList<>();

        String queryString = "SELECT * FROM " + COURSE_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst())
        {
            do {
                int id = cursor.getInt(0);
                String course_code = cursor.getString(1);
                String course_name = cursor.getString(2);
                String prof_name = cursor.getString(3);
                String day = cursor.getString(4);
                int start_time = cursor.getInt(5);
                int end_time = cursor.getInt(6);
                String classRoom = cursor.getString(7);
                String note = cursor.getString(8);

                CourseModel courseModel = new CourseModel(id,course_code,course_name,prof_name,day,start_time,end_time,classRoom,note);
                viewList.add(courseModel);

            }while (cursor.moveToNext());
        }
        return viewList;
    }

    public void updateRecord(int ID, String course_code, String course_name,String prof_name,String day, int start_time, int end_time, String classRoom,String note) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_COURSE_CODE, course_code);
        values.put(COLUMN_COURSE_NAME, course_name);
        values.put(COLUMN_PROF_NAME, prof_name);
        values.put(COLUMN_DAY, day);
        values.put(COLUMN_START_TIME, start_time);
        values.put(COLUMN_END_TIME, end_time);
        values.put(COLUMN_CLASSROOM, classRoom);
        values.put(COLUMN_NOTE, note);

        String where = COLUMN_ID +"= ?";
        String[] whereArgs = {String.valueOf(ID)};

        db.update(COURSE_TABLE,values,where,whereArgs);
        db.close();
    }

    public void deleteRecord(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(COURSE_TABLE, "ID=?", new String[]{String.valueOf(id)});
        db.close();
    }
}
