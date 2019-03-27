package com.example.android.proyectodai;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EstActivity extends AppCompatActivity {

    private EditText etEdo;
    private TextView datos, rank;

    private AdminSQLiteOpenHelper admin;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_est);

        // Instanciamos nuestra AdminSQLiteOpenHelper.
        admin = new AdminSQLiteOpenHelper(this, "base", null, 1);
        // Abrimos la base de datos.
        db = admin.getWritableDatabase();
        //Toast.makeText(this, "¡Base de datos abierta! :)", Toast.LENGTH_LONG).show();

        etEdo = (EditText) findViewById(R.id.etEdo);
        datos = (TextView) findViewById(R.id.data);
        rank = (TextView) findViewById(R.id.ranking);
        /**
         * Carga el cuadro de texto "rank" con todas las universidades
         * con el mayor número de alumnos.
         */
        Cursor f1 = db.rawQuery("select nombre from univ where idUniv in"+
                "(select idu from alumno group by idu order by count(cu) desc)", null);
        if(f1.getCount()>0){
            f1.moveToFirst();
            rank.setText(f1.getString(0).toString());
            while(!f1.isLast())
            {
                f1.moveToNext();
                rank.setText(rank.getText().toString()+"\n"+f1.getString(0));
            }
            f1.close();
        }
    }

    /**
     * Método que consulta todas las universidades que
     * pertenecen a un estado, dado por el usuario.
     * @param view
     */
    public void consultaUpE(View view)
    {
        String edo = etEdo.getText().toString();
        Cursor f = db.rawQuery("select nombre from univ where estado = '"+edo+"'", null);
        if(f.getCount()>0){
            f.moveToFirst();
            datos.setText("+ "+f.getString(0));
            while(!f.isLast())
            {
                f.moveToNext();
                datos.setText(datos.getText().toString()+"\n"+f.getString(0));
            }
            f.close();
        }
    }

    /**
     * Método que consulta el número de carreras que
     * imparten un universidad, dada por el usuario.
     * @param view
     */
    public void consultaCpU(View view){
        String Universidad = etEdo.getText().toString();
        Cursor f = db.rawQuery("select count(*) from carrera where idCar " +
                "in(select idc from alumno where idu " +
                "in(select idUniv from univ where nombre = '"+Universidad+"'))",null);
        if(f.getCount()>0){
            f.moveToFirst();
            datos.setText(f.getString(0).toString());
            f.close();
        }
    }

    /**
     * Método que consulta las universidades que
     * no imparten una carrera, dada por el usuario.
     * @param view
     */
    public void consultaUsC(View view){
        String Carrera = etEdo.getText().toString();
        Cursor f = db.rawQuery("select nombre from univ where idUniv not in(select idu from alumno where idc " +
                "in(select idCar from carrera where nombre = '"+Carrera+"'))",null);
        if(f.getCount()>0){
            f.moveToFirst();
            datos.setText(f.getString(0).toString());
            while(!f.isLast()) {
                f.moveToNext();
                datos.setText(datos.getText().toString() + "\n" + f.getString(0));
            }
            f.close();
        }

    }
}
