package com.paipaiwei.personal.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import com.paipaiwei.personal.db.entity.CityHistoryModel;


import java.util.List;

@Dao
public interface CityHistoryDao {


    @Query("SELECT *  FROM cityhistorymodel ORDER BY uid DESC  ")
    List<CityHistoryModel> getAll();

//    @Query("SELECT * FROM historicalmodel WHERE uid IN (:userIds)")
//    List<HistoricalModel> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM cityhistorymodel WHERE keyword LIKE :first AND " + "date LIKE :last LIMIT 1")
    CityHistoryModel findByName(String first, String last);


    @Insert
    void insertAll(CityHistoryModel... users);

    @Delete
    void delete(CityHistoryModel user);


    @Query("DELETE FROM CityHistoryModel")
    void deleteAll();

}
