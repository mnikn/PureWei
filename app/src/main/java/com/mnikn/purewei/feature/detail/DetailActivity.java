package com.mnikn.purewei.feature.detail;

import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mnikn.library.view.BaseSingleFragmentActivity;
import com.mnikn.library.view.BaseFragment;
import com.mnikn.purewei.R;
import com.mnikn.purewei.feature.write.WriteActivity;
import com.mnikn.purewei.model.WeiboModel;
import com.mnikn.purewei.support.Constant;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public class DetailActivity extends BaseSingleFragmentActivity {

    public static final String EXTRA_MODEL = "extra_model";

    public static void startActivity(Context context,WeiboModel model){
        Intent intent = new Intent(context,DetailActivity.class);
        intent.putExtra(EXTRA_MODEL,model);
        context.startActivity(intent);
    }

    @Override
    protected BaseFragment onCreateFragment() {
        return DetailFragment.newInstance();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        int id = item.getItemId();
        switch (id){
            case R.id.action_comment:
                WeiboModel model = getIntent().getParcelableExtra(EXTRA_MODEL);
                WriteActivity.startActivity(this, Constant.WRITE_COMMENT,model);
                break;
        }
        return false;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.weibo_context,menu);
    }
}
