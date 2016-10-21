package com.mnikn.purewei.feature.draft;

import android.content.Context;
import android.content.Intent;

import com.mnikn.mylibrary.mvp.view.activity.SingleFragmentActivity;
import com.mnikn.mylibrary.mvp.view.fragment.BaseFragment;

public class DraftActivity extends SingleFragmentActivity {

    public static void startActivity(Context context){
        Intent intent = new Intent(context,DraftActivity.class);
        context.startActivity(intent);
    }

    @Override
    public BaseFragment getFragment() {
        return DraftFragment.newInstance();
    }
}
