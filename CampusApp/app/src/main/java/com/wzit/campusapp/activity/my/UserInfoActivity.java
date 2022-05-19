package com.wzit.campusapp.activity.my;

import static com.wzit.campusapp.utils.common.CommonUtils.ClearAllInfo;
import static com.wzit.campusapp.utils.common.CommonUtils.Delay3s;
import static com.wzit.campusapp.utils.common.CommonUtils.DialogShow;
import static com.wzit.campusapp.utils.common.CommonUtils.GetEditStr;
import static com.wzit.campusapp.utils.common.CommonUtils.OKJsonPost;
import static com.wzit.campusapp.utils.common.CommonUtils.SaveKey;
import static com.wzit.campusapp.utils.common.CommonUtils.SelectImage;
import static com.wzit.campusapp.utils.common.CommonUtils.TSuccess2;
import static com.wzit.campusapp.utils.common.CommonUtils.TWarning;
import static com.wzit.campusapp.utils.common.CommonUtils.TWarning2;
import static com.wzit.campusapp.utils.common.CommonUtils.getUserInfo;
import static com.wzit.campusapp.utils.common.CommonUtils.uploadFile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.kongzue.dialog.listener.OnMenuItemClickListener;
import com.kongzue.dialog.v2.BottomMenu;
import com.lcw.library.imagepicker.ImagePicker;
import com.wzit.campusapp.R;
import com.wzit.campusapp.base.BaseActivityWhite;
import com.wzit.campusapp.bean.RegisterBean;
import com.wzit.campusapp.bean.UpdateUserBean;
import com.wzit.campusapp.bean.UplaodBean;
import com.wzit.campusapp.interfaces.OkSuccessInterface;
import com.wzit.campusapp.utils.api.AppNetConfig;
import com.wzit.campusapp.view.IconFontTextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 个人信息
 */
@SuppressLint("NonConstantResourceId")
public class UserInfoActivity extends BaseActivityWhite {

    private static final int REQUEST_SELECT_IMAGES_CODE = 0x01;

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
    @BindView(R.id.head)
    CircleImageView head;
    @BindView(R.id.top1)
    RelativeLayout top1;
    @BindView(R.id.iv_line3)
    ImageView ivLine3;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.top3)
    RelativeLayout top3;
    @BindView(R.id.iv_line4)
    ImageView ivLine4;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.top4)
    RelativeLayout top4;
    @BindView(R.id.iv_line5)
    ImageView ivLine5;
    @BindView(R.id.tv_sg)
    TextView tvSg;
    @BindView(R.id.et_signature)
    EditText etSignature;
    @BindView(R.id.top5)
    RelativeLayout top5;
    @BindView(R.id.top)
    RelativeLayout top;
    @BindView(R.id.btn_save)
    TextView btnSave;
    @BindView(R.id.et_school)
    EditText etSchool;
    @BindView(R.id.iv_line6)
    ImageView ivLine6;
    @BindView(R.id.top6)
    RelativeLayout top6;
    private int sex_mark = 4;
    private String path;
    private String imgurl = "";

    @Override
    protected int setLayoutId() {
        return R.layout.activity_userinfo;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void init() {
      tvTitle.setText("编辑资料");
      icMore.setVisibility(View.GONE);
      if (getUserInfo().getUserSex() == 0){
          sex_mark = 0;
          tvSex.setText("男");
      }else if (getUserInfo().getUserSex() == 1){
          sex_mark = 1;
          tvSex.setText("女");
      }else if (getUserInfo().getUserSex() == 2){
          sex_mark = 2;
          tvSex.setText("未知");
      }
        Glide.with(activity).load(AppNetConfig.ImgApi + getUserInfo().getUserHeaderimg()).into(head);
        etUsername.setText(getUserInfo().getUserName());
        etSchool.setText(getUserInfo().getUserSchool() + "");
        etSignature.setText(getUserInfo().getUserSignature());
    }

    /**
     * 点击事件
     */
    @OnClick({R.id.head, R.id.tv_sex, R.id.top4, R.id.btn_save,R.id.ic_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.head:
                SelectImage(activity, REQUEST_SELECT_IMAGES_CODE);//选择图片
                break;
            case R.id.tv_sex:
                getSex();
                break;
            case R.id.top4:
                break;
            case R.id.btn_save:
                SaveUserIno();
                break;
            case R.id.ic_back:
                finish();
                break;
        }
    }

    /**
     * 保存
     */
    private void SaveUserIno() {

        //获取文本框中的内容
        String name = GetEditStr(etUsername);
        String signature = GetEditStr(etSignature);
        String school = GetEditStr(etSchool);

        //判断
        if (signature.isEmpty() || name.isEmpty() || sex_mark == 4) {
            TWarning2("请完善信息");
        } else {
            //设置参数
            HashMap<Object, Object> map = new HashMap<>();
            map.put("userName", name);
            map.put("userSex", sex_mark);
            map.put("userSchool", school);
            map.put("userSignature",signature);
            map.put("userHeaderimg",imgurl);
            OKJsonPost(activity, new Gson().toJson(map), AppNetConfig.getUserUpdate, new OkSuccessInterface() {
                @Override
                public void OnSuccess(String json) {
                    UpdateUserBean bean = new Gson().fromJson(json, UpdateUserBean.class);
                    if (bean.getCode() == 1){
                        TSuccess2("保存成功");
                        SaveKey("USER", "user", json);
                        return;
                    }
                    TWarning2("保存失败");
                }
            });
        }
    }

    /**
     * 性别选择
     */
    private void getSex() {
        List<String> list = new ArrayList<>();
        list.add("男");
        list.add("女");
        list.add("未知");
        BottomMenu.show((AppCompatActivity) activity, list, new OnMenuItemClickListener() {
            @Override
            public void onClick(String text, int index) {
                tvSex.setText(text);
                sex_mark = index;
            }
        }, true);
    }

    /**
     * 选择图片回调
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        if (requestCode == REQUEST_SELECT_IMAGES_CODE) {
            List<String> imagePaths = data.getStringArrayListExtra(ImagePicker.EXTRA_SELECT_IMAGES);
            path = imagePaths.get(0);//图片路径
            uploadFile(activity, path, AppNetConfig.upload, new OkSuccessInterface() {
                @Override
                public void OnSuccess(String json) {
                    UplaodBean bean = new Gson().fromJson(json, UplaodBean.class);
                    if (bean.getCode() == 200) {
                        imgurl = bean.getFileName();
                        Glide.with(activity).load(path).into(head);//把选中的图片显示出来
                    } else {
                        TWarning2("图片上传失败");
                    }
                }
            });
        }
    }
}
