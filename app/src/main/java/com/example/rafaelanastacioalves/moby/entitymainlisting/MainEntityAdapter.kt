package com.example.rafaelanastacioalves.moby.entitymainlisting;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.rafaelanastacioalves.moby.R;
import com.example.rafaelanastacioalves.moby.domain.entities.MainEntity;
import com.example.rafaelanastacioalves.moby.listeners.RecyclerViewClickListener;

import kotlin.collections.ArrayList

class MainEntityAdapter(context: Context) : RecyclerView.Adapter<MainEntityViewHolder>() {
    lateinit private var recyclerViewClickListener: RecyclerViewClickListener
    private var items: List<MainEntity>? = null

    private val mContext: Context = context

    fun setRecyclerViewClickListener(aRVC: RecyclerViewClickListener ) {
        this.recyclerViewClickListener = aRVC;
    }

    fun getItems(): List<MainEntity>? {
        return this.items;
    }

    fun setItems(items: List<MainEntity>?) {
        this.items = items as ArrayList<MainEntity>;
        notifyDataSetChanged();
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainEntityViewHolder  {
        return MainEntityViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.detail_entity_viewholder, parent, false), recyclerViewClickListener);
    }



    override fun onBindViewHolder(holder: MainEntityViewHolder,position: Int ) {
        val aRepoW = getItems()?.get(position) as MainEntity;
        holder.bind(aRepoW, mContext);
    }


    override fun getItemCount(): Int {
        if (getItems() != null){
            return getItems()!!.size;
        }else{
            return 0;
        }
    }
}

