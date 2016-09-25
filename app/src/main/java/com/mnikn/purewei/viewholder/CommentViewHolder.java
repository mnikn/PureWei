package com.mnikn.purewei.viewholder;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.TextView;

import com.mnikn.mylibrary.adapter.EasyViewHolder;
import com.mnikn.mylibrary.util.GlideUtil;
import com.mnikn.purewei.R;
import com.mnikn.purewei.model.WeiboModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class CommentViewHolder extends EasyViewHolder<Cursor>{

    @BindView(R.id.circle_img_user_icon) CircleImageView circleImgUserIcon;
    @BindView(R.id.txt_user_name) TextView txtUserName;
    @BindView(R.id.txt_created_time) TextView txtCreatedTime;
    @BindView(R.id.txt_source) TextView txtSource;
    @BindView(R.id.txt_text) TextView txtText;

    private Context mContext;
    private WeiboModel model;

    public CommentViewHolder(Context context,View itemView) {
        super(itemView);
        mContext = context;
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindView(Cursor data) {
        model = new WeiboModel(data);

        GlideUtil.setCircleImage(
                mContext,
                model.profileImageUrl,
                circleImgUserIcon);
        txtText.setText(model.text);
        txtCreatedTime.setText(model.createdTime);
        txtSource.setText(model.source);
        txtUserName.setText(model.userName);
    }
}
