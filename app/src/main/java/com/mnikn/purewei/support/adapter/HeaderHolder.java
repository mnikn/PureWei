package com.mnikn.purewei.support.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mnikn.mylibrary.adapter.EasyViewHolder;
import com.mnikn.mylibrary.util.GlideUtil;
import com.mnikn.purewei.R;
import com.mnikn.purewei.mvp.model.UserModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class HeaderHolder extends EasyViewHolder<Cursor> {

    @BindView(R.id.txt_followers_count) TextView txtFollowersCount;
    @BindView(R.id.txt_friends_count) TextView txtFriendsCount;
    @BindView(R.id.txt_weibo_count) TextView txtWeiboCount;
    @BindView(R.id.txt_user_name) TextView txtUserName;
    @BindView(R.id.txt_user_description) TextView txtDescription;
    @BindView(R.id.circle_img_user_icon) CircleImageView circleImgUserIcon;
    @BindView(R.id.img_avatar) ImageView imgAvater;

    private Context mContext;

    public HeaderHolder(Context context,View itemView) {
        super(itemView);
        mContext = context;
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void bindView(Cursor data) {
        UserModel model = new UserModel(data);

        GlideUtil.setCircleImage(
                mContext,
                model.profileImageUrl,
                circleImgUserIcon);
        txtFollowersCount.setText(model.followersCount);
        txtFriendsCount.setText(model.friendsCount);
        txtWeiboCount.setText(model.weiboCount);
        txtUserName.setText(model.userName);
        txtDescription.setText(model.description);
    }
}
