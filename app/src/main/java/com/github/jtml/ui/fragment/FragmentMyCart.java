package com.github.jtml.ui.fragment;

import android.os.Bundle;

import com.github.jtml.R;
import com.github.jtml.base.BaseFragment;

/**
 * Created by pengjf on 2016/5/12.
 */
public class FragmentMyCart extends BaseFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_cart;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        init();

    }

    private void init() {
    }

    public static FragmentMyCart newInstance() {
        return new FragmentMyCart();
    }
}
