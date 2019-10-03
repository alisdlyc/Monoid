package com.example.booksshareapplication.MainPage;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.booksshareapplication.ModelTest.L;
import com.example.booksshareapplication.R;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class RegisterActivity extends AppCompatActivity {

    OkHttpClient okHttpClient=new OkHttpClient();
    private EditText mEtName,mEtPassword,mEtPostId;
    private ImageView mIvSignin;
    private static String mBaseUrl="http://39.107.77.0:8080/web_war/api";
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEtName=findViewById(R.id.name);
        mEtPostId=findViewById(R.id.PostId);
        mEtPassword=findViewById(R.id.password);
        mIvSignin=findViewById(R.id.signin);
        //本应用可获取
        mSharedPreferences=getSharedPreferences("UserData",MODE_PRIVATE);
        mEditor=mSharedPreferences.edit();


        mIvSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OkHttpClient client = new OkHttpClient();

                RequestBody requestBody = new FormBody.Builder()
                        .add("PostId", mEtPostId.getText().toString())
                        .add("name", mEtName.getText().toString())
                        .add("password", mEtPassword.getText().toString())
                        .build();

                Request request = new Request.Builder()
                        .url(mBaseUrl)
                        .post(requestBody)
                        .addHeader("Content-Type", "application/x-www-form-urlencoded")
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //将注册之后的文件保存到本地，下次自动登录
                mEditor.putString("PostId", mEtPostId.getText().toString())
                        .putString("name", mEtName.getText().toString())
                        .putString("password", mEtPassword.getText().toString())
                        .apply();
            }
        });


    }
}
