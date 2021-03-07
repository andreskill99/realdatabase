package com.example.realdatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.realdatabase.modelo.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class listapro extends AppCompatActivity {
    ListView mylistView;
    DatabaseReference ref;
    FirebaseDatabase database;
    ArrayList<String> list;
    ArrayAdapter<String>adapter;
    Product product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listapro);
        product=new Product();

mylistView=(ListView) findViewById(R.id.listview1);
database=FirebaseDatabase.getInstance();
ref=database.getReference("Product");
list=new ArrayList<>();
adapter=new ArrayAdapter<String>(this,R.layout.pro_info,R.id.proInfo,list);
ref.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
     for (DataSnapshot ds: snapshot.getChildren()){
         product=ds.getValue(Product.class);

         list.add("Codigo: "+product.getCodigo()
                 +"\nNombre: "+product.getNombre()
                 +"\nStock:  "+product.getStock()
                 +"\nCosto:  "+product.getCosto()
                 +"\nVenta:  "+product.getVenta());
     }
     mylistView.setAdapter(adapter);
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
});
    }



}
