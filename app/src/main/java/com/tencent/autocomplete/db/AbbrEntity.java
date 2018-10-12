package com.tencent.autocomplete.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by timfeng on 2018/10/12.
 */
@Entity(foreignKeys = @ForeignKey(entity = CatalogueEntity.class,
        parentColumns = "id",
        childColumns = "cat_id")
        ,tableName = "abbr")

public class AbbrEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "abbr_name")
    private String name;

    @ColumnInfo(name = "logo_uri")
    private String logoUri;

    @ColumnInfo(name = "cat_id")
    private String catId;
}
