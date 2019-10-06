package com.example.booksshareapplication.MainPage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import com.example.booksshareapplication.NewBooks.NewBooksActivity;
import com.example.booksshareapplication.R;
import com.example.booksshareapplication.Util.Course;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    public View view;
    private double posX,curPosX;
    private EditText mEditText;

    public MotionEvent mEvent;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    public ArrayList<Course> mBooksData;
    private ImageView mSearchIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSharedPreferences=getSharedPreferences("BooksData",MODE_PRIVATE);
        mEditor=mSharedPreferences.edit();

        mNavigationView = findViewById(R.id.home_left_Ngv);
        mDrawerLayout = findViewById(R.id.draw_main);
        mNavigationView.setItemIconTintList(null);

        mEditText=findViewById(R.id.books_search_text);
        mSearchIcon=findViewById(R.id.books_search_icon);

//        //若监听到右滑，打开左边抽屉
//        if(onTouchEvent(mEvent)){
//            mDrawerLayout.openDrawer(Gravity.LEFT);
//        }

        //点击搜索图标时，同样执行搜索操作
        mSearchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    search();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //点击键盘上的回车按钮时，收起键盘，执行search方法
        mEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode==KeyEvent.KEYCODE_ENTER){
                    //隐藏键盘
                    ((InputMethodManager)getSystemService(INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(MainActivity.this.getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                    //进行search
                    try {
                        search();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                return false;
            }
        });
    }

    private void search() throws IOException {
        String mSearchContext=mEditText.getText().toString().trim();
        if(TextUtils.isEmpty(mSearchContext)){
            //输入为空
        }else {
//        if(true){
////            调用搜索模块

            final OkHttpClient client = new OkHttpClient();

            RequestBody requestBody = new FormBody.Builder()
                    .add("PostId", "3")
                    .add("Keyword", mSearchContext)
                    .add("MaxNumber", "20")
                    .build();

            final Request request = new Request.Builder()
                    .url("http://39.107.77.0:8080/web_war/api")
                    .post(requestBody)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .build();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //获取接收到的response
                    Response response = null;
                    try {
                        response = client.newCall(request).execute();
                        String mJson= Objects.requireNonNull(response.body()).string()
                                .replace("\\","")
                                .replace("\"[","[")
                                .replace("]\"","]");
                        //使用Gson包将返回的responce转为Json串输出
                        Gson gson=new Gson();
//                        mBooksData=gson.fromJson(mJson,new TypeToken<ArrayList<Course>>(){}.getType());
                        Course course=gson.fromJson(mJson,Course.class);
                        Log.e("BooksData",course.toString());
//                        Intent intent=new Intent(MainActivity.this,NewBooksActivity.class);
////                        intent.putExtra("mBooksData",(Serializable) mBooksData);
//                        intent.putExtra("mBooksCourse", (Serializable) course);
//                        startActivity(intent);


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
//                    //response转Json
//                    try {
//                        Log.i("respose",response.body().string().replace("\\",""));
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    String mJson= null;
//                    try {
//                        mJson = Objects.requireNonNull(response.body()).string().replace("\\","");
//                    } catch (IOException e) {
////                        e.printStackTrace();
////                    }

//                    //使用Gson包将返回的responce转为Json串输出
//                    Gson gson=new Gson();
//                    mBooksData=gson.fromJson(mJson,new TypeToken<ArrayList<Course>>(){}.getType());
//                    //通过intent将mBooksData传入NewBoosActivity中
                }
            });
//            //获取接收到的response
//            Response response = client.newCall(request).execute();
//            //response转Json
//            Log.e("respose","----response----");
//            String mJson= Objects.requireNonNull(response.body()).string().replace("\\","");
//            //使用Gson包将返回的responce转为Json串输出
//            Gson gson=new Gson();
//            mBooksData=gson.fromJson(mJson,new TypeToken<ArrayList<Course>>(){}.getType());
//            //通过intent将mBooksData传入NewBoosActivity中


//            Intent intent=new Intent(MainActivity.this,NewBooksActivity.class);
//            intent.putExtra("mBooksData",(Serializable) mBooksData);
//            startActivity(intent);

        }
    }
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                posX = event.getX();
//                break;
//            case MotionEvent.ACTION_UP:
//                curPosX = event.getX();
//                break;
//        }
//
//        double moveX=posX-curPosX;
//
//        if (moveX > 20 && moveX < 5000) {
//            // mDesignClothesBackground
//            // .setBackgroundResource(idClothesBackground[0]);
//        }
//        // 右滑
//        else if(moveX < -20 && moveX > -5000) {
//            // mDesignClothesBackground
//            // .setBackgroundResource(idClothesBackground[1]);
//            return true;
//        }
//
//        return false;
//    }
}
