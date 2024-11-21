package com.example.proyectofinalescobedo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class Menu extends AppCompatActivity {

    public CardView cita, acecaDe, expedientes, inventario, tratamientos, redirigir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menu);

        cita = findViewById(R.id.cardCita);
        cita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(view.getContext(),AgendarCita.class);
                startActivity(intent1);
            }
        });

        acecaDe = findViewById(R.id.cardAcercaDe);
        acecaDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(view.getContext(),Citas.class);
                startActivity(intent1);
            }
        });

        expedientes = findViewById(R.id.cardExpedientes);
        expedientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(view.getContext(),Ezpedientes.class);
                startActivity(intent1);
            }
        });

        inventario = findViewById(R.id.cardInventario);
        inventario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(view.getContext(),Inventario.class);
                startActivity(intent1);
            }
        });

        tratamientos = findViewById(R.id.cardTratamientos);
        tratamientos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(view.getContext(),Tratamientos.class);
                startActivity(intent1);
            }
        });

        redirigir = findViewById(R.id.cardRedirigir);
        redirigir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(view.getContext(),RedireccionPacientes.class);
                startActivity(intent1);
            }
        });

        }


    }
