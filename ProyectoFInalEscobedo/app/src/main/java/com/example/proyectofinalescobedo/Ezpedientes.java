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

public class Ezpedientes extends AppCompatActivity {
    public EditText nombreCom, edad, correo, tratamientoPre, tratamientoAct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ezpedientes);

        nombreCom = (EditText) findViewById(R.id.etNombreCompleto);
        edad = (EditText) findViewById(R.id.etEdadExpediente);
        correo = (EditText) findViewById(R.id.etCorreoElectronico);
        tratamientoPre = (EditText) findViewById(R.id.etTratamientoPrevio);
        tratamientoAct = (EditText) findViewById(R.id.etTratamientoActual);

    }

    public void agregarExpediente(View view) {
        SQLiteDatabase bd = null;

        try {
            // Objeto de administración de la base de datos SQLite
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 3);
            bd = admin.getWritableDatabase(); // Base de datos disponible para escritura

            // Capturar los datos del formulario para procesarlos a la tabla expediente
            String Nombre = nombreCom.getText().toString();
            String Edad = edad.getText().toString();
            String Correo = correo.getText().toString();
            String TratamientoPre = tratamientoPre.getText().toString();
            String TratamientoAct = tratamientoAct.getText().toString();

            // Se crea un contenedor de variables para inyectar los valores a la tabla
            ContentValues registro = new ContentValues();

            // Se integran los campos de la tabla expediente con los valores del formulario
            registro.put("nombreCompletoBD", Nombre);
            registro.put("edadBD", Edad);
            registro.put("correoBD", Correo);
            registro.put("tratamientoPBD", TratamientoPre);
            registro.put("tratamientoABD", TratamientoAct);

            // Se inserta el registro en la tabla expediente
            bd.insert("expedientes", null, registro);

            // Limpiar campos del formulario
            nombreCom.setText("");
            edad.setText("");
            correo.setText("");
            tratamientoPre.setText("");
            tratamientoAct.setText("");

            // Ventana emergente
            Toast.makeText(this, "Expediente agregado correctamente", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            // Manejo de la excepción
            Toast.makeText(this, "Error al agregar expediente: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace(); // Para mostrar la excepción en el log
        } finally {
            if (bd != null && bd.isOpen()) {
                bd.close(); // Cierra la base de datos al final
            }
        }
    }


    public void consultarExpediente(View view) { // Inicia método consulta empleado
        // Objeto de administración de la base de datos SQLite
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 3);
        SQLiteDatabase bd = admin.getWritableDatabase(); // Base de datos disponible para escritura

        // Campo distintivo de búsqueda a la tabla empleado
        String Nombre = nombreCom.getText().toString();

        // Verifica que el campo nombre no esté vacío
        if (!Nombre.isEmpty()) {
            // Cursor para consulta de un registro utilizando argumentos
            Cursor fila = bd.rawQuery("SELECT edadBD, correoBD, tratamientoPBD, tratamientoABD FROM expedientes WHERE nombreCompletoBD = ?", new String[]{Nombre});

            if (fila.moveToFirst()) {
                edad.setText(fila.getString(0)); // Imprime el puesto desde la tabla
                correo.setText(fila.getString(1)); // Imprime los días trabajados desde la tabla
                tratamientoPre.setText(fila.getString(2)); // Imprime el sueldo diario desde la tabla
                tratamientoAct.setText(fila.getString(3));
            } else {
                // Ventana emergente
                Toast.makeText(this, "Error: El expediente no existe", Toast.LENGTH_LONG).show();
            }

            fila.close(); // Cierra el cursor después de su uso
        } else {
            // Ventana emergente si el campo está vacío
            Toast.makeText(this, "Por favor, ingresa un nombre", Toast.LENGTH_LONG).show();
        }

        bd.close(); // Cierra la base de datos
    } // Termina método consulta empleado

    public void editarExpediente(View view) { // Inicia método de editar

        // Objeto de administración para la base de datos SQLite
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 3);
        SQLiteDatabase bd = admin.getWritableDatabase(); // Base de datos disponible para escritura

        // Capturar los datos del formulario para procesarlos en la tabla articulo
        String Nombre = nombreCom.getText().toString();
        String Edad = edad.getText().toString();
        String Correo = correo.getText().toString();
        String TratamientoPre = tratamientoPre.getText().toString();
        String TratamientoAct = tratamientoAct.getText().toString();

        // Validar que los campos no estén vacíos
        if (!Nombre.isEmpty() && !Edad.isEmpty() && !Correo.isEmpty() && !TratamientoPre.isEmpty() && !TratamientoAct.isEmpty()) {

            // Se crea un contenedor de variables para inyectar los valores a la tabla
            ContentValues registro = new ContentValues();

            // Se integran los campos de la tabla articulo con los valores del formulario
            registro.put("nombreCompletoBD", Nombre);
            registro.put("edadBD", Edad);
            registro.put("correoBD", Correo);
            registro.put("tratamientoPBD", TratamientoPre);
            registro.put("tratamientoABD", TratamientoAct);
            //Registro no se cambiar

            // Instrucción SQL para editar
            int actualizacion = bd.update("expedientes", registro, "nombreCompletoBD = ?", new String[]{Nombre});

            bd.close();

            if (actualizacion > 0) {
                Toast.makeText(this, "Expediente actualizada\nÉxito\nVerifica consulta", Toast.LENGTH_LONG).show();
                this.nombreCom.setText(null);
                this.edad.setText(null);
                this.correo.setText(null);
                this.tratamientoPre.setText(null);
                this.tratamientoAct.setText(null);
            } else {
                Toast.makeText(this, "Error\nEl nombre no existe\nSin cambios", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Por favor, llena todos los campos", Toast.LENGTH_LONG).show();
        }
    }

    public void eliminarExpediente(View view) { // Inicia método para eliminar producto
        // Objeto de administración para la base de datos SQLite
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 3);
        SQLiteDatabase bd = admin.getWritableDatabase(); // Base de datos disponible para escritura

        // Campo distintivo de búsqueda a la tabla artículo basado en el nombre del producto
        String NombreBaja = nombreCom.getText().toString();

        // Verifica que el nombre no esté vacío antes de intentar eliminar
        if (!NombreBaja.isEmpty()) {
            // Instrucción SQL para eliminar el registro mediante el nombre del producto
            int confirmarBaja = bd.delete("expedientes", "nombreCompletoBD = ?", new String[]{NombreBaja});
            bd.close();

            if (confirmarBaja > 0) {
                Toast.makeText(this, "Expediente eliminado\nÉxito", Toast.LENGTH_LONG).show();
                this.nombreCom.setText(null);
                this.edad.setText(null);
                this.correo.setText(null);
                this.tratamientoPre.setText(null);
                this.tratamientoAct.setText(null);
            } else {
                Toast.makeText(this, "Error\nEl nombre no existe", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Por favor, ingresa un nombre", Toast.LENGTH_LONG).show();
        }

    }

}