package com.example.notetaker;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.RoomDatabase.Callback;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Note.class,version = 1,exportSchema = false)
public abstract class NoteDataBase extends RoomDatabase {

    private static NoteDataBase Instance;

    public abstract NoteDao noteDao();

    public static synchronized NoteDataBase getInstance(Context context){
        if(Instance==null){
            Instance= Room
                    .databaseBuilder(context.getApplicationContext(),NoteDataBase.class,"note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallbacks)
                    .build();
        }
        return Instance;
    }

    private static Callback roomCallbacks=new Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new populateDbAsyncTask(Instance).execute();
        }
    };

    private static class populateDbAsyncTask extends AsyncTask<Void,Void,Void>{

        NoteDao noteDao;
        public populateDbAsyncTask(NoteDataBase db){
            this.noteDao=db.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("Title 1","description 1",1));
            noteDao.insert(new Note("Title 2","description 2",4));
            noteDao.insert(new Note("Title 3","description 3",3));
            noteDao.insert(new Note("Title 4", "description 4",2));
            return null;
        }
    }



}
