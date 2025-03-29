package com.example.anmlvoicesapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AnimalActivity extends AppCompatActivity {
    public MediaPlayer sound1, sound2;
    public Button voiceButton1, voiceButton2;
    public ImageButton uaFlag, gbFlag, frFlag, selectedButton;
    public TextView textAnimalVoice;
    private MediaPlayer mediaPlayer;
    private int imageResourceId;
    private int soundResourceId1;
    private int soundResourceId2;
    private LanguageManager languageManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        languageManager = LanguageManager.getInstance(this);
        languageManager.applyLanguage(this);
        setContentView(R.layout.activity_animal);

        // Отримуємо дані з Intent
        imageResourceId = getIntent().getIntExtra("image_resource", 0);
        soundResourceId1 = getIntent().getIntExtra("sound_resource_1", 0);
        soundResourceId2 = getIntent().getIntExtra("sound_resource_2", 0);

        // Встановлюємо зображення
        ImageView animalImage = findViewById(R.id.animalImage);
        animalImage.setImageResource(imageResourceId);

        // Налаштовуємо кнопки відтворення звуку
        Button playSoundButton1 = findViewById(R.id.playSoundButton1);
        Button playSoundButton2 = findViewById(R.id.playSoundButton2);

        // Встановлюємо текст кнопок з урахуванням поточної мови
        playSoundButton1.setText(getString(R.string.play_sound_1));
        playSoundButton2.setText(getString(R.string.play_sound_2));

        playSoundButton1.setOnClickListener(v -> playSound(soundResourceId1));
        playSoundButton2.setOnClickListener(v -> playSound(soundResourceId2));

        // Налаштовуємо кнопку "Назад"
        Button backButton = findViewById(R.id.backButton);
        backButton.setText(getString(R.string.back));
        backButton.setOnClickListener(v -> finish());
    }

    private void playSound(int soundResourceId) {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        
        try {
            mediaPlayer = MediaPlayer.create(this, soundResourceId);
            if (mediaPlayer != null) {
                mediaPlayer.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    public void soundPlayButton(MediaPlayer sound, MediaPlayer sound2) {
        if(sound.isPlaying()){
            sound.pause();
            sound.seekTo(0);
            sound.setLooping(false);
        }

        if(sound2.isPlaying()){
            sound2.pause();
            sound.seekTo(0);
            sound2.setLooping(false);
        }

        sound.start();
        sound.setLooping(true);
    }

    public void highlightButton(ImageButton button) {
        // Remove the highlight from the previously selected button
        if (selectedButton != null) {
            selectedButton.setSelected(false);
        }

        // Highlight the clicked button
        button.setSelected(true);
        selectedButton = button;
    }
}