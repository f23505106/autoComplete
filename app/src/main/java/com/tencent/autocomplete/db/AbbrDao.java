package com.tencent.autocomplete.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface AbbrDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAll(List<AbbrEntity> abbrs);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long save(AbbrEntity abbr);

    @Query("SELECT * FROM abbr WHERE abbr_name = :name LIMIT 1")
    AbbrEntity findAbbr(String name);

    @Query("SELECT * FROM abbr")
    List<AbbrEntity> findAll();

    @Query("DELETE FROM abbr")
    void deleteAll();
}
