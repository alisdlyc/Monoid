package com.example.booksshareapplication.MainPage;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
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


public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    public View view;
    private float PosX,curPosX;
    private EditText mEditText;

    public MotionEvent mEvent;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    public ArrayList<Course> mBooksData;
    private ImageView mSearchIcon;
    private ImageView LoadingBooks;

    public String mSearchContext;
    private Toast mToast;
    private DrawerLayout mDrawlayout;


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
        LoadingBooks=findViewById(R.id.Loading_books);

        mDrawerLayout=findViewById(R.id.draw_main);
        mDrawerLayout.setOnTouchListener(this);



//        if(onTouchEvent(mEvent))
//            mDrawerLayout.openDrawer(Gravity.LEFT);
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

        mSearchContext=mEditText.getText().toString().trim();
        if(TextUtils.isEmpty(mSearchContext)){
            //输入为空
        }else {

            if(mToast==null){
                mToast=Toast.makeText(MainActivity.this,"查询中...",Toast.LENGTH_LONG);
            }else {
                //用户频繁点击查询按钮
                mToast.setText("查询中");
            }
            mToast.show();

            new GetBooksInfo().start();
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            PosX=event.getX();
        }

        if (event.getAction()==MotionEvent.ACTION_UP){
            curPosX=event.getX();
        }

        if(curPosX-PosX>350){
            mDrawerLayout.openDrawer(Gravity.LEFT);
        }
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private class GetBooksInfo extends Thread{
        @Override
        public void run() {
            super.run();

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

            Response response = null;
            try {
                response = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                String mJson = Objects.requireNonNull(response.body()).string()
                        .replace("\\","")
                        .replace("\"[","[")
                        .replace("]\"","]");
                //将返回的response数据标准json格式化
                mBooksData=function(mJson);

                Intent intent=new Intent(MainActivity.this, BooksShowActivity.class);
                intent.putExtra("mBooksData",(Serializable) mBooksData);
                startActivity(intent);

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }


        }
    }


    public ArrayList<Course> function(String json) throws JSONException {
        JSONObject obj = new JSONObject(json);
        JSONArray Books = obj.getJSONArray("Books");

        ArrayList<Course> data = new ArrayList<>();

        for (int i = 0; i < Books.length(); i++) {
            Course temp = new Course();
            JSONObject jsonObject = Books.getJSONObject(i);
//            temp.Area = jsonObject.getString("Area");
            temp.BookName = jsonObject.getString("BookName");
//            temp.IndexNumber = jsonObject.getString("indexNumber");
//            temp.Writer = jsonObject.getString("Writer");
//            temp.WriterInfo = jsonObject.getString("WriterInfo");
            temp.Press = jsonObject.getString("Press");
            temp.PressingYear = jsonObject.getString("PressingYear");
//            temp.BorringTimes = jsonObject.getString("BorrowingTimes");
//            temp.Department = jsonObject.getString("Department");
//            temp.Status = jsonObject.getString("Status");
//            temp.Floor = jsonObject.getString("Floor");
//            temp.Shelf = jsonObject.getString("Shelf");
//            temp.ShelfFloor = jsonObject.getString("ShelfFloor");
//            temp.DefaultComment = jsonObject.getString("DefaultComment");
//            temp.Star = jsonObject.getString("Star");
            temp.html=jsonObject.getString("html");

            data.add(temp);
        }

        return data;
    }
}
