package com.maugarciaf.finalprojectbymau.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.maugarciaf.finalprojectbymau.model.Doctors;

public class LawyersDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Lawyers.db";

    public LawyersDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + LawyersContract.LawyerEntry.TABLE_NAME + " ("
                + LawyersContract.LawyerEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + LawyersContract.LawyerEntry.ID + " TEXT NOT NULL,"
                + LawyersContract.LawyerEntry.NAME + " TEXT NOT NULL,"
                + LawyersContract.LawyerEntry.SPECIALTY + " TEXT NOT NULL,"
                + LawyersContract.LawyerEntry.PHONE_NUMBER + " TEXT NOT NULL,"
                + LawyersContract.LawyerEntry.BIO + " TEXT NOT NULL,"
                + LawyersContract.LawyerEntry.DIRECTION + " TEXT NOT NULL,"
                + LawyersContract.LawyerEntry.AVATAR_URI + " TEXT,"
                + "UNIQUE (" + LawyersContract.LawyerEntry.ID + "))");



        // Insertar datos ficticios para prueba inicial
        mockData(db);

    }

    private void mockData(SQLiteDatabase sqLiteDatabase) {
        mockLawyer(sqLiteDatabase, new Doctors ("Carlos Perez",
                "Abogado penalista",
                "300 200 1111",
                "Gran profesional con experiencia de 5 años en casos penales.",
                "Avenida Carlos Bonilla #29 ilobasco , Cabañas El Salvador.","carlos_perez.jpg"));
        mockLawyer(sqLiteDatabase, new Doctors ("Daniel Samper",
                "Abogado accidentes de tráfico",
                "300 200 2222",
                "Gran profesional con experiencia de 5 años en accidentes de tráfico.",
                "Avenida Carlos Bonilla #29 ilobasco , Cabañas El Salvador.",
                "daniel_samper.jpg"));
        mockLawyer(sqLiteDatabase, new Doctors ("Lucia Aristizabal",
                "Abogado de derechos laborales",
                "300 200 3333",
                "Gran profesional con más de 3 años de experiencia en defensa de los trabajadores.",
                "Avenida Carlos Bonilla #29 ilobasco , Cabañas El Salvador.",
                "lucia_aristizabal.jpg"));
        mockLawyer(sqLiteDatabase, new Doctors ("Marina Acosta",
                "Abogado de familia",
                "300 200 4444",
                "Gran profesional con experiencia de 5 años en casos de familia.",
                "Avenida Carlos Bonilla #29 ilobasco , Cabañas El Salvador.",
                "marina_acosta.jpg"));
        mockLawyer(sqLiteDatabase, new Doctors ("Olga Ortiz",
                "Abogado de administración pública",
                "300 200 5555",
                "Gran profesional con experiencia de 5 años en casos en expedientes de urbanismo.",
                "Avenida Carlos Bonilla #29 ilobasco , Cabañas El Salvador.",
                "olga_ortiz.jpg"));
        mockLawyer(sqLiteDatabase, new Doctors ("Pamela Briger",
                "Abogado fiscalista",
                "300 200 6666",
                "Gran profesional con experiencia de 5 años en casos de derecho financiero",
                "Avenida Carlos Bonilla #29 ilobasco , Cabañas El Salvador.",
                "pamela_briger.jpg"));
        mockLawyer(sqLiteDatabase, new Doctors ("Rodrigo Benavidez",
                "Abogado Mercantilista",
                "300 200 1111",
                "Gran profesional con experiencia de 5 años en redacción de contratos mercantiles",
                "Avenida Carlos Bonilla #29 ilobasco , Cabañas El Salvador.",
                "rodrigo_benavidez.jpg"));
        mockLawyer(sqLiteDatabase, new Doctors ("Tom Bonz",
                "Abogado penalista",
                "300 200 1111",
                "Gran profesional con experiencia de 5 años en casos penales.",
                "Avenida Carlos Bonilla #29 ilobasco , Cabañas El Salvador.",
                "tom_bonz.jpg"));
    }

    public long mockLawyer(SQLiteDatabase db, Doctors doctors) {
        return db.insert(
                LawyersContract.LawyerEntry.TABLE_NAME,
                null,
                doctors.toContentValues());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // No hay operaciones
    }

    public long saveLawyer(Doctors doctors) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.insert(
                LawyersContract.LawyerEntry.TABLE_NAME,
                null,
                doctors.toContentValues());

    }

    public Cursor getAllLawyers() {
        return getReadableDatabase()
                .query(
                        LawyersContract.LawyerEntry.TABLE_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
    }

    public Cursor getLawyerById(String lawyerId) {
        Cursor c = getReadableDatabase().query(
                LawyersContract.LawyerEntry.TABLE_NAME,
                null,
                LawyersContract.LawyerEntry.ID + " LIKE ?",
                new String[]{lawyerId},
                null,
                null,
                null);
        return c;
    }

    public int deleteLawyer(String lawyerId) {
        return getWritableDatabase().delete(
                LawyersContract.LawyerEntry.TABLE_NAME,
                LawyersContract.LawyerEntry.ID + " LIKE ?",
                new String[]{lawyerId});
    }

    public int updateLawyer(Doctors doctors, String lawyerId) {
        return getWritableDatabase().update(
                LawyersContract.LawyerEntry.TABLE_NAME,
                doctors.toContentValues(),
                LawyersContract.LawyerEntry.ID + " LIKE ?",
                new String[]{lawyerId}
        );
    }
}
