package com.mnikn.purewei.support.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mnikn.mylibrary.adapter.EasyViewHolder;
import com.mnikn.mylibrary.util.GlideUtil;
import com.mnikn.mylibrary.util.NumberUtil;
import com.mnikn.purewei.R;
import com.mnikn.purewei.data.WeiboContract;
import com.mnikn.purewei.mvp.model.WeiboModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class WeiboViewHolder extends EasyViewHolder<Cursor> {

    @BindView(R.id.container_item)public LinearLayout layout;

    @BindView(R.id.circle_img_user_icon) CircleImageView circleImgUserIcon;
    @BindView(R.id.txt_user_name) TextView txtUserName;
    @BindView(R.id.txt_created_time) TextView txtCreatedTime;
    @BindView(R.id.txt_source) TextView txtSource;
    @BindView(R.id.txt_text) TextView txtText;
    @BindView(R.id.txt_attitudes_count) TextView txtAttitudesCount;
    @BindView(R.id.txt_comments_count) TextView txtCommentsCount;
    @BindView(R.id.txt_reports_count) TextView txtReportsCount;

    TextView txtRetweet;

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public WeiboViewHolder(Context context,View itemView) {
        super(itemView);
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        ButterKnife.bind(this,itemView);
    }

    @Override
    public void bindView(Cursor data) {
        WeiboModel model = new WeiboModel(data);

        if(txtRetweet == null && !NumberUtil.isZero(WeiboContract.WeiboEntry.getRetweetId(data))){
            View retweet = mLayoutInflater.inflate(R.layout.include_item_retweet, null);
            layout.addView(retweet,2);
            txtRetweet = ButterKnife.findById(itemView,R.id.txt_retweet);
        }


        GlideUtil.setCircleImage(
                mContext,
                model.profileImageUrl,
                circleImgUserIcon);
        txtText.setText(model.text);
        txtCreatedTime.setText(model.createdTime);
        txtSource.setText(model.source);
        txtUserName.setText(model.userName);
        txtAttitudesCount.setText(model.attitudesCount);
        txtCommentsCount.setText(model.commentsCount);
        txtReportsCount.setText(model.reportsCount);
        if(txtRetweet != null){
            txtRetweet.setText(model.retweetText);
        }
//        Cursor picsCursor = mContext.getContentResolver().query(
//                WeiboDetailEntry.CONTENT_URI,
//                null,
//                WeiboDetailEntry.COLUMN_WEIBO_ID + " = ?",
//                new String[]{model.weiboId},
//                null);
//        if(DataUtil.isEmpty(picsCursor)) return;
//        picsCursor.moveToFirst();
//        do {
//            String url = WeiboDetailEntry.getPicsUrl(picsCursor);
//            if(url == null) return;
//            ImageView imageView = new ImageView(mContext);
//            imageView.setLayoutParams(new LinearLayout.LayoutParams(
//                    200,
//                    200));
//            GlideUtil.setImage(mContext,url,imageView);
//            layout.addView(imageView);
//        } while (picsCursor.moveToNext());
//        picsCursor.close();
    }
}
