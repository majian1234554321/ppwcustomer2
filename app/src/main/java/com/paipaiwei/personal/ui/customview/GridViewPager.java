package com.paipaiwei.personal.ui.customview;

import android.content.Context;
import android.util.Log;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.paipaiwei.personal.R;
import com.paipaiwei.personal.bean.Main1HeadBean;

import java.util.ArrayList;
import java.util.List;

public class GridViewPager extends RelativeLayout {
    private boolean hasCustomOval = false;
    private LayoutInflater inflater;
    private Context mContext;
    private ViewPager mPager;
    private LinearLayout mLlDot;
    private List<Main1HeadBean.TabsBean> mData;

    private List<GridView> mPagerList;
    private GridItemClickListener gridItemClickListener;
    private GridItemLongClickListener gridItemLongClickListener;

    public void setVis(){
        if (mLlDot!= null){
            mLlDot.setVisibility(GONE);
        }
    }

    /**
     * 总的页数 计算得出
     */
    private int pageCount;

    /**
     * 每一页显示的个数 可设置
     */
    private int pageSize = 10;

    /**
     * 当前显示的是第几页
     */
    private int curIndex = 0;

    public GridViewPager(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public GridViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public GridViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.view, this);
        mPager = (ViewPager) view.findViewById(R.id.viewpager);
        mLlDot = (LinearLayout) view.findViewById(R.id.ll_dot);
    }

    /**
     * necessary 必须作为最后一步
     *
     * @param list
     * @return
     */
    public GridViewPager init(List<Main1HeadBean.TabsBean> list) {
        mData = list;
        //总的页数=总数/每页数量，并取整
        pageCount = (int) Math.ceil(mData.size() * 1.0 / pageSize);
        mPagerList = new ArrayList<GridView>();

        for (int i = 0; i < pageCount; i++) {
            //每个页面都是inflate出一个新实例
            GridView gridView = (GridView) inflater.inflate(R.layout.gridview, mPager, false);
            gridView.setAdapter(new GridViewAdapter(mContext, mData, i, pageSize));
            mPagerList.add(gridView);

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                    if (gridItemClickListener == null) return;
                    int position = pos + curIndex * pageSize;
                    gridItemClickListener.click(pos, position, mData.get(position).text);
                }
            });
            //true if the callback consumed the long click, false otherwise
            gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int pos, long id) {
                    if (gridItemLongClickListener == null) return false;
                    else {
                        int position = pos + curIndex * pageSize;
                        gridItemLongClickListener.click(pos, position, mData.get(position).text);
                        return true;
                    }
                }
            });
        }
        //设置适配器
        mPager.setAdapter(new ViewPagerAdapter<GridView>(mPagerList));
        //设置圆点
        if (!hasCustomOval) setOvalLayout();
        return this;
    }


    /**
     * 设置圆点
     */
    private void setOvalLayout() {
        hasCustomOval = true;
        mLlDot.removeAllViews();
        for (int i = 0; i < pageCount; i++) {

            mLlDot.addView(inflater.inflate(R.layout.dot, null));
        }
        // 默认显示第一页
        mLlDot.getChildAt(0).findViewById(R.id.v_dot)
                .setBackgroundResource(R.drawable.dot_selected);
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageSelected(int position) {
                // 取消圆点选中
                mLlDot.getChildAt(curIndex)
                        .findViewById(R.id.v_dot)
                        .setBackgroundResource(R.drawable.dot_normal);
                // 圆点选中
                mLlDot.getChildAt(position)
                        .findViewById(R.id.v_dot)
                        .setBackgroundResource(R.drawable.dot_selected);
                curIndex = position;
            }

            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

    /**
     * optional 设置单元点击事件
     *
     * @param listener
     * @return
     */
    public GridViewPager setGridItemClickListener(GridItemClickListener listener) {
        gridItemClickListener = listener;
        return this;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = 0;
        for (int i = 0; mPagerList != null && i < mPagerList.size(); i++) {
            View child = mPagerList.get(i);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            mLlDot.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));


            Log.i("onMeasure", "getHeight：" + mLlDot.getHeight() + "getHeight2:" + mLlDot.getMeasuredHeight());

            int h = child.getMeasuredHeight() + mLlDot.getMeasuredHeight();
            if (h > height)
                height = h;
        }
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height + getPaddingBottom() + getPaddingTop(), MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    /**
     * optional 设置单元长按事件
     *
     * @param listener
     * @return
     */
    public GridViewPager setGridItemLongClickListener(GridItemLongClickListener listener) {
        gridItemLongClickListener = listener;
        return this;
    }

    public List<GridView> getmPagerList() {
        return mPagerList;
    }

    public int getPageCount() {
        return pageCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public GridViewPager setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public int getCurIndex() {
        return curIndex;
    }
}