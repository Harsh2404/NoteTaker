package com.example.notetaker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private NoteViewModel noteViewModel;

    private RecyclerView recyclerView;

    private NoteAdapter noteAdapter;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        noteViewModel=new ViewModelProvider(this).get(NoteViewModel.class);
//        recyclerView=(RecyclerView)findViewById(R.id.recycler_View);
//        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//        noteAdapter=new NoteAdapter(this);
//        recyclerView.setAdapter(noteAdapter);
//
//        noteViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(NoteViewModel.class);
//
//        noteViewModel.showall().observe(this, new Observer<List<Note>>() {
//            @Override
//            public void onChanged(List<Note> notes) {
//                Toast.makeText(getApplicationContext(),"List changed",Toast.LENGTH_SHORT).show();
//                Log.i("observer:","data changed in list");
//                noteAdapter.setNotes(notes);
//            }
//        });
        getSupportFragmentManager().beginTransaction().
                replace(R.id.note_container,new NoteDisplayFragment())
                .commit();

    }
}