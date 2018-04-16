package com.example.rafaelanastacioalves.moby.trippackagelisting;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.rafaelanastacioalves.moby.packagedetaillisting.PackageDetailsFragment;
import com.example.rafaelanastacioalves.moby.packagedetaillisting.TripPackageDetailActivity;
import com.example.rafaelanastacioalves.moby.R;
import com.example.rafaelanastacioalves.moby.entities.TripPackage;
import com.example.rafaelanastacioalves.moby.listeners.RecyclerViewClickListener;

import java.util.List;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<TripPackage>>, RecyclerViewClickListener {
    private final LoaderManager.LoaderCallbacks<List<TripPackage>> mCallback = MainActivity.this;
    private final RecyclerViewClickListener mClickListener = this;
    private TripPackageAdapter mTripPackageListAdapter;
    private int tripPackageListLoaderId = 10 ;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupViews();
        setupRecyclerView();
        initLoader();
    }

    private void setupViews() {
        setContentView(R.layout.activity_main);
        Timber.tag("LifeCycles");
        Timber.i("onCreate Activity");
    }

    private void initLoader() {
        if (getSupportLoaderManager().getLoader(tripPackageListLoaderId) == null) {
            getSupportLoaderManager().initLoader(tripPackageListLoaderId, null, mCallback);
        } else {
            getSupportLoaderManager().restartLoader(tripPackageListLoaderId, null, mCallback);
        }
    }

    private void setupRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.trip_package_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        if (mTripPackageListAdapter == null) {
            mTripPackageListAdapter = new TripPackageAdapter(this);
        }
        mTripPackageListAdapter.setRecyclerViewClickListener(mClickListener);
        mRecyclerView.setAdapter(mTripPackageListAdapter);
    }

    @Override
    public Loader<List<TripPackage>> onCreateLoader(int id, Bundle args) {
        Timber.d("onCreateLoader");
        return new TripPackageListAsyncTaskLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<List<TripPackage>> loader, List<TripPackage> data) {
        Timber.d("onLoadFinished");
        if (loader instanceof TripPackageListAsyncTaskLoader) {
            if (data == null) {
                mTripPackageListAdapter.setItems(null);
                //TODO add any error managing
                Timber.w("Nothing returned from Trip Package List API");

            }else{
                mTripPackageListAdapter.setItems(data);
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<List<TripPackage>> loader) {

    }

    @Override
    public void onClick(View view, int position) {
        TripPackage TripPackage = (TripPackage) mTripPackageListAdapter.getItems().get(position);

        AppCompatImageView transitionImageView = view.findViewById(R.id.detail_entity_imageview);
        startActivityByVersion(TripPackage, transitionImageView);


    }

    private void startActivityByVersion(TripPackage tripPackage, AppCompatImageView transitionImageView) {
        Intent i = new Intent(this, TripPackageDetailActivity.class);
        i.putExtra(PackageDetailsFragment.ARG_PACKAGE_ID, tripPackage.getId());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Bundle bundle = null;
            bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this,
                    transitionImageView, transitionImageView.getTransitionName()).toBundle();
            startActivity(i,bundle);

        }else{
            startActivity(i);
        }
    }
}
