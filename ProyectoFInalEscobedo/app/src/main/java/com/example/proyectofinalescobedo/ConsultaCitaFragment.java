package com.example.proyectofinalescobedo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ConsultaCitaFragment extends Fragment {
//ahora esta clase es para acerca del negocio

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_consulta_cita, container, false);
    }
}