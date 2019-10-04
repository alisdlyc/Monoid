package com.example.booksshareapplication.MainPage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.booksshareapplication.BlueTooth.BlueToothActivity;
import com.example.booksshareapplication.Label.LabelActivity;
import com.example.booksshareapplication.NewBooks.NewBooksActivity;
import com.example.booksshareapplication.R;
import com.example.booksshareapplication.MainPage.MyFragment.MyTabBorrowFragment;
import com.example.booksshareapplication.MainPage.MyFragment.MyTabCommentFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    public View view;
    private double posX,curPosX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNavigationView = findViewById(R.id.home_left_Ngv);
        mDrawerLayout = findViewById(R.id.draw_main);
        mNavigationView.setItemIconTintList(null);
        mDrawerLayout.openDrawer(Gravity.LEFT);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                posX = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                curPosX = event.getX();
                break;
        }


        double moveX=posX-curPosX;

        if (moveX > 20 && moveX < 5000) {
            // mDesignClothesBackground
            // .setBackgroundResource(idClothesBackground[0]);
        }
        // 右滑
        else if (moveX < -20 && moveX > -5000) {
            // mDesignClothesBackground
            // .setBackgroundResource(idClothesBackground[1]);
            mDrawerLayout.openDrawer(Gravity.LEFT);
        }

        return false;
    }




}
