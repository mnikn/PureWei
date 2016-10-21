package com.mnikn.purewei.feature.write;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import com.mnikn.mylibrary.mvp.view.activity.SingleFragmentActivity;
import com.mnikn.mylibrary.mvp.view.fragment.BaseFragment;
import com.mnikn.purewei.R;
import com.mnikn.purewei.model.WeiboModel;
import com.mnikn.purewei.support.Constant;

public class WriteActivity extends SingleFragmentActivity {

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.write,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public BaseFragment getFragment() {
        return WriteFragment.newInstance();
    }
}
