package com.example.introapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.introapp.databinding.ActivityAvatarPickerBinding;

import java.util.ArrayList;
import java.util.List;

public class AvatarPickerActivity extends AppCompatActivity {

    private ActivityAvatarPickerBinding binding;
    private List<Integer> avatarImagesId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAvatarPickerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        avatarImagesId = new ArrayList<Integer>();
        avatarImagesId.add(R.drawable.avatar1);
        avatarImagesId.add(R.drawable.avatar2);
        avatarImagesId.add(R.drawable.avatar3);
        avatarImagesId.add(R.drawable.avatar4);
        avatarImagesId.add(R.drawable.avatar5);
        avatarImagesId.add(R.drawable.avatar6);
        avatarImagesId.add(R.drawable.avatar7);
        avatarImagesId.add(R.drawable.avatar8);

        AvatarAdapter adapter = new AvatarAdapter(AvatarPickerActivity.this, avatarImagesId);
        binding.gvAvatars.setAdapter(adapter);

        // 3. Handle clicks at the Activity level
        binding.gvAvatars.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int chosenResId = avatarImagesId.get(position);

                Intent data = new Intent();
                data.putExtra("avatar_res_id", chosenResId);
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }
}