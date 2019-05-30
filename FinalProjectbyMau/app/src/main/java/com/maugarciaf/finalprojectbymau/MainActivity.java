package com.maugarciaf.finalprojectbymau;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    EditText edtMiUsuario, edtMiClave;
    Button btnIngresar, btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        btnIngresar = (Button) findViewById (R.id.button_ingresar);
        btnSalir = (Button) findViewById (R.id.button_salir);

        edtMiUsuario = (EditText) findViewById (R.id.miNombre);
        edtMiClave = (EditText) findViewById (R.id.miPassword);

        btnIngresar.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                ingresar (view);
            }
        });

        btnSalir.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                finish ();
            }
        });
    }

    private void ingresar(View view) {

        String usuariDigitado = edtMiUsuario.getText ().toString ();
        String claveDigitada = edtMiClave.getText ().toString ();


        if (TextUtils.isEmpty (usuariDigitado)) {

            edtMiUsuario.setError (getResources ().getString (R.string.usuario_vacio));
            edtMiUsuario.requestFocus ();

        } else if (TextUtils.isEmpty (claveDigitada)) {

            edtMiClave.setError (getResources ().getString (R.string.clave_vacia));
            edtMiClave.requestFocus ();
        } else {



        }


    }

}
