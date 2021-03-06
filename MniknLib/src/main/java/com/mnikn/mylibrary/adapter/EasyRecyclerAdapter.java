package com.mnikn.mylibrary.adapter;

import android.support.v7.widget.RecyclerView;

import com.mnikn.mylibrary.adapter.data.DataProvider;
import com.mnikn.mylibrary.adapter.data.WriteDataProvider;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public abstract class EasyRecyclerAdapter<D extends DataProvider,T> extends RecyclerView.Adapter<EasyViewHolder>{

    private D mDataProvider;
    private boolean mHasHeader;
    private boolean mHasFooter;

    public EasyRecyclerAdapter(D dataProvider){
        mDataProvider = dataProvider;
    }

    public Object getDataContainer(){
        return mDataProvider.getDataContainer();
    }

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

    public <C> void swap(C dataContainer){
        mDataProvider.swap(dataContainer);
        notifyDataSetChanged();
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
            if(position == getItemCount() - 1){
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
        int containerSize = ( getDataContainer() == null ? 0 : mDataProvider.size());
        if(mHasHeader && mHasFooter){
            size = containerSize + 2;
        }
        else if(mHasHeader || mHasFooter){
            size = containerSize + 1;
        }
        else{
            size = containerSize;
        }
        return size;
    }

}
