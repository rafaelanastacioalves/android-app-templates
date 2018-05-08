package com.example.rafaelanastacioalves.moby.entitymainlisting;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.rafaelanastacioalves.moby.R;
import com.example.rafaelanastacioalves.moby.entities.MainEntity;
import com.example.rafaelanastacioalves.moby.listeners.RecyclerViewClickListener;

import kotlin.collections.ArrayList

class MainEntityAdapter : RecyclerView.Adapter<MainEntityViewHolder>() {
    private var recyclerViewClickListener: RecyclerViewClickListener? = null
    private var items = ArrayList<MainEntity>()

    private var mContext: Context? = null

    public MainEntityAdapter(Context context) {
        mContext = context;
    }


    public void setRecyclerViewClickListener(RecyclerViewClickListener aRVC) {
        this.recyclerViewClickListener = aRVC;
    }

    public List<MainEntity> getItems() {
        return this.items;
    }

    public void setItems(List<MainEntity> items) {
        this.items = items;
        notifyDataSetChanged();


    }


    override public MainEntityViewHolder : onCreateViewHolder(parent: ViewGroup, viewType: int  {
        return new MainEntityViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.detail_entity_viewholder, parent, false), recyclerViewClickListener);
    }



    @Override
    public void onBindViewHolder(MainEntityViewHolder holder, int position) {
        MainEntity aRepoW = getItems().get(position);
        ((MainEntityViewHolder) holder).bind(aRepoW, mContext);
    }

    @Override
    public int getItemCount() {
        if (getItems() != null){
            return getItems().size();
        }else{
            return 0;
        }
    }
}

