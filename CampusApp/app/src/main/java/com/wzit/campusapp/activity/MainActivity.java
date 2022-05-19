package com.wzit.campusapp.activity;

import static com.wzit.campusapp.utils.common.CommonUtils.DefaultColor;
import static com.wzit.campusapp.utils.common.CommonUtils.QuitShow;
import static com.wzit.campusapp.utils.common.CommonUtils.SetCurPoint;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.wzit.campusapp.R;
import com.wzit.campusapp.base.BaseActivityWhite;
import com.wzit.campusapp.fragment.*;
import com.wzit.campusapp.utils.common.CommonUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 主页
 */
@SuppressLint("NonConstantResourceId")
public class MainActivity extends BaseActivityWhite {


    @BindView(R.id.childrenshow)
    LinearLayout childrenshow;
    @BindView(R.id.iv_one)
    ImageView ivOne;
    @BindView(R.id.tv_one)
    TextView tvOne;
    @BindView(R.id.tab_one)
    RelativeLayout tabOne;
    @BindView(R.id.iv_two)
    ImageView ivTwo;
    @BindView(R.id.tv_two)
    TextView tvTwo;
    @BindView(R.id.tab_two)
    RelativeLayout tabTwo;
    @BindView(R.id.iv_four)
    ImageView ivFour;
    @BindView(R.id.tv_four)
    TextView tvFour;
    @BindView(R.id.tab_four)
    RelativeLayout tabFour;
    @BindView(R.id.iv_five)
    ImageView ivFive;
    @BindView(R.id.tv_five)
    TextView tvFive;
    @BindView(R.id.tab_five)
    RelativeLayout tabFive;
    @BindView(R.id.tab_bottom)
    RelativeLayout tabBottom;
    //数据
    private ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    //记录当前正在显示的Fragment
    private Fragment currentFragment;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void init() {
        activity = this;
        fragmentArrayList.add(new OneFragment());
        fragmentArrayList.add(new TwoFragment());
        fragmentArrayList.add(new FourFragment());
        fragmentArrayList.add(new FiveFragment());
        replaceFragemnt(fragmentArrayList.get(0));
        selectPoint(0);
    }

    /**
     * 再按一次退出
     */
    @Override
    public void onBackPressed() {
        QuitShow(this);
    }

    /**
     * 底部Tab切换
     */
    private void selectPoint(int i) {
        ivOne.setImageResource(R.mipmap.one_nor);
        ivTwo.setImageResource(R.mipmap.two_nor);
        ivFour.setImageResource(R.mipmap.four_nor);
        ivFive.setImageResource(R.mipmap.five_nor);

        ivOne.setColorFilter(Color.parseColor(DefaultColor));
        ivTwo.setColorFilter(Color.parseColor(DefaultColor));
        ivFour.setColorFilter(Color.parseColor(DefaultColor));
        ivFive.setColorFilter(Color.parseColor(DefaultColor));

        tvOne.setTextColor(Color.parseColor(DefaultColor));
        tvTwo.setTextColor(Color.parseColor(DefaultColor));
        tvFour.setTextColor(Color.parseColor(DefaultColor));
        tvFive.setTextColor(Color.parseColor(DefaultColor));
        if (i == 0) {
            SetCurPoint(tvOne, ivOne, R.mipmap.one_sel);
        } else if (i == 1) {
            SetCurPoint(tvTwo, ivTwo, R.mipmap.two_sel);
        } else if (i == 2) {
            SetCurPoint(tvFour, ivFour, R.mipmap.four_sel);
        } else if (i == 3) {
            SetCurPoint(tvFive, ivFive, R.mipmap.five_sel);
        }
    }

    //TODO 替换Fragment的优化
    public void replaceFragemnt(Fragment fragment) {
        //TODO 1:获得管理者
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        //TODO 2:开启事务
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        //TODO 3:替换功能
        //隐藏当前正在显示的fragment
        if (currentFragment != null) {
            fragmentTransaction.hide(currentFragment);
        }
        //判断要添加的fragment时候被添加过
        if (fragment.isAdded()) {//被添加过
            //显示传过来
            fragmentTransaction.show(fragment);
        } else {//没有添加过
            //添加传过来的
            fragmentTransaction.add(R.id.childrenshow, fragment);
        }
        //TODO 4:提交
        fragmentTransaction.commit();
        //更新当前正在显示的Fragment
        currentFragment = fragment;
    }

    /**
     * 点击事件
     */
    @OnClick({R.id.tab_one, R.id.tab_two, R.id.tab_four, R.id.tab_five})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tab_one:
                replaceFragemnt(fragmentArrayList.get(0));
                selectPoint(0);
                break;
            case R.id.tab_two:
                replaceFragemnt(fragmentArrayList.get(1));
                selectPoint(1);
                break;
            case R.id.tab_four://跳转课程
                replaceFragemnt(fragmentArrayList.get(2));
                selectPoint(2);
                break;
            case R.id.tab_five:
                replaceFragemnt(fragmentArrayList.get(3));
                selectPoint(3);
                break;
        }
    }
}