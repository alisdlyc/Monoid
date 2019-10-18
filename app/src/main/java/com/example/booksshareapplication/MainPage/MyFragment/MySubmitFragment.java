package com.example.booksshareapplication.MainPage.MyFragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.booksshareapplication.MainPage.MainSearchActivity;
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

public class MySubmitFragment extends Fragment {
    private EditText mEtStudentId_sm,mEtPassword_sm;
    private ImageView mIvSubmit;
    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public int IsLogin=0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_submit,container,false);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mIvSubmit=view.findViewById(R.id.submit_send);
        mEtStudentId_sm=view.findViewById(R.id.PostId_submit);
        mEtPassword_sm=view.findViewById(R.id.password_submit);
        //如果以及成功注册则从本地读取数据
        sharedPreferences= Objects.requireNonNull(getActivity()).getSharedPreferences("BooksData",MODE_PRIVATE);
        editor=sharedPreferences.edit();

        if(sharedPreferences.getBoolean("IsRegister",false)){
            mEtStudentId_sm.setText(sharedPreferences.getString("STUDENTID",""));
            mEtPassword_sm.setText(sharedPreferences.getString("PASSWORD",""));
            mIvSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //对用户名和密码进行检测
                    final OkHttpClient client = new OkHttpClient();

                    RequestBody requestBody = new FormBody.Builder()
                            .add("PostId", "1")
                            .add("name", sharedPreferences.getString("STUDENT",""))
                            .add("password", sharedPreferences.getString("PASSWORD",""))
                            .build();

                    final Request request = new Request.Builder()
                            .url("http://39.107.77.0:8080/web_war/api")
                            .post(requestBody)
                            .addHeader("Content-Type", "application/x-www-form-urlencoded")
                            .build();


                    Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Response response=client.newCall(request).execute();
                                IsLogin=Integer.parseInt(Objects.requireNonNull(Objects.requireNonNull(response.body()).string()));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    //假设为1时用户名与密码匹配，则登录成功
                    switch (IsLogin){
                        case -1:
                            Toast.makeText(getContext(),"用户名或密码错误，请重新输入",Toast.LENGTH_SHORT).show();
                            break;
                        case 1:
                            //成功登录之后记录登录信息，在下次打开App的时候直接跳转到搜索界面
                            editor.putBoolean("LoginSuccess",true).apply();
                            Intent intent=new Intent(getActivity(), MainSearchActivity.class);
                            startActivity(intent);
                            Toast.makeText(getContext(),"成功登录",Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });
        }
    }
}
