package com.example.projet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Foot.db";
    public static final String TABLE_NAME_1 = "table_match";
    public static final String TABLE_NAME_2 = "table_joueur";
    public static final String COL_1_1 = "_id";
    public static final String COL_2_1 = "Stade";
    public static final String COL_3_1 = "Equipe1";
    public static final String COL_4_1 = "Formation1";
    public static final String COL_5_1 = "Equipe2";
    public static final String COL_6_1 = "Formation2";
    public static final String COL_7_1 = "Arbitre";
    public static final String COL_8_1 = "ScoreEquipe1";
    public static final String COL_9_1 = "ScoreEquipe2";
    public static final String COL_1_2 = "_id";
    public static final String COL_2_2 = "Nom";
    public static final String COL_3_2 = "NumEquipe";
    public static final String COL_4_2 = "id_match";
    public static final String COL_5_2 = "But";
    public static final String COL_6_2 = "Passe";
    public static final String COL_7_2 = "Jaune";
    public static final String COL_8_2 = "Rouge";




    private SQLiteDatabase database;



    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create table " + TABLE_NAME_1+ " (ID INTEGER PRIMARY KEY AUTOINCREMENT, Stade TEXT, Equipe1 TEXT, Formation1 TEXT, Equipe2 TEXT, Formation2 TEXT, Arbitre TEXT, ScoreEquipe1 INT, ScoreEquipe2 INT)");
        db.execSQL(" create table " + TABLE_NAME_2+ " (ID INTEGER PRIMARY KEY AUTOINCREMENT, Nom TEXT, NumEquipe INT, id_match INT, But INT, Passe INT, Jaune INT, Rouge INT)");

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

    public  boolean insertJoueur (String nom, int numEquipe, String role){
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM table_match", null);
        cursor.moveToLast();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2_2,nom);
        contentValues.put(COL_3_2,numEquipe);
        contentValues.put(COL_4_2,cursor.getString(0));

        long result = db.insert(TABLE_NAME_2,null,contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public void list_equipe (TextView textView, int col) {
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM table_match", null);
        textView.setText("");
        cursor.moveToLast();
        textView.append(cursor.getString(col) + " ");
    }

    public Cursor list_joueur (int equipe) {
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM table_match", null);
        cursor.moveToLast();
        String numMatch = cursor.getString(0);
        Cursor cursor1 = this.getReadableDatabase().rawQuery("SELECT * FROM table_joueur WHERE id_match LIKE "+ numMatch + " AND NumEquipe LIKE "+equipe, null);
        return cursor1;
    }

    public Cursor list_match () {
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM table_match", null);
        return cursor;
    }

    public boolean ajout_role (String id[], String role){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        if (role == "But"){
            contentValues.put(COL_5_2, 1);
        }else if (role == "Passe"){
            contentValues.put(COL_6_2, 1);
        }else if (role == "Jaune"){
            contentValues.put(COL_7_2, 1);
        }else if (role == "Rouge"){
            contentValues.put(COL_8_2, 1);
        }
        db.update("table_joueur", contentValues, "ID=?",new String[] {String.valueOf(id[0])});
        return true;
    }

    public boolean ajout_score (int score1, int score2){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_8_1, score1);
        contentValues.put(COL_9_1, score2);
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM table_match", null);
        cursor.moveToLast();
        String numMatch = cursor.getString(0);
        db.update("table_match", contentValues, "ID=?",new String[] {String.valueOf(numMatch)});
        return true;
    }
}
