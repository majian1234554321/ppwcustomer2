package com.paipaiwei.personal.db.dao;

import androidx.room.*;
import com.paipaiwei.personal.db.entity.HistoricalModel;


import java.util.List;

@Dao
public interface HistoricalDao {

    @Query("SELECT *  FROM historicalmodel ORDER BY uid DESC  ")
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
