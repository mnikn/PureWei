package com.mnikn.mylibrary.activity;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.mnikn.mylibrary.fragment.BaseFragment;

/**
 * Activity基类
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected abstract int getFragmentContainerId();

    protected void addFragment(BaseFragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        if(fragment != null){
            fm.beginTransaction()
                    .replace(getFragmentContainerId(),fragment)
                    .commit();
        }
    }

    protected void removeFragment(){
        FragmentManager fm = getSupportFragmentManager();
        if(fm.getBackStackEntryCount() > 1){
            fm.popBackStack();
        }
        else{
            finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(KeyEvent.KEYCODE_BACK == keyCode){
            FragmentManager fm = getSupportFragmentManager();
            if(fm.getBackStackEntryCount() == 1){
                finish();
                return true;
            }
        }
        return super.onKeyDown(keyCode,event);
    }
}
