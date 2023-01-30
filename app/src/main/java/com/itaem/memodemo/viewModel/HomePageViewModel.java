package com.itaem.memodemo.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.itaem.memodemo.adapter.HomePageAdapter;
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
    private MutableLiveData<List<NoteEntity>> noteList;

    public HomePageViewModel(@NonNull Application application) {
        super(application);
        NoteDatabase database = NoteDatabase.getDatabase(application);
        // 获取表
        noteDao = database.getNoteDao();
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
/*    public List<NoteEntity> queryAll(){
    }*/

    /**
     * 显示笔记、动态显示
     * @return
     */
    public MutableLiveData<List<NoteEntity>> getNoteList() {
        if (noteList==null){
            noteList = new MutableLiveData<>();
        }
        noteList.setValue(noteDao.queryAllNote());
        return noteList;
    }
}
