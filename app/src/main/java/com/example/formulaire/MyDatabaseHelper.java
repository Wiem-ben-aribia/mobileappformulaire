package com.example.formulaire;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "my_database";
    private static final int DATABASE_VERSION = 4;

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nom TEXT," +
                "prenom TEXT," +
                "age INTEGER," +
                "pays TEXT," +
                "interets TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Vous pouvez ajouter des instructions pour gérer la mise à jour de la base de données ici
    }
    public void ajouter(String nom,String prenom,String ageStr ,String pays){
        SQLiteDatabase bd = getWritableDatabase() ;
        ContentValues values = new ContentValues();
        values.put("nom", nom);
        values.put("prenom", prenom);
        values.put("age", ageStr);
        values.put("pays", pays);
        //values.put("interets", interets);

        long newRowId = bd.insert("users", null, values);
        String[] projection = {"id", "nom", "prenom", "age", "pays"};
        String selection = "age > ?";
        String[] selectionArgs = {"18"};
        String sortOrder = "nom DESC";

        //long result = bd.insert("Articles", null, values) ;
        /*if (newRowId == -1){
            Toast.makeText( ,"Failed !", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivity. , "succeeded !", Toast.LENGTH_SHORT).show();
        }*/
        bd.close() ;


    }

}


