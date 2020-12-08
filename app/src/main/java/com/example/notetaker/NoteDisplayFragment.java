package com.example.notetaker;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Struct;
import java.util.List;
import java.util.concurrent.Callable;

public class NoteDisplayFragment extends Fragment {


    RecyclerView recyclerView;
    NoteAdapter noteAdapter;
    NoteViewModel noteViewModel;
    FloatingActionButton floatingActionButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.note_display_fragment,container,false);
        recyclerView=view.findViewById(R.id.recycler_View);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        floatingActionButton=view.findViewById(R.id.add_button);
        setHasOptionsMenu(true);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddnoteFragment addnote=new AddnoteFragment();
                FragmentManager fm=getActivity().getSupportFragmentManager();
                fm.beginTransaction().replace(R.id.note_container,addnote).commit();
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        noteViewModel = new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication()).
                create(NoteViewModel.class);

        //showing all notes on activity created
        noteViewModel.showall().observe(getViewLifecycleOwner(), new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> noteList) {
                Toast.makeText(getActivity(),"list view updated",Toast.LENGTH_SHORT).show();
                noteAdapter=new NoteAdapter(getActivity());
                noteAdapter.setNotes(noteList);
                recyclerView.setAdapter(noteAdapter);

            }
        });

        //swipe action to delete note
        new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.note_container,new AddnoteFragment());
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                noteViewModel.deleteNote(noteAdapter.getNoteAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getContext(),"Note deleted",Toast.LENGTH_SHORT).show();

            }
        }).attachToRecyclerView(recyclerView);


    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete_all:
            {
                    NoteViewModel noteViewModel1 = new ViewModelProvider
                            .AndroidViewModelFactory(getActivity()
                            .getApplication())
                            .create(NoteViewModel.class);
                    noteViewModel1.deleteAll();
                }
                break;
            default:
                return true;
        }
        return true;
    }

}
