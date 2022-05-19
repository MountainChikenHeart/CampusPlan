package com.wzit.campusapp.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.wzit.campusapp.R;
import com.wzit.campusapp.activity.course.Course;
import com.wzit.campusapp.base.BaseActivityWhite;

import butterknife.BindView;

@SuppressLint("NonConstantResourceId")
public class AddCourseActivity extends BaseActivityWhite {

    @BindView(R.id.course_name)
    EditText inputCourseName;
    @BindView(R.id.teacher_name)
    EditText inputTeacher;
    @BindView(R.id.class_room)
    EditText inputClassRoom;

    @BindView(R.id.week)
    Spinner inputDay;
    @BindView(R.id.classes_begin)
    Spinner inputStart;
    @BindView(R.id.classes_ends)
    Spinner inputEnd;

    @BindView(R.id.button)
    Button okButton;

    boolean isRevise = false;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_add_course;
    }

    @Override
    protected void init() {
        setFinishOnTouchOutside(true);

        Intent intent = getIntent();
        final Course ReviseCourse = (Course) intent.getSerializableExtra("ReviseCourse");
        isRevise=intent.getBooleanExtra("isRevise",false);

        String courseName;
        String teacher;
        String classRoom;
        String day;
        String start;
        String end;

        if(isRevise){

            inputCourseName.setText(ReviseCourse.getCourseName());
            inputClassRoom.setText(ReviseCourse.getClassRoom());
            inputTeacher.setText(ReviseCourse.getTeacher());
            setSpinnerDefaultValue(inputDay,String.valueOf(ReviseCourse.getDay()));
            setSpinnerDefaultValue(inputStart,String.valueOf(ReviseCourse.getStart()));
            setSpinnerDefaultValue(inputEnd,String.valueOf(ReviseCourse.getEnd()));



            okButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String courseName = inputCourseName.getText().toString();
                    String teacher = inputTeacher.getText().toString();
                    String classRoom = inputClassRoom.getText().toString();
                    String day = inputDay.getSelectedItem().toString();
                    String start = inputStart.getSelectedItem().toString();
                    String end = inputEnd.getSelectedItem().toString();

                    if (courseName.equals("") || day.equals("") || start.equals("") || end.equals("")) {
                        Toast.makeText(AddCourseActivity.this, "基本课程信息未填写", Toast.LENGTH_SHORT).show();
                    }

                    Course newCourse = new Course(courseName, teacher, classRoom,
                            Integer.valueOf(day), Integer.valueOf(start), Integer.valueOf(end));

                    Intent intent = new Intent();
                    intent.putExtra("PreCourse",ReviseCourse);
                    intent.putExtra("newCourse", newCourse);
                    Log.d("AddCourseActivity","我进了了");
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            });

        }else {
            okButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String courseName = inputCourseName.getText().toString();
                    String teacher = inputTeacher.getText().toString();
                    String classRoom = inputClassRoom.getText().toString();
                    String day = inputDay.getSelectedItem().toString();
                    String start = inputStart.getSelectedItem().toString();
                    String end = inputEnd.getSelectedItem().toString();

                    if (courseName.equals("") || day.equals("") || start.equals("") || end.equals("")) {
                        Toast.makeText(AddCourseActivity.this, "基本课程信息未填写", Toast.LENGTH_SHORT).show();
                    } else {
                        Course course = new Course(courseName, teacher, classRoom,
                                Integer.valueOf(day), Integer.valueOf(start), Integer.valueOf(end));
                        Intent intent = new Intent(AddCourseActivity.this, FourFragment.class);
                        intent.putExtra("course", course);

                        setResult(Activity.RESULT_OK, intent);
                        finish();
                    }
                }
            });
        }
    }

    private void setSpinnerDefaultValue(Spinner spinner, String value) {
        SpinnerAdapter apsAdapter = spinner.getAdapter();
        int size = apsAdapter.getCount();
        for (int i = 0; i < size; i++) {

            if (TextUtils.equals(value, apsAdapter.getItem(i).toString())) {
                spinner.setSelection(i,true);
                break;
            }
        }
    }
}