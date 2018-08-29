package com.example.rafaelanastacioalves.moby.entitydetailing;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rafaelanastacioalves.moby.R;
import com.example.rafaelanastacioalves.moby.domain.entities.EntityDetails;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class EntityDetailsFragment extends Fragment implements View.OnClickListener {

    public static String ARG_PACKAGE_ID;
    private String PACKAGE_ID_LOADER_KEY = "package_id_loader_key";

    private LiveDataEntityDetailsViewModel mLiveDataEntityDetailsViewModel;



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
        subscribe();
    }



    private void subscribe() {
        String mPackageId = getArguments().getString(ARG_PACKAGE_ID);
        mLiveDataEntityDetailsViewModel = ViewModelProviders.of(this).get(LiveDataEntityDetailsViewModel.class);
        mLiveDataEntityDetailsViewModel.getEntityDetails(mPackageId).observe(this, new Observer<EntityDetails>() {
            @Override
            public void onChanged(@Nullable EntityDetails entityDetails) {
                setViewsWith(entityDetails);
            }
        });

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



    private void setupActionBarWithTitle(String title) {
        AppCompatActivity mActivity = (AppCompatActivity) getActivity();
        ActionBar actionBar = mActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(title);


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
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getActivity(), "Comprado!", Toast.LENGTH_SHORT).show();
    }


}
