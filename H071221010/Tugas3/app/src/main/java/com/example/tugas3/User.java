package com.example.tugas3;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class User implements Parcelable {
    private Integer foto_profil, foto_post, foto_story;
    private String username, followers, caption, following;

    public User(Integer fotoProfil, Integer fotoPost, Integer fotoStory, String username, String followers, String caption, String following) {
        foto_profil = fotoProfil;
        foto_post = fotoPost;
        foto_story = fotoStory;
        this.username = username;
        this.followers = followers;
        this.caption = caption;
        this.following = following;
    }

    protected User(Parcel in) {
        foto_profil = in.readInt();
        foto_post = in.readInt();
        foto_story = in.readInt();

        username = in.readString();
        followers = in.readString();
        caption = in.readString();
        following = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public Integer getFoto_profil() {
        return foto_profil;
    }

    public void setFoto_profil(Integer foto_profil) {
        this.foto_profil = foto_profil;
    }

    public Integer getFoto_post() {
        return foto_post;
    }

    public void setFoto_post(Integer foto_post) {
        this.foto_post = foto_post;
    }

    public Integer getFoto_story() {
        return foto_story;
    }

    public void setFoto_story(Integer foto_story) {
        this.foto_story = foto_story;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(foto_profil);
        dest.writeInt(foto_post);
        dest.writeInt(foto_story);

        dest.writeString(username);
        dest.writeString(followers);
        dest.writeString(caption);
        dest.writeString(following);
    }
}
