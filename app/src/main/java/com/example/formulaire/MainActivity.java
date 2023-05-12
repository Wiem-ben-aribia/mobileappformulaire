package com.example.formulaire;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.HashMap;
public class MainActivity extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnValider = (Button) findViewById(R.id.validation);
        EditText editTextAge = findViewById(R.id.age);
        Spinner spinnerPays = findViewById(R.id.spinner);
        String[] tableauOperation = getResources().getStringArray(R.array.pays_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tableauOperation);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPays.setAdapter(adapter);
        EditText editTextNom = findViewById(R.id.Name);
        EditText editTextPrenom = findViewById(R.id.prenom);
        RadioButton checkBoxInteret1 = findViewById(R.id.voyage);
        RadioButton checkBoxInteret2 = findViewById(R.id.jaime);

        String nom = editTextNom.getText().toString();
        String prenom = editTextPrenom.getText().toString();
        String ageStr = editTextAge.getText().toString();
        String pays = spinnerPays.getSelectedItem().toString();

        btnValider.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                String nom = editTextNom.getText().toString();
                String prenom = editTextPrenom.getText().toString();
                String ageStr = editTextAge.getText().toString();
                String pays = spinnerPays.getSelectedItem().toString();
                boolean isInteret1Selected = checkBoxInteret1.isChecked();
                boolean isInteret2Selected = checkBoxInteret2.isChecked();

                boolean isNomValid = nom.length() >= 2;
                boolean isPrenomValid = prenom.length() >= 2;
                boolean isAgeValid = !ageStr.isEmpty() && Integer.parseInt(ageStr) > 18;
                boolean isPaysValid = !pays.equals("Sélectionner un pays");
                boolean isInteretsValid = isInteret1Selected || isInteret2Selected;


                if (isNomValid && isPrenomValid && isAgeValid && isPaysValid && isInteretsValid) {

                } else {
                    // Afficher un message d'erreur pour les champs invalides
                    if (!isNomValid) {
                        editTextNom.setError("Le nom doit contenir au moins 2 caractères");
                    }
                    if (!isPrenomValid) {
                        editTextPrenom.setError("Le prénom doit contenir au moins 2 caractères");
                    }
                    if (!isAgeValid) {
                        editTextAge.setError("L'âge doit être un nombre supérieur à 18");
                    }
                    if (!isPaysValid) {
                        Toast.makeText(MainActivity.this, "Veuillez sélectionner un pays", Toast.LENGTH_SHORT).show();
                    }
                    if (!isInteretsValid) {
                        Toast.makeText(MainActivity.this, "Veuillez cocher au moins un centre d'intérêt", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        MyDatabaseHelper dbHelper = new MyDatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        dbHelper.ajouter(nom, prenom, ageStr, pays);




        /*Cursor cursor = db.query(
                "users",
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);

        while (cursor.moveToNext()) {
            long id = cursor.getLong(cursor.getColumnIndexOrThrow("id"));
            nom = cursor.getString(cursor.getColumnIndexOrThrow("nom"));
             prenom = cursor.getString(cursor.getColumnIndexOrThrow("prenom"));
            ageStr = cursor.getInt(cursor.getColumnIndexOrThrow("age"));
             pays = cursor.getString(cursor.getColumnIndexOrThrow("pays"));
            String interets = cursor.getString(cursor.getColumnIndexOrThrow("interets"));

            // Faites quelque chose avec les données lues
        }*/

        //cursor.close();


    }
}
