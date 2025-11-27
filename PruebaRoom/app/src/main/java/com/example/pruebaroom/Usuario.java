package com.example.pruebaroom;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity
public class Usuario {
    @PrimaryKey
    @NotNull
    public String usuario;
   public String nombre;
   public String email;

    public Usuario(@NotNull String usuario,String nombre, String email) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.email = email;
    }
}
