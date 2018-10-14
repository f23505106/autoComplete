package com.tencent.autocomplete.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by timfeng on 2018/10/12.
 */
@Entity(foreignKeys = @ForeignKey(entity = CatalogueEntity.class,
        parentColumns = "id",
        childColumns = "cat_id",
        onDelete = ForeignKey.CASCADE)
        ,indices = {@Index(value = "cat_id",unique = true)}
        ,tableName = "abbr")

public class AbbrEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "abbr_sid")
    public int sid;//server id

    @ColumnInfo(name = "abbr_name")
    public String name;

    @ColumnInfo(name = "logo_uri")
    public String logoUri;

    @ColumnInfo(name = "cat_id")
    public int catId;

    public AbbrEntity(int sid,String name,String logoUri,int catId){
        this.sid = sid;
        this.name = name;
        this.logoUri = logoUri;
        this.catId = catId;
    }
}
