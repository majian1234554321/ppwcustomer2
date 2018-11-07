package com.yjhh.ppwcustomer.ui.fragment;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.graphics.PointF;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;


import android.view.animation.AccelerateInterpolator;
import android.widget.*;
import com.yjhh.common.base.BaseFragment;
import com.yjhh.ppwcustomer.R;
import com.yjhh.ppwcustomer.adapter.AdapterLeftMenu;
import com.yjhh.ppwcustomer.adapter.AdapterRightDish;

import com.yjhh.ppwcustomer.bean.ModelDishMenu;
import com.yjhh.ppwcustomer.bean.ModelShopCart;
import com.yjhh.ppwcustomer.interfaces.ShopCartInterface;

import com.yjhh.ppwcustomer.ui.customview.RxFakeAddImageView;
import com.yjhh.ppwcustomer.ui.customview.RxPointFTypeEvaluator;

import java.util.ArrayList;
@SuppressLint("ValidFragment")
public class OrderDishesFragment extends BaseFragment  implements AdapterLeftMenu.onItemSelectedListener, ShopCartInterface {
    @Override
    protected int getLayoutRes() {
        return R.layout.orderdishesfragment;
    }



    public OrderDishesFragment(ModelShopCart mModelShopCart,ArrayList<ModelDishMenu> mModelDishMenuList){
        this.mModelShopCart = mModelShopCart;
        this.mModelDishMenuList = mModelDishMenuList;
    }


    TextView totalPriceTextView;

    LinearLayout mShoppingCartBottom;

    RecyclerView mLeftMenu;//左侧菜单栏

    RecyclerView mRightMenu;//右侧菜单栏

    TextView headerView;

    LinearLayout headerLayout;//右侧菜单栏最上面的菜单
    ImageView mShoppingCart;
    FrameLayout mShoppingCartLayout;
    TextView totalPriceNumTextView;

    RelativeLayout mMainLayout;
    private ModelDishMenu headMenu;
    private AdapterLeftMenu leftAdapter;
    private AdapterRightDish rightAdapter;
    private ArrayList<ModelDishMenu> mModelDishMenuList;//数据源
    private boolean leftClickType = false;//左侧菜单点击引发的右侧联动
    private ModelShopCart mModelShopCart;



    @Override
    protected void initView() {



        mMainLayout =  findActivityViewById(R.id.main_layout);
        totalPriceTextView =  findActivityViewById(R.id.shopping_cart_total_tv);
        mShoppingCartBottom =  findActivityViewById(R.id.shopping_cart_bottom);
        mLeftMenu =  findActivityViewById(R.id.left_menu);
        mRightMenu =  findActivityViewById(R.id.right_menu);
        headerView =  findActivityViewById(R.id.right_menu_tv);
        headerLayout =  findActivityViewById(R.id.right_menu_item);

        mShoppingCart =  findActivityViewById(R.id.shopping_cart);

        mShoppingCartLayout =  findActivityViewById(R.id.shopping_cart_layout);

        totalPriceNumTextView =  findActivityViewById(R.id.shopping_cart_total_num);




        initData();
        initView2();
        initAdapter();


    }




    private void initView2() {
        mLeftMenu.setLayoutManager(new LinearLayoutManager(context));
        mRightMenu.setLayoutManager(new LinearLayoutManager(context));

        mRightMenu.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.canScrollVertically(1) == false) {//无法下滑
                    showHeadView();
                    return;
                }
                View underView = null;
                if (dy > 0)
                    underView = mRightMenu.findChildViewUnder(headerLayout.getX(), headerLayout.getMeasuredHeight() + 1);
                else
                    underView = mRightMenu.findChildViewUnder(headerLayout.getX(), 0);
                if (underView != null && underView.getContentDescription() != null) {
                    int position = Integer.parseInt(underView.getContentDescription().toString());
                    ModelDishMenu menu = rightAdapter.getMenuOfMenuByPosition(position);

                    if (leftClickType || !menu.getMenuName().equals(headMenu.getMenuName())) {
                        if (dy > 0 && headerLayout.getTranslationY() <= 1 && headerLayout.getTranslationY() >= -1 * headerLayout.getMeasuredHeight() * 4 / 5 && !leftClickType) {// underView.getTop()>9
                            int dealtY = underView.getTop() - headerLayout.getMeasuredHeight();
                            headerLayout.setTranslationY(dealtY);
//                            Log.e(TAG, "onScrolled: "+headerLayout.getTranslationY()+"   "+headerLayout.getBottom()+"  -  "+headerLayout.getMeasuredHeight() );
                        } else if (dy < 0 && headerLayout.getTranslationY() <= 0 && !leftClickType) {
                            headerView.setText(menu.getMenuName());
                            int dealtY = underView.getBottom() - headerLayout.getMeasuredHeight();
                            headerLayout.setTranslationY(dealtY);
//                            Log.e(TAG, "onScrolled: "+headerLayout.getTranslationY()+"   "+headerLayout.getBottom()+"  -  "+headerLayout.getMeasuredHeight() );
                        } else {
                            headerLayout.setTranslationY(0);
                            headMenu = menu;
                            headerView.setText(headMenu.getMenuName());
                            for (int i = 0; i < mModelDishMenuList.size(); i++) {
                                if (mModelDishMenuList.get(i) == headMenu) {
                                    leftAdapter.setSelectedNum(i);
                                    break;
                                }
                            }
                            if (leftClickType) leftClickType = false;
                          //  Log.e(TAG, "onScrolled: " + menu.getMenuName());
                        }
                    }
                }
            }
        });


    }



    private void initAdapter() {
        leftAdapter = new AdapterLeftMenu(context, mModelDishMenuList);
        rightAdapter = new AdapterRightDish(context, mModelDishMenuList, mModelShopCart);
        mRightMenu.setAdapter(rightAdapter);
        mLeftMenu.setAdapter(leftAdapter);
        leftAdapter.addItemSelectedListener(this);
        rightAdapter.setShopCartInterface(this);
        initHeadView();
    }

    private void initHeadView() {
        headMenu = rightAdapter.getMenuOfMenuByPosition(0);
        headerLayout.setContentDescription("0");
        headerView.setText(headMenu.getMenuName());
    }




    @Override
    public void onDestroy() {
        super.onDestroy();
        leftAdapter.removeItemSelectedListener(this);
    }

    private void showHeadView() {
        headerLayout.setTranslationY(0);
        View underView = mRightMenu.findChildViewUnder(headerView.getX(), 0);
        if (underView != null && underView.getContentDescription() != null) {
            int position = Integer.parseInt(underView.getContentDescription().toString());
            ModelDishMenu menu = rightAdapter.getMenuOfMenuByPosition(position + 1);
            headMenu = menu;
            headerView.setText(headMenu.getMenuName());
            for (int i = 0; i < mModelDishMenuList.size(); i++) {
                if (mModelDishMenuList.get(i) == headMenu) {
                    leftAdapter.setSelectedNum(i);
                    break;
                }
            }
        }
    }

    @Override
    public void onLeftItemSelected(int position, ModelDishMenu menu) {
        int sum = 0;
        for (int i = 0; i < position; i++) {
            sum += mModelDishMenuList.get(i).getModelDishList().size() + 1;
        }
        LinearLayoutManager layoutManager = (LinearLayoutManager) mRightMenu.getLayoutManager();
        layoutManager.scrollToPositionWithOffset(sum, 0);
        leftClickType = true;
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
        listener.actionEvent("A","B");




    }

    @Override
    public void remove(View view, int position) {

        ActionListener listener = (ActionListener) getActivity();
        listener.actionEvent("A","B");

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




    public interface ActionListener
    {
        void actionEvent(String username, String password);
    }






}





