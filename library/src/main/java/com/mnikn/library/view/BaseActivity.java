package com.mnikn.library.view;

import android.support.v7.app.AppCompatActivity;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 * All activity need to extends BaseActivity
 */
public abstract class BaseActivity extends AppCompatActivity{

    protected void addFragment(BaseFragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .add(fragment,fragment.toString())
                .commit();
    }

    protected void removeFragment(BaseFragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .remove(fragment)
                .commit();
    }

    protected void replaceFragment(int containerId,BaseFragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(containerId,fragment)
                .commit();
    }
}
