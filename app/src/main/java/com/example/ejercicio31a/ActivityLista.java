package com.example.ejercicio31a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.ejercicio31a.Configuracion.SQLiteConexion;
import com.example.ejercicio31a.Configuracion.bdTransaccion;
import com.example.ejercicio31a.Configuracion.empleados;

import java.util.ArrayList;

public class ActivityLista extends AppCompatActivity {

    Button btnNuevoEmpleadoAgg;
    SQLiteConexion conexion;
    ListView listapersonas;
    ArrayList<empleados> lista;
    ArrayList<String> ArregloPersonas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);



        //llenar la lista
        conexion=new SQLiteConexion(this, bdTransaccion.NameDatabase, null, 1);
        listapersonas=(ListView) findViewById(R.id.listapersonas);

        ObtenerListaEmpleados();

        ArrayAdapter adp = new ArrayAdapter( this, android.R.layout.simple_list_item_1, ArregloPersonas);
        listapersonas.setAdapter(adp);

        //doble click
        listapersonas.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                empleados mostrarr = lista.get(i);

                Intent intent = new Intent(ActivityLista.this, ActivityActualizarE.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("empleado", mostrarr);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();

            }
        });

        // Para ir a agregar un nuevo empleado
        btnNuevoEmpleadoAgg = (Button) findViewById(R.id.btnAgregarNuevoEmp);
        btnNuevoEmpleadoAgg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void ObtenerListaEmpleados() {

        SQLiteDatabase db = conexion.getReadableDatabase();
        empleados list_personas = null;
        lista = new ArrayList<empleados>();

        //cursor de base de dats : nos apoya a recorrer la informacion de la tabla a la cual consultamos//

        Cursor cursor = db.rawQuery("SELECT * FROM " + bdTransaccion.tablaempleados, null);

        //Recorremos la informacion del cursor

        while (cursor.moveToNext()) {
            list_personas = new empleados();
            list_personas.setId(cursor.getInt(0));
            // list_personas.setId(cursor.getString(0));
            list_personas.setNombre(cursor.getString(1));
            list_personas.setApellidos(cursor.getString(2));
            list_personas.setEdad(cursor.getString(3));
            list_personas.setDireccion(cursor.getString(4));
            list_personas.setPuesto(cursor.getString(5));
            // list_personas.setImagen(cursor.getBlob(5));
            lista.add(list_personas);
        }
        cursor.close();

        filllist();


    }

    private void filllist() {
        ArregloPersonas = new ArrayList<String>();

        for (int i = 0; i < lista.size(); i++) {
            ArregloPersonas.add(lista.get(i).getId() + " | "
                    + lista.get(i).getNombre() + " "
                    + lista.get(i).getApellidos() + " || "
                   /* + lista.get(i).getEdad() + " años |"
                    + lista.get(i).getDireccion() + " |"*/
                    + lista.get(i).getPuesto());
        }
    }
}