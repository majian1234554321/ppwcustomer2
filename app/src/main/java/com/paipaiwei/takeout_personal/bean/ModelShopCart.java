package com.paipaiwei.takeout_personal.bean;

import android.util.Log;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cheng on 16-11-12.
 */
public class ModelShopCart implements Serializable {



    private int shoppingAccount;//商品总数
    private double shoppingTotalPrice;//商品总价钱
    private Map<ModelDish, Integer> shoppingSingle;//单个物品的总价价钱


    public List<ModelShopCartItem> items;

    public void setShoppingAccount(int shoppingAccount) {
        this.shoppingAccount = shoppingAccount;
    }


    public class ModelShopCartItem implements Serializable{
        public String dishName;
        public int dishCount;
        public  String dishId;
    }


    public void setShoppingTotalPrice(double shoppingTotalPrice) {
        this.shoppingTotalPrice = shoppingTotalPrice;
    }

    public void setShoppingSingle(Map<ModelDish, Integer> shoppingSingle) {
        this.shoppingSingle = shoppingSingle;
    }

    public ModelShopCart() {
        this.shoppingAccount = 0;
        this.shoppingTotalPrice = 0;
        this.shoppingSingle = new HashMap<>();
    }

    public int getShoppingAccount() {
        return shoppingAccount;
    }

    public double getShoppingTotalPrice() {
        return shoppingTotalPrice;
    }

    public Map<ModelDish, Integer> getShoppingSingleMap() {
        return shoppingSingle;
    }

    public boolean addShoppingSingle(ModelDish modelDish) {
        int remain = modelDish.getDishRemain();
        if (remain <= 0)
            return false;
        modelDish.setDishRemain(--remain);
        int num = 0;
        if (shoppingSingle.containsKey(modelDish)) {
            num = shoppingSingle.get(modelDish);
        }
        num += 1;
        shoppingSingle.put(modelDish, num);
        Log.e("TAG", "addShoppingSingle: " + shoppingSingle.get(modelDish));

        shoppingTotalPrice += modelDish.getDishPrice();
        shoppingAccount++;
        return true;
    }




    public boolean addShoppingSingle2(ModelDish modelDish) {
        int remain = modelDish.getDishRemain();
        if (remain <= 0)
            return false;
        modelDish.setDishRemain(remain);
        int num = 0;
        if (shoppingSingle.containsKey(modelDish)) {
            num = shoppingSingle.get(modelDish);
        }
        num += 1;
        shoppingSingle.put(modelDish, num);
        Log.e("TAG", "addShoppingSingle: " + shoppingSingle.get(modelDish));

        shoppingTotalPrice += modelDish.getDishPrice();
        shoppingAccount++;
        return true;
    }



    public boolean subShoppingSingle(ModelDish modelDish) {
        int num = 0;
        if (shoppingSingle.containsKey(modelDish)) {
            num = shoppingSingle.get(modelDish);
        }
        if (num <= 0) return false;
        num--;
        int remain = modelDish.getDishRemain();
        modelDish.setDishRemain(++remain);
        shoppingSingle.put(modelDish, num);
        if (num == 0) shoppingSingle.remove(modelDish);

        shoppingTotalPrice -= modelDish.getDishPrice();
        shoppingAccount--;
        return true;
    }

    public int getDishAccount() {
        return shoppingSingle.size();
    }

    public void clear() {
        this.shoppingAccount = 0;
        this.shoppingTotalPrice = 0;
        this.shoppingSingle.clear();
    }
}
