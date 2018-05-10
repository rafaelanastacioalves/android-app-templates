package com.example.rafaelanastacioalves.moby.entitymainlisting;

import android.content.Context;
import android.graphics.drawable.StateListDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rafaelanastacioalves.moby.R;
import com.example.rafaelanastacioalves.moby.entities.MainEntity;
import com.example.rafaelanastacioalves.moby.listeners.RecyclerViewClickListener;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

class MainEntityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    @BindView(R.id.trip_package_container)
    lateinit var tripPackageContainer: View
    lateinit private var aRecyclerViewListener: RecyclerViewClickListener
    @BindView(R.id.main_entity_imageview)
    lateinit var tripPackageImageView: ImageView
    @BindView(R.id.main_entity_title_textview)
    lateinit var tripPackageTitleTextView: TextView;

    constructor(itemView: View , clickListener: RecyclerViewClickListener) : this(itemView) {
        this.aRecyclerViewListener = clickListener
    }
    init {
        ButterKnife.bind(this, itemView)
        tripPackageContainer.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        aRecyclerViewListener.onClick(v, getAdapterPosition());
    }

    fun bind(aMainEntity: MainEntity, context: Context) {

        tripPackageTitleTextView.setText(aMainEntity.getTitle());
        val placeholderList: StateListDrawable= context.getResources().getDrawable(R.drawable.ic_placeholder_map_selector) as StateListDrawable;
        Picasso.get()
                .load(aMainEntity.getImage_url())
                .placeholder(placeholderList)
                .into(tripPackageImageView);


    }
}
