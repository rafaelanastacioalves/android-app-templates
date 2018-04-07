package com.example.rafaelanastacioalves.moby.trippackagelisting;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.rafaelanastacioalves.moby.R;
import com.example.rafaelanastacioalves.moby.entities.TripPackage;
import com.example.rafaelanastacioalves.moby.listeners.RecyclerViewClickListener;

import java.util.ArrayList;
import java.util.List;

public class TripPackageAdapter extends RecyclerView.Adapter<TripPackageViewHolder> {
    private RecyclerViewClickListener recyclerViewClickListener;
    private List<TripPackage> items = new ArrayList<>();

    private Context mContext;

    public TripPackageAdapter(Context context) {
        mContext = context;
    }


    public void setRecyclerViewClickListener(RecyclerViewClickListener aRVC) {
        this.recyclerViewClickListener = aRVC;
    }

    public List<TripPackage> getItems() {
        return this.items;
    }

    public void setItems(List<TripPackage> items) {
        this.items = items;
        notifyDataSetChanged();


    }

    @Override
    public TripPackageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TripPackageViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trip_package_viewholder, parent, false), recyclerViewClickListener);
    }



    @Override
    public void onBindViewHolder(TripPackageViewHolder holder, int position) {
        TripPackage aRepoW = getItems().get(position);
        ((TripPackageViewHolder) holder).bind(aRepoW, mContext);
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

