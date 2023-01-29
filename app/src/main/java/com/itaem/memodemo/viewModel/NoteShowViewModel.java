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

    public NoteShowViewModel(@NonNull Application application) {
        super(application);
        this.noteDao = NoteDatabase.getDatabase(application).getNoteDao();
    }

    public void insert(NoteEntity note){
        noteDao.insertNote(note);
    }
}
