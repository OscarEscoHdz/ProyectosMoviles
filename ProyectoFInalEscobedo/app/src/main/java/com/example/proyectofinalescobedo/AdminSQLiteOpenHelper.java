package com.example.proyectofinalescobedo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    //constructor metodo llamdado igual que la clase que inicializa y pasa parametros
    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String nombre, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, nombre, factory, version);
    }

    // crear objetos de base de datos (tabla citas) DDL create tipos de datos manejados por SQLite integer, reales, text @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table citas (nombreBD text primary key, apellidosBD text, edadBD integer, telefonoBD text, fechaBD date, descripcionBD text)");
        db.execSQL("create table expedientes (nombreCompletoBD text primary key, edadBD integer, correoBD text, tratamientoPBD text, tratamientoABD text)");
        db.execSQL("create table inventario (nombreProductoBD text primary key, cantidadBD integer, numProductoBD integer)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Eliminar las tablas si ya existen
        db.execSQL("DROP TABLE IF EXISTS citas");
        db.execSQL("DROP TABLE IF EXISTS expedientes");
        db.execSQL("DROP TABLE IF EXISTS inventario");

        // Volver a crear las tablas
        onCreate(db);
    }







}//termina clase
