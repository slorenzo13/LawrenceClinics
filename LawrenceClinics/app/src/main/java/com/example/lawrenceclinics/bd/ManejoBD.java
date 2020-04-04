package com.example.lawrenceclinics.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ManejoBD extends SQLiteOpenHelper {

    private static final String NAME = "clinica.db";
    private static final int VERSION = 1;

    private static ManejoBD INSTANCE;

    public synchronized static ManejoBD getInstance(Context ctx) {
        if(INSTANCE == null) {
            INSTANCE = new ManejoBD(ctx);
        }
        return INSTANCE;
    }

    private ManejoBD(@Nullable Context context) {
        super(context, NAME,  null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE login_cliente(" +
                "idUsuario INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "numeroCedula TEXT NOT NULL, " +
                "passwordUsuario TEXT NOT NULL, " +
                "MailUsuario TEXT NOT NULL, " +
                "NombreUsuario TEXT NOT NULL)");

        db.execSQL("CREATE TABLE area_medica(" +
                "idArea INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombreArea TEXT NOT NULL)");

        db.execSQL("CREATE TABLE doctores_clinica(" +
                "idDoctor INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombreDoctor TEXT NOT NULL," +
                "areaDoctor INTEGER NOT NULL, FOREIGN KEY(areaDoctor) REFERENCES area_medica(idArea))");

        db.execSQL("CREATE TABLE citas(" +
                "idCita INTEGER PRIMARY KEY AUTOINCREMENT," +
                "fecha TEXT NOT NULL," +
                "hora TEXT NOT NULL," +
                "idDoctor INTEGER NOT NULL, " +
                "FOREIGN KEY(idDoctor) REFERENCES doctores_clinica(idDoctor))");// +
                //"FOREIGN KEY(idHorario) REFERENCES horarios(idHorario))");

        /*db.execSQL("CREATE TABLE horarios(" +
                "idHorario INTEGER PRIMARY KEY AUTOINCREMENT," +
                "horaInicio TEXT NOT NULL," +
                "horaFin TEXT NOT NULL)");*/

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE login_cliente;");
        onCreate(db);
    }

}
