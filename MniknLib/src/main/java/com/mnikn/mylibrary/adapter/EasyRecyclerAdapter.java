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
    private boolean mHasHeader;
    private boolean mHasFooter;

    public EasyRecyclerAdapter() {
        mDataList = new ArrayList<>();
    }

    public void add(T data){
        mDataList.add(data);
        notifyItemInserted(DataUtil.getLastIndex(mDataList));
    }
    public void add(T data,int position){
        mDataList.add(position, data);
        notifyItemInserted(position);
    }
    public void addAll(List<T> dataList){
        mDataList.addAll(dataList);
        notifyDataSetChanged();
    }
    public void update(T data,int position){
        mDataList.set(position, data);
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

    public void setHasHeader(boolean hasHeader){
        mHasHeader = hasHeader;
    }
    public void setHasFooter(boolean hasFooter){
        mHasFooter = hasFooter;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onBindViewHolder(EasyViewHolder holder, int position) {
        if(mHasHeader && !mHasFooter){
            if(position == 0){
                holder.bindView(null);
            }
            else{
                holder.bindView(mDataList.get(position - 1));
            }
        }
        else if(mHasFooter && !mHasHeader){
            if(position == mDataList.size()){
                holder.bindView(null);
            }
            else{
                holder.bindView(mDataList.get(position));
            }
        }
        else if(mHasHeader && mHasFooter){
            if(position == 0 || position == mDataList.size() + 1){
                holder.bindView(null);
            }
            else{
                holder.bindView(mDataList.get(position - 1));
            }
        }
        else{
            holder.bindView(mDataList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        int size;
        if(mHasHeader && mHasFooter){
            size = mDataList.size() + 2;
        }
        else if(mHasHeader || mHasFooter){
            size = mDataList.size() + 1;
        }
        else{
            size = mDataList.size();
        }
        return size;
    }


}
