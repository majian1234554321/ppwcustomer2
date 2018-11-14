package com.yjhh.ppwcustomer.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by cheng on 16-11-10.
 */
public class ModelDishMenu implements Serializable {
    private String menuName;
    private ArrayList<ModelDish> mModelDishList;

    public ModelDishMenu(){

    }

    public ModelDishMenu(String menuName, ArrayList dishList){
        this.menuName = menuName;
        this.mModelDishList = dishList;
    }

    public ArrayList<ModelDish> getModelDishList() {
        return mModelDishList;
    }

    public void setModelDishList(ArrayList<ModelDish> modelDishList) {
        this.mModelDishList = modelDishList;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

}
