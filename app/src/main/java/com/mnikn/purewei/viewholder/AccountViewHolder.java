package com.mnikn.purewei.viewholder;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.TextView;

import com.mnikn.mylibrary.adapter.EasyViewHolder;
import com.mnikn.purewei.R;
import com.mnikn.purewei.data.WeiboContract;
import com.mnikn.purewei.data.WeiboDataHelper;
import com.mnikn.purewei.model.UserModel;
import com.mnikn.purewei.support.util.ImageDisplayUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class AccountViewHolder extends EasyViewHolder<Cursor> {

    @BindView(R.id.circleImg_icon) CircleImageView mCircleImgIcon;
    @BindView(R.id.txt_user_name) TextView mTxtUserName;

    private Context mContext;

    public AccountViewHolder(Context context,View itemView) {
        super(itemView);
        mContext = context;
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void bindView(Cursor data) {
        UserModel model = WeiboDataHelper.getInstance()
                .getUserModel(WeiboContract.AccountEntry.getUid(data));

        ImageDisplayUtil.displayFromNet(
                mContext,
                model.avatarLargeUrl,
                mCircleImgIcon);
        mTxtUserName.setText(model.userName);

    }
}
