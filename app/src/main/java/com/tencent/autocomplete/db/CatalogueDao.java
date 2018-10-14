package com.tencent.autocomplete.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.RoomWarnings;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by timfeng on 2018/10/12.
 */

@Dao
public interface CatalogueDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAll(List<CatalogueEntity> cats);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long save(CatalogueEntity cats);

    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    @Query("SELECT id FROM catalogue WHERE cat_name = :cat LIMIT 1")
    CatalogueEntity findCatalogue(String cat);

    @Query("SELECT * FROM catalogue")
    List<CatalogueEntity> findAll();

    @Query("DELETE FROM catalogue")
    void deleteAll();
}
