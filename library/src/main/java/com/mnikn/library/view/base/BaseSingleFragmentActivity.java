package com.mnikn.library.view.base;

import android.os.Bundle;

import com.mnikn.library.R;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 * A simple activity that contains one fragment.
 */
public abstract class BaseSingleFragmentActivity extends BaseActivity {

    private BaseFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        mFragment = onCreateFragment();

        replaceFragment(getContainerId(),mFragment);
    }

    public BaseFragment getFragment(){
        return mFragment;
    }

    /**
     * If you need to custom your own layout,you may have to override this two functions.
     */
    protected int getLayoutId(){
        return R.layout.activity_single_fragment;
    }
    protected int getContainerId(){
        return R.id.container;
    }

    protected abstract BaseFragment onCreateFragment();

}
