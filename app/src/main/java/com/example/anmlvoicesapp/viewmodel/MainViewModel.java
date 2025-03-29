package com.example.anmlvoicesapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.anmlvoicesapp.R;
import com.example.anmlvoicesapp.model.Animal;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {
    private MutableLiveData<List<Animal>> animals;
    private MutableLiveData<Animal> currentPlayingAnimal;

    public MainViewModel() {
        animals = new MutableLiveData<>();
        currentPlayingAnimal = new MutableLiveData<>();
        loadAnimals();
    }

    public LiveData<List<Animal>> getAnimals() {
        return animals;
    }

    public LiveData<Animal> getCurrentPlayingAnimal() {
        return currentPlayingAnimal;
    }

    public void setCurrentPlayingAnimal(Animal animal) {
        currentPlayingAnimal.setValue(animal);
    }

    private void loadAnimals() {
        List<Animal> animalList = new ArrayList<>();
        
        // Додаємо тварин
        animalList.add(new Animal(
                "Собака",
                "Найкращий друг людини",
                R.drawable.ic_dog,
                R.raw.dog_sound
        ));
        
        animalList.add(new Animal(
                "Кішка",
                "Грайливий домашній улюбленець",
                R.drawable.ic_cat,
                R.raw.cat_sound
        ));
        
        animalList.add(new Animal(
                "Корова",
                "Дає нам молоко",
                R.drawable.ic_cow,
                R.raw.cow_sound
        ));
        
        animalList.add(new Animal(
                "Курка",
                "Несе яйця",
                R.drawable.ic_chicken,
                R.raw.chicken_sound
        ));
        
        animalList.add(new Animal(
                "Свиня",
                "Рожевий домашній улюбленець",
                R.drawable.ic_pig,
                R.raw.pig_sound
        ));

        animals.setValue(animalList);
    }
} 