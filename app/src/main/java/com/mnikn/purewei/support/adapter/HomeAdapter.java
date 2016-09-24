package com.mnikn.purewei.support.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mnikn.mylibrary.adapter.EasyViewHolder;
import com.mnikn.mylibrary.adapter.RecyclerCursorAdapter;
import com.mnikn.mylibrary.interfaces.OnRecyclerItemClickListener;
import com.mnikn.purewei.R;
import com.mnikn.purewei.data.WeiboContract.UserEntry;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class HomeAdapter extends RecyclerCursorAdapter<EasyViewHolder> implements View.OnClickListener {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private OnRecyclerItemClickListener mListener;

    public HomeAdapter(Context context) {
        this(context,null);
    }

    public HomeAdapter(Context context,OnRecyclerItemClickListener listener) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mListener = listener;
    }

    @Override
    public EasyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        EasyViewHolder holder;
        View view = mLayoutInflater.inflate(R.layout.item_home,parent,false);
        holder = new WeiboViewHolder(mContext,view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onClick(View v) {
        if(mListener != null){
            mListener.onItemClick(v,UserEntry.getUsertId(getCursor()));
        }
    }
}
