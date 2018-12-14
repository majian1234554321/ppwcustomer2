package com.paipaiwei.takeout_personal.db;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;
import com.paipaiwei.takeout_personal.db.dao.HistoricalDao;
import com.paipaiwei.takeout_personal.db.entity.HistoricalModel;
import com.paipaiwei.takeout_personal.db.entity.TakeoutOrderModel;

@Database(entities = {HistoricalModel.class, TakeoutOrderModel.class}, version = 2, exportSchema = false)
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


}
