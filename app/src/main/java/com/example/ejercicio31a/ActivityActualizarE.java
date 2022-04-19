package com.example.ejercicio31a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ejercicio31a.Configuracion.SQLiteConexion;
import com.example.ejercicio31a.Configuracion.bdTransaccion;
import com.example.ejercicio31a.Configuracion.empleados;

public class ActivityActualizarE extends AppCompatActivity {

    Button btnAtras, btnllamar;
    SQLiteConexion conexion;
    //Spinner puesto;
    TextView id;
    EditText  nombres, apellidos, edad, direccion,puesto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_e);

        conexion = new SQLiteConexion(this, bdTransaccion.NameDatabase, null, 1);

        //Botones

        Button eliminar = (Button) findViewById(R.id.btnEliminar);
        Button actualizar = (Button) findViewById(R.id.btnActualizar);


        id = (TextView) findViewById(R.id.txtID);
        nombres = (EditText) findViewById(R.id.txtNombreActua);
        apellidos = (EditText) findViewById(R.id.txtApellidoActua);
        edad = (EditText) findViewById(R.id.txtEdadActua);
        direccion = (EditText) findViewById(R.id.txtDireccionActua);
        puesto = (EditText) findViewById(R.id.txtPuestoActua);
          btnAtras = (Button) findViewById(R.id.btnVolver);


        Bundle obj = getIntent().getExtras();

        empleados mostra = null;

        if (obj != null) {
            mostra = (empleados) obj.getSerializable("empleado");

            id.setText(mostra.getId().toString());
            nombres.setText(mostra.getNombre().toString());
            apellidos.setText(mostra.getApellidos().toString());
            edad.setText(mostra.getEdad());
            direccion.setText(mostra.getDireccion().toString());
            puesto.setText(mostra.getPuesto().toString());
        }
   // volver a pagina principal
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ActivityLista.class);
                startActivity(intent);
            }
        });



        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!nombres.getText().toString().isEmpty() && !apellidos.getText().toString().isEmpty() && !edad.getText().toString().isEmpty() && !direccion.getText().toString().isEmpty() ){
                    Actualizar();
                }
                else{
                    Toast.makeText(getApplicationContext()," Llenar los espacios vacios" ,Toast.LENGTH_LONG).show();

                }
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Eliminar();
            }
        });

    }

    private void Eliminar() {
        SQLiteDatabase db = conexion.getWritableDatabase();

        String[] params = {id.getText().toString()};
        String wherecond = bdTransaccion.id + "=?";
        db.delete(bdTransaccion.tablaempleados, wherecond, params);
        Toast.makeText(getApplicationContext(), "Dato eliminado", Toast.LENGTH_LONG).show();
        limpiar();
    }

    private void limpiar() {
        id.setText("");
        apellidos.setText("");
        nombres.setText("");
        direccion.setText("");
        edad.setText("");
    }

    private void Actualizar() {
        SQLiteDatabase db = conexion.getWritableDatabase();
        String[] params = {id.getText().toString()};
        ContentValues valores = new ContentValues();
        valores.put(bdTransaccion.nombre, nombres.getText().toString());
        valores.put(bdTransaccion.apellidos, apellidos.getText().toString());
        valores.put(bdTransaccion.edad, edad.getText().toString());
        valores.put(bdTransaccion.direccion, direccion.getText().toString());
        valores.put(bdTransaccion.puesto, puesto.getText().toString());
        db.update(bdTransaccion.tablaempleados, valores, bdTransaccion.id + "=?", params);
        Toast.makeText(getApplicationContext(), "Dato actualizado", Toast.LENGTH_LONG).show();
        limpiar();

    }
}