package com.mnikn.purewei.feature.write;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Menu;

import com.mnikn.library.view.BaseFragment;
import com.mnikn.library.view.BaseSingleFragmentActivity;
import com.mnikn.purewei.R;
import com.mnikn.purewei.data.WeiboContract;
import com.mnikn.purewei.data.entity.DraftEntity;
import com.mnikn.purewei.model.WeiboModel;
import com.mnikn.purewei.support.Constant;

public class WriteActivity extends BaseSingleFragmentActivity {

    public static final String EXTRA_TYPE = "extra_type";
    public static final String EXTRA_WEIBO = "extra_weibo";

    public static void startActivity(Context context,int type){
        Intent intent = new Intent(context,WriteActivity.class);
        intent.putExtra(EXTRA_TYPE,type);
        context.startActivity(intent);
    }

    public static void startActivity(Context context,int type,WeiboModel model){
        Intent intent = new Intent(context,WriteActivity.class);
        intent.putExtra(EXTRA_TYPE,type);
        intent.putExtra(EXTRA_WEIBO,model);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getIntent().getIntExtra(EXTRA_TYPE,1) == Constant.WRITE_COMMENT){
            setTitle(R.string.label_write_comment);
        }
    }

    @Override
    protected BaseFragment onCreateFragment() {
        return WriteFragment.newInstance();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.write,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        final String content = ((WriteFragment) getFragment()).edtWeibo.getText().toString();
        if(content.length() != 0){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.dialog_draft);
            builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    int type = getIntent().getIntExtra(EXTRA_TYPE,1);
                    if(type == Constant.WRITE_COMMENT){
                        long weiboId = ((WeiboModel) getIntent().getParcelableExtra(EXTRA_WEIBO)).weiboId;
                        getContentResolver().insert(
                                WeiboContract.DraftEntry.CONTENT_URI,
                                new DraftEntity(type,weiboId,content).toContentValues());
                    }
                    else{
                        getContentResolver().insert(
                                WeiboContract.DraftEntry.CONTENT_URI,
                                new DraftEntity(type,content).toContentValues());
                    }
                    WriteActivity.super.onBackPressed();
                }
            });
            builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    WriteActivity.super.onBackPressed();
                }
            });
            builder.show();
        }
        else{
            super.onBackPressed();
        }
    }
}
