package com.example.proyectofinalescobedo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class CitaFragment extends Fragment {
    public Button geo, telefono;

//ahora esta clase es para los tratamientos
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_cita, container, false);
        geo = view.findViewById(R.id.btnGeo);
        telefono = view.findViewById(R.id.btnTelefono);

        geo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentGEO = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:19.32175445049282, -99.06335411488281"));
                startActivity(intentGEO);
            }
        });

        telefono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intenTel = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:5520665036"));
                startActivity(intenTel);
            }
        });
        return view;
    }




}