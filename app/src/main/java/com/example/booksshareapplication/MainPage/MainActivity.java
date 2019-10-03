package com.example.booksshareapplication.MainPage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;

import com.example.booksshareapplication.BlueTooth.BlueToothActivity;
import com.example.booksshareapplication.Label.LabelActivity;
import com.example.booksshareapplication.NewBooks.NewBooksActivity;
import com.example.booksshareapplication.R;
import com.example.booksshareapplication.MainPage.TabFragment.MyTabBorrowFragment;
import com.example.booksshareapplication.MainPage.TabFragment.MyTabCommentFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ImageView mIv_drawable;
    private List<Fragment> mFragments;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLauout;
    private Button mBtn_mainpage,mBtn_label,mBtn_bluetooth,mBtn_newbooks;
    private String[] TabTitle={" "," "};
//    private SearchView mSearchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNavigationView=findViewById(R.id.home_left_Ngv);
        mTabLayout=findViewById(R.id.mid_tab);
        mViewPager=findViewById(R.id.mid_viewpager);
        mIv_drawable=findViewById(R.id.iv_top_user);
        mDrawerLauout=findViewById(R.id.draw_main);
//        mSearchView=findViewById(R.id.sv_top_search);
//
//        /*搜索图标是否显示在搜索框内*/
//        mSearchView.setIconifiedByDefault(true);
//        /*搜索框展开时是否显示提交按钮*/
//        mSearchView.setSubmitButtonEnabled(true);
//        /*将回车键设置为搜索*/
//        mSearchView.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
//        /*搜索框是否展开,false表示展开*/
//        mSearchView.setIconified(false);
//        /*提示词*/
//        mSearchView.setQueryHint("请输入要查询的书本名称");

//        onCreateOptionsMenu()

        mBtn_mainpage=findViewById(R.id.home_bot_mainpage);
        mBtn_mainpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到mainpage演示界面
                Intent intent=new Intent(MainActivity.this,MainActivity.class);
//                startActivity(intent);
            }
        });

        mBtn_label=findViewById(R.id.home_bot_label);
        mBtn_label.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到label演示界面
                Intent intent=new Intent(MainActivity.this, LabelActivity.class);
                startActivity(intent);
            }
        });

        mBtn_bluetooth=findViewById(R.id.home_bot_bluetooth);
        mBtn_bluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到bluetooth演示界面
                Intent intent=new Intent(MainActivity.this, BlueToothActivity.class);
                startActivity(intent);
            }
        });

        mBtn_newbooks=findViewById(R.id.home_bot_newbooks);
        mBtn_newbooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到newbooks演示界面
                Intent intent=new Intent(MainActivity.this, NewBooksActivity.class);
                startActivity(intent);
            }
        });

        mNavigationView.setItemIconTintList(null);

        //泛型存储Fragments
        mFragments=new ArrayList<>();
        mFragments.add(new MyTabBorrowFragment());
        mFragments.add(new MyTabCommentFragment());
        mTabLayout.setupWithViewPager(mViewPager);

        //借阅榜与评论榜切换适配器的实例化与设置
        MyTabAdapter myTabAdapter=new MyTabAdapter(getSupportFragmentManager(),0,mFragments);
        mViewPager.setAdapter(myTabAdapter);

        //点击头像时打开抽屉
        mIv_drawable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLauout.openDrawer(Gravity.LEFT);
            }
        });
    }


    //实现主页面中部Tab中Fragment的适配器
    private class MyTabAdapter extends FragmentPagerAdapter{

        private List<Fragment> list;
        public MyTabAdapter(@NonNull FragmentManager fm, int behavior,List<Fragment> list) {
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
