package com.example.android.proyectodai;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UnivActivity extends AppCompatActivity {

    private AdminSQLiteOpenHelper admin;
    private SQLiteDatabase db;
    private EditText id, nom, est;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_univ);

        admin = new AdminSQLiteOpenHelper(this,"base", null, 1);
        db = admin.getWritableDatabase();
        Toast.makeText(this, "¡Base de datos abierta! :)", Toast.LENGTH_LONG).show();

        id = (EditText) findViewById(R.id.etIDu);
        nom = (EditText) findViewById(R.id.etNomu);
        est = (EditText) findViewById(R.id.etEstado);
    }

    /**
     * Método que da de alta una universidad
     * @param view
     */
    public void altaUniv(View view)
    {
        String i, n, e, m = "Fracaso. :( Error:";
        i = id.getText().toString();//ID de la universidad
        n = nom.getText().toString();//Nombre de la universidad
        e = est.getText().toString();//Estado donde esta la universidad
        ContentValues contentValues = new ContentValues();
        contentValues.put("idUniv", i);
        contentValues.put("nombre", n);
        contentValues.put("estado", e);
        long j = db.insert("univ", null, contentValues);
        if(j>0)
            m = "¡Éxito! Agregados:";
        Toast.makeText(this, m+" "+j, Toast.LENGTH_LONG).show();
    }

    /**
     * Método que modifica el estado o
     * el nombre de la universidad.
     * @param view
     */
    public void cambioUniv(View view)
    {
        String i, n, e, m = "Fracaso. :( Error:";
        i = id.getText().toString();//ID de la universidad
        n = nom.getText().toString();//Nuevo nombre de la universidad
        e = est.getText().toString();//Nuevo estado de la universidad
        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", n);
        contentValues.put("estado", e);
        long j = db.update("univ", contentValues, "idUniv = "+i, null);
        if(j>0)
            m = "¡Éxito! Cambiados:";
        Toast.makeText(this, m+" "+j, Toast.LENGTH_LONG).show();
    }

    /**
     * Método que consulta los datos de estado
     * y nombre de una universidad.
     * @param view
     */
    public void consultaUniv(View view)
    {
        String i = id.getText().toString();//ID de la universidad
        Cursor f = db.rawQuery("select * from univ where idUniv = "+i, null);
        if(f.getCount()>0)
        {
            f.moveToFirst();
            nom.setText(f.getString(1));
            est.setText(f.getString(2));
            f.close();
        }
        else
            Toast.makeText(this, "¡Error! No existe la universidad de clave "+i, Toast.LENGTH_LONG).show();
    }
}
