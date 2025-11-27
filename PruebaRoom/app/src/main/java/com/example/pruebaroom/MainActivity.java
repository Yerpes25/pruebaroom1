package com.example.pruebaroom;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView tvUser;
    EditText usuario,nombre,email;
    Button btnInsertar, btnModificar, btnBorrar, btnConsultar;
    List<Usuario> listaUsuarios;
    AppDatabase appDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvUser = findViewById(R.id.texto);
        btnBorrar = findViewById(R.id.btnBorrar);
        btnConsultar= findViewById(R.id.btnConsultar);
        btnInsertar= findViewById(R.id.btnInsertar);
        btnModificar = findViewById(R.id.btnModificar);

        usuario = findViewById(R.id.edUsername);
        nombre = findViewById(R.id.edNombre);
        email = findViewById(R.id.edEmail);
        appDatabase = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "bdPruebaRoom"
        ).allowMainThreadQueries().build();

        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appDatabase.daoUsuario().insertarUsuario(
                        new Usuario(usuario.getText().toString(),
                                nombre.getText().toString(),
                                email.getText().toString()));

                mostrar();
            }
        });

        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appDatabase.daoUsuario().actualizarUsuario(usuario.getText().toString(),
                        nombre.getText().toString(),
                        email.getText().toString());
                mostrar();
            }
        });

        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appDatabase.daoUsuario().borrarUsuario(
                        usuario.getText().toString());
                mostrar();
            }
        });

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrar();
            }
        });

    }

    private void mostrar(){
        listaUsuarios = appDatabase.daoUsuario().obtenerUsuarios();
        String texto = "";
        for (int i=0; i<listaUsuarios.size();i++){
            texto+="Usuario" + i + ", " + listaUsuarios.get(i).usuario + " , " + listaUsuarios.get(i).nombre
                    + " , " + listaUsuarios.get(i).email;
        }

        tvUser.setText(texto);
    }
}