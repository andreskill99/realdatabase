package com.example.realdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

    }
    public void registrarse(View view) {
        EditText user = findViewById(R.id.et1);
        String usuario = user.getText().toString();
        EditText pass = findViewById(R.id.et2);
        String password = pass.getText().toString();
        if (!usuario.isEmpty() && !password.isEmpty()) {
            FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(usuario, password);
            Toast q = Toast.makeText(this, "Registro Completo ", Toast.LENGTH_LONG);
            q.show();
            Intent i = new Intent(this, persona.class);
            Bundle bun= new Bundle();
            bun.putString("correo",usuario);
            i.putExtras(bun);
            startActivity(i);


        }
        else{
            Toast q = Toast.makeText(this, "No se ha podido completar el registro ", Toast.LENGTH_LONG);
            q.show();
        }
    }
}