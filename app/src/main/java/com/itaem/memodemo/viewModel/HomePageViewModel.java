package com.itaem.memodemo.viewModel;

import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.view.MenuItem;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.itaem.memodemo.data.NoteDao;
import com.itaem.memodemo.data.NoteDatabase;
import com.itaem.memodemo.data.NoteEntity;

import java.util.List;

// 首页
// 存放笔记
public class HomePageViewModel extends AndroidViewModel {
    // 笔记表操作接口实例
    private final NoteDao noteDao;
    // 自动刷新检测数据
    private  LiveData<List<NoteEntity>> noteList;

    public HomePageViewModel(@NonNull Application application) {
        super(application);
        NoteDatabase database = NoteDatabase.getDatabase(application);
        // 获取表
        noteDao = database.getNoteDao();
        noteList = noteDao.queryAllNote();
    }

    /**
     *  删除笔记
     */
    public void delete(int id,NoteEntity note){
        note.setId(id);
        noteDao.deleteNote(note);
    }
    /**
     * 置顶笔记
     * @param id 更新笔记的id
     * @param newNote 更新笔记对象
     */
    public void update(int id,NoteEntity newNote){
        // 根据id进行定向更新
        newNote.setId(id);
        // 更新
        noteDao.updateNote(newNote);
    }
    /**
     * 显示笔记、动态显示
     * @return
     */
    public LiveData<List<NoteEntity>> getNoteList() {
        return noteList;
    }


}
