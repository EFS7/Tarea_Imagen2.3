package com.esdesign.tareaimagen23;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.esdesign.tareaimagen23.Configuracion.Backend;
import com.esdesign.tareaimagen23.Configuracion.SQLiteConexion;
import com.esdesign.tareaimagen23.Modelo.Fotografias;

import java.util.ArrayList;
import java.util.List;

public class ListaFotosActivity extends AppCompatActivity {

    SQLiteConexion con;
    Button refresh, nuevo_item;
    List<Fotografias> fotos_list;
    List<String> Arreglo;
    ListView listFotos;
    Integer id_foto_selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_fotos);

        con = new SQLiteConexion(this, Backend.DBnombre, null,1);

        refresh = (Button) findViewById(R.id.btnRefresh);
        nuevo_item = (Button) findViewById(R.id.btnNuevo);
        listFotos = (ListView) findViewById(R.id.list_fotos);

        ObtenerDatos();

        nuevo_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ventana_main = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(ventana_main);
            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObtenerDatos();
            }
        });

        listFotos.setOnItemClickListener((parent, view, position, id) -> {
            // Guardar la posición del elemento seleccionado
            id_foto_selected = position;
            Intent ver_foto = new Intent(ListaFotosActivity.this,VerImagen.class);
            ver_foto.putExtra("ID_FOTO", id_foto_selected);
            startActivity(ver_foto);
        });

    }

    private void ObtenerDatos() {
        SQLiteDatabase db = con.getReadableDatabase();
        Fotografias foto;
        fotos_list = new ArrayList<>();

        Cursor cursor = db.rawQuery(Backend.SelectAllFotos, null);

        while (cursor.moveToNext()) {
                foto = new Fotografias();
                foto.setId_foto(cursor.getInt(0));
                foto.setImagen(cursor.getString(1));
                foto.setDescripcion(cursor.getString(2));

                fotos_list.add(foto);
            }
        cursor.close();
        LlenarDatos();
    }

    private void LlenarDatos() {
        Arreglo = new ArrayList<String>();

        for(int x = 0; x < fotos_list.size(); x++){
            Arreglo.add(fotos_list.get(x).getId_foto() + " | " +
                    fotos_list.get(x).getDescripcion());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, Arreglo);
        listFotos.setAdapter(adapter);
    }

}