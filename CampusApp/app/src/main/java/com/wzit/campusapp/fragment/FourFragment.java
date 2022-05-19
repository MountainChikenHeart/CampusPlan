package com.wzit.campusapp.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.Toolbar;

import com.wzit.campusapp.R;
import com.wzit.campusapp.activity.course.Course;
import com.wzit.campusapp.activity.course.DatabaseHelper;
import com.wzit.campusapp.base.BaseFragment;
import com.wzit.campusapp.utils.common.CommonUtils;
import com.wzit.campusapp.view.IconFontTextView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 课程
 */
@SuppressLint("NonConstantResourceId")
public class FourFragment extends BaseFragment {

    @BindView(R.id.iv_top)
    ImageView ivTop;
    @BindView(R.id.iv_top2)
    ImageView ivTop2;
    @BindView(R.id.ic_back)
    IconFontTextView icBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ic_more)
    IconFontTextView icMore;
    @BindView(R.id.left_view_layout)
    LinearLayout leftViewLayout;

    @BindView(R.id.monday)
    RelativeLayout dayIdMonday;
    @BindView(R.id.tuesday)
    RelativeLayout dayIdTuesday;
    @BindView(R.id.wednesday)
    RelativeLayout dayIdWednesday;
    @BindView(R.id.thursday)
    RelativeLayout dayIdThursday;
    @BindView(R.id.friday)
    RelativeLayout dayIdFriday;
    @BindView(R.id.saturday)
    RelativeLayout dayIdSaturday;
    @BindView(R.id.weekday)
    RelativeLayout dayIdWeekday;


    //工具条
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    //星期几
    private RelativeLayout day;

    //SQLite Helper类
    private DatabaseHelper databaseHelper;

    //被点击的View
    View ClickedView;
    int currentCoursesNumber = 0;
    int maxCoursesNumber = 0;

    /**
     * 加载布局
     */
    @Override
    protected int setLayoutId() {
        return R.layout.fragment_four;
    }

    /**
     * 初始化
     */
    @Override
    protected void init() {
        icBack.setVisibility(View.GONE);
        tvTitle.setText("课程");
        icMore.setVisibility(View.GONE);
        ((AppCompatActivity)activity).setSupportActionBar(toolbar);

        String databaseName= CommonUtils.getUserInfo().getUserNum();
        databaseHelper = new DatabaseHelper(getActivity(), databaseName+".db", null, 1);

        //从数据库读取数据
        loadData();

        Course course = new Course();
        course.setEnd(10);
        createLeftView(course);
    }

    //从数据库加载数据
    @SuppressLint("Range")
    private void loadData() {
        ArrayList<Course> coursesList = new ArrayList<>(); //课程列表
        System.err.println(databaseHelper);
        System.err.println(activity);
        SQLiteDatabase sqLiteDatabase =  databaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from courses", null);
        if (cursor.moveToFirst()) {
            do {
                coursesList.add(new Course(
                        cursor.getString(cursor.getColumnIndex("course_name")),
                        cursor.getString(cursor.getColumnIndex("teacher")),
                        cursor.getString(cursor.getColumnIndex("class_room")),
                        cursor.getInt(cursor.getColumnIndex("day")),
                        cursor.getInt(cursor.getColumnIndex("class_start")),
                        cursor.getInt(cursor.getColumnIndex("class_end"))));
            } while(cursor.moveToNext());
        }
        cursor.close();

        //使用从数据库读取出来的课程信息来加载课程表视图
        for (Course course : coursesList) {
            createLeftView(course);
            createItemCourseView(course);
        }
    }

    //保存数据到数据库
    private void saveData(Course course) {
        SQLiteDatabase sqLiteDatabase =  databaseHelper.getWritableDatabase();
        sqLiteDatabase.execSQL
                ("insert into courses(course_name, teacher, class_room, day, class_start, class_end) " + "values(?, ?, ?, ?, ?, ?)",
                        new String[] {course.getCourseName(),
                                course.getTeacher(),
                                course.getClassRoom(),
                                course.getDay()+"",
                                course.getStart()+"",
                                course.getEnd()+""}
                );
    }

    //创建"第几节数"视图
    private void createLeftView(Course course) {
        int endNumber = course.getEnd();
        if (endNumber > maxCoursesNumber) {
            for (int i = 0; i < endNumber-maxCoursesNumber; i++) {
                View view = LayoutInflater.from(activity).inflate(R.layout.left_view, null);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(110,180);
                view.setLayoutParams(params);

                TextView text = view.findViewById(R.id.class_number_text);
                text.setText(String.valueOf(++currentCoursesNumber));

                leftViewLayout.addView(view);
            }
            maxCoursesNumber = endNumber;
        }
    }

    //获得控件里面的星期几控件
    private RelativeLayout getViewDay(int day){
        switch (day) {
            case 1: return dayIdMonday;
            case 2: return dayIdTuesday;
            case 3: return dayIdThursday;
            case 4: return dayIdWednesday;
            case 5: return dayIdFriday;
            case 6: return dayIdSaturday;
            case 7: return dayIdWeekday;
        }
        return null;
    }

    //创建单个课程视图
    private void createItemCourseView(final Course course) {
        int getDay = course.getDay();
        if ((getDay < 1 || getDay > 7) || course.getStart() > course.getEnd())
            Toast.makeText(activity, "星期几没写对,或课程结束时间比开始时间还早~~", Toast.LENGTH_LONG).show();
        else {

            day = getViewDay(getDay);

            int height = 180;
            final View v = LayoutInflater.from(activity).inflate(R.layout.course_card, null); //加载单个课程布局
            v.setY(height * (course.getStart()-1)); //设置开始高度,即第几节课开始
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams
                    (ViewGroup.LayoutParams.MATCH_PARENT,(course.getEnd()-course.getStart()+1)*height - 8); //设置布局高度,即跨多少节课

            v.setLayoutParams(params);
            TextView text = v.findViewById(R.id.text_view);
            text.setText(course.getCourseName() + "\n" + course.getTeacher() + "\n" + course.getClassRoom()); //显示课程名
            day.addView(v);

            //查看课程
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ClickedView=view;
                    Intent intent = new Intent(activity, SeeCourseActivity.class);
                    intent.putExtra("seeCourse", course);
                    startActivityForResult(intent, 1);
                }
            });
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_courses:
                Intent intent = new Intent(activity, AddCourseActivity.class);
                startActivityForResult(intent, 0);
                break;


        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            Course course = (Course) data.getSerializableExtra("course");
            //创建课程表左边视图(节数)
            createLeftView(course);
            //创建课程表视图
            createItemCourseView(course);
            //存储数据到数据库
            saveData(course);
        }

        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            Course PreCourse = (Course) data.getSerializableExtra("PreCourse");
            boolean isDelete = data.getBooleanExtra("isDelete", true);


            if (isDelete) {
                ClickedView.setVisibility(View.GONE);//先隐藏
                day = getViewDay(PreCourse.getDay());
                day.removeView(ClickedView);//再移除课程视图
                SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
                sqLiteDatabase.execSQL("delete from courses where course_name = ? and day =? and class_start=? and class_end=?",
                        new String[]{PreCourse.getCourseName(),
                                String.valueOf(PreCourse.getDay()),
                                String.valueOf(PreCourse.getStart()),
                                String.valueOf(PreCourse.getEnd())});
                Toast.makeText(activity, "删除成功", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(activity, AddCourseActivity.class);
                intent.putExtra("ReviseCourse", PreCourse);
                intent.putExtra("isRevise", true);
                startActivityForResult(intent, 2);
            }

        }

        if (requestCode == 2 && resultCode == Activity.RESULT_OK && data != null) {
            Course PreCourse = (Course) data.getSerializableExtra("PreCourse");
            Course newCourse = (Course) data.getSerializableExtra("newCourse");

            ClickedView.setVisibility(View.GONE);//先隐藏
            day = getViewDay(PreCourse.getDay());
            day.removeView(ClickedView);//再移除课程视图

            //创建课程表左边视图(节数)
            createLeftView(newCourse);
            //创建课程表视图
            createItemCourseView(newCourse);

            SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
            sqLiteDatabase.execSQL("update courses set " +
                            "course_name = ?,teacher = ?,class_room=? ,day=? ,class_start=? ,class_end =?" +
                            "where course_name = ? and day =? and class_start=? and class_end=?",
                    new String[]{newCourse.getCourseName(),
                            newCourse.getTeacher(),
                            newCourse.getClassRoom(),
                            String.valueOf(newCourse.getDay()),
                            String.valueOf(newCourse.getStart()),
                            String.valueOf(newCourse.getEnd()),
                            PreCourse.getCourseName(),
                            String.valueOf(PreCourse.getDay()),
                            String.valueOf(PreCourse.getStart()),
                            String.valueOf(PreCourse.getEnd())});
        }

    }

}
