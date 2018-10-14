package com.tencent.autocomplete.db;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by timfeng on 2018/10/12.
 */
@Entity(foreignKeys = @ForeignKey(entity = AbbrEntity.class,
        parentColumns = "id",
        childColumns = "abbr_id")
        ,tableName = "detail"
        ,indices = {//@Index(value = "phone_name",unique = true),
        @Index(value="phone_number",unique=true),
        @Index(value="name_t9"),
        @Index(value="name_t9_abbr"),
        @Index(value="abbr_id")})
public class DetailEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "phone_name")
    public String name;

    @ColumnInfo(name = "name_t9")
    public String t9;

    @ColumnInfo(name = "name_t9_index")
    public String t9Index;

    @ColumnInfo(name = "name_t9_abbr")
    public String t9Abbr;

    @ColumnInfo(name = "phone_number")
    public String number;

    @ColumnInfo(name = "abbr_id")
    public int abbrId;

    public DetailEntity(String name,String t9,String t9Index,String t9Abbr,String number,int abbrId){
        this.name = name;
        this.t9 = t9;
        this.t9Index = t9Index;
        this.t9Abbr = t9Abbr;
        this.number = number;
        this.abbrId = abbrId;
    }

}
