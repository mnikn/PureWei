package com.mnikn.purewei.support.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mnikn.purewei.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2016/9/17 0017.
 */
public class HeaderHolder extends RecyclerView.ViewHolder {

    private TextView txtFollowersCount;
    private TextView txtFriendsCount;
    private TextView txtWeiboCount;
    private CircleImageView circleImgUserIcon;
    private ImageView imgAvater;


    public HeaderHolder(View itemView) {
        super(itemView);
        txtFollowersCount = (TextView) itemView.findViewById(R.id.txt_followers_count);
        txtFriendsCount = (TextView) itemView.findViewById(R.id.txt_friends_count);
        txtWeiboCount = (TextView) itemView.findViewById(R.id.txt_weibo_count);
        circleImgUserIcon = (CircleImageView) itemView.findViewById(R.id.circle_img_user_icon);
        imgAvater = (ImageView) itemView.findViewById(R.id.img_avatar);
    }
}
