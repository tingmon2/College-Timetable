/**
 * @author Taekmin Jeong, Yeseom Choe
 * @Version 1.0
 * file name: CourseModel.java
 **/
package com.timeTable21.myapplication;

import java.sql.Time;

public class CourseModel {
    private int id;
    private String course_code;
    private String course_name;
    private String prof_name;
    private String day;
    private int start_time;
    private int end_time;
    private String classRoom;
    private String note;

    // for insert
    public CourseModel(int id,
                       String course_code,
                       String course_name,
                       String prof_name,
                       String day,
                       int start_time,
                       int end_time,
                       String classRoom,
                       String note) {
        this.id = id;
        this.course_code = course_code;
        this.course_name = course_name;
        this.prof_name = prof_name;
        this.day = day;
        this.start_time = start_time;
        this.end_time = end_time;
        this.classRoom = classRoom;
        this.note = note;
    }
    // for edit
    public CourseModel(String course_code,
                       String course_name,
                       String prof_name,
                       String day,
                       int start_time,
                       int end_time,
                       String classRoom,
                       String note) {

        this.course_code = course_code;
        this.course_name = course_name;
        this.prof_name = prof_name;
        this.day = day;
        this.start_time = start_time;
        this.end_time = end_time;
        this.classRoom = classRoom;
        this.note = note;
    }


    @Override
    public String toString() {
        return
                "id=" + id +
                        ", course_code :" + course_code +
                        "  course_name :" + course_name  +
                        "  prof_name :" + prof_name +
                        "  day :" + day +
                        "  start_time : " + start_time  +
                        "  end_time : " + end_time  +
                        "  classRoom :" + classRoom +
                        "  note :" + note ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getProf_name() {
        return prof_name;
    }

    public void setProf_name(String prof_name) {
        this.prof_name = prof_name;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getStart_time() {
        return start_time;
    }

    public void setStart_time(int start_time) {
        this.start_time = start_time;
    }

    public int getEnd_time() {
        return end_time;
    }

    public void setEnd_time(int end_time) {
        this.end_time = end_time;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
