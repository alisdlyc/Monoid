package com.example.booksshareapplication.NewBooks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.booksshareapplication.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class mRvNewbooksAdapter extends RecyclerView.Adapter<mRvNewbooksAdapter.LinearViewHolder> {
    private Context mContext;
    public mRvNewbooksAdapter(Context context){
        this.mContext=context;
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
//        holder.mIv_BookImage.setImageResource();
//        holder.mTv_BookTitle.setText();
//        holder.mTv_BookWriter.setText();
//        holder.mTv_BookISBN.setText();

    }

    @Override
    public int getItemCount() {
        //设置Linear Layout的长度
        //也就是最多的书本数目
        return 30;
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
