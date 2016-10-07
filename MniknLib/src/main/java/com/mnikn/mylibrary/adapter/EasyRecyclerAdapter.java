package com.mnikn.mylibrary.adapter;

import android.support.v7.widget.RecyclerView;

import com.mnikn.mylibrary.util.DataUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public abstract class EasyRecyclerAdapter<T> extends RecyclerView.Adapter<EasyViewHolder>{
    private List<T> mDataList;

    public EasyRecyclerAdapter() {
        mDataList = new ArrayList<>();
    }

    public void add(T data){
        mDataList.add(data);
        notifyItemInserted(DataUtil.getLastIndex(mDataList));
    }
    public void add(T data,int position){
        mDataList.add(position,data);
        notifyItemInserted(position);
    }
    public void addAll(List<T> dataList){
        mDataList.addAll(dataList);
        notifyDataSetChanged();
    }
    public void update(T data,int position){
        mDataList.set(position,data);
        notifyItemChanged(position);
    }
    public void remove(T data){
        mDataList.remove(data);
        notifyItemRemoved(DataUtil.getLastIndex(mDataList));
    }
    public void remove(int position){
        mDataList.remove(position);
        notifyItemRemoved(position);
    }
    public void clear(){
        mDataList.clear();
        notifyDataSetChanged();
    }
    public boolean isEmpty(){
        return mDataList.isEmpty();
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onBindViewHolder(EasyViewHolder holder, int position, List<Object> payloads) {
        holder.bindView(mDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }


}
