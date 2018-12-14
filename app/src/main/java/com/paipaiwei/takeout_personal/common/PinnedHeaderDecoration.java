package com.paipaiwei.takeout_personal.common;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Region;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.AdapterDataObserver;
import androidx.recyclerview.widget.RecyclerView.ItemDecoration;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import androidx.recyclerview.widget.RecyclerView.LayoutParams;
import androidx.recyclerview.widget.RecyclerView.State;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import android.util.SparseArray;
import android.view.View;
import android.view.View.MeasureSpec;

public class PinnedHeaderDecoration extends ItemDecoration {
    private int mHeaderPosition = -1;
    private int mPinnedHeaderTop;
    private boolean mIsAdapterDataChanged;
    private Rect mClipBounds;
    private View mPinnedHeaderView;
    private RecyclerView.Adapter mAdapter;
    private final SparseArray<PinnedHeaderCreator> mTypePinnedHeaderFactories = new SparseArray();
    private final AdapterDataObserver mAdapterDataObserver = new AdapterDataObserver() {
        public void onChanged() {
            PinnedHeaderDecoration.this.mIsAdapterDataChanged = true;
        }
    };

    public PinnedHeaderDecoration() {
    }

    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        this.createPinnedHeader(parent);
        if (this.mPinnedHeaderView != null) {
            int headerEndAt = this.mPinnedHeaderView.getTop() + this.mPinnedHeaderView.getHeight();
            View v = parent.findChildViewUnder((float)(c.getWidth() / 2), (float)(headerEndAt + 1));
            if (this.isPinnedView(parent, v)) {
                this.mPinnedHeaderTop = v.getTop() - this.mPinnedHeaderView.getHeight();
            } else {
                this.mPinnedHeaderTop = 0;
            }

            this.mClipBounds = c.getClipBounds();
            this.mClipBounds.top = this.mPinnedHeaderTop + this.mPinnedHeaderView.getHeight();
            c.clipRect(this.mClipBounds);
        }

    }

    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (this.mPinnedHeaderView != null) {
            c.save();
            this.mClipBounds.top = 0;
            c.clipRect(this.mClipBounds, Region.Op.UNION);
            c.translate(0.0F, (float)this.mPinnedHeaderTop);
            this.mPinnedHeaderView.draw(c);
            c.restore();
        }

    }

    private void createPinnedHeader(RecyclerView parent) {
        this.updatePinnedHeader(parent);
        LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager != null && layoutManager.getChildCount() > 0) {
            int firstVisiblePosition = ((RecyclerView.LayoutParams)layoutManager.getChildAt(0).getLayoutParams()).getViewAdapterPosition();
            int headerPosition = this.findPinnedHeaderPosition(parent, firstVisiblePosition);
            if (headerPosition >= 0 && this.mHeaderPosition != headerPosition) {
                this.mHeaderPosition = headerPosition;
                int viewType = this.mAdapter.getItemViewType(headerPosition);
                RecyclerView.ViewHolder pinnedViewHolder = this.mAdapter.createViewHolder(parent, viewType);
                this.mAdapter.bindViewHolder(pinnedViewHolder, headerPosition);
                this.mPinnedHeaderView = pinnedViewHolder.itemView;
                android.view.ViewGroup.LayoutParams layoutParams = this.mPinnedHeaderView.getLayoutParams();
                if (layoutParams == null) {
                    layoutParams = new android.view.ViewGroup.LayoutParams(-1, -2);
                    this.mPinnedHeaderView.setLayoutParams(layoutParams);
                }

                int heightMode = MeasureSpec.getMode(layoutParams.height);
                int heightSize = MeasureSpec.getSize(layoutParams.height);
                if (heightMode == 0) {
                    heightMode = 1073741824;
                }

                int maxHeight = parent.getHeight() - parent.getPaddingTop() - parent.getPaddingBottom();
                if (heightSize > maxHeight) {
                    heightSize = maxHeight;
                }

                int ws = MeasureSpec.makeMeasureSpec(parent.getWidth() - parent.getPaddingLeft() - parent.getPaddingRight(), 1073741824);
                int hs = MeasureSpec.makeMeasureSpec(heightSize, heightMode);
                this.mPinnedHeaderView.measure(ws, hs);
                this.mPinnedHeaderView.layout(0, 0, this.mPinnedHeaderView.getMeasuredWidth(), this.mPinnedHeaderView.getMeasuredHeight());
            }

        }
    }

    private int findPinnedHeaderPosition(RecyclerView parent, int fromPosition) {
        if (fromPosition <= this.mAdapter.getItemCount() && fromPosition >= 0) {
            for(int position = fromPosition; position >= 0; --position) {
                int viewType = this.mAdapter.getItemViewType(position);
                if (this.isPinnedViewType(parent, position, viewType)) {
                    return position;
                }
            }

            return -1;
        } else {
            return -1;
        }
    }

    private boolean isPinnedViewType(RecyclerView parent, int adapterPosition, int viewType) {
        PinnedHeaderDecoration.PinnedHeaderCreator pinnedHeaderCreator = (PinnedHeaderDecoration.PinnedHeaderCreator)this.mTypePinnedHeaderFactories.get(viewType);
        return pinnedHeaderCreator != null && pinnedHeaderCreator.create(parent, adapterPosition);
    }

    private boolean isPinnedView(RecyclerView parent, View v) {
        int position = parent.getChildAdapterPosition(v);
        return position == -1 ? false : this.isPinnedViewType(parent, position, this.mAdapter.getItemViewType(position));
    }

    private void updatePinnedHeader(RecyclerView parent) {
        RecyclerView.Adapter adapter = parent.getAdapter();
        if (this.mAdapter != adapter || this.mIsAdapterDataChanged) {
            this.resetPinnedHeader();
            if (this.mAdapter != null) {
                this.mAdapter.unregisterAdapterDataObserver(this.mAdapterDataObserver);
            }

            this.mAdapter = adapter;
            if (this.mAdapter != null) {
                this.mAdapter.registerAdapterDataObserver(this.mAdapterDataObserver);
            }
        }

    }

    private void resetPinnedHeader() {
        this.mHeaderPosition = -1;
        this.mPinnedHeaderView = null;
    }

    public void registerTypePinnedHeader(int itemType, PinnedHeaderDecoration.PinnedHeaderCreator pinnedHeaderCreator) {
        this.mTypePinnedHeaderFactories.put(itemType, pinnedHeaderCreator);
    }

    public interface PinnedHeaderCreator {
        boolean create(RecyclerView var1, int var2);
    }
}
