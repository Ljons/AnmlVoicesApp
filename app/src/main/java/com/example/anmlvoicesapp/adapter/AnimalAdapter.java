package com.example.anmlvoicesapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anmlvoicesapp.R;
import com.example.anmlvoicesapp.model.Animal;

import java.util.ArrayList;
import java.util.List;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder> {
    private List<Animal> animals = new ArrayList<>();
    private OnAnimalClickListener listener;
    private int lastPosition = -1;

    public interface OnAnimalClickListener {
        void onPlaySound(Animal animal);
    }

    public void setOnAnimalClickListener(OnAnimalClickListener listener) {
        this.listener = listener;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AnimalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_animal, parent, false);
        return new AnimalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalViewHolder holder, int position) {
        Animal animal = animals.get(position);
        holder.bind(animal);
        
        // Додаємо анімацію
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.item_animation);
            holder.itemView.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull AnimalViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }

    @Override
    public int getItemCount() {
        return animals.size();
    }

    class AnimalViewHolder extends RecyclerView.ViewHolder {
        private ImageView animalImage;
        private TextView animalName;
        private TextView animalDescription;
        private ImageButton playButton;

        public AnimalViewHolder(@NonNull View itemView) {
            super(itemView);
            animalImage = itemView.findViewById(R.id.animalImage);
            animalName = itemView.findViewById(R.id.animalName);
            animalDescription = itemView.findViewById(R.id.animalDescription);
            playButton = itemView.findViewById(R.id.playButton);
        }

        public void bind(Animal animal) {
            animalImage.setImageResource(animal.getImageResourceId());
            animalName.setText(animal.getName());
            animalDescription.setText(animal.getDescription());

            playButton.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onPlaySound(animal);
                }
            });
        }
    }
} 