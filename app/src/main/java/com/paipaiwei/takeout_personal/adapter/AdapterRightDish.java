package com.paipaiwei.takeout_personal.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.paipaiwei.takeout_personal.R;
import com.paipaiwei.takeout_personal.bean.ModelDish;
import com.paipaiwei.takeout_personal.bean.ModelShopCart;
import com.paipaiwei.takeout_personal.interfaces.ShopCartInterface;


import java.util.ArrayList;

/**
 * Created by cheng on 16-11-10.
 */
public class AdapterRightDish extends RecyclerView.Adapter {


    private Context mContext;
    private ArrayList<ModelDish> mMenuList;
    private int mItemCount;
    private ModelShopCart mModelShopCart;
    private ShopCartInterface shopCartImp;

    public AdapterRightDish(Context mContext, ArrayList<ModelDish> mMenuList, ModelShopCart modelShopCart){
        this.mContext = mContext;
        this.mMenuList = mMenuList;
        this.mItemCount = mMenuList.size();
        this.mModelShopCart = modelShopCart;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.right_dish_item1, parent, false);
            DishViewHolder viewHolder = new DishViewHolder(view);
            return viewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

            final DishViewHolder dishholder = (DishViewHolder) holder;
            if (dishholder != null) {

                final ModelDish modelDish = mMenuList.get(position);
                dishholder.right_dish_name_tv.setText(modelDish.getDishName());
                dishholder.right_dish_price_tv.setText(modelDish.getDishPrice()+"");
                dishholder.right_dish_layout.setContentDescription(position+"");




                int count = 0;
                if(mModelShopCart.getShoppingSingleMap().containsKey(modelDish)){
                    count = mModelShopCart.getShoppingSingleMap().get(modelDish);
                }



                if(count<=0){
                    dishholder.right_dish_remove_iv.setVisibility(View.GONE);
                    dishholder.right_dish_account_tv.setVisibility(View.GONE);
                }else {
                    dishholder.right_dish_remove_iv.setVisibility(View.VISIBLE);
                    dishholder.right_dish_account_tv.setVisibility(View.VISIBLE);
                    dishholder.right_dish_account_tv.setText(count+"");
                }
                dishholder.right_dish_add_iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(mModelShopCart.addShoppingSingle(modelDish)) {
                            notifyItemChanged(position);
                            if(shopCartImp!=null)
                                shopCartImp.add(view,position);
                        }
                    }
                });

                dishholder.right_dish_remove_iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(mModelShopCart.subShoppingSingle(modelDish)){
                            notifyItemChanged(position);
                            if(shopCartImp!=null)
                                shopCartImp.remove(view,position);
                        }
                    }
                });
            }
        }








    @Override
    public int getItemCount() {
        return mItemCount;
    }

    public ShopCartInterface getShopCartInterface() {
        return shopCartImp;
    }

    public void setShopCartInterface(ShopCartInterface shopCartImp) {
        this.shopCartImp = shopCartImp;
    }



    private class DishViewHolder extends RecyclerView.ViewHolder{
        private TextView right_dish_name_tv;
        private TextView right_dish_price_tv;
        private LinearLayout right_dish_layout;
        private ImageView right_dish_remove_iv;
        private ImageView right_dish_add_iv;
        private TextView right_dish_account_tv;

        public DishViewHolder(View itemView) {
            super(itemView);
            right_dish_name_tv = (TextView)itemView.findViewById(R.id.right_dish_name);
            right_dish_price_tv = (TextView)itemView.findViewById(R.id.right_dish_price);
            right_dish_layout = (LinearLayout)itemView.findViewById(R.id.right_dish_item);
            right_dish_remove_iv = (ImageView)itemView.findViewById(R.id.right_dish_remove);
            right_dish_add_iv = (ImageView)itemView.findViewById(R.id.right_dish_add);
            right_dish_account_tv = (TextView) itemView.findViewById(R.id.right_dish_account);
        }

    }
}
