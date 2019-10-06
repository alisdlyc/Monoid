package com.example.booksshareapplication.MainPage.MyFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.booksshareapplication.R;

import java.io.IOException;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;
import static com.example.booksshareapplication.MainPage.FirstSeeActivity.mBaseUrl;

public class MyRegisterFragment extends Fragment {

    private EditText mEtName_rs,mEtPassword_rs,mEtStudentId_rs,mEtDepartment_rs;
    private ImageView mIvSignin_rs;
    private MySubmitFragment mSmFragment;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private ImageView mSexImageView;
    private int IsMan=1;
    public int status;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_register,container,false);

//        if(true){
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mEtName_rs=view.findViewById(R.id.name_register);
        mEtPassword_rs=view.findViewById(R.id.password_register);
        mEtStudentId_rs=view.findViewById(R.id.StudentId_register);
        mIvSignin_rs=view.findViewById(R.id.register_send);
        mSexImageView=view.findViewById(R.id.sex_selecter);
        mEtDepartment_rs=view.findViewById(R.id.department_register);
        sharedPreferences= Objects.requireNonNull(getActivity()).getSharedPreferences("BooksData",MODE_PRIVATE);
        editor=sharedPreferences.edit();

        mSexImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IsMan==1){
                    mSexImageView.setImageResource(R.mipmap.sex_woman);
                    IsMan=2;
                }else{
                    mSexImageView.setImageResource(R.mipmap.sex_man);
                    IsMan=1;
                }
            }
        });


        mIvSignin_rs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final OkHttpClient client = new OkHttpClient();

                RequestBody requestBody = new FormBody.Builder()
                        .add("PostId","2")
                        .add("NAME", mEtName_rs.getText().toString())
                        .add("PASSWORD", mEtPassword_rs.getText().toString())
                        .add("STUDENTID",mEtStudentId_rs.getText().toString())
                        .add("DEPARTMENT",mEtDepartment_rs.getText().toString())
                        .add("SEX",((Integer)IsMan).toString())
//                        .add("SEX","1")
                        .build();

                //输入合法性判断,若合法则将数据传入数据库内
                //否则弹出相应的错误提示
                if(IsLegal()){
                    final Request request = new Request.Builder()
                            .url(mBaseUrl)
                            .post(requestBody)
                            .addHeader("Content-Type", "application/x-www-form-urlencoded")
                            .build();

//
//                    try {
//
//                        //服务器返回的数据
//                        Response response = client.newCall(request).execute();
//                        status=Integer.parseInt(Objects.requireNonNull(response.body()).string());
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Response response=client.newCall(request).execute();
                                status=Integer.parseInt(Objects.requireNonNull(response.body().string()));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    switch (status){
                        case 0://未成功
                            Toast.makeText(getContext(),"注册未成功，请稍后再试",Toast.LENGTH_SHORT).show();
                            break;
                        case 1://成功注册
                            //注册完毕之后使用登录页替换注册页所在的位置
                            mSmFragment=new MySubmitFragment();
                            Objects.requireNonNull(getFragmentManager()).beginTransaction().replace(R.id.fl_first_see,mSmFragment).commitAllowingStateLoss();

                            try {
                                Response response = client.newCall(request).execute();
                                //已经成功注册
                                editor.putString("STUDENTID",mEtStudentId_rs.getText().toString())
                                        .putString("NAME",mEtName_rs.getText().toString())
                                        .putString("PASSWORD",mEtPassword_rs.getText().toString())
                                        .putBoolean("IsRegister",true)
                                        .apply();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;
                        case -1://用户名已经存在
                            Toast.makeText(getContext(),"用户名已经存在",Toast.LENGTH_SHORT).show();
                            break;
                    }

                }
            }
        });
    }

    //判断用户的输入是否合法
    private boolean IsLegal(){
        return true;
    }
}
