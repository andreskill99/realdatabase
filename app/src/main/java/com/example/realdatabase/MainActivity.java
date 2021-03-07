package com.example.realdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;
import android.os.Bundle;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        spinner = (Spinner) findViewById(R.id.spinner);
        String []opciones={"Persona","Producto","Inventario"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.simple_spinner_dropdown_item, opciones);
        spinner.setAdapter(adapter);
    }

    public void Register(View v){

          Intent i=new Intent(this, Registro.class);
          startActivity(i);


    }
    public void entrar (View v){

            EditText user = findViewById(R.id.et1);
            String usuario = user.getText().toString();
            EditText pass = findViewById(R.id.et2);
            String password = pass.getText().toString();
            if (!usuario.isEmpty() && !password.isEmpty()) {
                FirebaseAuth.getInstance()
                        .signInWithEmailAndPassword(usuario, password);
                Toast q = Toast.makeText(this, "Ingreso exitoso ", Toast.LENGTH_LONG);
                q.show();

                String pais = spinner.getSelectedItem().toString();
                if (pais.equals("Persona")) {

                    Intent i = new Intent(this, persona.class);
                    Bundle bun= new Bundle();
                    bun.putString("correo",usuario);
                    i.putExtras(bun);
                    startActivity(i);
                } else
                if (pais.equals("Producto")) {
                    Intent i = new Intent(this, producto.class);
                    startActivity(i);
                } else
                if (pais.equals("Inventario")) {
                    Intent i = new Intent(this, inventario.class);
                    startActivity(i);
                }

            }else{


                Toast.makeText(MainActivity.this, "Usuario o contraseña incorrectos",
                        Toast.LENGTH_SHORT).show();
                }

    }



}
