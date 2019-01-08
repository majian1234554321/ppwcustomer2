package com.paipaiwei.personal.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.paipaiwei.personal.R;
import com.paipaiwei.personal.bean.Main1HeadBean;
import com.paipaiwei.personal.common.utils.GlideImageLoader;

import java.util.ArrayList;




public class RvAdapter extends RecyclerView.Adapter<RvAdapter.MyViewHolder> {
    public Context context;
    public ArrayList<Main1HeadBean.TabsBean> list;

    public RvAdapter(Context context, ArrayList<Main1HeadBean.TabsBean> list ) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View contentView= LayoutInflater.from(context).inflate(R.layout.rvadapteritem,parent,false);
        return new MyViewHolder(contentView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        new GlideImageLoader().displayImage(context,list.get(position).iconUrl,holder.imageView);
        holder.tv.setText(list.get(position).text);
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

     class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        public TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}