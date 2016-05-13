package com.github.jtml.ui.fragment;

import android.os.Bundle;

import com.github.jtml.R;
import com.github.jtml.base.BaseFragment;

/**
 * Created by pengjf on 2016/5/12.
 */
public class FragmentPerson extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_person;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        init();

    }

    public static FragmentPerson newInstance() {
        return new FragmentPerson();
    }

    private void init() {
    }

}
