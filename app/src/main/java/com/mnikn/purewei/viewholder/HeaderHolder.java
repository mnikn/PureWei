package com.mnikn.purewei.viewholder;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mnikn.mylibrary.adapter.EasyViewHolder;
import com.mnikn.purewei.R;
import com.mnikn.purewei.feature.photo.PhotoActivity;
import com.mnikn.purewei.model.UserModel;
import com.mnikn.purewei.support.util.ImageDisplayUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class HeaderHolder extends EasyViewHolder<Cursor> {

    public static final String EXTRA_PHOTO_URL = WeiboViewHolder.EXTRA_PHOTO_URL;

    @BindView(R.id.txt_followers_count) TextView txtFollowersCount;
    @BindView(R.id.txt_friends_count) TextView txtFriendsCount;
    @BindView(R.id.txt_weibo_count) TextView txtWeiboCount;
    @BindView(R.id.txt_user_name) TextView txtUserName;
    @BindView(R.id.txt_user_description) TextView txtDescription;
    @BindView(R.id.circle_img_user_icon) CircleImageView circleImgUserIcon;
    @BindView(R.id.img_cover) ImageView imgCover;

    private Context mContext;
    private UserModel mModel;

    public HeaderHolder(Context context,View itemView) {
        super(itemView);
        mContext = context;
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void bindView(Cursor data) {

        mModel = new UserModel(data);

        ImageDisplayUtil.displayFromNet(
                mContext,
                mModel.avatarHdUrl,
                circleImgUserIcon
        );

        txtFollowersCount.setText(mModel.followersCount);
        txtFriendsCount.setText(mModel.friendsCount);
        txtWeiboCount.setText(mModel.weiboCount);
        txtUserName.setText(mModel.userName);
        txtDescription.setText(mModel.description);

        if(mModel.coverUrl != null){
            ImageDisplayUtil.displayFromNet(mContext, mModel.coverUrl, imgCover);
        }
    }

    @OnClick(R.id.circle_img_user_icon)
    void navUserIcon(){
        Intent intent = new Intent(mContext,PhotoActivity.class);
        intent.putExtra(EXTRA_PHOTO_URL, mModel.avatarHdUrl);
        mContext.startActivity(intent);
    }

    @OnClick(R.id.img_cover)
    void navCover(){
        Intent intent = new Intent(mContext,PhotoActivity.class);
        intent.putExtra(EXTRA_PHOTO_URL,mModel.coverUrl);
        mContext.startActivity(intent);
    }

}
