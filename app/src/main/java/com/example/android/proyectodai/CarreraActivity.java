package com.example.android.proyectodai;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CarreraActivity extends AppCompatActivity {

    private AdminSQLiteOpenHelper admin;
    private SQLiteDatabase db;
    private EditText id, nom, creds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrera);

        // Instanciamos nuestra AdminSQLiteOpenHelper.
        admin = new AdminSQLiteOpenHelper(this, "base", null, 1);
        // Abrimos la base de datos.
        db = admin.getWritableDatabase();
        Toast.makeText(this, "¡Base de datos abierta! :)", Toast.LENGTH_LONG).show();

        id = (EditText) findViewById(R.id.etIDc);
        nom = (EditText) findViewById(R.id.etNomc);
        creds = (EditText) findViewById(R.id.etCreds);
    }

    /**
     * Método que da de alta a una carrera
     * @param view
     */
    public void altaCarr(View view)
    {
        String i, n, c, m = "Fracaso. :( Error:";
        i = id.getText().toString();//ID de la carrera
        n = nom.getText().toString();//Nombre de la carrera
        c = creds.getText().toString();//Créditos de la carrera
        ContentValues contentValues = new ContentValues();
        contentValues.put("idCar", i);
        contentValues.put("nombre", n);
        contentValues.put("creds", c);
        long j = db.insert("carrera", null, contentValues);
        if(j>0)
            m = "¡Éxito! Agregados:";
        Toast.makeText(this, m+" "+j, Toast.LENGTH_LONG).show();
    }

    /**
     * Método que cambia el nombre o
     * los créditos de una carrera
     * @param view
     */
    public void cambioCarr(View view)
    {
        String i, n, c, m = "Fracaso. :( Error:";
        i = id.getText().toString();//ID de la carrera
        n = nom.getText().toString();//Nombre nuevo de la carrea
        c = creds.getText().toString();//Nuevos créditos de la carrera
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", n);
        contentValues.put("creds", c);
        long j = db.update("carrera", contentValues, "idCar = "+i, null);
        if(j>0)
            m = "¡Éxito! Cambiados:";
        Toast.makeText(this, m+" "+j, Toast.LENGTH_LONG).show();
    }

    /**
     * Método que proporciona los datos
     * de una carrera, dada por el usuario.
     * @param view
     */
    public void consultaCarr(View view)
    {
        String i = id.getText().toString();//ID de la carrera.
        Cursor f = db.rawQuery("select * from carrera where idCar = "+i, null);
        if(f.getCount()>0)
        {
            f.moveToFirst();
            nom.setText(f.getString(1));
            creds.setText(f.getString(2));
            f.close();
        }
        else
            Toast.makeText(this, "¡Error! No existe la carrera de clave "+i, Toast.LENGTH_LONG).show();
    }
}
