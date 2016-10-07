package com.mnikn.purewei.viewholder;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.TextView;

import com.mnikn.mylibrary.adapter.EasyViewHolder;
import com.mnikn.purewei.R;
import com.mnikn.purewei.model.CommentModel;
import com.mnikn.purewei.support.util.ImageDisplayUtil;

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
    private CommentModel model;

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
    public void bindView(Cursor data) {
        model = new CommentModel(data);

        ImageDisplayUtil.displayFromNet(
                mContext,
                model.avatarLargeUrl,
                circleImgUserIcon
        );
        txtText.setText(model.text);
        txtCreatedTime.setText(model.createdTime);
        txtSource.setText(model.source);
        txtUserName.setText(model.userName);
    }
}
