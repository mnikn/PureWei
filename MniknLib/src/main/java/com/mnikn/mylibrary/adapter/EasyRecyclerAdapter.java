package com.mnikn.mylibrary.adapter;

import android.support.v7.widget.RecyclerView;

import com.mnikn.mylibrary.adapter.data.DataProvider;
import com.mnikn.mylibrary.adapter.data.WriteDataProvider;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public abstract class EasyRecyclerAdapter<T,D extends DataProvider> extends RecyclerView.Adapter<EasyViewHolder>{

    private D mDataProvider;
    private boolean mHasHeader;
    private boolean mHasFooter;

    public void add(T data){
        if(mDataProvider instanceof WriteDataProvider){
            ((WriteDataProvider) mDataProvider).add(data);
            notifyItemInserted(mDataProvider.size() - 1);
        }

    }
    public void add(T data,int position){
        if(mDataProvider instanceof WriteDataProvider){
            ((WriteDataProvider) mDataProvider).add(data, position);
            notifyItemInserted(position);
        }
    }

    public void update(T data,int position){
        if(mDataProvider instanceof WriteDataProvider){
            ((WriteDataProvider) mDataProvider).update(data,position);
            notifyItemInserted(position);
        }
    }

    public void remove(T data){
        if(mDataProvider instanceof WriteDataProvider){
            ((WriteDataProvider) mDataProvider).remove(data);
            notifyItemInserted(mDataProvider.size() - 1);
        }
    }

    public void remove(int position){
        if(mDataProvider instanceof WriteDataProvider){
            ((WriteDataProvider) mDataProvider).remove(position);
            notifyItemInserted(position);
        }
    }

    public void clear(){
        if(mDataProvider instanceof WriteDataProvider){
            ((WriteDataProvider) mDataProvider).clear();
            notifyDataSetChanged();
        }
    }

    public boolean isEmpty(){
        return mDataProvider.isEmpty();
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
                holder.bindView();
            }
            else{
                holder.bindView(mDataProvider.get(position - 1));
            }
        }
        else if(mHasFooter && !mHasHeader){
            if(position == mDataProvider.size()){
                holder.bindView();
            }
            else{
                holder.bindView(mDataProvider.get(position));
            }
        }
        else if(mHasHeader && mHasFooter){
            if(position == 0 || position == mDataProvider.size() + 1){
                holder.bindView();
            }
            else{
                holder.bindView(mDataProvider.get(position - 1));
            }
        }
        else{
            holder.bindView(mDataProvider.get(position));
        }
    }

    @Override
    public int getItemCount() {
        int size;
        if(mHasHeader && mHasFooter){
            size = mDataProvider.size() + 2;
        }
        else if(mHasHeader || mHasFooter){
            size = mDataProvider.size() + 1;
        }
        else{
            size = mDataProvider.size();
        }
        return size;
    }


}
