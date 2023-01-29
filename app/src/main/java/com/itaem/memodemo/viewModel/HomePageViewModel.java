package com.itaem.memodemo.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.itaem.memodemo.data.NoteDao;
import com.itaem.memodemo.data.NoteDatabase;
import com.itaem.memodemo.data.NoteEntity;

import java.util.List;

// 首页
// 存放笔记
public class HomePageViewModel extends AndroidViewModel {
    // 笔记表操作接口实例
    private NoteDao noteDao;
    public HomePageViewModel(@NonNull Application application) {
        super(application);
        NoteDatabase database = NoteDatabase.getDatabase(application);
        // 获取表
        noteDao = database.getNoteDao();
        // 查询
        List<NoteEntity> noteEntities = noteDao.queryAllNote();
    }

    /**
     * 新增笔记
     */
    public void insert(NoteEntity note){
        noteDao.insertNote(note);
    }
    /**
     *  删除笔记
     */
    public void delete(int id,NoteEntity note){
        note.setId(id);
        noteDao.deleteNote(note);
    }

    /**
     * 更新笔记
     * @param id 更新笔记的id
     * @param newNote 更新笔记对象
     */
    public void update(int id,NoteEntity newNote){
        // 根据id进行定向更新
        newNote.setId(id);
        // 更新
        noteDao.updateNote(newNote);
    }
    public List<NoteEntity> queryAll(){
        return noteDao.queryAllNote();
    }
}
