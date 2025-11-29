package com.example.introapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.introapp.databinding.ItemSongBinding;

import java.util.List;

public class SongsAdapter extends BaseAdapter {

    private final Context context;
    private final List<Song> data;
    private final LayoutInflater inflater;

    public SongsAdapter(Context context, List<Song> data) {
        this.context = context;
        this.data = data;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemSongBinding binding;

        binding = ItemSongBinding.inflate(inflater, parent, false);
        convertView = binding.getRoot();


        Song song = data.get(position);
        binding.tvName.setText(song.getName());
        binding.ivIcon.setImageResource(song.getIcon());

        return convertView;
    }
}
