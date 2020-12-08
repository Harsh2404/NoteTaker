package com.example.notetaker;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder>{

    private List<Note> mnotelist;
    private Context mcontext;

    public NoteAdapter(Context context){
        mcontext=context;
    }

    public void setNotes(List<Note> noteList){
        mnotelist=noteList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title,description,priority;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.note_title);
            description=(TextView)itemView.findViewById(R.id.note_description);
            priority=(TextView)itemView.findViewById(R.id.note_priority);
        }
    }

    @NonNull
    @Override
    public NoteAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);

        View noteView=inflater.inflate(R.layout.list_item,parent,false);

        MyViewHolder viewHolder=new MyViewHolder(noteView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.MyViewHolder holder, int position) {
        Note currentnote=mnotelist.get(position);

        TextView titleText=holder.title;
        titleText.setText(currentnote.getTitle());

        TextView descText=holder.description;
        descText.setText(currentnote.getDescription());

        TextView priorityText=holder.priority;
        priorityText.setText(String.valueOf(currentnote.getPriority()));
    }
    public Note getNoteAt(int position){
        return mnotelist.get(position);
    }
    @Override
    public int getItemCount() {
        return mnotelist.size();
    }


}
