package com.mnikn.purewei.support.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mnikn.mylibrary.adapter.RecyclerCursorAdapter;
import com.mnikn.mylibrary.util.DateUtil;
import com.mnikn.mylibrary.util.GlideUtil;
import com.mnikn.mylibrary.util.NumberUtil;
import com.mnikn.mylibrary.util.TextUtil;
import com.mnikn.purewei.R;
import com.mnikn.purewei.data.WeiboContract;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2016/9/9 0009.
 */
public class HomeAdapter extends RecyclerCursorAdapter<RecyclerView.ViewHolder> {

    private static final int NO_RETWEET = 1;
    private static final int WITH_RETWEET = 2;

    private Context mContext;

    public HomeAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        Cursor cursor = getCursor();
        cursor.moveToPosition(position);
        if(TextUtil.isEmpty(WeiboContract.WeiboEntry.getRetweetText(cursor))){
            return NO_RETWEET;
        }
        else{
            return WITH_RETWEET;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        switch (viewType){
            case NO_RETWEET:
                holder = new NoRetweetHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home_no_retweet,parent,false));
                break;
            case WITH_RETWEET:
                holder = new WithRetweetHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home_with_retweet,parent,false));
                break;
            default:
                throw new IllegalArgumentException("No such a view type:" + viewType);
        }
        //return new NoRetweetHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home_no_retweet,parent,false));
        //return new WithRetweetHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home_with_retweet,parent,false));
        return holder;
    }

    @Override
    public void bindView(final RecyclerView.ViewHolder holder) {
        Cursor cursor = getCursor();

        if(holder instanceof NoRetweetHolder){
            NoRetweetHolder noRetweetHolder = (NoRetweetHolder) holder;

            GlideUtil.setCircleImage(
                    mContext,
                    WeiboContract.UserEntry.getProfileImageUrl(cursor),
                    noRetweetHolder.cvUserIcon);
            noRetweetHolder.tvText.setText(WeiboContract.WeiboEntry.getText(cursor));
            noRetweetHolder.tvCreatedTime.setText(DateUtil.getShowDay(WeiboContract.WeiboEntry.getCreatedTime(cursor)));
            noRetweetHolder.tvSource.setText(WeiboContract.WeiboEntry.getSource(cursor));
            noRetweetHolder.tvUserName.setText(WeiboContract.UserEntry.getName(cursor));
            noRetweetHolder.tvAltitudesCount.setText(NumberUtil.longToString(WeiboContract.WeiboEntry.getAltitudesCount(cursor)));
            noRetweetHolder.tvCommentsCount.setText(NumberUtil.longToString(WeiboContract.WeiboEntry.getCommentsCount(cursor)));
            noRetweetHolder.tvReportsCount.setText(NumberUtil.longToString(WeiboContract.WeiboEntry.getReportsCount(cursor)));
        }
        else if(holder instanceof WithRetweetHolder){
            WithRetweetHolder withRetweetHolder = (WithRetweetHolder) holder;

            GlideUtil.setCircleImage(
                    mContext,
                    WeiboContract.UserEntry.getProfileImageUrl(cursor),
                    withRetweetHolder.cvUserIcon);
            withRetweetHolder.tvText.setText(WeiboContract.WeiboEntry.getText(cursor));
            withRetweetHolder.tvRetweet.setText(String.format("%s:%s", WeiboContract.WeiboEntry.getRetweetUserName(cursor), WeiboContract.WeiboEntry.getRetweetText(cursor)));
            withRetweetHolder.tvText.setText(WeiboContract.WeiboEntry.getText(cursor));
            withRetweetHolder.tvCreatedTime.setText(DateUtil.getShowDay(WeiboContract.WeiboEntry.getCreatedTime(cursor)));
            withRetweetHolder.tvSource.setText(WeiboContract.WeiboEntry.getSource(cursor));
            withRetweetHolder.tvUserName.setText(WeiboContract.UserEntry.getName(cursor));
            withRetweetHolder.tvAltitudesCount.setText(NumberUtil.longToString(WeiboContract.WeiboEntry.getAltitudesCount(cursor)));
            withRetweetHolder.tvCommentsCount.setText(NumberUtil.longToString(WeiboContract.WeiboEntry.getCommentsCount(cursor)));
            withRetweetHolder.tvReportsCount.setText(NumberUtil.longToString(WeiboContract.WeiboEntry.getReportsCount(cursor)));
        }


    }


    public static class NoRetweetHolder extends RecyclerView.ViewHolder{

        public CircleImageView cvUserIcon;
        public TextView tvUserName;
        public TextView tvCreatedTime;
        public TextView tvSource;
        public TextView tvText;
        public TextView tvAltitudesCount;
        public TextView tvCommentsCount;
        public TextView tvReportsCount;

        public NoRetweetHolder(View itemView) {
            super(itemView);
            cvUserIcon = (CircleImageView) itemView.findViewById(R.id.cv_home_user_icon);
            tvText = (TextView) itemView.findViewById(R.id.tv_home_text);
            tvCreatedTime = (TextView) itemView.findViewById(R.id.tv_home_created_time);
            tvSource = (TextView) itemView.findViewById(R.id.tv_home_source);
            tvUserName = (TextView) itemView.findViewById(R.id.tv_home_user_name);
            tvAltitudesCount = (TextView) itemView.findViewById(R.id.tv_altitudes_count);
            tvCommentsCount= (TextView) itemView.findViewById(R.id.tv_comments_count);
            tvReportsCount = (TextView) itemView.findViewById(R.id.tv_reports_count);
        }
    }

    public static class WithRetweetHolder extends RecyclerView.ViewHolder{

        public CircleImageView cvUserIcon;
        public TextView tvUserName;
        public TextView tvCreatedTime;
        public TextView tvSource;
        public TextView tvText;
        public TextView tvAltitudesCount;
        public TextView tvCommentsCount;
        public TextView tvReportsCount;
        public TextView tvRetweet;

        public WithRetweetHolder(View itemView) {
            super(itemView);
            cvUserIcon = (CircleImageView) itemView.findViewById(R.id.cv_home_user_icon);
            tvText = (TextView) itemView.findViewById(R.id.tv_home_text);
            tvCreatedTime = (TextView) itemView.findViewById(R.id.tv_home_created_time);
            tvSource = (TextView) itemView.findViewById(R.id.tv_home_source);
            tvUserName = (TextView) itemView.findViewById(R.id.tv_home_user_name);
            tvAltitudesCount = (TextView) itemView.findViewById(R.id.tv_altitudes_count);
            tvCommentsCount= (TextView) itemView.findViewById(R.id.tv_comments_count);
            tvReportsCount = (TextView) itemView.findViewById(R.id.tv_reports_count);
            tvRetweet = (TextView) itemView.findViewById(R.id.tv_home_reweet);
        }
    }
}
