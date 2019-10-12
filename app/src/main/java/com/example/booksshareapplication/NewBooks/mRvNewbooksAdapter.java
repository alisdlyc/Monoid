package com.example.booksshareapplication.NewBooks;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    public void onBindViewHolder(@NonNull mRvNewbooksAdapter.LinearViewHolder holder, final int position) {
    //修改RecycleView布局文件中的控件

        //修改布局文件控件的值来输出所有的书籍
        //根据positon的不同，来填入不同的书籍
        holder.mIv_BookImage.setImageResource(R.mipmap.bj_4);
        holder.mTv_BookTitle.setText(mBoosData.get(position).BookName);
        holder.mTv_BookWriter.setText(mBoosData.get(position).Press);
        holder.mTv_BookISBN.setText(mBoosData.get(position).PressingYear);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"BooksName"+mBoosData.get(position).html,Toast.LENGTH_LONG).show();

            }
        });
    }


    @Override
    public int getItemCount() {
        //设置Linear Layout的长度
        //也就是最多的书本数目
        return mBoosData.size();
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
    public interface OnItemClickListener{
        void onClick(int pos);
    }

}
