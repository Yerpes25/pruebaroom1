package com.example.pruebaroom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface DaoUsuario {
    @Query("Select *from usuario")
    List<Usuario> obtenerUsuarios();
    @Query("Select * from usuario where nombre = :user")
    Usuario obtenerUsuario(String user);
    @Insert
    void insertarUsuario(Usuario...usuarios);

    @Query("update usuario set nombre = :name, email = :correo where usuario= :user")
    void actualizarUsuario(String user,String name, String correo);

    @Query("delete from usuario where usuario = :user")
    void borrarUsuario(String user);
}
