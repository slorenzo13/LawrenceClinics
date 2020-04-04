package com.example.lawrenceclinics.bd;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Citas {

    private ManejoBD manejoBD;

    public Citas(ManejoBD manejoBD) {
        this.manejoBD = manejoBD;
    }

    public List<CitaDisponible> buscarCitasDisponibles(Date date) {
        //08.00 - 11.00 (30min)
        SQLiteDatabase db = manejoBD.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT horarios.* FROM horarios EXCEPT SELECT horarios.* FROM citas JOIN horarios " +
                "ON citas.idHorario = horarios.idHorario WHERE fecha = ?",new String[]{date.toString()});

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");

        List<CitaDisponible> citas = new ArrayList<>();
        while(cursor.moveToNext()) {
            String horaInicio = cursor.getString(cursor.getColumnIndex("horaInicio"));
            String horaFin = cursor.getString(cursor.getColumnIndex("horaFin"));

            try {
                CitaDisponible citaDisponible = new CitaDisponible(
                        simpleDateFormat.parse(horaInicio),
                        simpleDateFormat.parse(horaFin)
                );

                citas.add(citaDisponible);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        cursor.close();
        return citas;
    }
}
