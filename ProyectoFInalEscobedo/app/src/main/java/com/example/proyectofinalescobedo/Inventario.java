package com.example.proyectofinalescobedo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Inventario extends AppCompatActivity {
    public EditText nombreProducto, cantidadProducto, numeroProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inventario);

        nombreProducto = (EditText) findViewById(R.id.etNombreProducto);
        cantidadProducto = (EditText) findViewById(R.id.etCantidadProducto);
        numeroProducto = (EditText) findViewById(R.id.etNumeroProducto);

    }

    public void agregarExpediente(View view) {

        //objeto de administracion ala base de datos sqlite
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 3);
        SQLiteDatabase bd = admin.getWritableDatabase();//base de datos disponible para escritura

        //capturar los datos del formulario para procesarlos a la tabla articulo
        String NombreProducto = nombreProducto.getText().toString();
        String Cantidad = cantidadProducto.getText().toString();
        String NumeroProducto = numeroProducto.getText().toString();



        //Se crea un contenedor de variables para inyectar los valores a la tabla
        ContentValues registro = new ContentValues();

        //se integra los campos de la tabla articulo con los valores del formulario
        registro.put("nombreProductoBD", NombreProducto);
        registro.put("cantidadBD", Cantidad);
        registro.put("numProductoBD", NumeroProducto);


        //se inserta registro en la tabla articulo
        bd.insert("inventario", null, registro);
        bd.close();

        //limpia campos de formulario
        nombreProducto.setText("");
        cantidadProducto.setText("");
        numeroProducto.setText("");



        // ventana emergente
        Toast.makeText(this, "Producto agregado correctamente \n", Toast.LENGTH_LONG).show();
    }

    public void consultarProducto(View view) { // Inicia método consulta empleado
        // Objeto de administración de la base de datos SQLite
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 3);
        SQLiteDatabase bd = admin.getWritableDatabase(); // Base de datos disponible para escritura

        // Campo distintivo de búsqueda a la tabla empleado
        String Nombre = nombreProducto.getText().toString();

        // Verifica que el campo nombre no esté vacío
        if (!Nombre.isEmpty()) {
            // Cursor para consulta de un registro utilizando argumentos
            Cursor fila = bd.rawQuery("SELECT cantidadBD, numProductoBD FROM inventario WHERE nombreProductoBD = ?", new String[]{Nombre});

            if (fila.moveToFirst()) {
                cantidadProducto.setText(fila.getString(0)); // Imprime el puesto desde la tabla
                numeroProducto.setText(fila.getString(1)); // Imprime los días trabajados desde la tabla

            } else {
                // Ventana emergente
                Toast.makeText(this, "Error: El producto no existe", Toast.LENGTH_LONG).show();
            }

            fila.close(); // Cierra el cursor después de su uso
        } else {
            // Ventana emergente si el campo está vacío
            Toast.makeText(this, "Por favor, ingresa un nombre", Toast.LENGTH_LONG).show();
        }

        bd.close(); // Cierra la base de datos
    }

    public void editarProducto(View view) { // Inicia método de editar

        // Objeto de administración para la base de datos SQLite
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 3);
        SQLiteDatabase bd = admin.getWritableDatabase(); // Base de datos disponible para escritura

        // Capturar los datos del formulario para procesarlos en la tabla articulo
        String NombreProducto = nombreProducto.getText().toString();
        String Cantidad = cantidadProducto.getText().toString();
        String NumeroProducto = numeroProducto.getText().toString();

        // Validar que los campos no estén vacíos
        if (!NombreProducto.isEmpty() && !Cantidad.isEmpty() && !NumeroProducto.isEmpty()) {

            // Se crea un contenedor de variables para inyectar los valores a la tabla
            ContentValues registro = new ContentValues();

            // Se integran los campos de la tabla articulo con los valores del formulario
            registro.put("nombreProductoBD", NombreProducto);
            registro.put("cantidadBD", Cantidad);
            registro.put("numProductoBD", NumeroProducto);
            //Registro no se cambiar

            // Instrucción SQL para editar
            int actualizacion = bd.update("inventario", registro, "nombreProductoBD = ?", new String[]{NombreProducto});

            bd.close();

            if (actualizacion > 0) {
                Toast.makeText(this, "Cita actualizada\nÉxito\nVerifica consulta", Toast.LENGTH_LONG).show();
                this.nombreProducto.setText(null);
                this.cantidadProducto.setText(null);
                this.numeroProducto.setText(null);
            } else {
                Toast.makeText(this, "Error\nEl nombre no existe\nSin cambios", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Por favor, llena todos los campos", Toast.LENGTH_LONG).show();
        }
    }

    public void eliminarProducto(View view) { // Inicia método para eliminar producto
        // Objeto de administración para la base de datos SQLite
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 3);
        SQLiteDatabase bd = admin.getWritableDatabase(); // Base de datos disponible para escritura

        // Campo distintivo de búsqueda a la tabla artículo basado en el nombre del producto
        String NombreBaja = nombreProducto.getText().toString();

        // Verifica que el nombre no esté vacío antes de intentar eliminar
        if (!NombreBaja.isEmpty()) {
            // Instrucción SQL para eliminar el registro mediante el nombre del producto
            int confirmarBaja = bd.delete("inventario", "nombreProductoBD = ?", new String[]{NombreBaja});
            bd.close();

            if (confirmarBaja > 0) {
                Toast.makeText(this, "Producto eliminado\nÉxito", Toast.LENGTH_LONG).show();
                this.nombreProducto.setText(null);
                this.cantidadProducto.setText(null);
                this.numeroProducto.setText(null);
            } else {
                Toast.makeText(this, "Error\nEl producto no existe", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Por favor, ingresa un nombre", Toast.LENGTH_LONG).show();
        }

    }
}