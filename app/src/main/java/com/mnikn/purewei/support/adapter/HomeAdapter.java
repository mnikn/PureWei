package com.mnikn.purewei.support.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mnikn.mylibrary.adapter.RecyclerCursorAdapter;
import com.mnikn.mylibrary.interfaces.OnRecyclerItemClickListener;
import com.mnikn.mylibrary.util.DataUtil;
import com.mnikn.mylibrary.util.GlideUtil;
import com.mnikn.mylibrary.util.NumberUtil;
import com.mnikn.purewei.R;
import com.mnikn.purewei.data.WeiboContract.UserEntry;
import com.mnikn.purewei.data.WeiboContract.WeiboDetailEntry;
import com.mnikn.purewei.data.WeiboContract.WeiboEntry;
import com.mnikn.purewei.mvp.model.HomeModel;
import com.mnikn.purewei.support.viewholder.HomeViewHolder;

/**
 * Created by Administrator on 2016/9/9 0009.
 */
public class HomeAdapter extends RecyclerCursorAdapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private OnRecyclerItemClickListener mListener;

    public HomeAdapter(Context context) {
        this(context,null);
    }

    public HomeAdapter(Context context,OnRecyclerItemClickListener listener) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        View view = mLayoutInflater.inflate(R.layout.item_home,parent,false);
        holder = new HomeViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void bindView(final RecyclerView.ViewHolder holder) {
        Cursor cursor = getCursor();
        HomeModel model = new HomeModel(cursor);

        HomeViewHolder homeViewHolder = (HomeViewHolder) holder;
        LinearLayout layout = homeViewHolder.layout;
        if(homeViewHolder.txtRetweet == null && NumberUtil.notZero(WeiboEntry.getRetweetId(cursor))){
            View retweet = mLayoutInflater.inflate(R.layout.include_item_retweet,null);
            layout.addView(retweet, 2);
            homeViewHolder.txtRetweet = (TextView) homeViewHolder.itemView.findViewById(R.id.txt_reweet);
        }


        GlideUtil.setCircleImage(
                mContext,
                UserEntry.getProfileImageUrl(cursor),
                homeViewHolder.circleImgUserIcon);
        homeViewHolder.txtText.setText(model.text);
        homeViewHolder.txtCreatedTime.setText(model.createdTime);
        homeViewHolder.txtSource.setText(model.source);
        homeViewHolder.txtUserName.setText(model.userName);
        homeViewHolder.txtAttitudesCount.setText(model.attitudesCount);
        homeViewHolder.txtCommentsCount.setText(model.commentsCount);
        homeViewHolder.txtReportsCount.setText(model.reportsCount);
        if(homeViewHolder.txtRetweet != null){
            homeViewHolder.txtRetweet.setText(model.retweetText);
        }
        Cursor picsCursor = mContext.getContentResolver().query(
                WeiboDetailEntry.CONTENT_URI,
                null,
                WeiboDetailEntry.COLUMN_WEIBO_ID + " = ?",
                new String[]{model.weiboId},
                null);
        if(DataUtil.isEmpty(picsCursor)) return;
        picsCursor.moveToFirst();
        do {
            String url = WeiboDetailEntry.getPicsUrl(picsCursor);
            if(url == null) return;
            ImageView imageView = new ImageView(mContext);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            GlideUtil.setImage(mContext,url,imageView);
            layout.addView(imageView);
        } while (picsCursor.moveToNext());
        picsCursor.close();
    }

    @Override
    public void onClick(View v) {
        if(mListener != null){
            mListener.onItemClick(v,UserEntry.getUsertId(getCursor()));
        }
    }
}
