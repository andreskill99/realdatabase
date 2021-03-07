package com.example.realdatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.realdatabase.modelo.Person;
import com.example.realdatabase.modelo.Product;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class persona extends AppCompatActivity {

    private EditText et1,et2,et3;
    private TextView et4;
    private RadioButton r1,r2;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String TAG = "DocSnippets";
    private Spinner spinner2;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persona);

        et1=(EditText)findViewById(R.id.et1);
        et2=(EditText)findViewById(R.id.et2);
        et3=(EditText)findViewById(R.id.et3);
        et4=(TextView)findViewById(R.id.et4);
        r1=(RadioButton)findViewById(R.id.r1);
        r2=(RadioButton)findViewById(R.id.r2);

        spinner2 = (Spinner) findViewById(R.id.spinner2);
        String []opciones={"Seleccione el Pais","Ecuador","Colombia","Venezuela"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.simple_spinner_dropdown_item, opciones);
        spinner2.setAdapter(adapter);


        Bundle bun=this.getIntent().getExtras();

        if(bun!=null) {

            String email = bun.getString("correo");
            et4.setText(email);


        }
        iniciarFirebase();
    }


    private void iniciarFirebase() {

        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }


    public void guardar(View view){

        String cedu = et1.getText().toString();
        String nomb = et2.getText().toString();

        String gen="";

        if(r1.isChecked()==true){
            gen="Hombre";
        }
        if(r2.isChecked()==true){
            gen="Mujer";
        }

        String pa="";

        String pais = spinner2.getSelectedItem().toString();
        if (pais.equals("Seleccione")) {
            pa="Seleccione";

        } else
        if (pais.equals("Ecuador")) {
            pa="Ecuador";

        } else
        if (pais.equals("Colombia")) {
            pa="Colombia";
        } else
        if (pais.equals("Peru")) {
            pa="Peru";
        } else
        if (pais.equals("EEUU")) {
            pa="EEUU";
        } else
        if (pais.equals("Mexico")) {
            pa="Mexico";
        }
        String provin = et3.getText().toString();
        String correo = et4.getText().toString();
        Person p= new Person();
        p.setCedula(cedu);
        p.setNombre(nomb);
        p.setProvincia(provin);
        p.setPais(pa);
        p.setCorreo(correo);
        databaseReference.child("Person").child(p.getCedula()).setValue(p);
        Toast.makeText(this, "Guardado con exito",
                Toast.LENGTH_SHORT).show();

        /*
        Person p = new Person(nomb,gen,provin,correo,pais);
        db.collection("persona").document("persona").set(p).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "DocumentSnapshot successfully written!");
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });
        Map<String, Object> data = new HashMap<>();

        // [START set_with_id]
        db.collection("persona").document("new-city-id").set(data);
*/
        Toast.makeText(this, "Se cargaron los datos personales",  Toast.LENGTH_SHORT).show();

    }




    public void regresar(View view){
        Intent i=new Intent(this, MainActivity.class); startActivity(i);
    }
}