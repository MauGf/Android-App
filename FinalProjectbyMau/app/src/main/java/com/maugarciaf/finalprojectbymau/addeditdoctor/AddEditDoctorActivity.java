package com.maugarciaf.finalprojectbymau.addeditdoctor;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.maugarciaf.finalprojectbymau.R;
import com.maugarciaf.finalprojectbymau.doctors.LawyersActivity;

import java.util.Objects;

public class AddEditDoctorActivity extends AppCompatActivity {

    public static final int REQUEST_ADD_LAWYER = 1;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_add_edit_lawyer);
        Toolbar toolbar = findViewById (R.id.toolbar);
        setSupportActionBar (toolbar);
        Objects.requireNonNull (getSupportActionBar ()).setDisplayHomeAsUpEnabled(true);

        String lawyerId = getIntent().getStringExtra(LawyersActivity.EXTRA_LAWYER_ID);

        setTitle(lawyerId == null ? "Añadir Doctor" : "Editar Doctor");

        AddEditDoctorFragment addEditDoctorFragment = (AddEditDoctorFragment)
                getSupportFragmentManager().findFragmentById(R.id.add_edit_lawyer_container);
        if (addEditDoctorFragment == null) {
            addEditDoctorFragment = AddEditDoctorFragment.newInstance(lawyerId);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.add_edit_lawyer_container, addEditDoctorFragment)
                    .commit();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
