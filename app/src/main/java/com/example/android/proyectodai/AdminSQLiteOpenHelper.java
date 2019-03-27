package com.example.android.proyectodai;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Se debe crear una subclase, de la clase SQLiteOpenHelper de Android, para hacer el manejo de la BD.
 */
public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    //En el constructor se especifica el nombre de la BD (2o. parámetro) y la versión de esta (4o. parámetro).
    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * Método para crear las tablas necesarias: univ, carrera, alumno.
     * @param sqLiteDatabase sirve para indicar la base de datos en la cuál hay que ejecutar el código.
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table univ(" +
                "idUniv integer primary key, " +
                "nombre text, " +
                "estado text)");


        sqLiteDatabase.execSQL("create table carrera(" +
                "idCar integer primary key, " +
                "nombre text, " +
                "creds integer)");


        sqLiteDatabase.execSQL("create table alumno(" +
                "cu integer primary key, " +
                "nombre text, " +
                "idc integer, " +
                "idu integer, " +
                "foreign key(idc) references carrera(idCar), " +
                "foreign key(idu) references univ(idUniv))");
    }

    /**
     * Método para reemplazar las tablas nuevas si ya existen.
     * @param sqLiteDatabase la base de datos en la que se crearán las tablas.
     * @param i irrelevante.
     * @param i1 irrelevante.
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists univ");
        sqLiteDatabase.execSQL("create table univ(idUniv integer primary key, nombre text, estado text)");
        sqLiteDatabase.execSQL("drop table if exists carrera");
        sqLiteDatabase.execSQL("create table carrera(idCar integer primary key, nombre text, creds integer)");
        sqLiteDatabase.execSQL("drop table if exists alumno");
        sqLiteDatabase.execSQL("create table alumno(cu integer primary key, nombre text, idc integer, idu integer, " +
                "foreign key(idc) references carrera(idCar), " +
                "foreign key(idu) references univ(idUniv))");
    }
}
