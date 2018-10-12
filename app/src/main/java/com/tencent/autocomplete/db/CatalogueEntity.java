package com.tencent.autocomplete.db;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by timfeng on 2018/10/12.
 */
@Entity(tableName = "catalogue")
public class CatalogueEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "cat_name")
    private String name;
}
