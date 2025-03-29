package com.example.anmlvoicesapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CowActivity extends AppCompatActivity {
    public Button cowVoice1Button, cowVoice2Button;

    private MediaPlayer cow1Sound, cow2Sound;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cow);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cowVoice1Button = findViewById(R.id.voice1Button);
        cowVoice2Button = findViewById(R.id.voice2Button);

        cow1Sound = MediaPlayer.create(this, R.raw.cow_sound);
        cow2Sound = MediaPlayer.create(this, R.raw.cow_sound2);

        cowVoice1Button.setOnClickListener(v -> {
            soundPlayButton(cow1Sound, cow2Sound);
        });

        cowVoice2Button.setOnClickListener(v -> {
            soundPlayButton(cow2Sound, cow1Sound);
        });
    }

    private void soundPlayButton(MediaPlayer sound, MediaPlayer sound2) {
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

    public void goBack(View v){
        Intent intent = new Intent(this, MainActivity.class);
        cow1Sound.stop();
        cow2Sound.stop();
        startActivity(intent);
    }

    public void stopSounds(View v){
        cow1Sound.stop();
        cow2Sound.stop();
    }


}