package com.example.proyectofinalescobedo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class ConsultaCitaFragment extends Fragment {
//ahora esta clase es para acerca del negocio
    public Button contacto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_consulta_cita, container, false);
        contacto = view.findViewById(R.id.btnContacto);

        contacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentURL = new Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/qr/SSZGHPORA5DZJ1"));
                startActivity(intentURL);
            }
        });


        return view;
    }
}