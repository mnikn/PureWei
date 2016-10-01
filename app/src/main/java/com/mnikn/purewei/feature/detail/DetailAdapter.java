package com.mnikn.purewei.feature.detail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mnikn.mylibrary.adapter.EasyViewHolder;
import com.mnikn.mylibrary.adapter.RecyclerCursorAdapter;
import com.mnikn.purewei.R;
import com.mnikn.purewei.viewholder.CommentViewHolder;
import com.mnikn.purewei.viewholder.ContentViewHolder;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class DetailAdapter extends RecyclerCursorAdapter {

    private static final int CONTENT = 1;
    private static final int COMMENT = 2;

    private Context mContext;
    private long mWeiboId;

    public DetailAdapter(Context context,long weiboId){
        mContext = context;
        mWeiboId = weiboId;
    }

    @Override
    public EasyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        EasyViewHolder holder;
        switch (viewType){
            case CONTENT:
                holder = new ContentViewHolder(mContext,
                        LayoutInflater.from(mContext).inflate(R.layout.item_content,parent,false),
                        mWeiboId);
                break;
            case COMMENT:
                holder = new CommentViewHolder(mContext,
                        LayoutInflater.from(mContext).inflate(R.layout.item_comment,parent,false));
                break;
            default:
                throw new IllegalArgumentException("No such a view type:" + viewType);
        }
        return holder;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return CONTENT;
        }
        else{
            return COMMENT;
        }
    }
}
