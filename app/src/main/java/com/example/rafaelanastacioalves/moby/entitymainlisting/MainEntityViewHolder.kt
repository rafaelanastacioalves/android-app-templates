package com.example.rafaelanastacioalves.moby.entitymainlisting;

import android.content.Context
import android.graphics.drawable.StateListDrawable
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

import com.example.rafaelanastacioalves.moby.R
import com.example.rafaelanastacioalves.moby.domain.entities.MainEntity
import com.example.rafaelanastacioalves.moby.listeners.RecyclerViewClickListener
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.detail_entity_viewholder.view.*

class MainEntityViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), View.OnClickListener, LayoutContainer{

    lateinit private var aRecyclerViewListener: RecyclerViewClickListener


    constructor(itemView: View , clickListener: RecyclerViewClickListener) : this(itemView) {
        this.aRecyclerViewListener = clickListener


    }
    init {
        itemView.trip_package_container.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        aRecyclerViewListener.onClick(v, getAdapterPosition());
    }

    fun bind(aMainEntity: MainEntity, context: Context) {

        itemView.main_entity_title_textview.setText(aMainEntity.getTitle());
        val placeholderList: StateListDrawable= context.getResources().getDrawable(R.drawable.ic_placeholder_map_selector) as StateListDrawable;
        Picasso.get()
                .load(aMainEntity.getImage_url())
                .placeholder(placeholderList)
                .into(itemView.main_entity_imageview as ImageView);


    }
}
