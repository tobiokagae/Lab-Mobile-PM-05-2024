package com.example.tugas3;

import java.util.ArrayList;

public class DataSource {
    public static ArrayList<User> users = generateDummyUsers();

    private static ArrayList<User> generateDummyUsers() {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User(R.drawable.profileali, R.drawable.postali, R.drawable.storyali, "ejen_ali", "70K", "comot ♡", "12"));
        users.add(new User(R.drawable.profilekuromi, R.drawable.postkuromi, R.drawable.storykuromi, "official_kuromi", "561K", "dumppp", "34"));
        users.add(new User(R.drawable.profilecinnamoroll, R.drawable.postcinnamoroll, R.drawable.storycinnamoroll, "realcinnamoroll", "89.1K", "my version of me time", "34"));
        users.add(new User(R.drawable.profilerin, R.drawable.postrin, R.drawable.storyrin, "rinitoshi", "102K", "@erigo", "63"));
        users.add(new User(R.drawable.profileduri, R.drawable.postduri, R.drawable.storyduri, "bbbduri", "209K", "cuba teka apa yang saya fikirkan", "7"));
        users.add(new User(R.drawable.profileice, R.drawable.postice, R.drawable.storyice, "bbbice", "69.9K", "masa kelas memasak", "7"));
        users.add(new User(R.drawable.profilesolar, R.drawable.postsolar, R.drawable.storysolar, "bbbsolar", "90K", "senyum.", "7"));
        users.add(new User(R.drawable.profilemechamato, R.drawable.postmechamato, R.drawable.storymechamato, "mechamato", "153K", "hi mniez", "59"));
        users.add(new User(R.drawable.profilemelody, R.drawable.postmelody, R.drawable.storymelody, "my.melody", "94.5K", "yippiiii", "77"));
        users.add(new User(R.drawable.profilepompompurin, R.drawable.postpompompurin, R.drawable.storypompompurin, "pom2purin", "99.9K", "in love >.<", "95"));
        return users;
    }
}
