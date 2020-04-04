package com.example.lawrenceclinics.bd;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class AutenticacionBD {

    private ManejoBD bd;

    public AutenticacionBD(ManejoBD bd) {
        this.bd = bd;
    }

    private Usuario buscarPorNumeroCedula(String numeroCedula) {
        SQLiteDatabase db = bd.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM login_cliente WHERE numeroCedula = ?",new String[]{numeroCedula});
        Usuario usuario = null;
        if(cursor.moveToNext()) {
            long idUsuario = cursor.getLong(cursor.getColumnIndex("idUsuario"));
            String passwordUsuario = cursor.getString(cursor.getColumnIndex("passwordUsuario"));
            String mailUsuario = cursor.getString(cursor.getColumnIndex("MailUsuario"));
            String nombreUsuario = cursor.getString(cursor.getColumnIndex("NombreUsuario"));

            usuario = new Usuario(idUsuario,numeroCedula,passwordUsuario,mailUsuario,nombreUsuario);
        }
        cursor.close();
        return usuario;
    }

    public Usuario registrar(Usuario usuario) {
        if(buscarPorNumeroCedula(usuario.getNumeroCedula()) != null) {
            return null;
        }

        SQLiteDatabase db = bd.getWritableDatabase(); //getReadableDatabase()

        ContentValues values = new ContentValues();
        values.put("numeroCedula",usuario.getNumeroCedula());
        values.put("passwordUsuario",usuario.getPassword());
        values.put("MailUsuario",usuario.getMail());
        values.put("NombreUsuario",usuario.getNombre());

        long nuevoId = db.insert("login_cliente",null,values);
        usuario.setId(nuevoId);

        return usuario;
    }

    public boolean login(String numeroCedula, String password) {
        SQLiteDatabase db = bd.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT COUNT(idUsuario) FROM login_cliente WHERE numeroCedula = ? AND passwordUsuario = ?",new String[]{numeroCedula,password});
        if(cursor.moveToNext()) {
            int coincidencias = cursor.getInt(0);
            cursor.close();
            return coincidencias == 1;
        }
        cursor.close();
        return false;
    }



}
