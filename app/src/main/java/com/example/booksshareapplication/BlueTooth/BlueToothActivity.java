package com.example.booksshareapplication.BlueTooth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.example.booksshareapplication.Label.LabelActivity;
import com.example.booksshareapplication.MainPage.MainActivity;
import com.example.booksshareapplication.NewBooks.BooksShowActivity;
import com.example.booksshareapplication.R;

public class BlueToothActivity extends AppCompatActivity {

    private SearchView qwq;
    private Button mBtn_mainpage,mBtn_label,mBtn_bluetooth,mBtn_newbooks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue_tooth);

        mBtn_mainpage=findViewById(R.id.home_bot_mainpage);
        mBtn_mainpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到mainpage演示界面
                Intent intent=new Intent(BlueToothActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        mBtn_label=findViewById(R.id.home_bot_label);
        mBtn_label.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到label演示界面
                Intent intent=new Intent(BlueToothActivity.this, LabelActivity.class);
                startActivity(intent);
            }
        });

        mBtn_bluetooth=findViewById(R.id.home_bot_bluetooth);
        mBtn_bluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到bluetooth演示界面
                Intent intent=new Intent(BlueToothActivity.this, BlueToothActivity.class);
//                startActivity(intent);
            }
        });

        mBtn_newbooks=findViewById(R.id.home_bot_newbooks);
        mBtn_newbooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到newbooks演示界面
                Intent intent=new Intent(BlueToothActivity.this, BooksShowActivity.class);
                startActivity(intent);
            }
        });
    }
}
