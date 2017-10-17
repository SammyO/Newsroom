package com.oddhov.newsroom.data.local;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by sammy on 16/10/2017.
 */

@Entity
public class Favourite {
    @PrimaryKey
    private String id;

    public Favourite(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
