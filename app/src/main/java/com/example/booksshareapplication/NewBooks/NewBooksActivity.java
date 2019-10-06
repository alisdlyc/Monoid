package com.example.booksshareapplication.NewBooks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.booksshareapplication.BlueTooth.BlueToothActivity;
import com.example.booksshareapplication.Label.LabelActivity;
import com.example.booksshareapplication.MainPage.MainActivity;
import com.example.booksshareapplication.R;
import com.example.booksshareapplication.Util.Course;

import java.util.ArrayList;

public class NewBooksActivity extends AppCompatActivity {
    private RecyclerView mRv_newbooks;
    public Intent intent;
    public ArrayList<Course> mBooksData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_books);
        mRv_newbooks=findViewById(R.id.newbooks_center_rv);
        mRv_newbooks.setLayoutManager(new LinearLayoutManager(NewBooksActivity.this));
        //创建intent对象用于接收数据
        intent=getIntent();
        mBooksData=(ArrayList<Course>)intent.getSerializableExtra("mBooksData");
        //将服务器端返回的书籍数据传入适配器中
        mRv_newbooks.setAdapter(new mRvNewbooksAdapter(NewBooksActivity.this,mBooksData));
    }
}
