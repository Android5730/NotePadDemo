package com.itaem.memodemo.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.chad.library.adapter.base.entity.JSectionEntity;
import com.chad.library.adapter.base.entity.SectionEntity;

import java.io.Serializable;

// 实体，即数据库里的表
@Entity(tableName = "NoteEntity")
public class NoteEntity extends JSectionEntity implements Serializable {
    // 主键
    @PrimaryKey(autoGenerate = true)
    private int id;
    // 笔记标题
    @ColumnInfo(name = "note_title")
    private String note_title;

    // 笔记内容
    @ColumnInfo(name = "note_content")
    private String note_content;
    // 笔记发布时间
    @ColumnInfo(name = "note_time")
    private String note_time;
    // 笔记是否置顶
    @ColumnInfo(name = "note_isHeader")
    private boolean isHeader;

    public NoteEntity(String note_title, String note_content, String note_time) {
        this.note_title = note_title;
        this.note_content = note_content;
        this.note_time = note_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote_title() {
        return note_title;
    }

    public void setNote_title(String note_title) {
        this.note_title = note_title;
    }

    public String getNote_content() {
        return note_content;
    }

    public void setNote_content(String note_content) {
        this.note_content = note_content;
    }

    public String getNote_time() {
        return note_time;
    }

    public void setNote_time(String note_time) {
        this.note_time = note_time;
    }



    public void setHeader(boolean header) {
        isHeader = header;
    }

    @Override
    public boolean isHeader() {
        return isHeader;
    }
}
