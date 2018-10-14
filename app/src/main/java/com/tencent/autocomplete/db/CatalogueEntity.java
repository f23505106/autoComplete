package com.tencent.autocomplete.db;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by timfeng on 2018/10/12.
 */
@Entity(tableName = "catalogue",indices = {@Index(value = "cat_name",unique = true)})
public class CatalogueEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "cat_name")
    public String name;

    public CatalogueEntity(String name){
        this.name = name;
    }
}
