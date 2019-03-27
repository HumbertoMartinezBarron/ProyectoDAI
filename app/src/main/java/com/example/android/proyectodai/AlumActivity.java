package com.example.android.proyectodai;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AlumActivity extends AppCompatActivity {

    private EditText id, nom, carr, univ;
    private TextView iCarr, iUni;

    private AdminSQLiteOpenHelper admin;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alum);

        admin = new AdminSQLiteOpenHelper(this, "base", null, 1);
        db = admin.getWritableDatabase();

        id = (EditText) findViewById(R.id.etIDa);
        nom = (EditText) findViewById(R.id.etNoma);
        carr = (EditText) findViewById(R.id.etCarr);
        univ = (EditText) findViewById(R.id.etUni);
        iCarr = (TextView) findViewById(R.id.IDCar);
        iUni = (TextView) findViewById(R.id.IDUni);
    }

    /**
     * Método que consulta la ID de
     * la carrera, dada por el usuario.
     * @param view
     */
    public void consultaIDc(View view)
    {
        String nombre_carrera = carr.getText().toString();

        Cursor f = db.rawQuery("select * from carrera where nombre = '"+nombre_carrera+"'", null);
        if(f.getCount()>0)
        {
            f.moveToFirst();
            iCarr.setText(f.getString(0));
            f.close();
        }
        else
            Toast.makeText(this, "¡Error! No existe la carrera de nombre "+nombre_carrera, Toast.LENGTH_LONG).show();
    }

    /**
     * Método que consulta la ID de
     * la universidad, dada por el usuario.
     * @param view
     */
    public void consultaIDu(View view)
    {
        String nombre_carrera = univ.getText().toString();

        Cursor f = db.rawQuery("select * from univ where nombre = '"+nombre_carrera+"'", null);
        if(f.getCount()>0)
        {
            f.moveToFirst();
            iUni.setText(f.getString(0));
            f.close();
        }
        else
            Toast.makeText(this, "¡Error! No existe la universidad de nombre "+nombre_carrera, Toast.LENGTH_LONG).show();
    }

    /**
     * Método que da de alta a un alumno.
     * @param view
     */
    public void alta(View view)
    {
        String i, n, c, u, m = "Fallo. :( Error:";
        i = id.getText().toString();//ID del alumno
        n = nom.getText().toString();//Nombre del alumno
        c = iCarr.getText().toString();//ID de la carrera que cursa el alumno
        u = iUni.getText().toString();//ID de la universidad a la que pertenece el alumno

        ContentValues contentValues = new ContentValues();
        contentValues.put("cu", i);
        contentValues.put("nombre", n);
        contentValues.put("idc", c);
        contentValues.put("idu", u);
        long j = db.insert("alumno", null, contentValues);
        if(j>0)
            m = "¡Éxito! :) Agregados:";
        Toast.makeText(this, m+" "+j, Toast.LENGTH_LONG).show();
    }

    /**
     * Metodo que da de baja a un alumno.
     * @param view
     */
    public void baja(View view)
    {
        String i = id.getText().toString();//ID del alumno.
        String m = "Fracaso. :( Error:";
        long j = db.delete("alumno", "cu = "+i, null);
        if(j>0)
            m = "¡Éxito! :) Borrados:";
        Toast.makeText(this, m+" "+j, Toast.LENGTH_LONG).show();
    }
}
