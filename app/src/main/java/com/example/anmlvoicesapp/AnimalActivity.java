package com.example.anmlvoicesapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
    private Animation pulseAnimation;
    private Animation bounceAndRotateAnimation;
    private Button currentPlayingButton;
    private boolean isAnimating = false;
    private TextView soundDescriptionText;
    private String animalSoundText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        languageManager = LanguageManager.getInstance(this);
        languageManager.applyLanguage(this);
        setContentView(R.layout.activity_animal);

        // Ініціалізуємо анімації
        pulseAnimation = AnimationUtils.loadAnimation(this, R.anim.pulse_animation);
        bounceAndRotateAnimation = AnimationUtils.loadAnimation(this, R.anim.bounce_and_rotate);
        
        bounceAndRotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                isAnimating = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isAnimating = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        // Отримуємо дані з Intent
        Intent intent = getIntent();
        imageResourceId = intent.getIntExtra("image_resource", 0);
        soundResourceId1 = intent.getIntExtra("sound_resource_1", 0);
        soundResourceId2 = intent.getIntExtra("sound_resource_2", 0);

        // Встановлюємо зображення
        ImageView animalImage = findViewById(R.id.animalImage);
        animalImage.setImageResource(imageResourceId);

        // Налаштовуємо текст звуку
        soundDescriptionText = findViewById(R.id.soundDescriptionText);
        
        // Визначаємо текст звуку тварини
        if (imageResourceId == R.drawable.cat) {
            animalSoundText = getString(R.string.cat_sound);
        } else if (imageResourceId == R.drawable.dog1) {
            animalSoundText = getString(R.string.dog_sound);
        } else if (imageResourceId == R.drawable.cow) {
            animalSoundText = getString(R.string.cow_sound);
        } else if (imageResourceId == R.drawable.chicken) {
            animalSoundText = getString(R.string.chicken_sound);
        } else if (imageResourceId == R.drawable.pig) {
            animalSoundText = getString(R.string.pig_sound);
        }

        // Встановлюємо назву тварини
        TextView animalNameText = findViewById(R.id.animalNameText);
        String animalName = "";
        
        if (imageResourceId == R.drawable.cat) {
            animalName = getString(R.string.cat);
        } else if (imageResourceId == R.drawable.dog1) {
            animalName = getString(R.string.dog);
        } else if (imageResourceId == R.drawable.cow) {
            animalName = getString(R.string.cow);
        } else if (imageResourceId == R.drawable.chicken) {
            animalName = getString(R.string.chicken);
        } else if (imageResourceId == R.drawable.pig) {
            animalName = getString(R.string.pig);
        }
        
        animalNameText.setText(animalName);
        
        // Форматуємо текст опису звуку
        String soundDescription = String.format(getString(R.string.sound_description), animalName);
        soundDescriptionText.setText(soundDescription + "\n" + animalSoundText);

        // Налаштовуємо кнопки відтворення звуку
        Button playSound1Button = findViewById(R.id.playSound1Button);
        Button playSound2Button = findViewById(R.id.playSound2Button);

        // Встановлюємо текст кнопок з урахуванням поточної мови
        playSound1Button.setText(getString(R.string.play_sound_1));
        playSound2Button.setText(getString(R.string.play_sound_2));

        playSound1Button.setOnClickListener(v -> {
            if (!isAnimating) {
                stopCurrentSound();
                playSound(soundResourceId1);
                currentPlayingButton = playSound1Button;
                playSound1Button.startAnimation(pulseAnimation);
                animalImage.startAnimation(bounceAndRotateAnimation);
                showSoundDescription();
            }
        });

        playSound2Button.setOnClickListener(v -> {
            if (!isAnimating) {
                stopCurrentSound();
                playSound(soundResourceId2);
                currentPlayingButton = playSound2Button;
                playSound2Button.startAnimation(pulseAnimation);
                animalImage.startAnimation(bounceAndRotateAnimation);
                showSoundDescription();
            }
        });

        // Налаштовуємо кнопку "Назад"
        Button backButton = findViewById(R.id.backButton);
        backButton.setText(getString(R.string.back));
        backButton.setOnClickListener(v -> {
            stopCurrentSound();
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });
    }

    private void playSound(int soundResourceId) {
        stopCurrentSound();
        mediaPlayer = MediaPlayer.create(this, soundResourceId);
        mediaPlayer.setOnCompletionListener(mp -> {
            if (currentPlayingButton != null) {
                currentPlayingButton.clearAnimation();
                currentPlayingButton = null;
            }
        });
        mediaPlayer.start();
    }

    private void stopCurrentSound() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        if (currentPlayingButton != null) {
            currentPlayingButton.clearAnimation();
            currentPlayingButton = null;
        }
        soundDescriptionText.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopCurrentSound();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopCurrentSound();
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

    private void showSoundDescription() {
        soundDescriptionText.setVisibility(View.VISIBLE);
        soundDescriptionText.setAlpha(0f);
        soundDescriptionText.animate()
                .alpha(1f)
                .setDuration(500)
                .start();
    }
}