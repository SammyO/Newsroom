package com.oddhov.newsroom.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by sammy on 16/10/2017.
 */

@Dao
public interface FavouriteDao {

    @Query("select * from Favourite where id = :id")
    Favourite getFavouriteById(String id);

    @Insert(onConflict = REPLACE)
    void addFavourite(Favourite favourite);

    @Delete
    void deleteFavourite(Favourite favourite);
}
