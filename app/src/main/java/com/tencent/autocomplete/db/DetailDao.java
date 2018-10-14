package com.tencent.autocomplete.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface DetailDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAll(List<DetailEntity> details);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long save(DetailEntity abbr);

    @Query("SELECT * FROM detail WHERE (phone_number LIKE :name OR name_t9 LIKE :name OR name_t9_abbr LIKE :name)")
    List<DetailEntity> findT9(String name);

    @Query("SELECT * FROM detail")
    List<DetailEntity> findAll();

    @Query("DELETE FROM detail")
    void deleteAll();
}
