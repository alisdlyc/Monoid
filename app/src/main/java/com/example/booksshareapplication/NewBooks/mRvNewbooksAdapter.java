package com.example.booksshareapplication.NewBooks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.booksshareapplication.R;
import com.example.booksshareapplication.Util.Course;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class mRvNewbooksAdapter extends RecyclerView.Adapter<mRvNewbooksAdapter.LinearViewHolder> {
    private Context mContext;
    private ArrayList<Course> mBoosData;
    public mRvNewbooksAdapter(Context context,ArrayList<Course> mBooksData){
        this.mContext=context;
        this.mBoosData=mBooksData;
    }

    @NonNull
    @Override
    public mRvNewbooksAdapter.LinearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //传入需要的布局文件
       return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_rv_newbooks,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull mRvNewbooksAdapter.LinearViewHolder holder, int position) {
//        //修改布局文件中的控件
//        for (int i=0;)

        //如何进行activity之间的通信
        for (int i=1;i<11;i++){
//            holder.mIv_BookImage.setImageResource();
            holder.mIv_BookImage.setImageResource(R.mipmap.bj_1);
            holder.mTv_BookTitle.setText(mBoosData.get(i).BookName);
            holder.mTv_BookWriter.setText(mBoosData.get(i).Writer);
            holder.mTv_BookISBN.setText(mBoosData.get(i).IndexNumber);
        }


    }

    @Override
    public int getItemCount() {
        //设置Linear Layout的长度
        //也就是最多的书本数目
        return 10;
    }

    class LinearViewHolder extends RecyclerView.ViewHolder{
        private ImageView mIv_BookImage;
        private TextView mTv_BookTitle,mTv_BookWriter,mTv_BookISBN;

        public LinearViewHolder (View itemView){
            super(itemView);
            mIv_BookImage=itemView.findViewById(R.id.rv_newbooks_image);
            mTv_BookTitle=itemView.findViewById(R.id.rv_newbooks_title);
            mTv_BookWriter=itemView.findViewById(R.id.rv_newbooks_writer);
            mTv_BookISBN=itemView.findViewById(R.id.rv_newbooks_ISBN);
        }
    }
}
