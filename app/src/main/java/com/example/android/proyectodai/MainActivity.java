package com.example.android.proyectodai;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Clase de la actividad principal. Sirve para abrir las demás pantallas.
 */
public class MainActivity extends AppCompatActivity {

    private AdminSQLiteOpenHelper admin;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instanciamos nuestra AdminSQLiteOpenHelper.
        admin = new AdminSQLiteOpenHelper(this, "base", null, 1);
        // Abrimos la base de datos.
        db = admin.getWritableDatabase();
        Toast.makeText(this, "¡Base de datos abierta! :)", Toast.LENGTH_LONG).show();
    }

    /**
     * Método para abrir la ventana que corresponda.
     * @param view indica el botón desde el cuál se pidió el cambio de pantalla.
     */
    public void abre(View view)
    {
        Intent intent;
        switch(view.getId())
        {
            case R.id.btnUni:
                intent = new Intent(this, UnivActivity.class);
                break;
            case R.id.btnCarrera:
                intent = new Intent(this, CarreraActivity.class);
                break;
            case R.id.btnAlum:
                intent = new Intent(this, AlumActivity.class);
                break;
            case R.id.btnEstadísticas:
                intent = new Intent(this, EstActivity.class);
                break;
            default:
                intent = new Intent(this, MainActivity.class);
        }
        startActivity(intent);
    }
}
