package com.example.tugas4;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class User implements Parcelable {

    private String username, name, caption;
    private Integer profPic, postPic;
    private Uri selectedImageUri;


    public User(int profPic, int postPic, String username, String name, String caption) {
        this.profPic = profPic;
        this.postPic = postPic;
        this.username = username;
        this.name = name;
        this.caption = caption;
    }

    public User(int profPic, Uri selectedImageUri, String username, String name, String content) {
        this.profPic = profPic;
        this.selectedImageUri = selectedImageUri;
        this.username = username;
        this.name = name;
        this.caption = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return caption;
    }

    public void setDesc(String desc) {
        this.caption = desc;
    }

    public int getFotoProfile() {
        return profPic;
    }

    public void setFotoProfile(int fotoProfile) {
        this.profPic = fotoProfile;
    }

    public int getFotoPostingan() {
        if (postPic != null) {
            return postPic;
        } else {
            return 0;
        }
    }

    public void setFotoPostingan(int fotoPostingan) {
        this.postPic = fotoPostingan;
    }

    public Uri getSelectedImageUri() {
        return selectedImageUri;
    }

    public void setSelectedImageUri(Uri selectedImageUri) {
        this.selectedImageUri = selectedImageUri;
    }

    protected User(Parcel in) {
        profPic = in.readInt();
        postPic = in.readInt();
        username = in.readString();
        name = in.readString();
        caption = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(profPic);
        dest.writeInt(postPic);
        dest.writeString(username);
        dest.writeString(name);
        dest.writeString(caption);
    }
}
