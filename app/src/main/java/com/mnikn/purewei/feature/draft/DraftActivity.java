package com.mnikn.purewei.feature.draft;

import android.content.Context;
import android.content.Intent;

import com.mnikn.library.view.BaseFragment;
import com.mnikn.library.view.BaseSingleFragmentActivity;

public class DraftActivity extends BaseSingleFragmentActivity {

    public static void startActivity(Context context){
        Intent intent = new Intent(context,DraftActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected BaseFragment onCreateFragment() {
        return DraftFragment.newInstance();
    }
}
