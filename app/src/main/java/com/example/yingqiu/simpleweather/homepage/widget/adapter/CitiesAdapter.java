package com.example.yingqiu.simpleweather.homepage.widget.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yingqiu.simpleweather.App;
import com.example.yingqiu.simpleweather.R;
import com.example.yingqiu.simpleweather.listener.OnRecyclerViewClickListener;

import java.util.List;

/**
 * author YuanYingqiu
 * email 1049852196@qq.com
 * date 16-9-18
 */
public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.CitiesViewHolder> {
    private LayoutInflater inflater;
    private List<String> mCities;

    public CitiesAdapter(List<String> cities) {
        this.mCities = cities;
        inflater = LayoutInflater.from(App.getAppContext());
    }

    @Override
    public CitiesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CitiesViewHolder(inflater.inflate(R.layout.item_cities, parent, false), mListener);
    }

    @Override
    public void onBindViewHolder(CitiesViewHolder holder, int position) {
        holder.tvCity.setText(mCities.get(position));
    }

    @Override
    public int getItemCount() {
        return mCities.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    private OnRecyclerViewClickListener mListener;

    public void setOnClickListener(OnRecyclerViewClickListener listener) {
        this.mListener = listener;
    }

    static class CitiesViewHolder extends RecyclerView.ViewHolder {
        TextView tvCity;
        private OnRecyclerViewClickListener mListener;

        public CitiesViewHolder(View itemView, OnRecyclerViewClickListener listener) {
            super(itemView);
            this.mListener = listener;
            tvCity = (TextView) itemView.findViewById(R.id.tv_city);


            tvCity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null)
                        mListener.onItemClick(v, getLayoutPosition());
                }
            });


        }
    }
}
