package com.mnikn.mylibrary.activity;

import android.os.Bundle;
import android.view.MenuItem;

import com.mnikn.mylibrary.R;
import com.mnikn.mylibrary.fragment.BaseFragment;


public abstract class SingleFragmentActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fragment);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        addFragment(getFragment());
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.fragment_container;
    }

    public abstract BaseFragment getFragment();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
