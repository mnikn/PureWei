package com.mnikn.purewei.feature.settings;


import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v4.app.Fragment;

import com.mnikn.purewei.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountManageFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_account_manage);
        setHasOptionsMenu(true);
    }
}
