package com.example.realdatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.realdatabase.modelo.Product;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ventas extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    private EditText et1,et5;
    private TextView txt2,txt3,txt4,txt6,txt8;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventas);
        et1=(EditText)findViewById(R.id.et1);
        txt2=(TextView) findViewById(R.id.txt2);
        txt3=(TextView) findViewById(R.id.txt3);
        txt4=(TextView) findViewById(R.id.txt4);
        et5=(EditText) findViewById(R.id.et5);
        txt6=(TextView) findViewById(R.id.txt6);

        txt8=(TextView) findViewById(R.id.txt8);
        iniciarFirebase();
        mDatabase = FirebaseDatabase.getInstance().getReference();

    }
    private void iniciarFirebase() {

        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();


    }
    public void buscar(View view) {
        String cod = et1.getText().toString();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Product").child(cod);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String nomb = snapshot.child("nombre").getValue().toString();
                String stock = snapshot.child("stock").getValue().toString();
                String cost = snapshot.child("costo").getValue().toString();
                String vent = snapshot.child("venta").getValue().toString();

                txt2.setText(nomb);
                txt3.setText(stock);
                txt4.setText(vent);
                txt8.setText(cost);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void regresar(View view){
        Intent i=new Intent(this, MainActivity.class); startActivity(i);
    }
    public void guardar(View view){
        double a=0,b=0,c;
        String cod = et1.getText().toString();
        String nomb = txt2.getText().toString();
        String stock=txt3.getText().toString();
        String cant = et5.getText().toString();
        String costo = txt4.getText().toString();
        String venta = txt8.getText().toString();
        a=Double.parseDouble(stock);
        b=Double.parseDouble(cant);
        c=a-b;

        Product p= new Product();
        p.setCodigo(cod);
        p.setNombre(nomb);
        p.setStock(String.valueOf(c));
        p.setCosto(costo);
        p.setVenta(venta);
        mDatabase.child("Product").child(p.getCodigo()).setValue(p);
        Toast.makeText(this, "Se Vendio con exito",
                Toast.LENGTH_SHORT).show();
    }
    public void total(View view){
        double a=0,b=0,c=0;
        String precio = txt4.getText().toString();
        String compra = et5.getText().toString();

        a=Double.parseDouble(precio);
        b=Double.parseDouble(compra);
        c=a*b;

        txt6.setText(String.valueOf(c));
    }


}