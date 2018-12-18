package com.paipaiwei.personal.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import com.paipaiwei.personal.db.entity.TakeoutOrderModel;

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
