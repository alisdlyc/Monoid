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
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import com.example.booksshareapplication.NewBooks.BooksShowActivity;
import com.example.booksshareapplication.R;
import com.example.booksshareapplication.Util.Course;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
                    .add("MaxNumber", "50")
                    .build();

            final Request request = new Request.Builder()
                    .url("http://39.107.77.0:8080/web_war/api")
                    .post(requestBody)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .build();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Response response = null;
                    try {
                        //获取接收到的response
                        response = client.newCall(request).execute();
                        //将response变为标准的json格式
                        String mJson= Objects.requireNonNull(response.body()).string()
                                .replace("\\","")
                                .replace("\"[","[")
                                .replace("]\"","]");
                        //将数据转换化为Course的Arraylist
                        mBooksData=function(mJson);
                        for(int i=0;i<mBooksData.size();i++){
//                            Log.e("qwq",mBooksData.get(i).BookName);
//                            Log.e("qwq",mBooksData.get(i).Writer);
                        }
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

//            for(int i=0;i<mBooksData.size();i++){
//                Log.e("qwq",mBooksData.get(i).BookName);
//                Log.e("qwq",mBooksData.get(i).Writer);
//            }

            //通过intent将数据传入NewBooksActivity中，并且通过适配器填充数据到RecycleView中
            Intent intent=new Intent(MainActivity.this, BooksShowActivity.class);
            intent.putExtra("mBooksData",(Serializable) mBooksData);
            startActivity(intent);
        }
    }
    //监听右滑手势，打开抽屉
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

    public ArrayList<Course> function(String json) throws JSONException {
        JSONObject obj = new JSONObject(json);
        JSONArray Books = obj.getJSONArray("Books");

        ArrayList<Course> data = new ArrayList<>();
        Course temp = new Course();

        for (int i = 0; i < Books.length(); i++) {
            JSONObject jsonObject = Books.getJSONObject(i);
            temp.Area = jsonObject.getString("Area");
            temp.BookName = jsonObject.getString("BookName");
            temp.IndexNumber = jsonObject.getString("indexNumber");
            temp.Writer = jsonObject.getString("Writer");
            temp.WriterInfo = jsonObject.getString("WriterInfo");
            temp.Press = jsonObject.getString("Press");
            temp.PressingYear = jsonObject.getString("PressingYear");
            temp.BorringTimes = jsonObject.getString("BorrowingTimes");
            temp.Department = jsonObject.getString("Department");
            temp.Status = jsonObject.getString("Status");
            temp.Floor = jsonObject.getString("Floor");
            temp.Shelf = jsonObject.getString("Shelf");
            temp.ShelfFloor = jsonObject.getString("ShelfFloor");
            temp.DefaultComment = jsonObject.getString("DefaultComment");
            temp.Star = jsonObject.getString("Star");

            data.add(temp);
        }

        return data;
    }
}
