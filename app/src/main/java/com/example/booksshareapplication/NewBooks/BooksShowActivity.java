package com.example.booksshareapplication.NewBooks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

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
//        mBooksData=(ArrayList<Course>)intent.getSerializableExtra("mBooksData");
        //接受intent传入的书籍
        mBooksData=(ArrayList<Course>)getIntent().getSerializableExtra("mBooksData");

//        for(int i=0;i<mBooksData.size();i++){
//            Log.e("qwq",mBooksData.get(i).BookName);
//            Log.e("qwq",mBooksData.get(i).Writer);
//        }

        //将服务器端返回的书籍数据传入适配器中
        mRv_newbooks.setAdapter(new mRvNewbooksAdapter(BooksShowActivity.this,mBooksData));
    }
}
