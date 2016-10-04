package com.mnikn.purewei.viewholder;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mnikn.mylibrary.adapter.EasyViewHolder;
import com.mnikn.mylibrary.util.DataUtil;
import com.mnikn.mylibrary.util.DrawableUtil;
import com.mnikn.mylibrary.util.GlideUtil;
import com.mnikn.mylibrary.util.NumberUtil;
import com.mnikn.purewei.R;
import com.mnikn.purewei.data.WeiboContract;
import com.mnikn.purewei.feature.detail.DetailActivity;
import com.mnikn.purewei.feature.photo.PhotoActivity;
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
    public static final String EXTRA_PHOTO_URL = "extra_photo_url";

    @BindView(R.id.circle_img_user_icon) CircleImageView circleImgUserIcon;
    @BindView(R.id.circle_img_retweet) CircleImageView circleImgRetweet;
    @BindView(R.id.txt_user_name) TextView txtUserName;
    @BindView(R.id.txt_created_time) TextView txtCreatedTime;
    @BindView(R.id.txt_source) TextView txtSource;
    @BindView(R.id.txt_text) TextView txtText;
    @BindView(R.id.btn_attitudes) Button btnAttitudes;
    @BindView(R.id.btn_comments) Button btnComments;
    @BindView(R.id.btn_reports) Button btnReports;
    @BindView(R.id.layout_retweet) LinearLayout linearRetweet;
    @BindView(R.id.layout_pics) GridLayout gridPics;
    @BindView(R.id.layout_retweet_pics) GridLayout retweetGridPics;
    @BindView(R.id.txt_retweet_text) TextView txtRetweetText;
    @BindView(R.id.txt_retweet_user_name) TextView txtRetweetUserName;
    @BindView(R.id.txt_retweet_time) TextView txtRetweetTime;

    private Context mContext;
    private WeiboModel model;

    public WeiboViewHolder(Context context,View itemView) {
        super(itemView);
        mContext = context;
        setIsRecyclable(false);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindView(Cursor data) {
        model = new WeiboModel(data);

        if(!NumberUtil.isZero(model.retweetId)){
            linearRetweet.setVisibility(View.VISIBLE);
            txtRetweetText.setText(model.retweetText);
            txtRetweetUserName.setText(model.retweetUserName);
            txtRetweetTime.setText(model.retweetTime);
            GlideUtil.setCircleImage(mContext,model.retweetAvatarLargeUrl,circleImgRetweet);
            Cursor retweetPicsCursor = mContext.getContentResolver().query(
                    WeiboContract.WeiboPicsEntry.CONTENT_URI,
                    null,
                    WeiboContract.WeiboPicsEntry.COLUMN_WEIBO_ID + " = ?",
                    new String[]{NumberUtil.longToString(model.retweetId)},
                    null);
            setWeiboPics(retweetGridPics,retweetPicsCursor);
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
        if(model.liked){
            btnAttitudes.setCompoundDrawablesWithIntrinsicBounds(
                    DrawableUtil.getDrawable(mContext,R.drawable.ic_thumb_up_red_24dp),
                    null,
                    null,
                    null);
        }

        //加载图片
        Cursor picsCursor = mContext.getContentResolver().query(
                WeiboContract.WeiboPicsEntry.CONTENT_URI,
                null,
                WeiboContract.WeiboPicsEntry.COLUMN_WEIBO_ID + " = ?",
                new String[]{NumberUtil.longToString(model.weiboId)},
                null);
        gridPics.setVisibility(View.VISIBLE);
        setWeiboPics(gridPics, picsCursor);
    }

    @Optional
    @OnClick({R.id.circle_img_user_icon,R.id.txt_user_name})
    public void navUser(){
        long uid = model.userId;
        Intent intent = new Intent(mContext, UserActivity.class);
        intent.putExtra(EXTRA_UID,uid);
        mContext.startActivity(intent);
    }

//    @Optional
//    @OnClick({R.id.circle_img_retweet,R.id.txt_retweet_user_name})
//    public void navRetweetUser(){
//        long uid = model.userId;
//        Intent intent = new Intent(mContext, UserActivity.class);
//        intent.putExtra(EXTRA_UID,uid);
//        mContext.startActivity(intent);
//    }

    @Optional
    @OnClick({R.id.txt_text,R.id.btn_comments})
    public void navDetail(){
        long weiboId = model.weiboId;
        Intent intent = new Intent(mContext,DetailActivity.class);
        intent.putExtra(EXTRA_WEIBO_ID,weiboId);
        mContext.startActivity(intent);
    }

    @OnClick(R.id.txt_retweet_text)
    public void navRetweetDetail(){
        long retweetId = model.retweetId;
        Intent intent = new Intent(mContext,DetailActivity.class);
        intent.putExtra(EXTRA_WEIBO_ID,retweetId);
        mContext.startActivity(intent);
    }

    private void setWeiboPics(GridLayout gridLayout,Cursor cursor){
        if(DataUtil.isEmpty(cursor)) return;
        cursor.moveToFirst();
        int rowCount = cursor.getCount() / 3;
        if(rowCount >= 0 && cursor.getCount() % 3 != 0){
            ++rowCount;
        }
        gridLayout.setRowCount(rowCount);
        gridLayout.setColumnCount(3);
        do {
            String middleUrl = WeiboContract.WeiboPicsEntry.getMiddleUrl(cursor);
            final String largeUrl = WeiboContract.WeiboPicsEntry.getLargeUrl(cursor);
            ImageView imageView = new ImageView(mContext);
            GridLayout.LayoutParams param = new GridLayout.LayoutParams();
            param.height = 160;
            param.width = 160;
            param.rightMargin = 5;
            param.topMargin = 5;
            param.setGravity(Gravity.CENTER);
            imageView.setLayoutParams(param);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext,PhotoActivity.class);
                    intent.putExtra(EXTRA_PHOTO_URL,largeUrl);
                    mContext.startActivity(intent);
                }
            });

            GlideUtil.setImage(mContext,middleUrl,imageView);
            gridLayout.addView(imageView);
        } while (cursor.moveToNext());
        cursor.close();
    }
}
