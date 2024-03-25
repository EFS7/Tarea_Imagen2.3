package com.esdesign.tareaimagen23.Configuracion;

import android.database.sqlite.SQLiteDatabase;


public class Backend {
    public static final String DBnombre = "TareaFotos";
    public static final String TablaFotos = "fotos";
    public static final String id_foto = "id_foto";
    public static final String imagen = "imagen";
    public static final String descripcion = "descripcion";

    public static final String CreateTablaFotos = "Create table "+TablaFotos+"("+
            "id_foto INTEGER PRIMARY KEY AUTOINCREMENT, imagen BLOB, descripcion, TEXT)";

    public static final String DropTablaFotos = "DROP TABLE IF EXISTS "+TablaFotos;

    public static final String SelectAllFotos = "SELECT * FROM "+TablaFotos;


}
