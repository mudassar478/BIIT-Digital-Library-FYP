package com.example.biitdigitallibrarysystem.models;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insertAll(User... users);

    @Query("SELECT id, book_name FROM user WHERE book_name=:fileUri")
    List<User> getFilePath(String fileUri);

    @Query("SELECT id, book_name FROM user WHERE book_name=:fileUri")
    List<User> getFvtFilePath(String fileUri);

//    @Query("SELECT id, file_uri FROM user WHERE type=:typ")
//    List<User> getAllUri(String typ);
//
//    @Query("SELECT id, file_uri FROM user WHERE type=:typ")
//    List<User> getAllFvtUri(String typ);

    @Query("DELETE FROM user WHERE book_name=:fileUri")
    void deleteFromFavourites(String fileUri);

    @Query("DELETE FROM user WHERE book_name=:fileUri")
    void deleteFavourites(String fileUri);
}
