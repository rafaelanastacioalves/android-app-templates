package com.example.rafaelanastacioalves.moby.entitymainlisting;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.rafaelanastacioalves.moby.R;
import com.example.rafaelanastacioalves.moby.domain.entities.MainEntity;
import com.example.rafaelanastacioalves.moby.listeners.RecyclerViewClickListener;

import java.util.ArrayList;
import java.util.List;

class MainEntityAdapter extends PagedListAdapter<MainEntity, MainEntityViewHolder> {
    private static final DiffUtil.ItemCallback<MainEntity> DIFF_CALLBACK = new DiffUtil.ItemCallback<MainEntity>() {

        @Override
        public boolean areItemsTheSame(@NonNull MainEntity mainEntity, @NonNull MainEntity t1) {
            return mainEntity.getId() == t1.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull MainEntity mainEntity, @NonNull MainEntity t1) {
            return mainEntity == t1;
        }
    };

    private RecyclerViewClickListener recyclerViewClickListener;
    private List<MainEntity> items = new ArrayList<>();

    private Context mContext;

    MainEntityAdapter(Context context) {
        super(DIFF_CALLBACK);
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

    @Override
    public MainEntityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
        if (getItems() != null) {
            return getItems().size();
        } else {
            return 0;
        }
    }
}

