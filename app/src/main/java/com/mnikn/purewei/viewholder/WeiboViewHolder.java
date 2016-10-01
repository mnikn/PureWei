package com.mnikn.purewei.viewholder;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mnikn.mylibrary.adapter.EasyViewHolder;
import com.mnikn.mylibrary.util.GlideUtil;
import com.mnikn.mylibrary.util.NumberUtil;
import com.mnikn.purewei.R;
import com.mnikn.purewei.feature.detail.DetailActivity;
import com.mnikn.purewei.feature.user.UserActivity;
import com.mnikn.purewei.model.WeiboModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class WeiboViewHolder extends EasyViewHolder<Cursor>{

    public static final String EXTRA_UID = "extra_uid";
    public static final String EXTRA_WEIBO_ID = "extra_weibo_id";

    @BindView(R.id.container_item) LinearLayout layout;
    @BindView(R.id.circle_img_user_icon) CircleImageView circleImgUserIcon;
    @BindView(R.id.txt_user_name) TextView txtUserName;
    @BindView(R.id.txt_created_time) TextView txtCreatedTime;
    @BindView(R.id.txt_source) TextView txtSource;
    @BindView(R.id.txt_text) TextView txtText;
    @BindView(R.id.btn_attitudes) Button btnAttitudes;
    @BindView(R.id.btn_comments) Button btnComments;
    @BindView(R.id.btn_reports) Button btnReports;

    TextView txtRetweetText;
    TextView txtRetweetUserName;

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private WeiboModel model;

    public WeiboViewHolder(Context context,View itemView) {
        super(itemView);
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindView(Cursor data) {
        model = new WeiboModel(data);

        if(txtRetweetText == null && !NumberUtil.isZero(model.retweedId)){
            View retweet = mLayoutInflater.inflate(R.layout.include_item_retweet,null);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(6,14,6,0);
            retweet.setLayoutParams(params);
            layout.addView(retweet,2);
            txtRetweetText = ButterKnife.findById(itemView,R.id.txt_retweet_text);
            txtRetweetUserName = ButterKnife.findById(itemView,R.id.txt_retweet_user_name);
        }


        GlideUtil.setCircleImage(
                mContext,
                model.avatarLargeUrl,
                circleImgUserIcon);
        txtText.setText(model.text);
        txtCreatedTime.setText(model.createdTime);
        txtSource.setText(model.source);
        txtUserName.setText(model.userName);
        btnAttitudes.setText(model.attitudesCount);
        btnComments.setText(model.commentsCount);
        btnReports.setText(model.reportsCount);
        if(txtRetweetText != null){
            txtRetweetText.setText(model.retweetText);
            txtRetweetUserName.setText(model.retweetUserName);
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

    @Optional
    @OnClick({R.id.circle_img_user_icon,R.id.txt_user_name})
    public void navToUserActivity(){
        long uid = model.userId;
        Intent intent = new Intent(mContext, UserActivity.class);
        intent.putExtra(EXTRA_UID,uid);
        mContext.startActivity(intent);
    }

    @Optional
    @OnClick({R.id.txt_text,R.id.btn_comments})
    public void navToDetailActivity(){
        long weiboId = model.weiboId;
        Intent intent = new Intent(mContext,DetailActivity.class);
        intent.putExtra(EXTRA_WEIBO_ID,weiboId);
        mContext.startActivity(intent);
    }
}
