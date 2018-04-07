package com.example.rafaelanastacioalves.moby.packagedetaillisting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.rafaelanastacioalves.moby.R;

import timber.log.Timber;


public class TripPackageDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_detail);
        setupActionBar();


        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Timber.i("PullRequestsFragment ARG PACKAGE: " + getIntent().getStringExtra(PackageDetailsFragment.ARG_PACKAGE_ID));
            Bundle arguments = new Bundle();
            arguments.putString(PackageDetailsFragment.ARG_PACKAGE_ID,
                    getIntent().getStringExtra(PackageDetailsFragment.ARG_PACKAGE_ID));
            PackageDetailsFragment fragment = new PackageDetailsFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.package_detail_fragment_container, fragment)
                    .commit();


            supportPostponeEnterTransition();
        }
    }

    private void setupActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

    }

}
