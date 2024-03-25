package com.esdesign.tareaimagen23.Modelo;

import java.util.PrimitiveIterator;

public class Fotografias {

    private Integer id_foto;
    private String imagen;
    private String descripcion;

    public Fotografias(){

    }

    public Fotografias(Integer id_foto, String imagen, String descripcion) {
        this.id_foto = id_foto;
        this.imagen = imagen;
        this.descripcion = descripcion;
    }

    public Integer getId_foto() {
        return id_foto;
    }

    public void setId_foto(Integer id_foto) {
        this.id_foto = id_foto;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
