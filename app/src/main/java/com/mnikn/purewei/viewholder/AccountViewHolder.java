package com.mnikn.purewei.viewholder;

import android.content.Context;
import android.view.ViewGroup;

import com.mnikn.mylibrary.adapter.EasyViewHolder;
import com.mnikn.purewei.model.AccountModel;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class AccountViewHolder extends EasyViewHolder<AccountModel> {
    public AccountViewHolder(Context context, ViewGroup parent, int layoutId) {
        super(context, parent, layoutId);
    }

    @Override
    public void bindView(AccountModel data) {
        super.bindView(data);
    }
}
