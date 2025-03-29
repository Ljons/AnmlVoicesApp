package com.example.anmlvoicesapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DogActivity extends AnimalActivity {
    public MediaPlayer sound1, sound2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dog);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        voiceButton1 = findViewById(R.id.catVoice1Button);
        voiceButton2 = findViewById(R.id.catVoice2Button);

        sound1 = MediaPlayer.create(this, R.raw.dog_sound);
        sound2 = MediaPlayer.create(this, R.raw.dog_sound2);

        voiceButton1.setOnClickListener(v -> {
            soundPlayButton(sound1, sound2);
        });

        voiceButton2.setOnClickListener(v -> {
            soundPlayButton(sound2, sound1);
        });

        uaFlag = findViewById(R.id.uaImgFlagDog);
        gbFlag = findViewById(R.id.gbImgFlagDog);
        frFlag = findViewById(R.id.frImgFlagDog);
        selectedButton = uaFlag;
        textAnimalVoice = findViewById(R.id.textDogVoice);

        uaFlag.setOnClickListener(v -> {
            highlightButton(uaFlag);
            textAnimalVoice.setText("гав-гав, ав-ав");
        });

        gbFlag.setOnClickListener(v -> {
            highlightButton(gbFlag);
            textAnimalVoice.setText("йап-йап/арф-арф (yap yap/arf arf), вуф-вуф/руф-руф (woof woof/ruff ruff), (bow wow)");
        });

        frFlag.setOnClickListener(v -> {
            highlightButton(frFlag);
            textAnimalVoice.setText("ау-ау (ouah ouah)");
        });
    }

    public void goBack(View v){
        Intent intent = new Intent(this, MainActivity.class);
        sound1.stop();
        sound2.stop();
        startActivity(intent);
    }

    public void stopSounds(View v){
        sound1.stop();
        sound2.stop();
    }
}