package com.example.booksshareapplication.BooksSearch_Sec;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.booksshareapplication.R;
import com.example.booksshareapplication.Util.BooksInfoCourse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class mBooksInfoAdapter extends RecyclerView.Adapter<mBooksInfoAdapter.LinearViewHolder> {
    private Context mContext;
    public ArrayList<BooksInfoCourse> mBooksInfo;

    public mBooksInfoAdapter(Context context, ArrayList<BooksInfoCourse> mBooksInfo){
        this.mContext=context;
        this.mBooksInfo=mBooksInfo;
    }

    @NonNull
    @Override
    public mBooksInfoAdapter.LinearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //传入需要的布局文件
       return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_rv_mbooksinfo,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull LinearViewHolder holder, final int position) {
    //修改RecycleView布局文件中的控件
        //修改布局文件控件的值来输出所有的书籍
        //根据positon的不同，来填入不同的书籍
        holder.mIv_BookImage.setImageResource(R.mipmap.bj_4);
        holder.mTvBookName.setText(mBooksInfo.get(position).BookName);
        holder.mTvindexNumber.setText(mBooksInfo.get(position).IndexNumber);
        holder.mTvWriter.setText(mBooksInfo.get(position).Writer);
        holder.mTvWriterInfo.setText(mBooksInfo.get(position).WriterInfo);
        holder.mTvPress.setText(mBooksInfo.get(position).Press);
        holder.mTvPressingYear.setText(mBooksInfo.get(position).PressingYear);
        holder.mTvBorrowingTimes.setText("当前已被借阅"+mBooksInfo.get(position).BorringTimes+"次");
        holder.mTvStatus.setText(mBooksInfo.get(position).Status);
        holder.mTvDepartment.setText(mBooksInfo.get(position).Department);
        holder.mTvFloor.setText(mBooksInfo.get(position).Floor);
        holder.mTvArea.setText(mBooksInfo.get(position).Area);
        holder.mTvShelf.setText(mBooksInfo.get(position).Shelf);
        holder.mTvShelfFloor.setText(mBooksInfo.get(position).ShelfFloor);
        holder.mTvDefaultComment.setText(mBooksInfo.get(position).DefaultComment);
        holder.mTvStar.setText(mBooksInfo.get(position).Star);

        //设置点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"BooksName"+ mBooksInfo.get(position).html,Toast.LENGTH_LONG).show();
            }
        });
    }

    public ArrayList<BooksInfoCourse> function(String json) throws JSONException {
        JSONObject obj = new JSONObject(json);
        JSONArray Books = obj.getJSONArray("Books");

        ArrayList<BooksInfoCourse> data = new ArrayList<>();

        for (int i = 0; i < Books.length(); i++) {
            BooksInfoCourse temp = new BooksInfoCourse();
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
            temp.html=jsonObject.getString("html");
            data.add(temp);
        }

        return data;
    }


    @Override
    public int getItemCount() {
        //设置Linear Layout的长度
        //也就是最多的书本数目
        return mBooksInfo.size();
    }

    class LinearViewHolder extends RecyclerView.ViewHolder{
        private ImageView mIv_BookImage;
        private TextView mTvBookName,mTvindexNumber,mTvWriter,mTvWriterInfo,mTvPress
                ,mTvPressingYear,mTvBorrowingTimes,mTvStatus,mTvDepartment,mTvFloor
                ,mTvArea,mTvShelf,mTvShelfFloor,mTvDefaultComment,mTvStar;

        public LinearViewHolder (View itemView){
            super(itemView);
            mIv_BookImage=itemView.findViewById(R.id.rv_newbooks_image);

            mTvBookName=itemView.findViewById(R.id.rv_booksinfo_BookName);
            mTvindexNumber=itemView.findViewById(R.id.rv_booksinfo_indexNumber);
            mTvWriter=itemView.findViewById(R.id.rv_booksinfo_Writer);
            mTvWriterInfo=itemView.findViewById(R.id.rv_booksinfo_WriterInfo);
            mTvPress=itemView.findViewById(R.id.rv_booksinfo_Press);
            mTvPressingYear=itemView.findViewById(R.id.rv_booksinfo_PressingYear);
            mTvBorrowingTimes=itemView.findViewById(R.id.rv_booksinfo_BorrowingTimes);
            mTvStatus=itemView.findViewById(R.id.rv_booksinfo_Status);
            mTvDepartment=itemView.findViewById(R.id.rv_booksinfo_Department);
            mTvFloor=itemView.findViewById(R.id.rv_booksinfo_Floor);
            mTvArea=itemView.findViewById(R.id.rv_booksinfo_Area);
            mTvShelf=itemView.findViewById(R.id.rv_booksinfo_Shelf);
            mTvShelfFloor=itemView.findViewById(R.id.rv_booksinfo_ShelfFloor);
            mTvDefaultComment=itemView.findViewById(R.id.rv_booksinfo_DefaultComment);
            mTvStar=itemView.findViewById(R.id.rv_booksinfo_Star);
        }
    }
    public interface OnItemClickListener{
        void onClick(int pos);
    }

}
