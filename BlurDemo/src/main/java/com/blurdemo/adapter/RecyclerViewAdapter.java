package com.blurdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blurdemo.R;

/**
 * Recycler adapter
 *
 * @author Qiushui
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private static final int ITEM_COUNT = 10;

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    public RecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            return new HeaderHolder(LayoutInflater.from(context).inflate(R.layout.rv_header, parent, false));
        }
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.rv_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return ITEM_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        } else {
            return TYPE_ITEM;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(View itemView) {
            super(itemView);
        }
    }

    private class HeaderHolder extends RecyclerView.ViewHolder {
        HeaderHolder(View itemView) {
            super(itemView);
        }
    }
}
