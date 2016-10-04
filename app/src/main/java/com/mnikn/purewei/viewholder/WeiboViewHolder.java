package com.mnikn.purewei.viewholder;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    @BindView(R.id.container_item) LinearLayout layout;
    @BindView(R.id.circle_img_user_icon) CircleImageView circleImgUserIcon;
    @BindView(R.id.txt_user_name) TextView txtUserName;
    @BindView(R.id.txt_created_time) TextView txtCreatedTime;
    @BindView(R.id.txt_source) TextView txtSource;
    @BindView(R.id.txt_text) TextView txtText;
    @BindView(R.id.btn_attitudes) Button btnAttitudes;
    @BindView(R.id.btn_comments) Button btnComments;
    @BindView(R.id.btn_reports) Button btnReports;

    View viewRetweet;
    TextView txtRetweetText;
    TextView txtRetweetUserName;

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private WeiboModel model;

    public WeiboViewHolder(Context context,View itemView) {
        super(itemView);
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        setIsRecyclable(false);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindView(Cursor data) {
        model = new WeiboModel(data);

        if(viewRetweet == null && !NumberUtil.isZero(model.retweedId)){
            viewRetweet = mLayoutInflater.inflate(R.layout.include_item_retweet,null);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(6, 14, 6, 0);
            viewRetweet.setLayoutParams(params);
            layout.addView(viewRetweet,2);
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
        if(model.liked){
            btnAttitudes.setCompoundDrawablesWithIntrinsicBounds(
                    DrawableUtil.getDrawable(mContext,R.drawable.ic_thumb_up_red_24dp),
                    null,
                    null,
                    null);
        }
        if(txtRetweetText != null){
            txtRetweetText.setText(model.retweetText);
            txtRetweetUserName.setText(model.retweetUserName);
        }

        //加载图片
        final Cursor picsCursor = mContext.getContentResolver().query(
                WeiboContract.WeiboPicsEntry.CONTENT_URI,
                null,
                WeiboContract.WeiboPicsEntry.COLUMN_WEIBO_ID + " = ?",
                new String[]{NumberUtil.longToString(model.weiboId)},
                null);
        if(DataUtil.isEmpty(picsCursor)) return;
        picsCursor.moveToFirst();
        GridLayout picsLayout = new GridLayout(mContext);
        int rowCount = picsCursor.getCount() / 3;
        if(rowCount >= 0 && picsCursor.getCount() % 3 != 0){
            ++rowCount;
        }
        picsLayout.setRowCount(rowCount);
        picsLayout.setColumnCount(3);
        do {
            String middleUrl = WeiboContract.WeiboPicsEntry.getMiddleUrl(picsCursor);
            final String largeUrl = WeiboContract.WeiboPicsEntry.getLargeUrl(picsCursor);
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
            picsLayout.addView(imageView);
        } while (picsCursor.moveToNext());
        layout.addView(picsLayout,2);
        picsCursor.close();
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
