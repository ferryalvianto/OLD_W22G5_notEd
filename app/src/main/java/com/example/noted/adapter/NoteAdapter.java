package com.example.noted.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.noted.R;
import com.example.noted.model.Note;

import java.util.List;

public class NoteAdapter extends BaseAdapter {

    List<Note> notes;
    public NoteAdapter(List<Note> notes) {
        this.notes = notes;
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            view = inflater.inflate(R.layout.note_item,viewGroup,false);
        }

        TextView title = view.findViewById(R.id.textViewTitle);
        TextView content = view.findViewById(R.id.textViewContent);

        title.setText(notes.get(i).getNotetitle());
        content.setText(notes.get(i).getNotecontent());

        return view;
    }
}