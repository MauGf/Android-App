package com.maugarciaf.finalprojectbymau.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.maugarciaf.finalprojectbymau.model.Doctors;

public class DoctorsDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Lawyers.db";

    public DoctorsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DoctorsContract.DoctorEntry.TABLE_NAME + " ("
                + DoctorsContract.DoctorEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + DoctorsContract.DoctorEntry.ID + " TEXT NOT NULL,"
                + DoctorsContract.DoctorEntry.NAME + " TEXT NOT NULL,"
                + DoctorsContract.DoctorEntry.SPECIALTY + " TEXT NOT NULL,"
                + DoctorsContract.DoctorEntry.PHONE_NUMBER + " TEXT NOT NULL,"
                + DoctorsContract.DoctorEntry.BIO + " TEXT NOT NULL,"
                + DoctorsContract.DoctorEntry.DIRECTION + " TEXT NOT NULL,"
                + DoctorsContract.DoctorEntry.AVATAR_URI + " TEXT,"
                + "UNIQUE (" + DoctorsContract.DoctorEntry.ID + "))");



        // Insertar datos ficticios de doctores para prueba inicial en la app
        mockData(db);

    }

    private void mockData(SQLiteDatabase sqLiteDatabase) {
        mockDoctor(sqLiteDatabase, new Doctors ("Dr. Boris Ferman",
                "Anestesiólogo",
                "300 200 1111",
                "Gran profesional con experiencia de 5 años en el area.",
                "Villavicencio Plaza, Local 4 Col Escalón,San Salvador.",
                "image1.jpg"));
        mockDoctor(sqLiteDatabase, new Doctors ("Dr. Jose Heriberto",
                "Fisiatra",
                "300 200 2222",
                "Gran profesional con experiencia de 5 años en el area de fisioterapia.",
                "87 av. Norte y 1a. Calle Pte. # 4444, CECLINE, Colonia Escalon.",
                "image2.jpg"));
        mockDoctor(sqLiteDatabase, new Doctors ("Dra. Carmen Alexandra",
                "Pediatra",
                "300 200 3333",
                "Gran profesional con más de 3 años de experiencia en el area de pediatria.",
                "Colonia Lomas Verdes, 5ta calle poniente # 5276, San Salvador",
                "image3.jpg"));
        mockDoctor(sqLiteDatabase, new Doctors ("Dra. Gladis Carolina",
                "Dermatologa",
                "300 200 4444",
                "Gran profesional con experiencia de 5 años en en el area.",
                "8 av. Norte entre 5ta y 7° Calle Oriente, Barrio Nuevo Metapan, Santa Ana",
                "image4.jpg"));
        mockDoctor(sqLiteDatabase, new Doctors ("Dra. Liliana Morely",
                "Medico General",
                "300 200 5555",
                "Gran profesional con experiencia de mas de 5 años en el area.",
                "13 calle oriente entre avenida independencia sur y 3a avenida sur, Edificio Clínicas HM, Santa Ana.(En la misma cuadra del colegio Montessori y los Bomberos).",
                "image5.jpg"));
        mockDoctor(sqLiteDatabase, new Doctors ("Dra. Cecilia Irene",
                "Oncologa",
                "300 200 6666",
                "Gran profesional con experiencia de 5 años en el area de oncologia",
                "Avenida Carlos Bonilla #29 ilobasco , Cabañas El Salvador.",
                "image6.jpg"));
        mockDoctor(sqLiteDatabase, new Doctors ("Dr. Mauricio Ernesto",
                "Gastroenterologo",
                "300 200 1111",
                "Gran profesional con experiencia de 5 años en el area",
                "Villavicencio Plaza, 2ª nivel, local 26, Col Escalon, San Salvador.",
                "image7.jpg"));
        mockDoctor(sqLiteDatabase, new Doctors ("Dr. Ricardo Ernesto",
                "Neurocirujano",
                "300 200 1111",
                "Gran profesional con experiencia de 5 años en el area.",
                "Villavicencio Plaza 2° nivel,local Neurociencias Col. Escalon.",
                "image8.jpg"));
    }

    public long mockDoctor(SQLiteDatabase db, Doctors doctors) {
        return db.insert(
                DoctorsContract.DoctorEntry.TABLE_NAME,
                null,
                doctors.toContentValues());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // No hay operaciones
    }

    public long saveDoctor(Doctors doctors) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.insert(
                DoctorsContract.DoctorEntry.TABLE_NAME,
                null,
                doctors.toContentValues());

    }

    public Cursor getAllDoctors() {
        return getReadableDatabase()
                .query(
                        DoctorsContract.DoctorEntry.TABLE_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
    }

    public Cursor getDoctorById(String lawyerId) {
        Cursor c = getReadableDatabase().query(
                DoctorsContract.DoctorEntry.TABLE_NAME,
                null,
                DoctorsContract.DoctorEntry.ID + " LIKE ?",
                new String[]{lawyerId},
                null,
                null,
                null);
        return c;
    }

    public int deleteDoctor(String lawyerId) {
        return getWritableDatabase().delete(
                DoctorsContract.DoctorEntry.TABLE_NAME,
                DoctorsContract.DoctorEntry.ID + " LIKE ?",
                new String[]{lawyerId});
    }

    public int updateDoctor(Doctors doctors, String lawyerId) {
        return getWritableDatabase().update(
                DoctorsContract.DoctorEntry.TABLE_NAME,
                doctors.toContentValues(),
                DoctorsContract.DoctorEntry.ID + " LIKE ?",
                new String[]{lawyerId}
        );
    }
}
