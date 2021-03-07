package com.example.realdatabase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

public class inventario extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario);

         //Toolbar  toolbar =findViewById(R.id.toolbar1);

        //setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.example_menu,menu);
        //MenuInflater inflater = getMenuInflater();
        //inflater.inflate(R.menu.example_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.vent:
                Intent i = new Intent(this, ventas.class);
                startActivity(i);
                break;
            case R.id.comp:
                Intent c = new Intent(this, compras.class);
                startActivity(c);
                break;
            case R.id.list:
                Intent l = new Intent(this, listapro.class);
                startActivity(l);
                break;
            case R.id.cerrar:
                Intent s = new Intent(this, MainActivity.class);
                startActivity(s);
                break;
        }
        return true;
    }



}