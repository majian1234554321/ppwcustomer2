package com.yjhh.ppwcustomer.ui.activity;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.yjhh.common.base.BaseActivity;
import com.yjhh.ppwcustomer.R;


@Route(path = "/RefreshLayoutActivity/RefreshLayout")
public class RefreshLayoutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh_layout);


        final TextView mTitle = findViewById(R.id.common_index_header_tv_title);

        AppBarLayout appBarLayout = findViewById(R.id.appbarlayout);

        RefreshLayout refreshLayout = findViewById(R.id.refreshLayout);

        refreshLayout.setRefreshHeader(new ClassicsHeader(this));


        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                mTitle.setAlpha(-verticalOffset * 1.0f / appBarLayout.getTotalScrollRange());
            }
        });
    }


}
