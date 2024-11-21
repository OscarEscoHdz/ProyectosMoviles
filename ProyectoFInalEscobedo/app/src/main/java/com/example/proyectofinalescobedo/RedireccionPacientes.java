package com.example.proyectofinalescobedo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class RedireccionPacientes extends AppCompatActivity {
    public Button geo1, geo2;
    private WebView webView = null;
    private WebView webView2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_redireccion_pacientes);

        //integracion xml-java
        this.webView = (WebView) findViewById(R.id.webview);
        this.webView2 = (WebView) findViewById(R.id.webview2);

        //Apuntador a memoria a identificar componente fijo embebido externo
        WebSettings webSettings = webView.getSettings();
        webView.loadUrl("https://www.doctoralia.com.mx/erika-jazmin-carbajal-orozco/dentista-odontologo/iztapalapa?prevent-patient-app-banner=true&utm_source=google&utm_medium=gmb&utm_campaign=379779&utm_content=book_visit&hl=es-419&gei=3oE-Z-rXJbbDp84P3eHK-AQ&rwg_token=AJKvS9WjJpmaEyEru8nDxFnz0Wf-aJUszvc3eUedelbftPw3VQA9znLXsjpKI2UOFnprizVA_Uv5D3XiQfSsMxwpvmu-36GTAg%3D%3D#highlight-calendar");
        webView2.loadUrl("https://business.google.com/v/clo-dental/03689122850441914324/13d8/_?caid=21401891425&agid=164580788860&gclid=CjwKCAiArva5BhBiEiwA-oTnXZs7PHFLfaSghanAn51sDRRG209YgT9XTTwwOYah8uKjXV94iNdgzhoC0GsQAvD_BwE");

        geo1 = findViewById(R.id.btnUbicacion1);
        geo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentGEO = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:19.32175445049282, -99.06335411488281"));
                startActivity(intentGEO);
            }
        });

        geo2 = findViewById(R.id.btnUbucacion2);
        geo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentGEO = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:19.339528370894694, -99.07141833582912"));
                startActivity(intentGEO);
            }
        });
    }
}