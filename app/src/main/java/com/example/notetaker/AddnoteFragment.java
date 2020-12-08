package com.example.notetaker;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;

public class AddnoteFragment extends Fragment {
    private EditText edittextTitle, edittextDescription;

    private NumberPicker numberPicker;
    private NoteViewModel noteViewModel;

    private FragmentManager fm;


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                saveNote();
                return true;
            case R.id.close:
                close();
            default:
                return true;
        }
    }

    private void close() {
        fm.beginTransaction().replace(R.id.note_container,new NoteDisplayFragment()).commit();

    }

    private void saveNote() {
        String title=edittextTitle.getText().toString();
        String description=edittextDescription.getText().toString();

        int priority=numberPicker.getValue();
        Note note=new Note(title,description,priority);

        noteViewModel.insertNote(note);
        Toast.makeText(getContext(),"Note saved according to priority number",Toast.LENGTH_LONG).show();
        fm.beginTransaction().replace(R.id.note_container,new NoteDisplayFragment()).commit();

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_note_fragment, container, false);

        edittextTitle = v.findViewById(R.id.edit_title);
        edittextDescription = v.findViewById(R.id.edit_description);
        numberPicker = v.findViewById(R.id.priority_number_picker);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);

        setHasOptionsMenu(true);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        noteViewModel = new ViewModelProvider.AndroidViewModelFactory(getActivity().getApplication()).
                create(NoteViewModel.class);
        fm=getActivity().getSupportFragmentManager();

        
    }
}
