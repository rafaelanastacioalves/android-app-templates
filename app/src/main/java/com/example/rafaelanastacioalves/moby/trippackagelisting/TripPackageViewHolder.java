package com.example.rafaelanastacioalves.moby.trippackagelisting;

import android.content.Context;
import android.graphics.drawable.StateListDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rafaelanastacioalves.moby.R;
import com.example.rafaelanastacioalves.moby.entities.TripPackage;
import com.example.rafaelanastacioalves.moby.listeners.RecyclerViewClickListener;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TripPackageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    @BindView(R.id.trip_package_container) View tripPackageContainer;
    private RecyclerViewClickListener aRecyclerViewListener;
    @BindView(R.id.detail_entity_imageview) ImageView tripPackageImageView;
    @BindView(R.id.trip_package_value_textview) TextView tripPackageValueTextView;
    @BindView(R.id.detail_entity_title_textview) TextView tripPackageTitleTextView;


    public TripPackageViewHolder(View itemView, RecyclerViewClickListener clickListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.aRecyclerViewListener = clickListener;
        tripPackageContainer.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        aRecyclerViewListener.onClick(v, getAdapterPosition());
    }

    public void bind(TripPackage aTripPackage, Context context) {

        tripPackageTitleTextView.setText(aTripPackage.getTitle());
        tripPackageValueTextView.setText(aTripPackage.getPrice());
        final StateListDrawable placeholderList = (StateListDrawable) context.getResources().getDrawable(R.drawable.ic_placeholder_map_selector);
        Picasso.get()
                .load(aTripPackage.getImage_url())
                .placeholder(placeholderList)
                .into(tripPackageImageView);


    }
}
