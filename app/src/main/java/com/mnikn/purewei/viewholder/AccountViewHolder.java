package com.mnikn.purewei.viewholder;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mnikn.library.support.adapter.EasyViewHolder;
import com.mnikn.library.utils.Numbers;
import com.mnikn.library.utils.ResourcesUtils;
import com.mnikn.purewei.App;
import com.mnikn.purewei.R;
import com.mnikn.purewei.data.WeiboContract;
import com.mnikn.purewei.data.dao.AccountDao;
import com.mnikn.purewei.data.dao.UserDao;
import com.mnikn.purewei.feature.home.HomeActivity;
import com.mnikn.purewei.model.User;
import com.mnikn.purewei.support.AccessTokenKeeper;
import com.mnikn.purewei.support.util.ImageDisplayUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class AccountViewHolder extends EasyViewHolder<Cursor> {

    @BindView(R.id.circleImg_avatars)
    CircleImageView circleImgAvatars;
    @BindView(R.id.imgBtn_delete)
    ImageButton imgBtnDelete;
    @BindView(R.id.txt_user_name)
    TextView txtUserName;

    private Context mContext;

    public AccountViewHolder(Context context, View itemView) {
        super(itemView);
        mContext = context;
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindView(int position, final Cursor cursor) {
        if (App.isNightMode()) {
            imgBtnDelete.setImageDrawable(ResourcesUtils.getDrawable(mContext, R.drawable.ic_delete_night));
        }

        final User model = UserDao.getUser(WeiboContract.AccountEntry.getId(cursor));

        ImageDisplayUtil.displayFromNet(
                mContext,
                model.avatarLarge,
                circleImgAvatars);
        txtUserName.setText(model.name);

        imgBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDao.delete(model.id);
                if (AccessTokenKeeper.readAccessToken(mContext).getUid()
                        .equals(Numbers.longToString(model.id))) {
                    AccessTokenKeeper.clear(mContext);
                }
            }
        });

        setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccessTokenKeeper.clear(mContext);
                AccessTokenKeeper.writeAccessToken(mContext, AccountDao.getAccount(cursor).toOauth2AccessToken());
                mContext.startActivity(new Intent(mContext, HomeActivity.class));
            }
        });
    }
}
