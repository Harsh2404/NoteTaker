package com.example.notetaker;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private NoteRepository noteRepository;
    private LiveData<List<Note>> notelist;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        noteRepository=new NoteRepository(application);

    }


    public void insertNote(Note note){
        noteRepository.insertRepo(note);
    }

    public void updateNote(Note note){
        noteRepository.updateRepo(note);
    }
    public void deleteNote(Note note){
        noteRepository.deleteRepo(note);
    }
    public void deleteAll(){
        noteRepository.deleteAllRepo();
    }

    public LiveData<List<Note>> showall(){
        notelist=noteRepository.showAllNotes();
        return notelist;
    }

}
