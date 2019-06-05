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
        id = cursor.getString(cursor.getColumnIndex(DoctorsContract.LawyerEntry.ID));
        name = cursor.getString(cursor.getColumnIndex(DoctorsContract.LawyerEntry.NAME));
        specialty = cursor.getString(cursor.getColumnIndex(DoctorsContract.LawyerEntry.SPECIALTY));
        phoneNumber = cursor.getString(cursor.getColumnIndex(DoctorsContract.LawyerEntry.PHONE_NUMBER));
        bio = cursor.getString(cursor.getColumnIndex(DoctorsContract.LawyerEntry.BIO));
        direction = cursor.getString(cursor.getColumnIndex(DoctorsContract.LawyerEntry.DIRECTION));
        avatarUri = cursor.getString(cursor.getColumnIndex(DoctorsContract.LawyerEntry.AVATAR_URI));

    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(DoctorsContract.LawyerEntry.ID, id);
        values.put(DoctorsContract.LawyerEntry.NAME, name);
        values.put(DoctorsContract.LawyerEntry.SPECIALTY, specialty);
        values.put(DoctorsContract.LawyerEntry.PHONE_NUMBER, phoneNumber);
        values.put(DoctorsContract.LawyerEntry.BIO, bio);
        values.put(DoctorsContract.LawyerEntry.DIRECTION, direction);
        values.put(DoctorsContract.LawyerEntry.AVATAR_URI, avatarUri);

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
