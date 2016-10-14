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
import com.mnikn.mylibrary.util.NumberUtil;
import com.mnikn.mylibrary.util.ResourcesUtil;
import com.mnikn.purewei.R;
import com.mnikn.purewei.data.WeiboContract;
import com.mnikn.purewei.data.WeiboDataHelper;
import com.mnikn.purewei.feature.photo.PhotoActivity;
import com.mnikn.purewei.model.WeiboModel;
import com.mnikn.purewei.support.util.ImageDisplayUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class ContentViewHolder extends EasyViewHolder<Cursor>{

    public static final String EXTRA_PHOTO_URL = WeiboViewHolder.EXTRA_PHOTO_URL;

    @BindView(R.id.container_item) LinearLayout layout;
    @BindView(R.id.circleImg_avatars) CircleImageView circleImgUserAvatars;
    @BindView(R.id.circleImg_retweet_avatars) CircleImageView circleImgRetweetAvatars;
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

    private Context mContext;
    private WeiboModel model;

    public ContentViewHolder(Context context,View itemView,WeiboModel model) {
        super(itemView);
        mContext = context;
        this.model = model;
        setIsRecyclable(false);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindView() {
        if(!NumberUtil.isZero(model.retweetId)){
            linearRetweet.setVisibility(View.VISIBLE);
            txtRetweetText.setText(model.retweetModel.text);
            txtRetweetUserName.setText(model.retweetModel.userName);
            txtRetweetTime.setText(model.retweetModel.createdTime);
            ImageDisplayUtil.displayFromNet(mContext, model.retweetModel.avatarLargeUrl, circleImgRetweetAvatars);
            Cursor retweetPicsCursor = WeiboDataHelper.getInstance().getWeiboPics(model.retweetId);
            setWeiboPics(retweetGridPics,retweetPicsCursor);
        }

        ImageDisplayUtil.displayFromNet(
                mContext,
                model.avatarLargeUrl,
                circleImgUserAvatars
        );
        txtText.setText(model.text);
        txtCreatedTime.setText(model.createdTime);
        txtSource.setText(model.source);
        txtUserName.setText(model.userName);
        btnAttitudes.setText(model.attitudesCount);
        if(model.liked){
            btnAttitudes.setCompoundDrawablesWithIntrinsicBounds(
                    ResourcesUtil.getDrawable(mContext, R.drawable.ic_thumb_up_red_24dp),
                    null,
                    null,
                    null);
        }

        //加载图片
        Cursor picsCursor = WeiboDataHelper.getInstance().getWeiboPics(model.weiboId);
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

            ImageDisplayUtil.displayFromNet(mContext, middleUrl, imageView);
            gridLayout.addView(imageView);
        } while (cursor.moveToNext());
        cursor.close();
    }
}
