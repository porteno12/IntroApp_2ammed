package com.example.introapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.introapp.databinding.ItemAvatarBinding;

import java.util.List;

public class AvatarAdapter extends BaseAdapter {

    private Context context;
    private List<Integer> avatarsImages;

    public AvatarAdapter(Context context, List<Integer> avatarsImages) {
        this.context = context;
        this.avatarsImages = avatarsImages;
    }

    @Override
    public int getCount() {
        return avatarsImages.size();
    }

    @Override
    public Object getItem(int position) {
        return avatarsImages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ItemAvatarBinding binding = ItemAvatarBinding.inflate(LayoutInflater.from(context),
                parent, false);
        convertView = binding.getRoot();


        int imageId = avatarsImages.get(position);

        binding.ivAvatarImage.setImageResource(imageId);
        binding.tvAvatarLabel.setText("Avatar " + (position + 1));

        return convertView;
    }
}
