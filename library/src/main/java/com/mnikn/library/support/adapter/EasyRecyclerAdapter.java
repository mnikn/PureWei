package com.mnikn.library.support.adapter;

import android.support.v7.widget.RecyclerView;

import com.mnikn.library.support.adapter.data.IDataProvider;
import com.mnikn.library.support.adapter.data.IWritableDataProvider;
import com.mnikn.library.support.adapter.data.ListDataProvider;


/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public abstract class EasyRecyclerAdapter<Provider extends IDataProvider, Data> extends RecyclerView.Adapter<EasyViewHolder>{

    private static Class mContainerClass;

    private Provider mDataProvider;
    private boolean mHasHeader;
    private boolean mHasFooter;

    @SuppressWarnings("unchecked")
    public EasyRecyclerAdapter() {
        mDataProvider = (Provider) new ListDataProvider<Data>();
    }

    public EasyRecyclerAdapter(Provider provider) {
        mDataProvider = provider;
    }

    public Object getDataContainer(){
        return mDataProvider.getDataContainer();
    }

    @SuppressWarnings("unchecked")
    public void add(Data data){
        if(mDataProvider instanceof IWritableDataProvider){
            ((IWritableDataProvider) mDataProvider).add(data);
            notifyItemInserted(mDataProvider.size() - 1);
        }

    }

    @SuppressWarnings("unchecked")
    public void add(Data data,int position){
        if(mDataProvider instanceof IWritableDataProvider){
            ((IWritableDataProvider) mDataProvider).add(data, position);
            notifyItemInserted(position);
        }
    }

    @SuppressWarnings("unchecked")
    public <C> void swap(C dataContainer){
        mDataProvider.swap(dataContainer);
        notifyDataSetChanged();
    }

    @SuppressWarnings("unchecked")
    public void update(Data data,int position){
        if(mDataProvider instanceof IWritableDataProvider){
            ((IWritableDataProvider) mDataProvider).update(data,position);
            notifyItemInserted(position);
        }
    }

    @SuppressWarnings("unchecked")
    public void remove(Data data){
        if(mDataProvider instanceof IWritableDataProvider){
            ((IWritableDataProvider) mDataProvider).remove(data);
            notifyItemInserted(mDataProvider.size() - 1);
        }
    }

    public void remove(int position){
        if(mDataProvider instanceof IWritableDataProvider){
            ((IWritableDataProvider) mDataProvider).remove(position);
            notifyItemInserted(position);
        }
    }

    public void clear(){
        if(mDataProvider instanceof IWritableDataProvider){
            ((IWritableDataProvider) mDataProvider).clear();
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
                holder.bindView(position,null);
            }
            else{
                holder.bindView(position, mDataProvider.get(position - 1));
            }
        }
        else if(mHasFooter && !mHasHeader){
            if(position == getItemCount() - 1){
                holder.bindView(position,null);
            }
            else{
                holder.bindView(position,mDataProvider.get(position));
            }
        }
        else if(mHasHeader && mHasFooter){
            if(position == 0 || position == mDataProvider.size() + 1){
                holder.bindView(position,null);
            }
            else{
                holder.bindView(position,mDataProvider.get(position - 1));
            }
        }
        else{
            holder.bindView(position,mDataProvider.get(position));
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
