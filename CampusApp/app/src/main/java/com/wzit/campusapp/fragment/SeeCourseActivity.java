package com.wzit.campusapp.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wzit.campusapp.R;
import com.wzit.campusapp.activity.course.Course;
import com.wzit.campusapp.base.BaseActivityWhite;

import butterknife.BindView;

@SuppressLint("NonConstantResourceId")
public class SeeCourseActivity extends BaseActivityWhite {

    @BindView(R.id.see_course_name)
    TextView seeCourseName;
    @BindView(R.id.see_week)
    TextView seeDay;
    @BindView(R.id.see_classes_begin)
    TextView seeStart;
    @BindView(R.id.see_classes_ends)
    TextView seeEnd;
    @BindView(R.id.see_teacher_name)
    TextView seeTeacher;
    @BindView(R.id.see_class_room)
    TextView seeClassRoom;


    @Override
    protected int setLayoutId() {
        return R.layout.activity_see_course;
    }

    @Override
    protected void init() {
        Intent intent = getIntent();
        final Course seeCourse = (Course) intent.getSerializableExtra("seeCourse");
        seeCourseName.setText(seeCourse.getCourseName());
        seeDay.setText(String.valueOf(seeCourse.getDay()));
        seeStart.setText(String.valueOf(seeCourse.getStart()));
        seeEnd.setText(String.valueOf(seeCourse.getEnd()));
        seeTeacher.setText(seeCourse.getTeacher());
        seeClassRoom.setText(seeCourse.getClassRoom());

        Button delBtn = (Button)findViewById(R.id.btn_del);
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SeeCourseActivity.this, FourFragment.class);
                intent.putExtra("PreCourse", seeCourse);
                intent.putExtra("isDelete",true);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        //修改按钮被按下时
        Button ReviseBtn = (Button)findViewById(R.id.btn_revise);
        ReviseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SeeCourseActivity.this, FourFragment.class);
                intent.putExtra("PreCourse", seeCourse);
                intent.putExtra("isDelete",false);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("SeeCourseActivity", "修改的返回来了");
    }
}