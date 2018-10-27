package com.example.rafaelanastacioalves.moby.entitydetailing;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.rafaelanastacioalves.moby.R;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import timber.log.Timber;


public class EntityDetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        setContentView(R.layout.activity_package_detail);
        setupActionBar();
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Timber.i("PullRequestsFragment ARG PACKAGE: " + getIntent().getStringExtra(EntityDetailsFragment.ARG_PACKAGE_ID));
            Bundle arguments = new Bundle();
            arguments.putString(EntityDetailsFragment.ARG_PACKAGE_ID,
                    getIntent().getStringExtra(EntityDetailsFragment.ARG_PACKAGE_ID));
            EntityDetailsFragment fragment = new EntityDetailsFragment();
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
