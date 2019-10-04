package com.example.booksshareapplication.MainPage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.example.booksshareapplication.MainPage.MyFragment.MyRegisterFragment;
import com.example.booksshareapplication.R;

public class FirstSeeActivity extends AppCompatActivity {

    public static String mBaseUrl="http://39.107.77.0:8080/web_war/api";
    private FrameLayout mFramLayout;
    private MyRegisterFragment mRsFragment;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_see);
        mFramLayout=findViewById(R.id.fl_first_see);
        //实例化
        mRsFragment=new MyRegisterFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fl_first_see,mRsFragment).commitAllowingStateLoss();

        mSharedPreferences=getSharedPreferences("BooksData",MODE_PRIVATE);
        mEditor=mSharedPreferences.edit();

    }

}
