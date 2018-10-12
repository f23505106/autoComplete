package com.tencent.autocomplete.db;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by timfeng on 2018/10/12.
 */
@Entity(foreignKeys = @ForeignKey(entity = AbbrEntity.class,
        parentColumns = "id",
        childColumns = "abbr_id")
        ,tableName = "detail")
public class DetailEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "phone_name")
    private String name;

    @ColumnInfo(name = "phone_number")
    private String number;

    @ColumnInfo(name = "abbr_id")
    private int abbrId;

}
