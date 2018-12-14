package com.paipaiwei.takeout_personal.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.paipaiwei.takeout_personal.R;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by cheng on 16-11-10.
 */
public class AdapterLeftMenu extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<String> mMenuList;
    private int mSelectedNum;
    private List<onItemSelectedListener> mSelectedListenerList;

    public interface onItemSelectedListener {
        public void onLeftItemSelected(int postion, String menu);
    }

    public void addItemSelectedListener(onItemSelectedListener listener) {
        if (mSelectedListenerList != null)
            mSelectedListenerList.add(listener);
    }

    public void removeItemSelectedListener(onItemSelectedListener listener) {
        if (mSelectedListenerList != null && !mSelectedListenerList.isEmpty())
            mSelectedListenerList.remove(listener);
    }

    public AdapterLeftMenu(Context mContext, ArrayList<String> mMenuList) {
        this.mContext = mContext;
        this.mMenuList = mMenuList;
        this.mSelectedNum = -1;
        this.mSelectedListenerList = new ArrayList<>();
        if (mMenuList.size() > 0)
            mSelectedNum = 0;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.left_menu_item, parent, false);
        LeftMenuViewHolder viewHolder = new LeftMenuViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {

        final LeftMenuViewHolder viewHolder = (LeftMenuViewHolder) holder;
        viewHolder.menuName.setText(mMenuList.get(position));
        if (mSelectedNum == position) {
            viewHolder.menuLayout.setSelected(true);
        } else {
            viewHolder.menuLayout.setSelected(false);
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int clickPosition = position;
                setSelectPosition(clickPosition);
                notifyItemSelected(clickPosition);



            }
        });


    }

    @Override
    public int getItemCount() {
        return mMenuList.size();
    }


    public int getSelectPosition() {
        return mSelectedNum;
    }

    public void setSelectPosition(int mSelectedNum) {
        this.mSelectedNum = mSelectedNum;
        notifyDataSetChanged();

    }


    private class LeftMenuViewHolder extends RecyclerView.ViewHolder {

        TextView menuName;
        LinearLayout menuLayout;

        public LeftMenuViewHolder(final View itemView) {
            super(itemView);
            menuName = (TextView) itemView.findViewById(R.id.left_menu_textview);
            menuLayout = (LinearLayout) itemView.findViewById(R.id.left_menu_item);

        }
    }

    private void notifyItemSelected(int position) {
        if (mSelectedListenerList != null && !mSelectedListenerList.isEmpty()) {
            for (onItemSelectedListener listener : mSelectedListenerList) {
                listener.onLeftItemSelected(position, mMenuList.get(position));
            }
        }
    }
}
