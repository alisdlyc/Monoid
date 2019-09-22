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

public class NewBooksActivity extends AppCompatActivity {

    private Button mBtn_mainpage,mBtn_label,mBtn_bluetooth,mBtn_newbooks;
    private RecyclerView mRv_newbooks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_books);
        mRv_newbooks=findViewById(R.id.newbooks_center_rv);
        mRv_newbooks.setLayoutManager(new LinearLayoutManager(NewBooksActivity.this));
        mRv_newbooks.setAdapter(new mRvNewbooksAdapter(NewBooksActivity.this));

        mBtn_mainpage=findViewById(R.id.home_bot_mainpage);
        mBtn_mainpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到mainpage演示界面
                Intent intent=new Intent(NewBooksActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        mBtn_label=findViewById(R.id.home_bot_label);
        mBtn_label.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到label演示界面
                Intent intent=new Intent(NewBooksActivity.this, LabelActivity.class);
                startActivity(intent);
            }
        });

        mBtn_bluetooth=findViewById(R.id.home_bot_bluetooth);
        mBtn_bluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到bluetooth演示界面
                Intent intent=new Intent(NewBooksActivity.this, BlueToothActivity.class);
                startActivity(intent);
            }
        });

        mBtn_newbooks=findViewById(R.id.home_bot_newbooks);
        mBtn_newbooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到newbooks演示界面
                Intent intent=new Intent(NewBooksActivity.this, NewBooksActivity.class);
//                startActivity(intent);
            }
        });
    }
}
