package com.paipaiwei.personal.db;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;
import com.paipaiwei.personal.db.dao.CityHistoryDao;
import com.paipaiwei.personal.db.dao.HistoricalDao;
import com.paipaiwei.personal.db.entity.CityHistoryModel;
import com.paipaiwei.personal.db.entity.HistoricalModel;
import com.paipaiwei.personal.db.entity.TakeoutOrderModel;

@Database(entities = {HistoricalModel.class, TakeoutOrderModel.class, CityHistoryModel.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    private static final String DB_NAME = "UserDatabase.db";
    private static volatile AppDataBase instance;

    public static synchronized AppDataBase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static AppDataBase create(final Context context) {
        return Room.databaseBuilder(
                context,
                AppDataBase.class,
                DB_NAME).build();
    }

    public abstract HistoricalDao getHistoricalDao();

    public abstract CityHistoryDao getCityHistoryDao();


}
