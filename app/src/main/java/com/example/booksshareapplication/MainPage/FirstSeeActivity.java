package com.example.booksshareapplication.MainPage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.booksshareapplication.MainPage.TabFragment.MyRegisterFragment;
import com.example.booksshareapplication.MainPage.TabFragment.MySubmitFragment;
import com.example.booksshareapplication.MainPage.TabFragment.MyTabBorrowFragment;
import com.example.booksshareapplication.MainPage.TabFragment.MyTabCommentFragment;
import com.example.booksshareapplication.R;

import java.util.ArrayList;
import java.util.List;

public class FirstSeeActivity extends AppCompatActivity {

    private String[] TabTitle={"登录","注册"};
    private List<Fragment> mFragments;
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_see);
        mViewPager=findViewById(R.id.fs_ViewPager);

        //泛型存储Fragments
        mFragments=new ArrayList<>();
        mFragments.add(new MyRegisterFragment());
        mFragments.add(new MySubmitFragment());

        //借阅榜与评论榜切换适配器的实例化与设置
        FirstSeeActivity.MyTabAdapter myTabAdapter=new FirstSeeActivity.MyTabAdapter(getSupportFragmentManager(),0,mFragments);
        mViewPager.setAdapter(myTabAdapter);
    }
    //实现主页面中部Tab中Fragment的适配器
    private class MyTabAdapter extends FragmentPagerAdapter {

        private List<Fragment> list;
        public MyTabAdapter(@NonNull FragmentManager fm, int behavior, List<Fragment> list) {
            //behavior设置为0时，为setUserVisibleHint
            //为1时，为setMaxLifeCycle
            super(fm, behavior);
            this.list=list;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return TabTitle[position];
        }
    }


}
