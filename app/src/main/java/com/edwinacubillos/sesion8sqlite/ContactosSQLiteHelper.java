package com.edwinacubillos.sesion8sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Edwin on 27/09/2016.
 */

public class ContactosSQLiteHelper extends SQLiteOpenHelper {

    private String DATA_BASE_NAME = "AgendaBD";
    private int DATA_VERSION=1;

    String sqlCreate = "CREATE TABLE Contactos (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nombre     TEXT," +
            "telefono   INTEGER," +
            "correo     TEXT)";

    public ContactosSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Contactos");
        db.execSQL(sqlCreate);
    }
}
