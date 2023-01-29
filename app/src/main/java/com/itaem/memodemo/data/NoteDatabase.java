package com.itaem.memodemo.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

// 从数据库中获取表，即实体Entity类
@Database(entities = {NoteEntity.class},version = 1,exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {
    // 设置单例模式
    private static NoteDatabase INSTANCE;
    public static synchronized NoteDatabase getDatabase(Context context){
        if (INSTANCE ==null){
            // 三个参数：控制器，Dao工具抽象类,Database的文件名称
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),NoteDatabase.class,"Note database")
                    .allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
    // 获取Dao，即表的操作接口
    public abstract NoteDao getNoteDao();
}
