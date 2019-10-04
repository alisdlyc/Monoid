package com.example.booksshareapplication.MainPage.MyFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.booksshareapplication.MainPage.FirstSeeActivity;
import com.example.booksshareapplication.MainPage.MainActivity;
import com.example.booksshareapplication.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MySubmitFragment extends Fragment {
    private EditText mEtPostId_sm,mEtPassword_sm;
    private ImageView mIvSubmit;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_submit,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mIvSubmit=view.findViewById(R.id.submit_send);
        mEtPostId_sm=view.findViewById(R.id.PostId_submit);
        mEtPassword_sm=view.findViewById(R.id.password_submit);
        //如果以及成功注册则从本地读取数据
        sharedPreferences=getActivity().getSharedPreferences("BooksData",Context.MODE_PRIVATE);

        if(sharedPreferences.getBoolean("IsRegister",false)){
            mEtPostId_sm.setText(sharedPreferences.getString("PostId",""));
            mEtPassword_sm.setText(sharedPreferences.getString("password",""));
            mIvSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
}
