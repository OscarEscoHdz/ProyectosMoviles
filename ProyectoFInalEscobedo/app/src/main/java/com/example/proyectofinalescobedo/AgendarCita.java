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

public class AgendarCita extends AppCompatActivity {
    public EditText nombre, apellidos, edad, telefono, fecha, descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_agendar_cita);

        nombre = (EditText) findViewById(R.id.etNombreCompleto);
        apellidos = (EditText) findViewById(R.id.etApellidosPaciente);
        edad = (EditText) findViewById(R.id.etEdadExpediente);
        telefono = (EditText) findViewById(R.id.etCorreoElectronico);
        fecha = (EditText) findViewById(R.id.etTratamientoPrevio);
        descripcion = (EditText) findViewById(R.id.etTratamientoActual);

    }

    public void agendarCita(View view) {
        SQLiteDatabase bd = null;

        try {
            // Objeto de administración a la base de datos SQLite
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 3);
            bd = admin.getWritableDatabase(); // Base de datos disponible para escritura

            // Capturar los datos del formulario para procesarlos a la tabla
            String Nombre = nombre.getText().toString();
            String Apellidos = apellidos.getText().toString();
            String Edad = edad.getText().toString();
            String Telefono = telefono.getText().toString();
            String Fecha = fecha.getText().toString();
            String Descripcion = descripcion.getText().toString();

            // Crear un contenedor de variables para inyectar los valores a la tabla
            ContentValues registro = new ContentValues();

            // Asociar los campos de la tabla con los valores del formulario
            registro.put("nombreBD", Nombre);
            registro.put("apellidosBD", Apellidos);
            registro.put("edadBD", Edad);
            registro.put("telefonoBD", Telefono);
            registro.put("fechaBD", Fecha);
            registro.put("descripcionBD", Descripcion);

            // Insertar el registro en la tabla "citas"
            bd.insert("citas", null, registro);

            // Limpiar campos del formulario
            nombre.setText("");
            apellidos.setText("");
            edad.setText("");
            telefono.setText("");
            fecha.setText("");
            descripcion.setText("");

            // Ventana emergente
            Toast.makeText(this, "Cita agendada correctamente", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            // Manejo de la excepción
            Toast.makeText(this, "Error al agendar la cita: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace(); // Para mostrar la excepción en el log
        } finally {
            if (bd != null && bd.isOpen()) {
                bd.close(); // Cierra la base de datos al final
            }
        }
    }



    public void consultarCita(View view) { // Inicia método consulta empleado
        // Objeto de administración de la base de datos SQLite
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 3);
        SQLiteDatabase bd = admin.getWritableDatabase(); // Base de datos disponible para escritura

        // Campo distintivo de búsqueda a la tabla empleado
        String Nombre = nombre.getText().toString();

        // Verifica que el campo nombre no esté vacío
        if (!Nombre.isEmpty()) {
            // Cursor para consulta de un registro utilizando argumentos
            Cursor fila = bd.rawQuery("SELECT apellidosBD, edadBD, telefonoBD, fechaBD, descripcionBD FROM citas WHERE nombreBD = ?", new String[]{Nombre});

            if (fila.moveToFirst()) {
                apellidos.setText(fila.getString(0)); // Imprime el puesto desde la tabla
                edad.setText(fila.getString(1)); // Imprime los días trabajados desde la tabla
                telefono.setText(fila.getString(2)); // Imprime el sueldo diario desde la tabla
                fecha.setText(fila.getString(3));
                descripcion.setText(fila.getString(4));
            } else {
                // Ventana emergente
                Toast.makeText(this, "Error: La cita no existe", Toast.LENGTH_LONG).show();
            }

            fila.close(); // Cierra el cursor después de su uso
        } else {
            // Ventana emergente si el campo está vacío
            Toast.makeText(this, "Por favor, ingresa un nombre", Toast.LENGTH_LONG).show();
        }

        bd.close(); // Cierra la base de datos
    } // Termina método consulta empleado

    public void editarProducto(View view) { // Inicia método de editar

        // Objeto de administración para la base de datos SQLite
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 3);
        SQLiteDatabase bd = admin.getWritableDatabase(); // Base de datos disponible para escritura

        // Capturar los datos del formulario para procesarlos en la tabla articulo
        String Nombre = nombre.getText().toString();
        String Apellidos = apellidos.getText().toString();
        String Edad = edad.getText().toString();
        String Telefono = telefono.getText().toString();
        String Fecha = fecha.getText().toString();
        String Descripcion = descripcion.getText().toString();

        // Validar que los campos no estén vacíos
        if (!Nombre.isEmpty() && !Apellidos.isEmpty() && !Edad.isEmpty() && !Telefono.isEmpty() && !Fecha.isEmpty() && !Descripcion.isEmpty()) {

            // Se crea un contenedor de variables para inyectar los valores a la tabla
            ContentValues registro = new ContentValues();

            // Se integran los campos de la tabla articulo con los valores del formulario
            registro.put("nombreBD", Nombre);
            registro.put("apellidosBD", Apellidos);
            registro.put("edadBD", Edad);
            registro.put("telefonoBD", Telefono);
            registro.put("fechaBD", Fecha);
            registro.put("descripcionBD", Descripcion);
            //Registro no se cambiar

            // Instrucción SQL para editar
            int actualizacion = bd.update("citas", registro, "nombreBD = ?", new String[]{Nombre});

            bd.close();

            if (actualizacion > 0) {
                Toast.makeText(this, "Cita actualizada\nÉxito\nVerifica consulta", Toast.LENGTH_LONG).show();
                this.nombre.setText(null);
                this.apellidos.setText(null);
                this.edad.setText(null);
                this.telefono.setText(null);
                this.fecha.setText(null);
                this.descripcion.setText(null);
            } else {
                Toast.makeText(this, "Error\nEl nombre no existe\nSin cambios", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Por favor, llena todos los campos", Toast.LENGTH_LONG).show();
        }
    }

    public void eliminarCita(View view) { // Inicia método para eliminar producto
        // Objeto de administración para la base de datos SQLite
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 3);
        SQLiteDatabase bd = admin.getWritableDatabase(); // Base de datos disponible para escritura

        // Campo distintivo de búsqueda a la tabla artículo basado en el nombre del producto
        String NombreBaja = nombre.getText().toString();

        // Verifica que el nombre no esté vacío antes de intentar eliminar
        if (!NombreBaja.isEmpty()) {
            // Instrucción SQL para eliminar el registro mediante el nombre del producto
            int confirmarBaja = bd.delete("citas", "nombreBD = ?", new String[]{NombreBaja});
            bd.close();

            if (confirmarBaja > 0) {
                Toast.makeText(this, "Cita eliminado\nÉxito", Toast.LENGTH_LONG).show();
                this.nombre.setText(null);
                this.apellidos.setText(null);
                this.edad.setText(null);
                this.telefono.setText(null);
                this.fecha.setText(null);
                this.descripcion.setText(null);
            } else {
                Toast.makeText(this, "Error\nEl nombre no existe", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Por favor, ingresa un nombre", Toast.LENGTH_LONG).show();
        }

    }
}