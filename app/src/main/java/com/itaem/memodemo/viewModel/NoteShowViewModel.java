package com.itaem.memodemo.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.itaem.memodemo.data.NoteDao;
import com.itaem.memodemo.data.NoteDatabase;
import com.itaem.memodemo.data.NoteEntity;

public class NoteShowViewModel extends AndroidViewModel {
    private NoteDao noteDao;
    private NoteEntity updateNote;

    public NoteShowViewModel(@NonNull Application application) {
        super(application);
        this.noteDao = NoteDatabase.getDatabase(application).getNoteDao();
    }

    public void insert(NoteEntity note){
        noteDao.insertNote(note);
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

    public NoteEntity getUpdateNote() {
        return updateNote;
    }

    public void setUpdateNote(NoteEntity updateNote) {
        this.updateNote = updateNote;
    }
}
