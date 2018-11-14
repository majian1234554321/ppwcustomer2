package com.yjhh.ppwcustomer.bean;

import java.io.Serializable;

public class DishBean  implements Serializable {
    public ModelDish dish;
    public int count;

    public DishBean(ModelDish dish, int count) {
        this.dish = dish;
        this.count = count;
    }

    public DishBean() {
    }
}
