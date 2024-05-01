package com.example.tugas5;

import java.util.ArrayList;

public class DataSource {
    public static ArrayList<User> users = generateDummyUsers();

    private static ArrayList<User> generateDummyUsers() {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User(R.drawable.garden, R.drawable.garden, "@user_1", "User Satu", "secret garden"));
        users.add(new User(R.drawable.beach, R.drawable.beach, "@user_2", "User Dua", "sea"));
        users.add(new User(R.drawable.love, R.drawable.love, "@user_3", "User Tiga", "prettyyy"));
        users.add(new User(R.drawable.pizza, R.drawable.pizza, "@user_4", "User Empat", "pizza mi fav food"));
        users.add(new User(R.drawable.vinil, R.drawable.vinil, "@user_5", "User Lima", "wow"));
        return users;
    }
}
