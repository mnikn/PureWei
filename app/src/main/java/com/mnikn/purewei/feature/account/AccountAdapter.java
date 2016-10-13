package com.mnikn.purewei.feature.account;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mnikn.mylibrary.adapter.EasyRecyclerAdapter;
import com.mnikn.mylibrary.adapter.EasyViewHolder;
import com.mnikn.mylibrary.adapter.data.CursorDataProvider;
import com.mnikn.purewei.R;
import com.mnikn.purewei.viewholder.AccountViewHolder;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class AccountAdapter extends EasyRecyclerAdapter<CursorDataProvider,Object> {

    private Context mContext;

    public AccountAdapter(CursorDataProvider dataProvider,Context context) {
        super(dataProvider);
        mContext = context;
    }

    @Override
    public EasyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AccountViewHolder(mContext,LayoutInflater.from(mContext).inflate(
                R.layout.item_account,parent,false));
    }
}
