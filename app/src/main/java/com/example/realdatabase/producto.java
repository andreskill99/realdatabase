package com.example.realdatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.realdatabase.modelo.Product;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Tag;



public class producto extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    private EditText et1,et2,et3,et4,et5;
    private DatabaseReference mDatabase;
    //producto productoSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);
        et1=(EditText)findViewById(R.id.et1);
        et2=(EditText)findViewById(R.id.et2);
        et3=(EditText)findViewById(R.id.et3);
        et4=(EditText) findViewById(R.id.et4);
        et5=(EditText) findViewById(R.id.et5);
        iniciarFirebase();
        mDatabase = FirebaseDatabase.getInstance().getReference();



    }
    private void iniciarFirebase() {

        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();


    }
    public void guardar(View view){
        String cod = et1.getText().toString();
        String nomb = et2.getText().toString();
        String stock = et3.getText().toString();
        String costo = et4.getText().toString();
        String venta = et5.getText().toString();

        Product p= new Product();
        p.setCodigo(cod);
        p.setNombre(nomb);
        p.setStock(stock);
        p.setCosto(costo);
        p.setVenta(venta);
        mDatabase.child("Product").child(p.getCodigo()).setValue(p);
        Toast.makeText(this, "Guardado con exito",
                Toast.LENGTH_SHORT).show();
    }
    public void regresar(View view){
        Intent i=new Intent(this, MainActivity.class); startActivity(i);
    }
    public void actualizar(View view){

        String cod = et1.getText().toString();
        String nomb = et2.getText().toString();
        String stock = et3.getText().toString();
        String costo = et4.getText().toString();
        String venta = et5.getText().toString();

        Product p= new Product();
        p.setCodigo(cod);
        p.setNombre(nomb);
        p.setStock(stock);
        p.setCosto(costo);
        p.setVenta(venta);
        mDatabase.child("Product").child(p.getCodigo()).setValue(p);
        Toast.makeText(this, "Se Actualizo con exito",
                Toast.LENGTH_SHORT).show();
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

                et2.setText(nomb);
                et3.setText(stock);
                et4.setText(cost);
                et5.setText(vent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void borrar(View view){
        String cod = et1.getText().toString();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        Query applesQuery = ref.child("Product").child(cod);

        applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                        appleSnapshot.getRef().removeValue();
                        Toast toast1 =
                                Toast.makeText(getApplicationContext(),
                                        "Registro borrado exitosamente", Toast.LENGTH_SHORT);

                        toast1.show();
                        break;


                }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                "Error al borrar", Toast.LENGTH_SHORT);

                toast1.show();
            }
        });
        }
}