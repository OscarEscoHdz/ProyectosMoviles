package com.example.proyectofinalescobedo;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Tratamientos extends AppCompatActivity {

    public SearchView searchView;
    public ListView listView;
    public ArrayList<String> list;

    public TextView tvPrecio, tvDuracion;
    ArrayAdapter<String> adapter;

    private String precios[] = {"$6000","$200","500","$400"};
    private String duracion[] = {"2 anios", "1 hora", "1 hora", "tiempo no estimado"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tratamientos);

        searchView = (SearchView)findViewById(R.id.searchView);
        listView = (ListView) findViewById(R.id.lv1);

        //salida de precio
        tvPrecio = findViewById(R.id.tvPrecio);
        tvDuracion = findViewById(R.id.tvDuracion);

        this.tvPrecio.setText(null);

        list = new ArrayList<>();
        list.add("Brackets");
        list.add("Limpieza");
        list.add("Extraccion");
        list.add("Consulta de urgencia");


        //Integracion de los elementos a la lista
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        //Implementacion de los precios
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long id) {
                if (posicion < precios.length) {
                    tvPrecio.setText("Precio: " + precios[posicion]);  // Validación de índice de precios
                    tvDuracion.setText("Duracion:" + duracion[posicion]);
                } else {
                    tvPrecio.setText("Precio no disponible");  // Manejar el caso fuera de rango
                }
            }
        });


        //Busqueda automatizada
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Convertir el query a minúsculas
                String queryLower = query.toLowerCase();

                // Convertir cada elemento de la lista a minúsculas y verificar si contiene el query
                boolean encontrado = false;
                for (String item : list) {
                    if (item.toLowerCase().contains(queryLower)) {
                        encontrado = true;
                        break;
                    }
                }

                // Filtrar o mostrar mensaje si no se encuentra
                if (encontrado) {
                    adapter.getFilter().filter(queryLower); // Filtrar la consulta
                } else {
                    Toast.makeText(Tratamientos.this, "No se encontró palabra", Toast.LENGTH_LONG).show();
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                return false;
            }
        });

    }
}