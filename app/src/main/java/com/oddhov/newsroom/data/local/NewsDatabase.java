package com.oddhov.newsroom.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by sammy on 16/10/2017.
 */

@Database(entities = {Favourite.class}, version = 1)
public abstract class NewsDatabase extends RoomDatabase {

    abstract public FavouriteDao favouriteDao();
}
