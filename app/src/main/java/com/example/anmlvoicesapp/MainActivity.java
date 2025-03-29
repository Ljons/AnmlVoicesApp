package com.example.anmlvoicesapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private LanguageManager languageManager;
    private ImageButton selectedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        languageManager = LanguageManager.getInstance(this);
        languageManager.applyLanguage(this);
        setContentView(R.layout.activity_main);

        // Налаштовуємо кнопки для мов
        ImageButton uaFlag = findViewById(R.id.uaFlag);
        ImageButton gbFlag = findViewById(R.id.gbFlag);
        ImageButton frFlag = findViewById(R.id.frFlag);

        // Встановлюємо обробники натискань для прапорів
        uaFlag.setOnClickListener(v -> setLanguage("uk", uaFlag));
        gbFlag.setOnClickListener(v -> setLanguage("en", gbFlag));
        frFlag.setOnClickListener(v -> setLanguage("fr", frFlag));

        // Встановлюємо вибрану мову
        String currentLanguage = languageManager.getLanguage();
        switch (currentLanguage) {
            case "uk":
                selectedButton = uaFlag;
                break;
            case "en":
                selectedButton = gbFlag;
                break;
            case "fr":
                selectedButton = frFlag;
                break;
        }
        updateSelectedButton();

        // Налаштовуємо кнопки для тварин
        ImageButton catButton = findViewById(R.id.catButton);
        ImageButton cowButton = findViewById(R.id.cowButton);
        ImageButton dogButton = findViewById(R.id.dogButton);

        // Встановлюємо обробники натискань
        catButton.setOnClickListener(v -> openAnimalPage(R.drawable.cat, R.raw.cat_sound, R.raw.cat_sound2));
        cowButton.setOnClickListener(v -> openAnimalPage(R.drawable.cow, R.raw.cow_sound, R.raw.cow_sound2));
        dogButton.setOnClickListener(v -> openAnimalPage(R.drawable.dog1, R.raw.dog_sound, R.raw.dog_sound2));
    }

    private void setLanguage(String languageCode, ImageButton button) {
        selectedButton = button;
        languageManager.setLanguage(this, languageCode);
        updateSelectedButton();
        languageManager.recreateActivity(this);
    }

    private void updateSelectedButton() {
        if (selectedButton != null) {
            selectedButton.setAlpha(1.0f);
            // Скидаємо прозорість інших кнопок
            ImageButton uaFlag = findViewById(R.id.uaFlag);
            ImageButton gbFlag = findViewById(R.id.gbFlag);
            ImageButton frFlag = findViewById(R.id.frFlag);
            
            if (uaFlag != selectedButton) uaFlag.setAlpha(0.5f);
            if (gbFlag != selectedButton) gbFlag.setAlpha(0.5f);
            if (frFlag != selectedButton) frFlag.setAlpha(0.5f);
        }
    }

    private void openAnimalPage(int imageResource, int soundResource1, int soundResource2) {
        Intent intent = new Intent(this, AnimalActivity.class);
        intent.putExtra("image_resource", imageResource);
        intent.putExtra("sound_resource_1", soundResource1);
        intent.putExtra("sound_resource_2", soundResource2);
        startActivity(intent);
    }
}