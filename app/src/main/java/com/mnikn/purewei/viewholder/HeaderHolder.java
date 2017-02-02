package com.mnikn.purewei.viewholder;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mnikn.library.support.adapter.EasyViewHolder;
import com.mnikn.library.utils.Numbers;
import com.mnikn.purewei.R;
import com.mnikn.purewei.feature.photo.PhotoActivity;
import com.mnikn.purewei.model.User;
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
    @BindView(R.id.circleImg_avatars) CircleImageView circleImgUserAvatars;
    @BindView(R.id.img_cover) ImageView imgCover;

    private Context mContext;
    private User mModel;

    public HeaderHolder(Context context,View itemView,User model) {
        super(itemView);
        mContext = context;
        mModel = model;
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void bindView(int position,Cursor cursor) {
        ImageDisplayUtil.displayFromNet(
                mContext,
                mModel.avatarHd,
                circleImgUserAvatars
        );

        txtFollowersCount.setText(Numbers.longToString(mModel.followersCount));
        txtFriendsCount.setText(Numbers.longToString(mModel.friendsCount));
        txtWeiboCount.setText(Numbers.longToString(mModel.statusesCount));
        txtUserName.setText(mModel.name);
        txtDescription.setText(mModel.description);

        if(mModel.coverImage != null){
            ImageDisplayUtil.displayFromNet(mContext, mModel.coverImage, imgCover);
        }
    }

    @OnClick(R.id.circleImg_avatars)
    void navUserIcon(){
        Intent intent = new Intent(mContext,PhotoActivity.class);
        intent.putExtra(EXTRA_PHOTO_URL, mModel.avatarHd);
        mContext.startActivity(intent);
    }

    @OnClick(R.id.img_cover)
    void navCover(){
        Intent intent = new Intent(mContext,PhotoActivity.class);
        intent.putExtra(EXTRA_PHOTO_URL,mModel.coverImage);
        mContext.startActivity(intent);
    }

}
