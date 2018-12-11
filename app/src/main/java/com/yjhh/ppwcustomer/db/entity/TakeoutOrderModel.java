package com.yjhh.ppwcustomer.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class TakeoutOrderModel {

    @PrimaryKey(autoGenerate = true)
    public int uid2;

    @ColumnInfo(name = "restaurantid") //id餐厅
    public String restaurantid;

    @ColumnInfo(name = "restauranttype") //餐厅的名称
    public String restauranttype;

    @ColumnInfo(name = "dishesname") //菜品名称
    public String dishesname; //

    public TakeoutOrderModel() {
    }

    @ColumnInfo(name = "dishesid")//菜品id
    public String dishesid;

    @ColumnInfo(name = "dishesprice") //菜品价格
    public String dishesprice;

    @ColumnInfo(name = "dishescount")//菜品数量
    public int dishescount;//

    @Ignore
    public TakeoutOrderModel( String restaurantid, String restauranttype, String dishesname, String dishesid, String dishesprice, int dishescount) {

        this.restaurantid = restaurantid;
        this.restauranttype = restauranttype;
        this.dishesname = dishesname;
        this.dishesid = dishesid;
        this.dishesprice = dishesprice;
        this.dishescount = dishescount;
    }
}