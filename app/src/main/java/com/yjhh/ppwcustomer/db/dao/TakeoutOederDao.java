package com.yjhh.ppwcustomer.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import com.yjhh.ppwcustomer.db.entity.HistoricalModel;
import com.yjhh.ppwcustomer.db.entity.TakeoutOrderModel;

import java.util.List;

@Dao
public interface TakeoutOederDao {

    @Query("SELECT * FROM takeoutordermodel")
    List<TakeoutOrderModel> getAll();


    @Query("SELECT restaurantId = restaurantid FROM takeoutordermodel")
    List<TakeoutOrderModel> getAllBYId(String restaurantId);

//    @Query("SELECT * FROM historicalmodel WHERE uid IN (:userIds)")
//    List<HistoricalModel> loadAllByIds(int[] userIds);


    @Insert
    void insertAll(TakeoutOrderModel... users);

    @Delete
    void delete(TakeoutOrderModel user);


    @Query("DELETE FROM TakeoutOrderModel")
    void deleteAll();

}
