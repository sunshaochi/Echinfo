package com.beyonditsm.echinfo.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.beyonditsm.echinfo.R;
import com.beyonditsm.echinfo.base.BaseActivity;
import com.beyonditsm.echinfo.view.ClearEditText;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * 搜索
 * Created by wangbin on 16/4/6.
 */
public class SearchAct extends BaseActivity{
    @ViewInject(R.id.ceSearch)
    private ClearEditText ceSearch;
    @Override
    public void setLayout() {
        setContentView(R.layout.act_search);
    }

    @Override
    public void init(Bundle savedInstanceState) {

        ceSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}
