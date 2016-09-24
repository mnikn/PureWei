package com.mnikn.purewei.support.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mnikn.mylibrary.adapter.EasyViewHolder;
import com.mnikn.mylibrary.adapter.RecyclerCursorAdapter;
import com.mnikn.purewei.R;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class UserAdapter extends RecyclerCursorAdapter<EasyViewHolder> {

    private static final int HEADER = 1;
    private static final int WEIBO = 2;

    private Context mContext;

    public UserAdapter(Context context){
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return HEADER;
        }
        else{
            return WEIBO;
        }
    }

    @Override
    public EasyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        EasyViewHolder holder;
        switch (viewType){
            case HEADER:
                holder = new HeaderHolder(mContext,inflater.inflate(R.layout.item_user_header,parent,false));
                break;
            case WEIBO:
                holder = new WeiboViewHolder(mContext,inflater.inflate(R.layout.item_home,parent,false));
                break;
            default:
                throw new IllegalArgumentException("No such a view type:" + viewType);
        }
        return holder;
    }
}
