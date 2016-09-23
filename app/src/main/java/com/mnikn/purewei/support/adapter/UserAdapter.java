package com.mnikn.purewei.support.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mnikn.mylibrary.adapter.RecyclerCursorAdapter;
import com.mnikn.mylibrary.util.GlideUtil;
import com.mnikn.mylibrary.util.NumberUtil;
import com.mnikn.purewei.R;
import com.mnikn.purewei.data.WeiboContract;
import com.mnikn.purewei.mvp.model.HomeModel;
import com.mnikn.purewei.support.viewholder.HeaderHolder;
import com.mnikn.purewei.support.viewholder.HomeViewHolder;

/**
 * Created by Administrator on 2016/9/16 0016.
 */
public class UserAdapter extends RecyclerCursorAdapter<RecyclerView.ViewHolder> {

    private static final int HEADER = 1;
    private static final int WEIBO = 2;

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public UserAdapter(Context context){
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public void bindView(RecyclerView.ViewHolder holder) {
        Cursor cursor = getCursor();

        HomeModel model = new HomeModel(cursor);

        if(holder instanceof HomeViewHolder){
            HomeViewHolder homeViewHolder = (HomeViewHolder) holder;
            if(homeViewHolder.txtRetweet == null && NumberUtil.notZero(WeiboContract.WeiboEntry.getRetweetId(cursor))){
                LinearLayout layout = homeViewHolder.layout;
                View retweet = mLayoutInflater.inflate(R.layout.include_item_retweet,null);
                layout.addView(retweet, 2);
                homeViewHolder.txtRetweet = (TextView) homeViewHolder.itemView.findViewById(R.id.txt_reweet);

            }

            GlideUtil.setCircleImage(
                    mContext,
                    WeiboContract.UserEntry.getProfileImageUrl(cursor),
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
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return HEADER;
        }
        else{
            return WEIBO;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        RecyclerView.ViewHolder holder;
        switch (viewType){
            case HEADER:
                holder = new HeaderHolder(inflater.inflate(R.layout.item_user_header,parent,false));
                break;
            case WEIBO:
                holder = new HomeViewHolder(inflater.inflate(R.layout.item_home,parent,false));
                break;
            default:
                throw new IllegalArgumentException("No such a view type:" + viewType);
        }
        return holder;
    }
}
