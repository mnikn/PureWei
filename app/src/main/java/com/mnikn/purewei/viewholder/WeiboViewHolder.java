package com.mnikn.purewei.viewholder;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mnikn.mylibrary.adapter.EasyViewHolder;
import com.mnikn.mylibrary.util.DataUtil;
import com.mnikn.mylibrary.util.NumberUtil;
import com.mnikn.mylibrary.util.ResourcesUtil;
import com.mnikn.purewei.App;
import com.mnikn.purewei.R;
import com.mnikn.purewei.data.WeiboContract;
import com.mnikn.purewei.data.WeiboDataHelper;
import com.mnikn.purewei.feature.detail.DetailActivity;
import com.mnikn.purewei.feature.photo.PhotoActivity;
import com.mnikn.purewei.feature.user.UserActivity;
import com.mnikn.purewei.model.UserModel;
import com.mnikn.purewei.model.WeiboModel;
import com.mnikn.purewei.support.util.ImageDisplayUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class WeiboViewHolder extends EasyViewHolder<Cursor>{

    public static final String EXTRA_USER = "extra_user";
    public static final String EXTRA_WEIBO = "extra_weibo";
    public static final String EXTRA_PHOTO_URL = "extra_photo_url";

    @BindView(R.id.circleImg_avatars) CircleImageView circleImgUserAvatars;
    @BindView(R.id.circleImg_retweet_avatars) CircleImageView circleImgRetweetAvatars;
    @BindView(R.id.txt_user_name) TextView txtUserName;
    @BindView(R.id.txt_created_time) TextView txtCreatedTime;
    @BindView(R.id.txt_source) TextView txtSource;
    @BindView(R.id.txt_text) TextView txtText;
    @BindView(R.id.btn_attitudes) Button btnAttitudes;
    @BindView(R.id.btn_comments) Button btnComments;
    @BindView(R.id.btn_reports) Button btnReports;
    @BindView(R.id.imgBtn_more) ImageButton imgBtnMore;
    @BindView(R.id.layout_retweet) LinearLayout linearRetweet;
    @BindView(R.id.layout_pics) GridLayout gridPics;
    @BindView(R.id.layout_retweet_pics) GridLayout retweetGridPics;
    @BindView(R.id.txt_retweet_text) TextView txtRetweetText;
    @BindView(R.id.txt_retweet_user_name) TextView txtRetweetUserName;
    @BindView(R.id.txt_retweet_time) TextView txtRetweetTime;

    private Context mContext;
    private WeiboModel mWeiboModel;
    private UserModel mUserModel;

    public WeiboViewHolder(Context context,View itemView) {
        super(itemView);
        mContext = context;
        setIsRecyclable(false);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindView(Cursor data) {

        if(App.isNightMode()){
            btnAttitudes.setCompoundDrawablesWithIntrinsicBounds(ResourcesUtil.getDrawable(mContext, R.drawable.ic_thumb_up_night),
                    null, null, null);
            btnComments.setCompoundDrawablesWithIntrinsicBounds(ResourcesUtil.getDrawable(mContext, R.drawable.ic_comment_night),
                    null, null, null);
            btnReports.setCompoundDrawablesWithIntrinsicBounds(ResourcesUtil.getDrawable(mContext,R.drawable.ic_retweet_night),
                    null,null,null);
            imgBtnMore.setImageDrawable(ResourcesUtil.getDrawable(mContext, R.drawable.ic_more_night));
        }

        mWeiboModel = new WeiboModel(data);
        mUserModel = new UserModel(data);

        if(!NumberUtil.isZero(mWeiboModel.retweetId)){
            linearRetweet.setVisibility(View.VISIBLE);
            txtRetweetText.setText(mWeiboModel.retweetModel.text);
            txtRetweetUserName.setText(mWeiboModel.retweetModel.userName);
            txtRetweetTime.setText(mWeiboModel.retweetModel.createdTime);
            ImageDisplayUtil.displayFromNet(mContext, mWeiboModel.retweetModel.avatarLargeUrl, circleImgRetweetAvatars);
            Cursor retweetPicsCursor = WeiboDataHelper.getInstance().getWeiboPics(mWeiboModel.retweetId);
            setWeiboPics(retweetGridPics,retweetPicsCursor);
        }

        ((AppCompatActivity) mContext).registerForContextMenu(imgBtnMore);

        ImageDisplayUtil.displayFromNet(
                mContext,
                mWeiboModel.avatarLargeUrl,
                circleImgUserAvatars
        );

        txtText.setText(mWeiboModel.text);
        txtCreatedTime.setText(mWeiboModel.createdTime);
        txtSource.setText(mWeiboModel.source);
        txtUserName.setText(mWeiboModel.userName);
        btnAttitudes.setText(mWeiboModel.attitudesCount);
        btnComments.setText(mWeiboModel.commentsCount);
        btnReports.setText(mWeiboModel.reportsCount);
        if(mWeiboModel.liked){
            btnAttitudes.setCompoundDrawablesWithIntrinsicBounds(
                    ResourcesUtil.getDrawable(mContext,R.drawable.ic_thumb_up_red),
                    null,
                    null,
                    null);
        }

        //加载图片
        Cursor picsCursor = WeiboDataHelper.getInstance().getWeiboPics(mWeiboModel.weiboId);
        gridPics.setVisibility(View.VISIBLE);
        setWeiboPics(gridPics, picsCursor);
    }

    @Optional
    @OnClick({R.id.circleImg_avatars,R.id.txt_user_name})
    public void navUser(){
        Intent intent = new Intent(mContext, UserActivity.class);
        intent.putExtra(EXTRA_USER,mUserModel);
        mContext.startActivity(intent);
    }

    @Optional
    @OnClick({R.id.txt_text,R.id.btn_comments})
    public void navDetail(){
        Intent intent = new Intent(mContext,DetailActivity.class);
        intent.putExtra(EXTRA_WEIBO, mWeiboModel);
        mContext.startActivity(intent);
    }

    @Optional
    @OnClick({R.id.circleImg_retweet_avatars,R.id.txt_retweet_text})
    public void navRetweetDetail(){
        Intent intent = new Intent(mContext,DetailActivity.class);
        intent.putExtra(EXTRA_WEIBO, mWeiboModel.retweetModel);
        mContext.startActivity(intent);
    }

    @Optional
    @OnClick(R.id.imgBtn_more)
    void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setItems(ResourcesUtil.getStringArray(mContext, R.array.entry_btn_more), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
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
                    Intent intent = new Intent(mContext, PhotoActivity.class);
                    intent.putExtra(EXTRA_PHOTO_URL, largeUrl);
                    mContext.startActivity(intent);
                }
            });

            ImageDisplayUtil.displayFromNet(mContext,middleUrl,imageView);
            gridLayout.addView(imageView);
        } while (cursor.moveToNext());
        cursor.close();
    }
}
