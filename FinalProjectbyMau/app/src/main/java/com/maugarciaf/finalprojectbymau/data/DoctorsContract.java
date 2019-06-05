package com.maugarciaf.finalprojectbymau.data;

import android.provider.BaseColumns;

public class DoctorsContract {

    public static abstract class DoctorEntry implements BaseColumns {
        public static final String TABLE_NAME ="lawyer";

        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String SPECIALTY = "specialty";
        public static final String PHONE_NUMBER = "phoneNumber";
        public static final String AVATAR_URI = "avatarUri";
        public static final String BIO = "bio";
        public static final String DIRECTION = "direction";
    }
}
