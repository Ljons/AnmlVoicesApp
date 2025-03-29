package com.example.anmlvoicesapp.model;

public class Animal {
    private String name;
    private String description;
    private int imageResourceId;
    private int soundResourceId;

    public Animal(String name, String description, int imageResourceId, int soundResourceId) {
        this.name = name;
        this.description = description;
        this.imageResourceId = imageResourceId;
        this.soundResourceId = soundResourceId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public int getSoundResourceId() {
        return soundResourceId;
    }
} 