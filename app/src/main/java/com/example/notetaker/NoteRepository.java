package com.example.notetaker;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class NoteRepository {

    private NoteDao noteDao;

    private LiveData<List<Note>> allnote;

    public NoteRepository(Application application){
        NoteDataBase noteDataBase=NoteDataBase.getInstance(application);
        noteDao=noteDataBase.noteDao();

    }
    public void insertRepo(Note note){
        new insertAsyncTask(noteDao).execute(note);
    }

    public void updateRepo(Note note){
        new updateAsyncTask(noteDao).execute(note);
    }

    public void deleteRepo(Note note){
        new deleteAsyncTask(noteDao).execute(note);
    }

    public void deleteAllRepo(){
       new deleteAllAsyncTask(noteDao).execute();
    }

    public LiveData<List<Note>> showAllNotes(){
        allnote=noteDao.showAllNotes();
        return allnote;
    }

    //insertAshyncTask
    class insertAsyncTask extends AsyncTask<Note,Void,Void>{
        NoteDao noteDao;

        public insertAsyncTask(NoteDao noteDao){
            this.noteDao=noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    //updateAsync Task
    class updateAsyncTask extends AsyncTask<Note,Void,Void>{
        NoteDao noteDao;

        public updateAsyncTask(NoteDao noteDao){
            this.noteDao=noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    //delete async Task
    class deleteAsyncTask extends AsyncTask<Note,Void,Void>{
        private NoteDao noteDao1;

        deleteAsyncTask(NoteDao noteDao){
            noteDao1=noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao1.delete(notes[0]);
            return null;
        }
    }

    //delete All async Task
    class deleteAllAsyncTask extends AsyncTask<Void,Void,Void>{
        private NoteDao noteDao1;

        deleteAllAsyncTask(NoteDao notedao){
            noteDao1=noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao1.deleteAll();
            return  null;
        }


    }

}
