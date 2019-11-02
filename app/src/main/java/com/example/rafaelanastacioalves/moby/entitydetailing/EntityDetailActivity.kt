package com.example.rafaelanastacioalves.moby.entitydetailing

import android.os.Bundle

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

import com.example.rafaelanastacioalves.moby.R

import timber.log.Timber


class EntityDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_package_detail)
        setupActionBar()


        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Timber.i("PullRequestsFragment ARG PACKAGE: " + intent.getStringExtra(EntityDetailsFragment.ARG_PACKAGE_ID))
            val arguments = Bundle()
            arguments.putString(EntityDetailsFragment.ARG_PACKAGE_ID,
                    intent.getStringExtra(EntityDetailsFragment.ARG_PACKAGE_ID))
            val fragment = EntityDetailsFragment()
            fragment.arguments = arguments
            supportFragmentManager.beginTransaction()
                    .add(R.id.package_detail_fragment_container, fragment)
                    .commit()


            supportPostponeEnterTransition()
        }
    }

    private fun setupActionBar() {
        val toolbar = findViewById<View>(R.id.detail_toolbar) as Toolbar
        setSupportActionBar(toolbar)

    }

}
