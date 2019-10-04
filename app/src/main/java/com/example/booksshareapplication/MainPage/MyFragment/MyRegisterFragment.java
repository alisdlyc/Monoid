package com.example.booksshareapplication.MainPage.MyFragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.booksshareapplication.R;

import java.io.IOException;

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

    private EditText mEtName_rs,mEtPassword_rs,mEtPostId_rs;
    private ImageView mIvSignin_rs;
    private MySubmitFragment mSmFragment;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_register,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mEtName_rs=view.findViewById(R.id.name_register);
        mEtPassword_rs=view.findViewById(R.id.password_register);
        mEtPostId_rs=view.findViewById(R.id.PostId_register);
        mIvSignin_rs=view.findViewById(R.id.register_send);
        sharedPreferences=getActivity().getSharedPreferences("BooksData",MODE_PRIVATE);
        editor=sharedPreferences.edit();

        mIvSignin_rs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClient client = new OkHttpClient();
//
                RequestBody requestBody = new FormBody.Builder()
                        .add("PostId", mEtPostId_rs.getText().toString())
                        .add("name", mEtName_rs.getText().toString())
                        .add("password", mEtPassword_rs.getText().toString())
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
                mSmFragment=new MySubmitFragment();
                getFragmentManager().beginTransaction().replace(R.id.fl_first_see,mSmFragment).commitAllowingStateLoss();

                //判断是否成功注册
                editor.putString("PostId",mEtPostId_rs.getText().toString())
                        .putString("name",mEtName_rs.getText().toString())
                        .putString("password",mEtPassword_rs.getText().toString())
                        .apply();
                editor.putBoolean("IsRegister",true).apply();
            }
        });
    }


}
