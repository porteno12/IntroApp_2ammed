package com.example.introapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.introapp.databinding.ActivitySongListBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * SongListActivity
 * Displays a ListView of songs.
 * Short click: open the song URL.
 * Long click: delete the song from the list.
 * FAB click: add a new song to the list.
 */
public class SongListActivity extends AppCompatActivity {

    private ActivitySongListBinding binding;
    private List<Song> songs;
    private SongsAdapter adapter;
    private int addedCounter = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySongListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initSongs();
        setupListView();
        setupFab();
    }

    /**
     * Creates the initial song list.
     */
    private void initSongs() {
        songs = new ArrayList<Song>();

        int defaultIcon = android.R.drawable.ic_media_play;

        songs.add(new Song("Adele - Hello",
                "https://www.youtube.com/watch?v=YQHsXMglC9A",
                defaultIcon));
        songs.add(new Song("Ed Sheeran - Shape of You",
                "https://www.youtube.com/watch?v=JGwWNGJdvx8",
                defaultIcon));
        songs.add(new Song("Coldplay - Yellow",
                "https://www.youtube.com/watch?v=yKNxeF4KMsY",
                defaultIcon));
        songs.add(new Song("Imagine Dragons - Believer",
                "https://www.youtube.com/watch?v=7wtfhZwyrcc",
                defaultIcon));
        songs.add(new Song("Queen - Bohemian Rhapsody",
                "https://www.youtube.com/watch?v=fJ9rUzIMcZQ",
                defaultIcon));
        songs.add(new Song("Maroon 5 - Memories",
                "https://www.youtube.com/watch?v=SlPhMPnQ58k",
                defaultIcon));
    }

    /**
     * Connects the adapter to the ListView
     * and sets click & long-click behaviors.
     */
    private void setupListView() {
        adapter = new SongsAdapter(this, songs);
        binding.listViewSongs.setAdapter(adapter);

        // Short click → open URL
        binding.listViewSongs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song song = (Song) adapter.getItem(position);
                if (song != null) {
                    openSongUrl(song.getUrl());
                }
            }
        });

        // Long click → delete item
        binding.listViewSongs.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (position >= 0 && position < songs.size()) {
                    Song removed = songs.remove(position);
                    adapter.notifyDataSetChanged();
                    if (removed != null) {
                        Toast.makeText(SongListActivity.this,
                                "Deleted: " + removed.getName(),
                                Toast.LENGTH_SHORT).show();
                    }
                }
                return true; // event handled
            }
        });
    }

    /**
     * Sets up the Floating Action Button to add a new song.
     */
    private void setupFab() {
        binding.fabAddSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = "New Song " + addedCounter;
                String url = "https://www.youtube.com"; // placeholder

                Song newSong = new Song(title, url, android.R.drawable.ic_media_play);
                songs.add(newSong);
                adapter.notifyDataSetChanged();
                addedCounter++;

                Toast.makeText(SongListActivity.this,
                        "Added: " + newSong.getName(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Opens the given URL using an ACTION_VIEW intent.
     */
    private void openSongUrl(String url) {
        if (url == null || url.trim().isEmpty()) {
            Toast.makeText(this, "URL is empty", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            Uri uri = Uri.parse(url.trim());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "No app can open this link", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Invalid URL", Toast.LENGTH_SHORT).show();
        }
    }
}
