package com.mnikn.purewei.viewholder;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.Gravity;
import android.view.LayoutInflater;
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
import com.mnikn.purewei.feature.photo.PhotoActivity;
import com.mnikn.purewei.model.WeiboModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class ContentViewHolder extends EasyViewHolder<Cursor>{

    public static final String EXTRA_PHOTO_URL = WeiboViewHolder.EXTRA_PHOTO_URL;

    @BindView(R.id.container_item) LinearLayout layout;
    @BindView(R.id.circle_img_user_icon) CircleImageView circleImgUserIcon;
    @BindView(R.id.circle_img_retweet) CircleImageView circleImgRetweet;
    @BindView(R.id.txt_user_name) TextView txtUserName;
    @BindView(R.id.txt_created_time) TextView txtCreatedTime;
    @BindView(R.id.txt_source) TextView txtSource;
    @BindView(R.id.txt_text) TextView txtText;
    @BindView(R.id.btn_attitudes) Button btnAttitudes;
    @BindView(R.id.layout_retweet) LinearLayout linearRetweet;
    @BindView(R.id.layout_pics) GridLayout gridPics;
    @BindView(R.id.layout_retweet_pics) GridLayout retweetGridPics;
    @BindView(R.id.txt_retweet_text) TextView txtRetweetText;
    @BindView(R.id.txt_retweet_user_name) TextView txtRetweetUserName;
    @BindView(R.id.txt_retweet_time) TextView txtRetweetTime;

    TextView txtRetweet;

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private WeiboModel model;

    private long mWeiboId;

    public ContentViewHolder(Context context,View itemView,long weiboId) {
        super(itemView);
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mWeiboId = weiboId;
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindView(Cursor data) {

        Cursor weiboCursor = mContext.getContentResolver().query(
                WeiboContract.WeiboEntry.buildWeiboUriWithUser(),
                null,
                WeiboContract.WeiboEntry.COLUMN_WEIBO_ID + " = ?",
                new String[]{NumberUtil.longToString(mWeiboId)},
                null);

        if(weiboCursor == null) return;

        weiboCursor.moveToFirst();
        model = new WeiboModel(weiboCursor);
        weiboCursor.close();


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
