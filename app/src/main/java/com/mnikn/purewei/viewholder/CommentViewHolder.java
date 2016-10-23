package com.mnikn.purewei.viewholder;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.TextView;

import com.mnikn.library.support.adapter.EasyViewHolder;
import com.mnikn.purewei.R;
import com.mnikn.purewei.feature.user.UserActivity;
import com.mnikn.purewei.model.CommentModel;
import com.mnikn.purewei.model.UserModel;
import com.mnikn.purewei.support.util.ImageDisplayUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class CommentViewHolder extends EasyViewHolder<Cursor>{

    @BindView(R.id.circleImg_avatars) CircleImageView circleImgUserAvatars;
    @BindView(R.id.txt_user_name) TextView txtUserName;
    @BindView(R.id.txt_created_time) TextView txtCreatedTime;
    @BindView(R.id.txt_source) TextView txtSource;
    @BindView(R.id.txt_text) TextView txtText;

    private Context mContext;
    private CommentModel mCommentModel;
    private UserModel mUserModel;

    public CommentViewHolder(Context context,View itemView) {
        super(itemView);
        mContext = context;
        ButterKnife.bind(this, itemView);

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });
    }

    @Override
    public void bindView(int position,Cursor cursor) {
        mCommentModel = new CommentModel(cursor);
        mUserModel = new UserModel(cursor);

        ImageDisplayUtil.displayFromNet(
                mContext,
                mCommentModel.avatarLargeUrl,
                circleImgUserAvatars
        );
        txtText.setText(mCommentModel.text);
        txtCreatedTime.setText(mCommentModel.createdTime);
        txtSource.setText(mCommentModel.source);
        txtUserName.setText(mCommentModel.userName);
    }

    @Optional
    @OnClick({R.id.circleImg_avatars,R.id.txt_user_name})
    public void navUser(){
        Intent intent = new Intent(mContext, UserActivity.class);
        intent.putExtra(WeiboViewHolder.EXTRA_USER,mUserModel);
        mContext.startActivity(intent);
    }
}
