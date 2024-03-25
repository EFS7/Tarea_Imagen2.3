package com.esdesign.tareaimagen23;

import static com.esdesign.tareaimagen23.R.id.descripcion_txt;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.esdesign.tareaimagen23.Configuracion.Backend;
import com.esdesign.tareaimagen23.Configuracion.SQLiteConexion;

import java.util.Base64;

public class VerImagen extends AppCompatActivity {

    SQLiteConexion con;
    Integer id_foto_selected;
    String s_imagen, s_descripcion;
    TextView descripcion;
    ImageView foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_imagen);

        con = new SQLiteConexion(this, Backend.DBnombre, null, 1);

        descripcion = (TextView) findViewById(R.id.descripcion_tex);
        foto = (ImageView) findViewById(R.id.foto_imgV);
        
        id_foto_selected = getIntent().getIntExtra("ID_FOTO", -1);

        DatosPorID(id_foto_selected);
        
        descripcion.setText(s_descripcion);
        
        DecodeImage();

    }

    private void DecodeImage() {
        try{
            byte[] decoder = Base64.getDecoder().decode(s_imagen);
            Bitmap foto_decode = BitmapFactory.decodeByteArray(decoder, 0, decoder.length);

            foto.setImageBitmap(foto_decode);
            foto.setVisibility(View.VISIBLE);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }

    }

    public void DatosPorID(int id) {
        SQLiteDatabase db = con.getReadableDatabase();
        String[] columns = {"imagen", "descripcion"};
        String selection = "id_foto = ?";
        String[] selectionID = {String.valueOf(id)};

        Cursor cursor = db.query(Backend.TablaFotos, columns, selection, selectionID, null, null, null);

        if (cursor.moveToFirst()) {
            s_imagen = cursor.getString(cursor.getColumnIndex("imagen"));
            s_descripcion = cursor.getString(cursor.getColumnIndex("descripcion"));

        }
        cursor.close();
    }
}