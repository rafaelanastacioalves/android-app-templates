package com.example.rafaelanastacioalves.moby.entitydetailing;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rafaelanastacioalves.moby.R;
import com.example.rafaelanastacioalves.moby.entities.EntityDetails;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 */
public class EntityDetailsFragment extends Fragment implements LoaderManager.LoaderCallbacks<EntityDetails>, View.OnClickListener {

    private static final int PACKAGE_DETAIL_LOADER = 11;
    public static String ARG_PACKAGE_ID;
    private String PACKAGE_ID_LOADER_KEY = "package_id_loader_key";


    @BindView(R.id.detail_entity_detail_name)
    TextView tripPackageDetailValor;

    @BindView(R.id.trip_package_detail_imageview)
    ImageView tripPackageDetailImageview;



    public EntityDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpLoader();


    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflateViews(inflater, container);
    }


    private View inflateViews(LayoutInflater inflater, ViewGroup container) {
        View rootView = inflater.inflate(R.layout.fragment_detail_entity_detail_view, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    private void setUpLoader() {
        String mPackageId = getArguments().getString(ARG_PACKAGE_ID);

        Bundle bundle = new Bundle();
        bundle.putString(PACKAGE_ID_LOADER_KEY, mPackageId);

        getLoaderManager().initLoader(PACKAGE_DETAIL_LOADER, bundle, this);
    }

    private void setupActionBarWithTitle(String title) {
        AppCompatActivity mActivity = (AppCompatActivity) getActivity();
        ActionBar actionBar = mActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(title);


        }
    }


    @Override
    public Loader<EntityDetails> onCreateLoader(int id, Bundle args) {
        Timber.i("EntityDetailActivity - onCreateLoader");
        String packageId = args.getString(PACKAGE_ID_LOADER_KEY);
        return new EntityDetailAsyncTaskLoader(getContext(), packageId);
    }

    @Override
    public void onLoadFinished(Loader<EntityDetails> loader, EntityDetails data) {
        if (loader instanceof EntityDetailAsyncTaskLoader) {
            setViewsWith(data);

        }
    }

    private void setViewsWith(EntityDetails entityDetails) {

        tripPackageDetailValor.setText(entityDetails.getPrice());
        setupActionBarWithTitle(entityDetails.getTitle());
        Picasso.get()
                .load(entityDetails.getImage_url())
                .into(tripPackageDetailImageview, new Callback() {
                    @Override
                    public void onSuccess() {
                        getActivity().supportStartPostponedEnterTransition();
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });


    }


    @Override
    public void onLoaderReset(Loader<EntityDetails> loader) {
        Timber.i("onLoaderReset");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getActivity(), "Comprado!", Toast.LENGTH_SHORT).show();
    }


}
