package com.example.booksshareapplication.BooksSearch_First;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.booksshareapplication.R;
import com.example.booksshareapplication.Util.Course;

import java.util.ArrayList;

public class BooksShowActivity extends AppCompatActivity {
    private RecyclerView mRv_newbooks;
    public Intent intent;
    public ArrayList<Course> mBooksData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_books);

        mRv_newbooks=findViewById(R.id.newbooks_center_rv);
        mRv_newbooks.setLayoutManager(new LinearLayoutManager(BooksShowActivity.this));
        //创建intent对象用于接收数据
        intent=getIntent();
        //接受intent传入的书籍
        mBooksData=(ArrayList<Course>)getIntent().getSerializableExtra("mBooksData");
        mRv_newbooks.setAdapter(new mBooksShowAdapter(BooksShowActivity.this,mBooksData));
    }


}
