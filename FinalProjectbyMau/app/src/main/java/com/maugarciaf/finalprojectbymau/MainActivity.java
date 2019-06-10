package com.maugarciaf.finalprojectbymau;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.maugarciaf.finalprojectbymau.helper.InputValidationLogin;
import com.maugarciaf.finalprojectbymau.doctors.DoctorsActivity;
import com.maugarciaf.finalprojectbymau.data.DatabaseHelperUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = MainActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutPassword;

    private TextInputEditText textInputEditTextName;
    private TextInputEditText textInputEditTextPassword;

    private AppCompatButton appCompatButtonLogin;


    private InputValidationLogin inputValidationLogin;
    private DatabaseHelperUser databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_login);
        getSupportActionBar ().hide ();

        initViews ();
        initListeners ();
        initObjects ();
    }


    private void initViews() {

        nestedScrollView = (NestedScrollView) findViewById (R.id.nestedScrollView);

        textInputLayoutName = (TextInputLayout) findViewById (R.id.textInputLayoutName);
        textInputLayoutPassword = (TextInputLayout) findViewById (R.id.textInputLayoutPassword);

        textInputEditTextName = (TextInputEditText) findViewById (R.id.textInputEditTextName);
        textInputEditTextPassword = (TextInputEditText) findViewById (R.id.textInputEditTextPassword);

        appCompatButtonLogin = (AppCompatButton) findViewById (R.id.appCompatButtonLogin);
        AppCompatButton btnSalir = (AppCompatButton) findViewById (R.id.button_salir);

        btnSalir.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                finish ();
            }
        });

    }

    private void initListeners() {
        appCompatButtonLogin.setOnClickListener (this);

    }

    private void initObjects() {
        databaseHelper = new DatabaseHelperUser (activity);
        inputValidationLogin = new InputValidationLogin (activity);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId ()) {
            case R.id.appCompatButtonLogin:
                verifyFromSQLite ();
                break;
        }
    }

    private void verifyFromSQLite() {
        if (!inputValidationLogin.isInputEditTextFilled (textInputEditTextName, textInputLayoutName, getString (R.string.error_message_email))) {
            return;
        }
        if (!inputValidationLogin.isInputEditTextFilled (textInputEditTextPassword, textInputLayoutPassword, getString (R.string.error_message_password))) {
            return;
        }

        if (databaseHelper.checkUser (textInputEditTextName.getText ().toString ().trim ()
                , textInputEditTextPassword.getText ().toString ().trim ())) {


            Intent accountsIntent = new Intent (activity, DoctorsActivity.class);
            emptyInputEditText ();
            startActivity (accountsIntent);


        } else {
            // Snack Bar to show success message that record is wrong
            Snackbar.make (nestedScrollView, getString (R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show ();
        }
    }

    private void emptyInputEditText() {
        textInputEditTextName.setText (null);
        textInputEditTextPassword.setText (null);
    }
}
