package com.koch.sampleproject.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MovieDao {
    @Query("SELECT * FROM movieentity")
    List<MovieEntity> getAll();

    @Query("SELECT * FROM movieentity WHERE uid IN (:movieIds)")
    List<MovieEntity> loadAllByIds(int[] movieIds);

    @Query("SELECT * FROM movieentity WHERE title LIKE :name LIMIT 1")
    MovieEntity findByName(String name);

    @Insert
    void insertAll(MovieEntity... movieEntity);

    @Delete
    void delete(MovieEntity movieEntity);
}
