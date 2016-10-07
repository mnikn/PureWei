package com.mnikn.purewei.support.base;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * @author <a href="mailto:iamtruelyking@gmail.com">mnikn</a>
 */
public abstract class BasePreferenceFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(getPreferenceResource());
        setHasOptionsMenu(true);
    }

    protected abstract int getPreferenceResource();
}
