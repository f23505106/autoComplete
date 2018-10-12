package com.tencent.autocomplete.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by timfeng on 2018/10/12.
 */

@Dao
public interface CatalogueDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAll(List<CatalogueEntity> posts);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(CatalogueEntity post);

    @Update
    void update(CatalogueEntity post);

    @Delete
    void delete(CatalogueEntity post);

    @Query("SELECT * FROM Post")
    List<CatalogueEntity> findAll();
}
