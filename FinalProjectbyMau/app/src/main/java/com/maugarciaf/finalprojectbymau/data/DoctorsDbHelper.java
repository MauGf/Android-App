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



        // Insertar datos ficticios para prueba inicial
        mockData(db);

    }

    private void mockData(SQLiteDatabase sqLiteDatabase) {
        mockDoctor(sqLiteDatabase, new Doctors ("Carlos Perez",
                "Abogado penalista",
                "300 200 1111",
                "Gran profesional con experiencia de 5 años en casos penales.",
                "Avenida Carlos Bonilla #29 ilobasco , Cabañas El Salvador.","carlos_perez.jpg"));
        mockDoctor(sqLiteDatabase, new Doctors ("Daniel Samper",
                "Abogado accidentes de tráfico",
                "300 200 2222",
                "Gran profesional con experiencia de 5 años en accidentes de tráfico.",
                "Avenida Carlos Bonilla #29 ilobasco , Cabañas El Salvador.",
                "daniel_samper.jpg"));
        mockDoctor(sqLiteDatabase, new Doctors ("Lucia Aristizabal",
                "Abogado de derechos laborales",
                "300 200 3333",
                "Gran profesional con más de 3 años de experiencia en defensa de los trabajadores.",
                "Avenida Carlos Bonilla #29 ilobasco , Cabañas El Salvador.",
                "lucia_aristizabal.jpg"));
        mockDoctor(sqLiteDatabase, new Doctors ("Marina Acosta",
                "Abogado de familia",
                "300 200 4444",
                "Gran profesional con experiencia de 5 años en casos de familia.",
                "Avenida Carlos Bonilla #29 ilobasco , Cabañas El Salvador.",
                "marina_acosta.jpg"));
        mockDoctor(sqLiteDatabase, new Doctors ("Olga Ortiz",
                "Abogado de administración pública",
                "300 200 5555",
                "Gran profesional con experiencia de 5 años en casos en expedientes de urbanismo.",
                "Avenida Carlos Bonilla #29 ilobasco , Cabañas El Salvador.",
                "olga_ortiz.jpg"));
        mockDoctor(sqLiteDatabase, new Doctors ("Pamela Briger",
                "Abogado fiscalista",
                "300 200 6666",
                "Gran profesional con experiencia de 5 años en casos de derecho financiero",
                "Avenida Carlos Bonilla #29 ilobasco , Cabañas El Salvador.",
                "pamela_briger.jpg"));
        mockDoctor(sqLiteDatabase, new Doctors ("Rodrigo Benavidez",
                "Abogado Mercantilista",
                "300 200 1111",
                "Gran profesional con experiencia de 5 años en redacción de contratos mercantiles",
                "Avenida Carlos Bonilla #29 ilobasco , Cabañas El Salvador.",
                "rodrigo_benavidez.jpg"));
        mockDoctor(sqLiteDatabase, new Doctors ("Tom Bonz",
                "Abogado penalista",
                "300 200 1111",
                "Gran profesional con experiencia de 5 años en casos penales.",
                "Avenida Carlos Bonilla #29 ilobasco , Cabañas El Salvador.",
                "tom_bonz.jpg"));
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
