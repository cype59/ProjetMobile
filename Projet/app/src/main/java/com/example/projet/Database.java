package com.example.projet;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Foot.db";
    public static final String TABLE_NAME_1 = "table_match";
    public static final String TABLE_NAME_2 = "table_joueur";
    public static final String COL_1_1 = "ID";
    public static final String COL_2_1 = "Stade";
    public static final String COL_3_1 = "Equipe1";
    public static final String COL_4_1 = "Formation1";
    public static final String COL_5_1 = "Equipe2";
    public static final String COL_6_1 = "Formation2";
    public static final String COL_7_1 = "Arbitre";
    public static final String COL_1_2 = "ID";
    public static final String COL_2_2 = "Nom";
    public static final String COL_3_2 = "NumEquipe";
    public static final String COL_4_2 = "Role";
    public static final String COL_5_2 = "id_match";


    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create table " + TABLE_NAME_1+ " (ID INTEGER PRIMARY KEY AUTOINCREMENT, Stade TEXT, Equipe1 TEXT, Formation1 TEXT, Equipe2 TEXT, Formation2 TEXT, Arbitre TEXT)");
        db.execSQL(" create table " + TABLE_NAME_2+ " (ID INTEGER PRIMARY KEY AUTOINCREMENT, Nom TEXT, NumEquipe INT, Role TEXT, id_match INT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_2);
        onCreate(db);
    }

    public  boolean insertMatch (String stade, String equipe1, String formation1, String equipe2, String formation2, String arbitre){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2_1,stade);
        contentValues.put(COL_3_1,equipe1);
        contentValues.put(COL_4_1,formation1);
        contentValues.put(COL_5_1,equipe2);
        contentValues.put(COL_6_1,formation2);
        contentValues.put(COL_7_1,arbitre);
        long result = db.insert(TABLE_NAME_1,null,contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public  boolean insertJoueur (String nom, int numEquipe, String role, int idMatch){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2_2,nom);
        contentValues.put(COL_3_2,numEquipe);
        contentValues.put(COL_4_2,role);
        contentValues.put(COL_5_2,idMatch);

        long result = db.insert(TABLE_NAME_2,null,contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
}
