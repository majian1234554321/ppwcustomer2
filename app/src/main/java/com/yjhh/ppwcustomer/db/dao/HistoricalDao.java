package com.yjhh.ppwcustomer.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import com.yjhh.ppwcustomer.db.entity.HistoricalModel;


import java.util.List;

@Dao
public interface HistoricalDao {

    @Query("SELECT * FROM historicalmodel ORDER BY keyword DESC   LIMIT 0,10")
    List<HistoricalModel> getAll();

//    @Query("SELECT * FROM historicalmodel WHERE uid IN (:userIds)")
//    List<HistoricalModel> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM historicalmodel WHERE keyword LIKE :first AND " + "date LIKE :last LIMIT 1")
    HistoricalModel findByName(String first, String last);


    @Insert
    void insertAll(HistoricalModel... users);

    @Delete
    void delete(HistoricalModel user);


    @Query("DELETE FROM historicalmodel")
    void deleteAll();

}
