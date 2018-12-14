package com.paipaiwei.takeout_personal.ui.fragment;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.graphics.PointF;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.View;


import android.view.animation.AccelerateInterpolator;
import android.widget.*;
import com.yjhh.common.base.BaseFragment;
import com.paipaiwei.takeout_personal.R;
import com.paipaiwei.takeout_personal.adapter.AdapterLeftMenu;
import com.paipaiwei.takeout_personal.adapter.AdapterRightDish;

import com.paipaiwei.takeout_personal.bean.ModelDish;
import com.paipaiwei.takeout_personal.bean.ModelShopCart;
import com.paipaiwei.takeout_personal.interfaces.ShopCartInterface;

import com.paipaiwei.takeout_personal.ui.MeiTuanItem;
import com.paipaiwei.takeout_personal.ui.customview.RxFakeAddImageView;
import com.paipaiwei.takeout_personal.ui.customview.RxPointFTypeEvaluator;

import java.util.ArrayList;


@SuppressLint("ValidFragment")
public class OrderDishesFragment extends BaseFragment implements AdapterLeftMenu.onItemSelectedListener, ShopCartInterface {

    private ArrayList<String> list;

    @Override
    protected int getLayoutRes() {
        return R.layout.orderdishesfragment;
    }


    public OrderDishesFragment(ModelShopCart mModelShopCart, ArrayList<ModelDish> mModelDishMenuList) {
        this.mModelShopCart = mModelShopCart;
        this.mModelDishMenuList = mModelDishMenuList;
    }


    TextView totalPriceTextView;

    LinearLayout mShoppingCartBottom;

    RecyclerView mLeftMenu;//左侧菜单栏

    RecyclerView mRightMenu;//右侧菜单栏


    ImageView mShoppingCart;
    FrameLayout mShoppingCartLayout;
    TextView totalPriceNumTextView;

    RelativeLayout mMainLayout;

    private AdapterLeftMenu leftAdapter;
    private AdapterRightDish rightAdapter;
    private ArrayList<ModelDish> mModelDishMenuList;//数据源

    private ModelShopCart mModelShopCart;


    @Override
    protected void initView() {


        mMainLayout = findActivityViewById(R.id.main_layout);
        totalPriceTextView = findActivityViewById(R.id.shopping_cart_total_tv);
        mShoppingCartBottom = findActivityViewById(R.id.shopping_cart_bottom);
        mLeftMenu = findActivityViewById(R.id.left_menu);
        mRightMenu = findActivityViewById(R.id.right_menu);


        mShoppingCart = findActivityViewById(R.id.shopping_cart);

        mShoppingCartLayout = findActivityViewById(R.id.shopping_cart_layout);

        totalPriceNumTextView = findActivityViewById(R.id.shopping_cart_total_num);


        initData();
        initView2();
        initAdapter();


    }


    private void initView2() {
        mLeftMenu.setLayoutManager(new LinearLayoutManager(context));
        mRightMenu.setLayoutManager(new LinearLayoutManager(context));


        mRightMenu.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int position = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                //Log.e("dy:","dy:"+dy);
                Log.e("position:", "position:" + position);
                int lastPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                Log.e("lastPosition:", "lastPosition:" + lastPosition);
                if (dy > 0) {
                    if (position + 1 < mModelDishMenuList.size()) {
                        if (position == 0) {
                            leftAdapter.setSelectPosition(0);
                        } else {

                            if (isTopNotEqualsNext(position)) {

                                leftAdapter.setSelectPosition(rightBoundLeftPosition(position));
                                mLeftMenu.scrollToPosition(rightBoundLeftPosition(position));
                                Log.e("rightBoundLeftPosition:", "rightBoundLeftPosition:" + rightBoundLeftPosition(position));
                                // ((LinearLayoutManager) leftView.getLayoutManager()).smoothScrollToPosition(leftView,null,data.rightBoundLeftPosition(data.getRightData().get(position),data.getLeftData()));
                            }
                        }
                    }

                } else {
                    if (position + 1 < mModelDishMenuList.size()) {
                        if (position == 0) {
                            leftAdapter.setSelectPosition(0);
                        } else {
                            if (isTopNotEqualsBefore(position)) {
                                leftAdapter.setSelectPosition(rightBoundLeftPosition(position));
                                mLeftMenu.scrollToPosition(rightBoundLeftPosition(position));

                                //   ((LinearLayoutManager) leftView.getLayoutManager()).smoothScrollToPosition(leftView,null,data.rightBoundLeftPosition(data.getRightData().get(position),data.getLeftData()));
                            }
                        }
                    }

                }
            }
        });

    }


    private void initAdapter() {


        list = new ArrayList<>();

        list.add("早点");
        list.add("午餐");
        list.add("晚餐");
        list.add("夜宵");


        leftAdapter = new AdapterLeftMenu(context, list);
        rightAdapter = new AdapterRightDish(context, mModelDishMenuList, mModelShopCart);
        // MeiTuanItem
        mRightMenu.addItemDecoration(new MeiTuanItem(mActivity, mModelDishMenuList));


        mRightMenu.setAdapter(rightAdapter);
        mLeftMenu.setAdapter(leftAdapter);
        leftAdapter.addItemSelectedListener(this);
        rightAdapter.setShopCartInterface(this);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        leftAdapter.removeItemSelectedListener(this);
    }


    @Override
    public void onLeftItemSelected(int position, String menu) {


        ((LinearLayoutManager) mRightMenu.getLayoutManager()).scrollToPositionWithOffset(leftBoundRightPosition(position), 0);

    }

    public int leftBoundRightPosition(int position) {

        for (int i = 0; i < mModelDishMenuList.size(); i++) {

            if (mModelDishMenuList.get(i).tyee.equals(list.get(position))) {
                return i;
            } else {
                continue;
            }
        }
        return 0;
    }


    @Override
    public void add(View view, int position) {
        int[] addLocation = new int[2];
        int[] cartLocation = new int[2];
        int[] recycleLocation = new int[2];
        view.getLocationInWindow(addLocation);
        mShoppingCart.getLocationInWindow(cartLocation);
        mRightMenu.getLocationInWindow(recycleLocation);

        PointF startP = new PointF();
        PointF endP = new PointF();
        PointF controlP = new PointF();

        startP.x = addLocation[0];
        startP.y = addLocation[1] - recycleLocation[1];
        endP.x = cartLocation[0];
        endP.y = cartLocation[1] - recycleLocation[1];
        controlP.x = endP.x;
        controlP.y = startP.y;

        final RxFakeAddImageView rxFakeAddImageView = new RxFakeAddImageView(context);
        mMainLayout.addView(rxFakeAddImageView);
        rxFakeAddImageView.setImageResource(R.drawable.ic_add_circle_blue_700_36dp);
        rxFakeAddImageView.getLayoutParams().width = getResources().getDimensionPixelSize(R.dimen.item_dish_circle_size);
        rxFakeAddImageView.getLayoutParams().height = getResources().getDimensionPixelSize(R.dimen.item_dish_circle_size);
        rxFakeAddImageView.setVisibility(View.VISIBLE);
        ObjectAnimator addAnimator = ObjectAnimator.ofObject(rxFakeAddImageView, "mPointF",
                new RxPointFTypeEvaluator(controlP), startP, endP);
        addAnimator.setInterpolator(new AccelerateInterpolator());
        addAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                rxFakeAddImageView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                rxFakeAddImageView.setVisibility(View.GONE);
                mMainLayout.removeView(rxFakeAddImageView);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        ObjectAnimator scaleAnimatorX = new ObjectAnimator().ofFloat(mShoppingCart, "scaleX", 0.6f, 1.0f);
        ObjectAnimator scaleAnimatorY = new ObjectAnimator().ofFloat(mShoppingCart, "scaleY", 0.6f, 1.0f);
        scaleAnimatorX.setInterpolator(new AccelerateInterpolator());
        scaleAnimatorY.setInterpolator(new AccelerateInterpolator());
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(scaleAnimatorX).with(scaleAnimatorY).after(addAnimator);
        animatorSet.setDuration(800);
        animatorSet.start();

        showTotalPrice();


        ActionListener listener = (ActionListener) getActivity();
        listener.actionEvent("A", "B");


       /* Observable dis = Observable.create(new ObservableOnSubscribe<TakeoutOrderModel>() {

            @Override
            public void subscribe(ObservableEmitter<TakeoutOrderModel> emitter) throws Exception {

                TakeoutOrderModel model = new TakeoutOrderModel();

                emitter.onNext(model);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TakeoutOrderModel>() {
                    @Override
                    public void accept(TakeoutOrderModel takeoutOrderModel) throws Exception {

                    }
                });
*/

    }

    @Override
    public void remove(View view, int position) {

        ActionListener listener = (ActionListener) getActivity();
        listener.actionEvent("A", "B");

        showTotalPrice();
    }

    public void showTotalPrice() {
        if (mModelShopCart != null && mModelShopCart.getShoppingTotalPrice() > 0) {

            totalPriceTextView.setText("¥ " + mModelShopCart.getShoppingTotalPrice());

            totalPriceNumTextView.setText("" + mModelShopCart.getShoppingAccount());
        } else {

        }

        rightAdapter.notifyDataSetChanged();
    }


    public interface ActionListener {
        void actionEvent(String username, String password);
    }


    public boolean isTopNotEqualsBefore(int position) {


        return mModelDishMenuList.get(position).tyee.equals(mModelDishMenuList.get(position - 1).tyee);


    }

    public boolean isTopNotEqualsNext(int position) {
        return mModelDishMenuList.get(position).tyee.equals(mModelDishMenuList.get(position + 1).tyee);
    }

    public int rightBoundLeftPosition(int position) {

        for (int i = 0; i < list.size(); i++) {

            if (mModelDishMenuList.get(position).tyee.equals(list.get(i))) {
                return i;
            } else {
                continue;
            }
        }
        return 0;
    }

}








