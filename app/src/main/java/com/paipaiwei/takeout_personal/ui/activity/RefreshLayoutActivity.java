package com.paipaiwei.takeout_personal.ui.activity;

import com.google.android.material.appbar.AppBarLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.yjhh.common.base.BaseActivity;
import com.paipaiwei.takeout_personal.R;


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
