package com.example.yingqiu.simpleweather.homepage.widget.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yingqiu.simpleweather.App;
import com.example.yingqiu.simpleweather.R;
import com.example.yingqiu.simpleweather.homepage.bean.Province;
import com.example.yingqiu.simpleweather.listener.OnRecyclerViewClickListener;

import java.util.List;

/**
 * author YuanYingqiu
 * email 1049852196@qq.com
 * date 16-9-19
 */
public class ProvincesAdapter extends RecyclerView.Adapter<ProvincesAdapter.ProvinceViewHolder> {
    private List<Province> mProvinces;
    private LayoutInflater mInflater;
    public ProvincesAdapter(List<Province> provinces){
        this.mProvinces = provinces;
        mInflater = LayoutInflater.from(App.getAppContext());
    }

    @Override
    public ProvinceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProvinceViewHolder(mInflater.inflate(R.layout.item_provinces,parent,false),mListener);
    }

    @Override
    public void onBindViewHolder(ProvinceViewHolder holder, int position) {
        holder.tvProvince.setText(mProvinces.get(position).getProvinceName());
    }

    @Override
    public int getItemCount() {
        return mProvinces.size();
    }

    private OnRecyclerViewClickListener mListener;

    public void setOnClickListener(OnRecyclerViewClickListener listener) {
        this.mListener = listener;
    }

    static class ProvinceViewHolder extends RecyclerView.ViewHolder{
        TextView tvProvince;
        private OnRecyclerViewClickListener mListener;
        public ProvinceViewHolder(View itemView,OnRecyclerViewClickListener listener) {
            super(itemView);
            this.mListener = listener;
            tvProvince = (TextView) itemView.findViewById(R.id.tv_province);

            tvProvince.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener!=null)
                        mListener.onItemClick(v,getLayoutPosition());
                }
            });
        }
    }
}
