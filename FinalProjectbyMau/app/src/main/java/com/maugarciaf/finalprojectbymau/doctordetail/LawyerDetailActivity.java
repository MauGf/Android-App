package com.maugarciaf.finalprojectbymau.doctordetail;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.maugarciaf.finalprojectbymau.R;
import com.maugarciaf.finalprojectbymau.doctors.LawyersActivity;
import com.maugarciaf.finalprojectbymau.utils.Tools;

import java.util.Objects;

public class LawyerDetailActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_lawyer_detail);
        Toolbar toolbar = findViewById (R.id.toolbar);
        setSupportActionBar (toolbar);
        Objects.requireNonNull (getSupportActionBar ()).setDisplayHomeAsUpEnabled(true);

        Tools.setSystemBarTransparent (this);

        String id = getIntent().getStringExtra(LawyersActivity.EXTRA_LAWYER_ID);

        LawyerDetailFragment fragment = (LawyerDetailFragment)
                getSupportFragmentManager().findFragmentById(R.id.lawyer_detail_container);
        if (fragment == null) {
            fragment = LawyerDetailFragment.newInstance(id);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.lawyer_detail_container, fragment)
                    .commit();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lawyer_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
