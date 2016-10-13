package com.mnikn.purewei.feature.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mnikn.mylibrary.adapter.EasyRecyclerAdapter;
import com.mnikn.mylibrary.adapter.EasyViewHolder;
import com.mnikn.mylibrary.adapter.data.CursorDataProvider;
import com.mnikn.purewei.R;
import com.mnikn.purewei.model.UserModel;
import com.mnikn.purewei.viewholder.HeaderHolder;
import com.mnikn.purewei.viewholder.WeiboViewHolder;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class UserAdapter extends EasyRecyclerAdapter<CursorDataProvider,Object> {

    private static final int HEADER = 1;
    private static final int WEIBO = 2;

    private Context mContext;
    private UserModel mUserModel;

    public UserAdapter(CursorDataProvider dataProvider,Context context,UserModel model){
        super(dataProvider);
        mContext = context;
        mUserModel = model;
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
                holder = new HeaderHolder(
                        mContext,
                        inflater.inflate(R.layout.item_user_header,parent,false),
                        mUserModel);
                break;
            case WEIBO:
                holder = new WeiboViewHolder(mContext,inflater.inflate(R.layout.item_weibo,parent,false));
                break;
            default:
                throw new IllegalArgumentException("No such a view type:" + viewType);
        }
        return holder;
    }
}
