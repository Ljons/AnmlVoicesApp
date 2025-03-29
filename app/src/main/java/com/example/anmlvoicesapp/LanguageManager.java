package com.example.anmlvoicesapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.preference.PreferenceManager;

import java.util.Locale;

public class LanguageManager {
    private static final String LANGUAGE_KEY = "selected_language";
    private static LanguageManager instance;
    private SharedPreferences preferences;

    private LanguageManager(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static LanguageManager getInstance(Context context) {
        if (instance == null) {
            instance = new LanguageManager(context.getApplicationContext());
        }
        return instance;
    }

    public void setLanguage(Context context, String languageCode) {
        preferences.edit().putString(LANGUAGE_KEY, languageCode).apply();
        updateLocale(context, languageCode);
    }

    public String getLanguage() {
        return preferences.getString(LANGUAGE_KEY, "uk");
    }

    private void updateLocale(Context context, String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);
        Resources resources = context.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config, resources.getDisplayMetrics());
    }

    public void applyLanguage(Context context) {
        String languageCode = getLanguage();
        updateLocale(context, languageCode);
    }

    public void recreateActivity(Context context) {
        if (context instanceof MainActivity) {
            ((MainActivity) context).recreate();
        } else if (context instanceof AnimalActivity) {
            ((AnimalActivity) context).recreate();
        }
    }
} 