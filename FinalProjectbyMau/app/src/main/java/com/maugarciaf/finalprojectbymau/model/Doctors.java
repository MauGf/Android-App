package com.maugarciaf.finalprojectbymau.model;

import android.content.ContentValues;
import android.database.Cursor;

import com.maugarciaf.finalprojectbymau.data.DoctorsContract;

import java.util.UUID;

public class Doctors {
    private String id;
    private String name;
    private String specialty;
    private String phoneNumber;
    private String bio;
    private String avatarUri;
    private String direction;


    public Doctors(String name,
                   String specialty, String phoneNumber,
                   String bio, String direction, String avatarUri) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.specialty = specialty;
        this.phoneNumber = phoneNumber;
        this.bio = bio;
        this.direction = direction;
        this.avatarUri = avatarUri;

    }

    public Doctors(Cursor cursor) {
        id = cursor.getString(cursor.getColumnIndex(DoctorsContract.DoctorEntry.ID));
        name = cursor.getString(cursor.getColumnIndex(DoctorsContract.DoctorEntry.NAME));
        specialty = cursor.getString(cursor.getColumnIndex(DoctorsContract.DoctorEntry.SPECIALTY));
        phoneNumber = cursor.getString(cursor.getColumnIndex(DoctorsContract.DoctorEntry.PHONE_NUMBER));
        bio = cursor.getString(cursor.getColumnIndex(DoctorsContract.DoctorEntry.BIO));
        direction = cursor.getString(cursor.getColumnIndex(DoctorsContract.DoctorEntry.DIRECTION));
        avatarUri = cursor.getString(cursor.getColumnIndex(DoctorsContract.DoctorEntry.AVATAR_URI));

    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(DoctorsContract.DoctorEntry.ID, id);
        values.put(DoctorsContract.DoctorEntry.NAME, name);
        values.put(DoctorsContract.DoctorEntry.SPECIALTY, specialty);
        values.put(DoctorsContract.DoctorEntry.PHONE_NUMBER, phoneNumber);
        values.put(DoctorsContract.DoctorEntry.BIO, bio);
        values.put(DoctorsContract.DoctorEntry.DIRECTION, direction);
        values.put(DoctorsContract.DoctorEntry.AVATAR_URI, avatarUri);

        return values;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getBio() {
        return bio;
    }

    public String getDirection() {
        return direction;
    }
    public String getAvatarUri() {
        return avatarUri;
    }

}
