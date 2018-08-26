package com.example.rafaelanastacioalves.moby.entitymainlisting;

import android.content.Context;
import android.graphics.drawable.StateListDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rafaelanastacioalves.moby.R;
import com.example.rafaelanastacioalves.moby.domain.entities.MainEntity;
import com.example.rafaelanastacioalves.moby.listeners.RecyclerViewClickListener;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainEntityViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    @BindView(R.id.trip_package_container) View tripPackageContainer;
    private RecyclerViewClickListener aRecyclerViewListener;
    @BindView(R.id.main_entity_imageview) ImageView tripPackageImageView;
    @BindView(R.id.main_entity_title_textview) TextView tripPackageTitleTextView;


    public MainEntityViewHolder(View itemView, RecyclerViewClickListener clickListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.aRecyclerViewListener = clickListener;
        tripPackageContainer.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        aRecyclerViewListener.onClick(v, getAdapterPosition());
    }

    public void bind(MainEntity aMainEntity, Context context) {

        tripPackageTitleTextView.setText(aMainEntity.getTitle());
        final StateListDrawable placeholderList = (StateListDrawable) context.getResources().getDrawable(R.drawable.ic_placeholder_map_selector);
        Picasso.get()
                .load(aMainEntity.getImage_url())
                .placeholder(placeholderList)
                .into(tripPackageImageView);


    }
}
