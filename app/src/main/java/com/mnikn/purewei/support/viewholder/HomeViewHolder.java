package com.mnikn.purewei.support.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mnikn.purewei.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2016/9/17 0017.
 */
public class HomeViewHolder extends RecyclerView.ViewHolder {

    public LinearLayout layout;

    public CircleImageView circleImgUserIcon;
    public TextView txtUserName;
    public TextView txtCreatedTime;
    public TextView txtSource;
    public TextView txtText;
    public TextView txtAttitudesCount;
    public TextView txtCommentsCount;
    public TextView txtReportsCount;

    public TextView txtRetweet;

    public HomeViewHolder(View itemView) {
        super(itemView);

        layout = (LinearLayout) itemView.findViewById(R.id.container_item);

        circleImgUserIcon = (CircleImageView) itemView.findViewById(R.id.circle_img_user_icon);
        txtText = (TextView) itemView.findViewById(R.id.txt_text);
        txtCreatedTime = (TextView) itemView.findViewById(R.id.txt_created_time);
        txtSource = (TextView) itemView.findViewById(R.id.txt_source);
        txtUserName = (TextView) itemView.findViewById(R.id.txt_user_name);
        txtAttitudesCount = (TextView) itemView.findViewById(R.id.txt_attitudes_count);
        txtCommentsCount= (TextView) itemView.findViewById(R.id.txt_comments_count);
        txtReportsCount = (TextView) itemView.findViewById(R.id.txt_reports_count);

    }
}
