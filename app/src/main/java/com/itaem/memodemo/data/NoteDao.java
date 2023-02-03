package com.itaem.memodemo.data;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

// 对表的一系列操作——增删改查
@Dao
public interface NoteDao {
    // 添加
    @Insert
    void insertNote(NoteEntity note);
    // 改
    @Update
    void updateNote(NoteEntity note);
    // 单删
    @Delete
    void deleteNote(NoteEntity note);
    // 多删
    @Delete
    void deleteMoreNote(NoteEntity...notes);
    // 查——显示
    @Query("SELECT * FROM NoteEntity ORDER BY ID DESC")
    LiveData<List<NoteEntity>> queryAllNote();

    // 单个查询
    @Query("SELECT * From NoteEntity Where id = :patten")
    NoteEntity queryNote(int patten);

    // 获取置顶集合
    @Query("SELECT * FROM NoteEntity WHERE note_isHeader='true'")
    LiveData<List<NoteEntity>> queryTop();
}
