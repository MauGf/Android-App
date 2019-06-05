package com.maugarciaf.finalprojectbymau.doctors;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.maugarciaf.finalprojectbymau.AboutActivity;
import com.maugarciaf.finalprojectbymau.R;

public class DoctorsActivity extends AppCompatActivity {
    public static final String EXTRA_DOCTOR_ID = "extra_lawyer_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_doctors);
        Toolbar toolbar = findViewById (R.id.toolbar);
        setSupportActionBar (toolbar);

        DoctorsFragment fragment = (DoctorsFragment)
                getSupportFragmentManager ().findFragmentById (R.id.lawyers_container);

        if (fragment == null) {
            fragment = DoctorsFragment.newInstance ();
            getSupportFragmentManager ()
                    .beginTransaction ()
                    .add (R.id.lawyers_container, fragment)
                    .commit ();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater ().inflate (R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId ();

        if (id == R.id.action_settings) {
            Intent intentSettings = new Intent (getApplicationContext (), AboutActivity.class);
            intentSettings.setFlags (Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity (intentSettings);
            return true;
        }

        return super.onOptionsItemSelected (item);
    }

}
