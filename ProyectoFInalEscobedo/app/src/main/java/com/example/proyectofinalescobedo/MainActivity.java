package com.example.proyectofinalescobedo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;


public class MainActivity extends AppCompatActivity {

    private EditText correo, password;
    private Button ingresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        correo = (EditText) findViewById(R.id.etCorreo);
        password = (EditText) findViewById(R.id.etpassword);
        ingresar = (Button) findViewById(R.id.btnIngresar);

        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(correo.getText().toString().equals("admin")&&password.getText().toString().equals("123")){
                    Intent intent1 = new Intent(view.getContext(),Menu.class);
                    startActivity(intent1);
            }else{
                    correo.getText().toString().isEmpty();
                    correo.setError("Correo incorrecto");
                    password.getText().toString().isEmpty();
                    password.setError("Contraseña incorrecta");
                    YoYo.with(Techniques.Pulse).duration(1000).repeat(1).playOn(ingresar);
                }
            }
        });

    }
}