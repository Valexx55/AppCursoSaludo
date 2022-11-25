package edu.marta.practicas.appcursosaludo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private EditText etnombre;
    private EditText etapellidos;
    private EditText etfechanac;
    private ImageView imageView;//foto


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.etnombre = findViewById(R.id.editTextNombre);
        this.etapellidos = findViewById(R.id.editTextApellidos);
        this.etfechanac = findViewById(R.id.editTextFechaNac);
        this.imageView = findViewById(R.id.imageView);

    }

    //TODO La aplicación le mostrará un saludo de bienvenida en el que aparezcan el nombre y apellidos del usuario y una imagen de un vehículo si el usuario es mayor de edad o una imagen infantil si el usuario es menor de edad.
    public void botonAceptarPulsado(View view) {
        Log.d("ETIQUETA_LOG", "Botón aceptar pulsado");
        String nombre = this.etnombre.getText().toString();
        String apellidos = this.etapellidos.getText().toString();
        String mensaje_saludo = "HOLA " + nombre + " " + apellidos;

        Toast toast = Toast.makeText(this, mensaje_saludo, Toast.LENGTH_LONG);
        toast.show();//muestro el saludo

        String fecha_nac = this.etfechanac.getText().toString();
        //vamos a suponer que el usuario ha introducido correctamente la fecha
        //?? es mayor de edad el usuario
        if (esMayorEdad(fecha_nac)) //==true
        {
            //mayor edad
            Log.d("ETIQUETA_LOG", "MAYOR EDAD");
            this.imageView.setImageResource(R.drawable.ic_baseline_directions_car_24);
        } else {
            //menor edad
            Log.d("ETIQUETA_LOG", "MENOR EDAD");
            this.imageView.setImageResource(R.drawable.ic_baseline_child_care_24);
        }
        //SI ES MAYOR DE EDAD
        //MOSTRAMOS COCHE EN IMAGEVIEW
        //SI ES MENOR
        //MOSTRAR BEBÉ
        cerrrarTeclado ();
    }

    private void cerrrarTeclado ()
    {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(etfechanac.getWindowToken(), 0);
    }

    private boolean esMayorEdad(String fecha_nacimiento) {
        boolean mayor_edad = false;
        Date fecha_date = null;

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
            try {
                fecha_date = sdf.parse(fecha_nacimiento);
                Calendar calendar = Calendar.getInstance();//obtengo el calendario actual
                calendar.add(Calendar.YEAR, -18);
                Date fecha_hace_18_anios = new Date(calendar.getTimeInMillis());
                mayor_edad = fecha_date.before(fecha_hace_18_anios);

            } catch (ParseException e) {
                e.printStackTrace();
            }

        return mayor_edad;
    }


}