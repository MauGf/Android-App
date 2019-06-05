package com.maugarciaf.finalprojectbymau.doctors;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.maugarciaf.finalprojectbymau.AboutActivity;
import com.maugarciaf.finalprojectbymau.R;

public class LawyersActivity extends AppCompatActivity {
    public static final String EXTRA_LAWYER_ID = "extra_lawyer_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_lawyers);
        Toolbar toolbar = findViewById (R.id.toolbar);
        setSupportActionBar (toolbar);

        LawyersFragment fragment = (LawyersFragment)
                getSupportFragmentManager ().findFragmentById (R.id.lawyers_container);

        if (fragment == null) {
            fragment = LawyersFragment.newInstance ();
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
