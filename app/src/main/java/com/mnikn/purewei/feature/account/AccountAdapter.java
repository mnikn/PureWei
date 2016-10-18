package com.mnikn.purewei.feature.account;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mnikn.mylibrary.adapter.EasyRecyclerAdapter;
import com.mnikn.mylibrary.adapter.EasyViewHolder;
import com.mnikn.mylibrary.adapter.data.CursorDataProvider;
import com.mnikn.mylibrary.util.ResourcesUtil;
import com.mnikn.purewei.App;
import com.mnikn.purewei.R;
import com.mnikn.purewei.feature.home.HomeActivity;
import com.mnikn.purewei.viewholder.AccountViewHolder;

import butterknife.ButterKnife;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class AccountAdapter extends EasyRecyclerAdapter<CursorDataProvider,Object> {

    public static final String EXTRA_AUTHORIZE = "extra_authorize";

    private Context mContext;
    private final int FOOTER = 100;
    private final int ACCOUNT = 101;

    public AccountAdapter(CursorDataProvider dataProvider,Context context) {
        super(dataProvider);
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == getItemCount() - 1){
            return FOOTER;
        }
        return ACCOUNT;
    }

    @Override
    public EasyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType == FOOTER){
            EasyViewHolder holder = new EasyViewHolder(LayoutInflater.from(mContext)
                    .inflate(R.layout.item_add_account, parent, false));
            holder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, HomeActivity.class);
                    intent.putExtra(EXTRA_AUTHORIZE,true);
                    mContext.startActivity(intent);
                }
            });
            if(App.isNightMode()){
                ((TextView) ButterKnife.findById(holder.itemView,R.id.txt_add_account))
                        .setCompoundDrawablesWithIntrinsicBounds(ResourcesUtil.getDrawable(mContext,R.drawable.ic_add_night),
                                null,null,null);
            }
            return holder;
        }
        return new AccountViewHolder(mContext,LayoutInflater.from(mContext).inflate(
                R.layout.item_account,parent,false));
    }
}
